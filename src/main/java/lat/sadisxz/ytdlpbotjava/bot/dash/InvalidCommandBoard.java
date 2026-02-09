package lat.sadisxz.ytdlpbotjava.bot.dash;

import org.springframework.stereotype.Component;

@Component
public class InvalidCommandBoard {
    public String command(){
        return String.format("""
                Invalid command, please check the command list:
                `/commands`""");
    }
}
