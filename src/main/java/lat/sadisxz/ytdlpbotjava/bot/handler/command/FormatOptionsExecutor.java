package lat.sadisxz.ytdlpbotjava.bot.handler.command;

import lat.sadisxz.ytdlpbotjava.bot.dash.FormatOptionsBoard;
import lat.sadisxz.ytdlpbotjava.bot.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class FormatOptionsExecutor implements CommandExecutor{
    private final Logger log = LoggerFactory.getLogger(FormatOptionsExecutor.class);
    private final FormatOptionsBoard format;

    public FormatOptionsExecutor(FormatOptionsBoard format) {
        this.format = format;
    }

    @Override
    public SendMessage execute(UserDTO userDTO){
        log.info("Processing format options command. UserId:{}",userDTO.id());
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(userDTO.id());
        sendMessage.setText(format.formatOptionsBoard(userDTO.id(), userDTO.message()));
        sendMessage.enableMarkdown(true);
        return sendMessage;
    }
}
