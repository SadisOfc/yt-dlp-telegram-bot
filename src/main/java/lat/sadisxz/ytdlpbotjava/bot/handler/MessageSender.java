package lat.sadisxz.ytdlpbotjava.bot.handler;

import lat.sadisxz.ytdlpbotjava.bot.handler.builder.AudioBuilder;
import lat.sadisxz.ytdlpbotjava.bot.handler.builder.DocumentBuilder;
import lat.sadisxz.ytdlpbotjava.bot.handler.builder.VideoBuilder;
import lat.sadisxz.ytdlpbotjava.bot.handler.command.*;
import lat.sadisxz.ytdlpbotjava.bot.model.enums.MediaType;
import lat.sadisxz.ytdlpbotjava.bot.dto.UserDTO;
import lat.sadisxz.ytdlpbotjava.config.DownloaderProperties;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class MessageSender {
    private final VideoBuilder videoSender;
    private final AudioBuilder audioBuilder;
    private final FormatOptionsExecutor formatOptionsExecutor;
    private final StartExecutor startExecutor;
    private final InformationExecutor informationExecutor;
    private final UserListExecutor listExecutor;
    private final AddUserExecutor addUserExecutor;
    private final RemoveUserExecutor removeUserExecutor;
    private final DocumentBuilder documentBuilder;
    private final CommandListExecutor commandListExecutor;
    private final InvalidCommandExecutor invalidCommandExecutor;
    private final WhitelistExecutor whitelistExecutor;

    private final String PATH;

    public MessageSender(VideoBuilder videoSender, AudioBuilder audioBuilder, FormatOptionsExecutor formatOptionsExecutor, StartExecutor startExecutor, InformationExecutor informationExecutor, UserListExecutor listExecutor, AddUserExecutor addUserExecutor, RemoveUserExecutor removeUserExecutor, DocumentBuilder documentBuilder, DownloaderProperties downloaderProperties, CommandListExecutor commandListExecutor, InvalidCommandExecutor invalidCommandExecutor, WhitelistExecutor whitelistExecutor) {
        this.videoSender = videoSender;
        this.audioBuilder = audioBuilder;
        this.formatOptionsExecutor = formatOptionsExecutor;
        this.startExecutor = startExecutor;
        this.informationExecutor = informationExecutor;
        this.listExecutor = listExecutor;
        this.addUserExecutor = addUserExecutor;
        this.removeUserExecutor = removeUserExecutor;
        this.documentBuilder = documentBuilder;
        this.PATH = downloaderProperties.base_path();
        this.commandListExecutor = commandListExecutor;
        this.invalidCommandExecutor = invalidCommandExecutor;
        this.whitelistExecutor = whitelistExecutor;
    }

    public Object coordinateResponse(UserDTO user){
        String[] message = user.message();
        Path pathDownloads;
        Long chatId = user.id();

        switch (message[0].toLowerCase()){
            case "/start"->{
                return startExecutor.sendStart(user);
            }
            case "/info"->{
                return informationExecutor.sendStart(user);
            }
            case "/vid"->{
                pathDownloads = Paths.get(PATH+chatId.toString()+"/"+ MediaType.MP4.toString().toLowerCase()+"/");
                return videoSender.sendVideo(pathDownloads,chatId, message);
            }

            case "/aud"->{
                pathDownloads = Paths.get(PATH+chatId.toString()+"/"+ MediaType.MP3.toString().toLowerCase()+"/");
                return audioBuilder.sendAudio(pathDownloads,chatId, message);
            }

            case "/format"->{
                return formatOptionsExecutor.sendFormatsToUser(chatId,message);
            }

            case "/format_d"->{
                pathDownloads = Paths.get(PATH+chatId.toString()+"/"+ MediaType.FORMAT.toString().toLowerCase()+"/");
                return documentBuilder.sendDocument(pathDownloads, chatId, message);
            }
            case "/user_list"->{
                return listExecutor.sendUserList(user);
            }
            case "/add_user"->{
                return addUserExecutor.addUser(user);
            }
            case "/remove_user"->{
                return removeUserExecutor.removeUser(user);
            }
            case "/commands"->{
                return commandListExecutor.execute(chatId);
            }
            case "/whitelist_switch"->{
                return whitelistExecutor.execute(user);
            }
            default -> {
                return invalidCommandExecutor.execute(chatId);
            }
        }
    }
}
