package lat.sadisxz.ytdlpbotjava.bot.dash;

import lat.sadisxz.ytdlpbotjava.config.TelegramBotProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class UnknownUserBoard {
    private String owner_username;

    public UnknownUserBoard(TelegramBotProperties telegramBotProperties) {
        this.owner_username = telegramBotProperties.owner_username();
    }

    public SendMessage unknownUser(Long id){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(id);
        sendMessage.setText(String.format("You're not on the whitelist. Contact an administrator\n" +
                "@%s", owner_username));
        return sendMessage;
    }
}
