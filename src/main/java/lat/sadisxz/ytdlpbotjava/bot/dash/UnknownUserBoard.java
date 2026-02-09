package lat.sadisxz.ytdlpbotjava.bot.dash;

import lat.sadisxz.ytdlpbotjava.config.TelegramBotProperties;
import org.springframework.stereotype.Component;

@Component
public class UnknownUserBoard {
    private String owner_username;

    public UnknownUserBoard(TelegramBotProperties telegramBotProperties) {
        this.owner_username = telegramBotProperties.owner_username();
    }

    public String unknownUser(){
        return String.format("You're not on the whitelist. Contact an administrator\n" +
                "@%s", owner_username);
    }
}
