package lat.sadisxz.ytdlpbotjava.bot.handler.sender;

import lat.sadisxz.ytdlpbotjava.bot.handler.commands.VideoExecutor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;

import java.nio.file.Path;


@Component
public class VideoSender {
    private final VideoExecutor videoExecutor;

    public VideoSender(VideoExecutor videoExecutor) {
        this.videoExecutor = videoExecutor;
    }

    public SendVideo sendVideo(Path path, Long chatId, String[] message){
        SendVideo sendVideo= new SendVideo();
        sendVideo.setChatId(chatId);
        sendVideo.setVideo(videoExecutor.sendVideoToUser(path,chatId, message));
        return sendVideo;
    }
}
