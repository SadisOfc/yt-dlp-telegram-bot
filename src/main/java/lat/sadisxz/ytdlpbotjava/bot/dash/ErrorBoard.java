package lat.sadisxz.ytdlpbotjava.bot.dash;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class ErrorBoard {
    public SendMessage invalidPermission(Long id){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(id);
        sendMessage.setText("You do not have permission to use this command");
        return sendMessage;
    }
}
