package lat.sadisxz.ytdlpbotjava.bot.handler.command;

import lat.sadisxz.ytdlpbotjava.bot.dash.InformationBoard;
import lat.sadisxz.ytdlpbotjava.bot.dto.UserDTO;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class InformationExecutor {
    private final InformationBoard informationBoard;

    public InformationExecutor(InformationBoard informationBoard) {
        this.informationBoard = informationBoard;
    }

    public SendMessage sendStart(UserDTO user){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(informationBoard.informationEnglish());
        sendMessage.setChatId(user.id());
        sendMessage.setParseMode("HTML");
        sendMessage.setReplyMarkup(informationBoard.informationMarkup());
        return sendMessage;
    }
}
