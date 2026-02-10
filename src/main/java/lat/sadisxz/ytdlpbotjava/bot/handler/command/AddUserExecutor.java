package lat.sadisxz.ytdlpbotjava.bot.handler.command;

import lat.sadisxz.ytdlpbotjava.bot.dash.AddUserBoard;
import lat.sadisxz.ytdlpbotjava.bot.dto.UserDTO;
import lat.sadisxz.ytdlpbotjava.bot.service.UserService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class AddUserExecutor {
    private final UserService userService;
    private final AddUserBoard addUserBoard;

    public AddUserExecutor(UserService userService, AddUserBoard addUserBoard) {
        this.userService = userService;
        this.addUserBoard = addUserBoard;
    }

    public SendMessage addUser(UserDTO user){
        userService.addUserToWhitelist(user);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(addUserBoard.addUser(Long.parseLong(user.message()[1])));
        sendMessage.setChatId(user.id());
        sendMessage.enableMarkdown(true);
        return sendMessage;
    }
}
