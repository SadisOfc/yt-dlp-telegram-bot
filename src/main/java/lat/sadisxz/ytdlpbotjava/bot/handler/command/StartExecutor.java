package lat.sadisxz.ytdlpbotjava.bot.handler.command;

import lat.sadisxz.ytdlpbotjava.bot.dash.StartBoard;
import lat.sadisxz.ytdlpbotjava.bot.dto.UserDTO;
import lat.sadisxz.ytdlpbotjava.bot.dto.UserRequest;
import lat.sadisxz.ytdlpbotjava.bot.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class StartExecutor implements CommandExecutor{
    private final Logger log = LoggerFactory.getLogger(StartExecutor.class);
    private final StartBoard startBoard;
    private final UserService userService;

    public StartExecutor(StartBoard startBoard, UserService userService) {
        this.startBoard = startBoard;
        this.userService = userService;
    }

    @Override
    public SendMessage execute(UserDTO user){
        log.info("Processing start command. UserId:{}",user.id());
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(startBoard.start(userService.getOrCreateUser(new UserRequest(user.id(), user.name(), user.username()))));
        sendMessage.setChatId(user.id());
        sendMessage.enableMarkdown(true);
        return sendMessage;
    }
}
