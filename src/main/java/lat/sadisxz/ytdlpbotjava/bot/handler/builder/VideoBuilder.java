package lat.sadisxz.ytdlpbotjava.bot.handler.builder;

import lat.sadisxz.ytdlpbotjava.bot.handler.command.VideoExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;

import java.nio.file.Path;


@Component
public class VideoBuilder {
    private final Logger log = LoggerFactory.getLogger(VideoBuilder.class);
    private final VideoExecutor videoExecutor;

    public VideoBuilder(VideoExecutor videoExecutor) {
        this.videoExecutor = videoExecutor;
    }

    public SendVideo sendVideo(Path path, Long chatId, String[] message){
        log.info("Processing video command. UserId:{}",chatId.toString());
        SendVideo sendVideo= new SendVideo();
        sendVideo.setChatId(chatId);
        sendVideo.setVideo(videoExecutor.sendVideoToUser(path,chatId, message));
        return sendVideo;
    }
}
