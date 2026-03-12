package lat.sadisxz.ytdlpbotjava.bot.handler.command;

import lat.sadisxz.ytdlpbotjava.bot.dash.InformationBoard;
import lat.sadisxz.ytdlpbotjava.bot.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class InformationExecutor implements CommandExecutor{
    private final Logger log = LoggerFactory.getLogger(InformationExecutor.class);
    private final InformationBoard informationBoard;

    public InformationExecutor(InformationBoard informationBoard) {
        this.informationBoard = informationBoard;
    }

    @Override
    public SendMessage execute(UserDTO user){
        log.info("Processing information command. UserId:{}",user.id());
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(informationBoard.informationEnglish());
        sendMessage.setChatId(user.id());
        sendMessage.setParseMode("HTML");
        sendMessage.setReplyMarkup(informationBoard.informationMarkup());
        return sendMessage;
    }
}
