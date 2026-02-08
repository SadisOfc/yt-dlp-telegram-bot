package lat.sadisxz.ytdlpbotjava.bot.handler.commands;

import lat.sadisxz.ytdlpbotjava.bot.dash.UserListBoard;
import lat.sadisxz.ytdlpbotjava.bot.model.UserDTO;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class UserListExecutor {
    private final UserListBoard listBoard;

    public UserListExecutor(UserListBoard listBoard) {
        this.listBoard = listBoard;
    }

    public SendMessage sendUserList(UserDTO user){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(listBoard.userList());
        sendMessage.setChatId(user.id());
        sendMessage.enableMarkdown(true);
        return sendMessage;
    }
}
