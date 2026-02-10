package lat.sadisxz.ytdlpbotjava.bot.handler.command;

import lat.sadisxz.ytdlpbotjava.bot.dash.UnknownUserBoard;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class UnknownUserExecutor {
    private final UnknownUserBoard unknownUserBoard;

    public UnknownUserExecutor(UnknownUserBoard unknownUserBoard) {
        this.unknownUserBoard = unknownUserBoard;
    }

    public SendMessage execute(Long chatId){
        String txt = unknownUserBoard.unknownUser();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(txt);
        return sendMessage;
    }
}
