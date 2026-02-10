package lat.sadisxz.ytdlpbotjava.bot;

import lat.sadisxz.ytdlpbotjava.bot.dto.UserRequest;
import lat.sadisxz.ytdlpbotjava.bot.handler.MessageSender;
import lat.sadisxz.ytdlpbotjava.bot.handler.file.FileCleaner;
import lat.sadisxz.ytdlpbotjava.bot.model.enums.MediaType;
import lat.sadisxz.ytdlpbotjava.bot.dto.UserDTO;
import lat.sadisxz.ytdlpbotjava.bot.model.enums.UserRole;
import lat.sadisxz.ytdlpbotjava.bot.service.UserService;
import lat.sadisxz.ytdlpbotjava.config.DownloaderProperties;
import lat.sadisxz.ytdlpbotjava.config.TelegramBotProperties;
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

    private final MessageSender messageSender;
    private final FileCleaner fileCleaner;
    private final UserService userService;

    private final String BOTUSERNAME;
    private final String BOTTOKEN;
    private final String PATH;

    public TelegramBot(TelegramBotProperties telegramBotProperties, MessageSender messageSender, DownloaderProperties downloaderProperties, FileCleaner fileCleaner, UserService userService) {
        this.BOTUSERNAME = telegramBotProperties.username();
        this.BOTTOKEN = telegramBotProperties.token();
        this.messageSender = messageSender;
        this.PATH = downloaderProperties.base_path();
        this.fileCleaner = fileCleaner;
        this.userService = userService;
    }

    @Override
    public void onUpdateReceived(@NonNull Update update){
        if(update.hasMessage() && update.getMessage().hasText()){
            Long chatId = update.getMessage().getChatId();
            String name = update.getMessage().getChat().getFirstName();
            String username = update.getMessage().getChat().getUserName();
            String[] message = update.getMessage().getText().split(" ");
            UserRole userRole = userService.getOrCreateUser(new UserRequest(chatId, name, username)).getRole();

            UserDTO user= new UserDTO(name, username, chatId, userRole, message);
            System.out.println("User: " + username + " - Id: " + chatId);
            userService.validateUserAccess(user);

            executeMethod(messageSender.coordinateResponse(user));
            System.out.println("Se envió el mensaje");
            Path pathDownload = identificatorMediaType(user);
            if(pathDownload!=null){
                System.out.println(fileCleaner.delete(pathDownload));
            }
        }
    }

    public Path identificatorMediaType(UserDTO user){
        switch (user.message()[0]){
            case "/vid"->{
                return Paths.get(PATH+user.id().toString()+"/"+ MediaType.MP4.toString().toLowerCase()+"/");
            }
            case "/aud"->{
                return Paths.get(PATH+user.id().toString()+"/"+ MediaType.MP3.toString().toLowerCase()+"/");
            }
            case "/format_d"->{
                return Paths.get(PATH+user.id().toString()+"/"+ MediaType.FORMAT.toString().toLowerCase()+"/");
            }
            default ->{return null;}
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
