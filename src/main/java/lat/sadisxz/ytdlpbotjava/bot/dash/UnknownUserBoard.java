package lat.sadisxz.ytdlpbotjava.bot.dash;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class UnknownUserBoard {
    public SendMessage unknownUser(Long id){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(id);
        sendMessage.setText("You're not on the whitelist. Contact an administrator\n" +
                "@Sadisxz");
        return sendMessage;
    }
}
