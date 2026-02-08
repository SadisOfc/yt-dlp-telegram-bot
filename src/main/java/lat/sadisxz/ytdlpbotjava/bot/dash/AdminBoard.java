package lat.sadisxz.ytdlpbotjava.bot.dash;

import org.springframework.stereotype.Component;

@Component
public class AdminBoard {
    public String addBoard(Long id){
        return String.format("""
                ***ᴜꜱᴇʀ ᴀᴅᴅᴇᴅ:*** `%s`
                """, id);
    }

    public String addFailedBoard(Long id){
        return String.format("""
                ***𝚄ꜱᴇʀ ᴄᴏᴜʟᴅ ɴᴏᴛ ʙᴇ ᴀᴅᴅᴇᴅ:*** `%s`
                """, id);
    }
}
