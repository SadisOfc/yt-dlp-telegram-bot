package lat.sadisxz.ytdlpbotjava.bot.dash;

import lat.sadisxz.ytdlpbotjava.bot.model.enums.UserRole;
import org.springframework.stereotype.Component;

@Component
public class RemoveUserBoard {
    public String removeUser(Long id){
        return String.format("""
                Removed user
                ɪᴅ: `%s` - ꜱᴛᴀᴛᴜꜱ: `%s`
                """, id, UserRole.GUEST);
    }
}
