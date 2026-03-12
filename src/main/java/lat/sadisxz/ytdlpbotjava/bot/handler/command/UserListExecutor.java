package lat.sadisxz.ytdlpbotjava.bot.handler.command;

import lat.sadisxz.ytdlpbotjava.bot.dash.UserListBoard;
import lat.sadisxz.ytdlpbotjava.bot.dto.UserDTO;
import lat.sadisxz.ytdlpbotjava.bot.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class UserListExecutor implements CommandExecutor{
    private final Logger log = LoggerFactory.getLogger(UserListExecutor.class);
    private final UserListBoard listBoard;
    private final UserService userService;

    public UserListExecutor(UserListBoard listBoard, UserService userService) {
        this.listBoard = listBoard;
        this.userService = userService;
    }

    @Override
    public SendMessage execute(UserDTO user){
        log.info("Processing user list command. UserId:{}",user.id());
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(listBoard.userList(userService.userList(user)));
        sendMessage.setChatId(user.id());
        sendMessage.enableMarkdown(true);
        return sendMessage;
    }
}
