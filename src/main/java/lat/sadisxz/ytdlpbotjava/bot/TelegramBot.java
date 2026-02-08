package lat.sadisxz.ytdlpbotjava.bot;


import lat.sadisxz.ytdlpbotjava.bot.dash.CommandsBoard;
import lat.sadisxz.ytdlpbotjava.bot.dash.ErrorBoard;
import lat.sadisxz.ytdlpbotjava.bot.dash.UnknownUserBoard;
import lat.sadisxz.ytdlpbotjava.bot.handler.cleaner.FileCleaner;
import lat.sadisxz.ytdlpbotjava.bot.handler.command.*;
import lat.sadisxz.ytdlpbotjava.bot.handler.builder.AudioBuilder;
import lat.sadisxz.ytdlpbotjava.bot.handler.builder.DocumentBuilder;
import lat.sadisxz.ytdlpbotjava.bot.handler.builder.VideoBuilder;
import lat.sadisxz.ytdlpbotjava.bot.model.MediaType;
import lat.sadisxz.ytdlpbotjava.bot.model.UserDTO;
import lat.sadisxz.ytdlpbotjava.bot.model.UserStatus;
import lat.sadisxz.ytdlpbotjava.config.DownloaderProperties;
import lat.sadisxz.ytdlpbotjava.config.TelegramBotProperties;
import lat.sadisxz.ytdlpbotjava.repository.UserRegistry;
import lat.sadisxz.ytdlpbotjava.utils.UserSent;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final FileCleaner fileCleaner;
    private final VideoBuilder videoSender;
    private final AudioBuilder audioBuilder;
    private final UserSent userSent;
    private final FormatOptionsExecutor formatOptionsExecutor;
    private final StartExecutor startExecutor;
    private final InformationExecutor informationExecutor;
    private final UserListExecutor listExecutor;
    private final UserRegistry userRegistry;
    private final UnknownUserBoard unknownUserBoard;
    private final AddUserExecutor addUserExecutor;
    private final ErrorBoard errorBoard;
    private final RemoveUserExecutor removeUserExecutor;
    private final DocumentBuilder documentBuilder;
    private final CommandsBoard commandsBoard;


    private final String BOTUSERNAME;
    private final String BOTTOKEN;
    private final String PATH;

    public TelegramBot(FileCleaner fileCleaner, VideoBuilder videoSender, AudioBuilder audioBuilder, UserSent userSent, FormatOptionsExecutor formatOptionsExecutor, StartExecutor startExecutor, InformationExecutor informationExecutor, UserListExecutor listExecutor, UserRegistry userRegistry, UnknownUserBoard unknownUserBoard, AddUserExecutor addUserExecutor, ErrorBoard errorBoard, RemoveUserExecutor removeUserExecutor, DocumentBuilder documentBuilder, CommandsBoard commandsBoard, TelegramBotProperties telegramBotProperties, DownloaderProperties downloaderProperties) {
        this.fileCleaner = fileCleaner;
        this.videoSender = videoSender;
        this.audioBuilder = audioBuilder;
        this.userSent = userSent;
        this.formatOptionsExecutor = formatOptionsExecutor;
        this.startExecutor = startExecutor;
        this.informationExecutor = informationExecutor;
        this.listExecutor = listExecutor;
        this.userRegistry = userRegistry;
        this.unknownUserBoard = unknownUserBoard;
        this.addUserExecutor = addUserExecutor;
        this.errorBoard = errorBoard;
        this.removeUserExecutor = removeUserExecutor;
        this.documentBuilder = documentBuilder;
        this.commandsBoard = commandsBoard;
        this.BOTUSERNAME = telegramBotProperties.username();
        this.BOTTOKEN = telegramBotProperties.token();
        this.PATH = downloaderProperties.base_path();
    }

    @Override
    public void onUpdateReceived(@NonNull Update update){
        if(update.hasMessage() && update.getMessage().hasText()){
            Long chatId = update.getMessage().getChatId();
            String name = update.getMessage().getChat().getFirstName();
            String username = update.getMessage().getChat().getUserName();

            System.out.println("User: " + username + " - Id: " + chatId);
            if(userRegistry.findUserById(chatId) == null){
                executeMethod(unknownUserBoard.unknownUser(chatId));
                return;
            }
            String[] message = update.getMessage().getText().split(" ");
            UserDTO user= new UserDTO(name, username, chatId, userRegistry.findUserById(chatId), message);
            Path pathDownloads;

            switch (message[0].toLowerCase()){
                case "/start"->{
                    executeMethod(startExecutor.sendStart(user));
                }
                case "/info"->{
                    executeMethod(informationExecutor.sendStart(user));
                }
                case "/vid"->{
                    pathDownloads = Paths.get(PATH+chatId+"/"+ MediaType.MP4.toString().toLowerCase()+"/");
                    executeMethod(videoSender.sendVideo(pathDownloads,chatId, message));
                    System.out.println(userSent.messageSent(MediaType.MP4, username, chatId));
                    System.out.println(fileCleaner.delete(pathDownloads, MediaType.MP4));
                }

                case "/aud"->{
                    pathDownloads = Paths.get(PATH+chatId.toString()+"/"+ MediaType.MP3.toString().toLowerCase()+"/");
                    executeMethod(audioBuilder.sendAudio(pathDownloads,chatId, message));
                    System.out.println(userSent.messageSent(MediaType.MP3, username, chatId));
                    System.out.println(fileCleaner.delete(pathDownloads, MediaType.MP3));
                }

                case "/format"->{
                    executeMethod(formatOptionsExecutor.sendFormatsToUser(chatId,message));
                }

                case "/format_d"->{
                    pathDownloads = Paths.get(PATH+chatId+"/"+ MediaType.FORMAT.toString().toLowerCase()+"/");
                    executeMethod(documentBuilder.sendDocument(pathDownloads, chatId, message));
                    System.out.println(userSent.messageSent(MediaType.FORMAT, username, chatId));
                    System.out.println(fileCleaner.delete(pathDownloads, MediaType.FORMAT));
                }
                case "/list"->{
                    executeMethod(listExecutor.sendUserList(user));
                }
                case "/add_user"->{
                    if(userRegistry.findUserById(chatId) == UserStatus.USER){
                        executeMethod(errorBoard.invalidPermission(chatId));
                        break;
                    };
                    executeMethod(addUserExecutor.addUser(chatId, Long.parseLong(message[1]), UserStatus.USER));
                }
                case "/remove_user"->{
                    if(userRegistry.findUserById(chatId) != UserStatus.OWNER){
                        executeMethod(errorBoard.invalidPermission(chatId));
                        break;
                    };
                    executeMethod(removeUserExecutor.removeUser(chatId, Long.parseLong(message[1])));
                }
                case "/commands"->{
                    executeMethod(commandsBoard.sendCommands(chatId));
                }
                default -> {
                }
            }

        }
    }

    public Message executeMethod(Object o){
        try{
            if(o instanceof SendVideo)return execute((SendVideo) o);
            if(o instanceof SendAudio) return execute((SendAudio) o);
            if(o instanceof SendDocument) return execute((SendDocument) o);
            if(o instanceof SendMessage) return execute((SendMessage) o);
        }catch(TelegramApiException e){
            System.out.println("Hubo un error. Motivo: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public String getBotUsername() {
        return BOTUSERNAME;
    }
    @Override
    public String getBotToken(){
        return BOTTOKEN;
    }
}
