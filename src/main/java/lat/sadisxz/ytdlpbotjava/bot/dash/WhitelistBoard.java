package lat.sadisxz.ytdlpbotjava.bot.dash;

import org.springframework.stereotype.Component;

@Component
public class WhitelistBoard {
    public String switchWhitelist(boolean newStatus){
        return String.format("""
                The whitelist status has been updated. The current status is:
                - `%s`
                """,newStatus);
    }
}
