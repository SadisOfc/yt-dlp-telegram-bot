package lat.sadisxz.ytdlpbotjava.bot.handler.sender;

import lat.sadisxz.ytdlpbotjava.bot.handler.commands.AudioExecutor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;

import java.nio.file.Path;

@Component
public class AudioBuilder {

    private final AudioExecutor audioExecutor;

    public AudioBuilder(AudioExecutor audioExecutor) {
        this.audioExecutor = audioExecutor;
    }

    public SendAudio sendAudio(Path path, Long chatId, String[] message){
        SendAudio sendAudio = new SendAudio();
        sendAudio.setChatId(chatId);
        sendAudio.setAudio(audioExecutor.sendAudioToUser(path, chatId, message));
        return sendAudio;
    }
}
