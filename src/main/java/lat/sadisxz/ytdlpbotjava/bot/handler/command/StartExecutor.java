package lat.sadisxz.ytdlpbotjava.bot.handler.command;

import lat.sadisxz.ytdlpbotjava.bot.dash.StartBoard;
import lat.sadisxz.ytdlpbotjava.bot.dto.UserDTO;
import lat.sadisxz.ytdlpbotjava.bot.dto.UserRequest;
import lat.sadisxz.ytdlpbotjava.bot.service.UserService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class StartExecutor {
    private final StartBoard startBoard;
    private final UserService userService;

    public StartExecutor(StartBoard startBoard, UserService userService) {
        this.startBoard = startBoard;
        this.userService = userService;
    }

    public SendMessage sendStart(UserDTO user){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(startBoard.start(userService.getOrCreateUser(new UserRequest(user.id(), user.name(), user.username()))));
        sendMessage.setChatId(user.id());
        sendMessage.enableMarkdown(true);
        return sendMessage;
    }
}
