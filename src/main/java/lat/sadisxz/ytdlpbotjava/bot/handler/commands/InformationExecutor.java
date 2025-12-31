package lat.sadisxz.ytdlpbotjava.bot.handler.commands;

import lat.sadisxz.ytdlpbotjava.bot.dash.InformationBoard;
import lat.sadisxz.ytdlpbotjava.bot.model.UserDTO;
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
        sendMessage.enableMarkdown(true);
        sendMessage.setParseMode("HTML");
        sendMessage.setReplyMarkup(informationBoard.informationMarkup());
        return sendMessage;
    }
}
