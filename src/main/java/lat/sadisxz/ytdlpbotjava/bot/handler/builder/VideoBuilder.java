package lat.sadisxz.ytdlpbotjava.bot.handler.builder;

import lat.sadisxz.ytdlpbotjava.bot.handler.command.VideoExecutor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;

import java.nio.file.Path;


@Component
public class VideoBuilder {
    private final VideoExecutor videoExecutor;

    public VideoBuilder(VideoExecutor videoExecutor) {
        this.videoExecutor = videoExecutor;
    }

    public SendVideo sendVideo(Path path, Long chatId, String[] message){
        SendVideo sendVideo= new SendVideo();
        sendVideo.setChatId(chatId);
        sendVideo.setVideo(videoExecutor.sendVideoToUser(path,chatId, message));
        return sendVideo;
    }
}
