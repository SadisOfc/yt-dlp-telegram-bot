package lat.sadisxz.ytdlpbotjava.bot.handler.commands;

import lat.sadisxz.ytdlpbotjava.bot.dash.FormatOptionsBoard;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class FormatOptionsExecutor {
    private final FormatOptionsBoard format;

    public FormatOptionsExecutor(FormatOptionsBoard format) {
        this.format = format;
    }

    public SendMessage sendFormatsToUser(Long chatId, String[] message){

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(format.formatOptionsBoard(chatId, message));
        sendMessage.enableMarkdown(true);
        return sendMessage;
    }
}
