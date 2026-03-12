package lat.sadisxz.ytdlpbotjava.bot.handler.command;

import lat.sadisxz.ytdlpbotjava.bot.dash.CommandsBoard;
import lat.sadisxz.ytdlpbotjava.bot.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class CommandListExecutor implements CommandExecutor{
    private final Logger log = LoggerFactory.getLogger(CommandListExecutor.class);
    private final CommandsBoard commandsBoard;

    public CommandListExecutor(CommandsBoard commandsBoard) {
        this.commandsBoard = commandsBoard;
    }

    @Override
    public SendMessage execute(UserDTO userDTO){
        log.info("Processing command list. UserId:{}",userDTO.id());
        String txt = commandsBoard.sendCommands();
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(userDTO.id());
        sendMessage.setText(txt);
        return sendMessage;
    }
}
