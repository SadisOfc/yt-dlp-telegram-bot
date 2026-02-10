package lat.sadisxz.ytdlpbotjava.bot.handler.command;

import lat.sadisxz.ytdlpbotjava.bot.dash.UserListBoard;
import lat.sadisxz.ytdlpbotjava.bot.dto.UserDTO;
import lat.sadisxz.ytdlpbotjava.bot.service.UserService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class UserListExecutor {
    private final UserListBoard listBoard;
    private final UserService userService;

    public UserListExecutor(UserListBoard listBoard, UserService userService) {
        this.listBoard = listBoard;
        this.userService = userService;
    }

    public SendMessage sendUserList(UserDTO user){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(listBoard.userList(userService.userList(user)));
        sendMessage.setChatId(user.id());
        sendMessage.enableMarkdown(true);
        return sendMessage;
    }
}
