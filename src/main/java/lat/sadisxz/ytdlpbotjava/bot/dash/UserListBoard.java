package lat.sadisxz.ytdlpbotjava.bot.dash;

import lat.sadisxz.ytdlpbotjava.bot.model.UserStatus;
import lat.sadisxz.ytdlpbotjava.config.TelegramBotProperties;
import lat.sadisxz.ytdlpbotjava.repository.UserRegistry;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserListBoard {
    private final UserRegistry userRegistry;
    private final TelegramBotProperties telegramBotProperties;

    public UserListBoard(UserRegistry userRegistry, TelegramBotProperties telegramBotProperties) {
        this.userRegistry = userRegistry;
        this.telegramBotProperties = telegramBotProperties;
    }

    public String userList(){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("""
                彡 ꜰᴀꜱᴛᴅʟʙᴏᴛ  - @%s 彡
                ———————————————————————————
                ᴜꜱᴇʀ ʟɪꜱᴛ
                """,telegramBotProperties.username()));
        for(Map.Entry<Long, UserStatus> entry : userRegistry.getAllUsers().entrySet()){
            sb.append(String.format("ɪᴅ: `%s` - ꜱᴛᴀᴛᴜꜱ: `%s`\n", entry.getKey(), entry.getValue()));
        }
        return sb.toString();
    }
}
