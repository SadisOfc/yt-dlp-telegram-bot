package lat.sadisxz.ytdlpbotjava.bot.dash;

import lat.sadisxz.ytdlpbotjava.bot.dto.UserResponse;
import lat.sadisxz.ytdlpbotjava.config.TelegramBotProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserListBoard {
    private final TelegramBotProperties telegramBotProperties;

    public UserListBoard(TelegramBotProperties telegramBotProperties) {
        this.telegramBotProperties = telegramBotProperties;
    }

    public String userList(List<UserResponse> userList){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("""
                ***彡 ꜰᴀꜱᴛᴅʟʙᴏᴛ  - @%s 彡***
                ———————————————————————————
                ***ᴜꜱᴇʀ ʟɪꜱᴛ***
                """,telegramBotProperties.username()));
        for(UserResponse u : userList){
            sb.append(String.format("ɪᴅ: `%s` - ᴜꜱᴇʀɴᴀᴍᴇ: %s - ꜱᴛᴀᴛᴜꜱ: `%s`\n", u.id(), u.username(), u.role()));
        }
        return sb.toString();
    }
}
