package lat.sadisxz.ytdlpbotjava.bot.handler.command;

import lat.sadisxz.ytdlpbotjava.bot.dash.InvalidCommandBoard;
import lat.sadisxz.ytdlpbotjava.bot.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class InvalidCommandExecutor implements CommandExecutor{
    private final Logger log = LoggerFactory.getLogger(InvalidCommandExecutor.class);
    private final InvalidCommandBoard invalidCommandBoard;

    public InvalidCommandExecutor(InvalidCommandBoard invalidCommandBoard) {
        this.invalidCommandBoard = invalidCommandBoard;
    }

    @Override
    public SendMessage execute(UserDTO user){
        log.info("Processing invalid command. UserId:{}",user.id());
        String txt = invalidCommandBoard.command();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(user.id());
        sendMessage.enableMarkdown(true);
        sendMessage.setText(txt);
        return sendMessage;
    }
}
