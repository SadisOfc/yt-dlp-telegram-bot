package lat.sadisxz.ytdlpbotjava.bot.dash;

import lat.sadisxz.ytdlpbotjava.bot.model.enums.UserRole;
import org.springframework.stereotype.Component;

@Component
public class AddUserBoard {
    public String addUser(Long id){
        return String.format("""
                Added user
                ɪᴅ: `%s` - ꜱᴛᴀᴛᴜꜱ: `%s`
                """, id, UserRole.USER);
    }
}
