package lat.sadisxz.ytdlpbotjava.bot.handler.command;

import lat.sadisxz.ytdlpbotjava.bot.dash.RemoveUserBoard;
import lat.sadisxz.ytdlpbotjava.bot.dto.UserDTO;
import lat.sadisxz.ytdlpbotjava.bot.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class RemoveUserExecutor implements CommandExecutor{
    private final Logger log = LoggerFactory.getLogger(RemoveUserExecutor.class);
    private final UserService userService;
    private final RemoveUserBoard removeUserBoard;

    public RemoveUserExecutor(UserService userService, RemoveUserBoard removeUserBoard) {
        this.userService = userService;
        this.removeUserBoard = removeUserBoard;
    }

    @Override
    public SendMessage execute(UserDTO user){
        log.info("Processing remove user command. UserId:{}",user.id());
        userService.removeUserToWhitelist(user);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(removeUserBoard.removeUser(Long.parseLong(user.message()[1])));
        sendMessage.setChatId(user.id());
        sendMessage.enableMarkdown(true);
        return sendMessage;
    }
}
