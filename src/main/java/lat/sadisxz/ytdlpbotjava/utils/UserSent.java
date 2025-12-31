package lat.sadisxz.ytdlpbotjava.utils;

import lat.sadisxz.ytdlpbotjava.bot.model.MediaType;
import org.springframework.stereotype.Component;

@Component
public class UserSent {
    public String messageSent(MediaType mediaType, String username, Long chatId){
        return String.format("Se envió el %s al usuario %s - %s",mediaType, username, chatId);
    }
}
