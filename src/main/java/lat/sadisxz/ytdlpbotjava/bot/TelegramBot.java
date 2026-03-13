package lat.sadisxz.ytdlpbotjava.bot;

import lat.sadisxz.ytdlpbotjava.bot.dto.UserRequest;
import lat.sadisxz.ytdlpbotjava.bot.exception.BotException;
import lat.sadisxz.ytdlpbotjava.bot.handler.MessageSender;
import lat.sadisxz.ytdlpbotjava.bot.handler.file.FileCleaner;
import lat.sadisxz.ytdlpbotjava.bot.model.enums.MediaType;
import lat.sadisxz.ytdlpbotjava.bot.dto.UserDTO;
import lat.sadisxz.ytdlpbotjava.bot.model.enums.UserRole;
import lat.sadisxz.ytdlpbotjava.bot.service.UserService;
import lat.sadisxz.ytdlpbotjava.config.DownloaderProperties;
import lat.sadisxz.ytdlpbotjava.config.TelegramBotProperties;
import org.jspecify.annotations.NonNull;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.slf4j.Logger;

@Component
public class TelegramBot extends TelegramLongPollingBot {
    Logger log = LoggerFactory.getLogger(TelegramBot.class);

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
        if(!update.hasMessage() || !update.getMessage().hasText()){
            return;
        }
            Long chatId = update.getMessage().getChatId();
            String name = update.getMessage().getChat().getFirstName();
            String username = (update.getMessage().getChat().getUserName()==null) ? "null" : "@"+update.getMessage().getChat().getUserName();
            String[] message = update.getMessage().getText().split("\\s+");
            log.info("Name={},Username={}, ID={}, Message: {}", name, username, chatId, update.getMessage().getText());
            UserDTO user;
            try{
                UserRole userRole = userService.getOrCreateUser(new UserRequest(chatId, name, username)).getRole();
                user = new UserDTO(name, username, chatId, userRole, message);
                userService.validateUserAccess(user);
            }
            catch(BotException e){
                sendError(chatId, e.getMessage());
                log.warn("Access denied or invalid role",e);
                return;
            }

            try{
                log.info("Running messageSender method.");
                Object response = messageSender.coordinateResponse(user);
                if(response!=null){
                    sendResponse(response);
                }
                Path pathDownload;
                if(isDownloadCommand(message[0]) && (pathDownload= mediaPath(user))!=null){
                    log.info("Identifying the path of the downloaded file (cleaning)");
                    log.info(fileCleaner.delete(pathDownload));
                }
            }catch(TelegramApiException e){
                sendError(chatId,e.getMessage());
                log.error("TelegramApi Error. Details: {}", e.getMessage(), e);
            }catch(BotException e){
                sendError(chatId, e.getMessage());
                log.warn("BotException. Details: {}", e.getMessage(), e);
            }
    }

    public boolean isDownloadCommand(String command){
        return switch (command){
          case "/vid", "/aud", "/format_d"-> true;
          default -> false;
        };
    }

    public Path mediaPath(UserDTO user){
        return switch (user.message()[0]){
            case "/vid"->Paths.get(PATH+user.id().toString(), MediaType.MP4.toString().toLowerCase());
            case "/aud"->Paths.get(PATH+user.id().toString(), MediaType.MP3.toString().toLowerCase());
            case "/format_d"->Paths.get(PATH+user.id().toString(), MediaType.FORMAT.toString().toLowerCase());
            default ->null;
        };
    }

    public void sendResponse(Object response) throws TelegramApiException {
        switch (response){
            case SendMessage sm->{ execute(sm); logSend(Long.parseLong(sm.getChatId()), "TEXT");}
            case SendAudio sa->{ execute(sa); logSend(Long.parseLong(sa.getChatId()), "AUDIO");}
            case SendVideo sv->{ execute(sv); logSend(Long.parseLong(sv.getChatId()), "VIDEO");}
            case SendDocument sd->{ execute(sd); logSend(Long.parseLong(sd.getChatId()), "DOCUMENT");}
            default -> {log.warn("Unknown response type: {}", response.getClass().getSimpleName());}
        }
    }

    public void logSend(Long chatId, String messageType){
        log.info("The message was sent. UserID:{}, MessageType: {}", chatId, messageType);
    }

    public void sendError(Long chatId,String message){
        try{
            execute(messageSender.coordinateError(chatId, message));
        }catch(TelegramApiException e){
            log.error("Unexpected error sending the message", e);
        }
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
