package lat.sadisxz.ytdlpbotjava.bot.dash;

import org.springframework.stereotype.Component;

@Component
public class BotErrorBoard {
    public String sendError(String error){
        return String.format("""
                An error ocurred during the download process, reason:
                %s""", error);
    }
}
