package lat.sadisxz.ytdlpbotjava.bot.handler.commands;

import lat.sadisxz.ytdlpbotjava.bot.dash.StartBoard;
import lat.sadisxz.ytdlpbotjava.bot.model.UserDTO;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class StartExecutor {
    private final StartBoard startBoard;

    public StartExecutor(StartBoard startBoard) {
        this.startBoard = startBoard;
    }

    public SendMessage sendStart(UserDTO user){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(startBoard.startBotEnglish(user));
        sendMessage.setChatId(user.id());
        sendMessage.enableMarkdown(true);
        return sendMessage;
    }
}
