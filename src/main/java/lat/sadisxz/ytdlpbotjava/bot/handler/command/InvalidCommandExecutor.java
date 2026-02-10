package lat.sadisxz.ytdlpbotjava.bot.handler.command;

import lat.sadisxz.ytdlpbotjava.bot.dash.InvalidCommandBoard;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class InvalidCommandExecutor {
    private final InvalidCommandBoard invalidCommandBoard;

    public InvalidCommandExecutor(InvalidCommandBoard invalidCommandBoard) {
        this.invalidCommandBoard = invalidCommandBoard;
    }

    public SendMessage execute(Long chatId){
        String txt = invalidCommandBoard.command();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableMarkdown(true);
        sendMessage.setText(txt);
        return sendMessage;
    }
}
