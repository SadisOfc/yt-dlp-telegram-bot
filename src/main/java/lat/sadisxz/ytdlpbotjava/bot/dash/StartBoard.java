package lat.sadisxz.ytdlpbotjava.bot.dash;

import lat.sadisxz.ytdlpbotjava.bot.model.entity.UserEntity;
import lat.sadisxz.ytdlpbotjava.config.TelegramBotProperties;
import org.springframework.stereotype.Component;

@Component
public class StartBoard {
    private final TelegramBotProperties telegramBotProperties;

    public StartBoard(TelegramBotProperties telegramBotProperties) {
        this.telegramBotProperties = telegramBotProperties;
    }

    public String start(UserEntity user){
        return String.format("""
                ***彡 ꜰᴀꜱᴛᴅʟʙᴏᴛ  - @%s 彡***
                ———————————————————————————
                ***ɪᴅ:*** `%s`
                ***ᴜꜱᴇʀ:*** @%s
                ***ꜱᴛᴀᴛᴜꜱ:*** `%s`""",telegramBotProperties.username(),user.getId(),user.getUsername(),user.getRole());
    }
}
