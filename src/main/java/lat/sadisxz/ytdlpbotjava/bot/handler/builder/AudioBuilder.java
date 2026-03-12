package lat.sadisxz.ytdlpbotjava.bot.handler.builder;

import lat.sadisxz.ytdlpbotjava.bot.handler.command.AudioExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;

import java.nio.file.Path;

@Component
public class AudioBuilder {
    private final Logger log = LoggerFactory.getLogger(AudioBuilder.class);
    private final AudioExecutor audioExecutor;

    public AudioBuilder(AudioExecutor audioExecutor) {
        this.audioExecutor = audioExecutor;
    }

    public SendAudio sendAudio(Path path, Long chatId, String[] message){
        log.info("Processing audio command. UserId:{}",chatId.toString());
        SendAudio sendAudio = new SendAudio();
        sendAudio.setChatId(chatId);
        sendAudio.setAudio(audioExecutor.sendAudioToUser(path, chatId, message));
        return sendAudio;
    }
}
