package lat.sadisxz.ytdlpbotjava.bot.handler.command;

import lat.sadisxz.ytdlpbotjava.bot.dash.WhitelistBoard;
import lat.sadisxz.ytdlpbotjava.bot.dto.UserDTO;
import lat.sadisxz.ytdlpbotjava.bot.service.UserService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class WhitelistExecutor {
    private final UserService userService;
    private final WhitelistBoard whitelistBoard;

    public WhitelistExecutor(UserService userService, WhitelistBoard whitelistBoard) {
        this.userService = userService;
        this.whitelistBoard = whitelistBoard;
    }

    public SendMessage execute(UserDTO user){
        boolean newStatus = userService.whitelistSwitch(user);
        String txt = whitelistBoard.switchWhitelist(newStatus);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(txt);
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(user.id());
        return sendMessage;
    }
}
