package lat.sadisxz.ytdlpbotjava.bot.handler.command;

import lat.sadisxz.ytdlpbotjava.bot.dash.BotErrorBoard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class ErrorBotExecutor{
    private final BotErrorBoard botErrorBoard;

    public ErrorBotExecutor(BotErrorBoard botErrorBoard) {
        this.botErrorBoard = botErrorBoard;
    }

    public SendMessage execute(Long chatId, String message){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableMarkdown(true);
        sendMessage.setText(botErrorBoard.sendError(message));
        return sendMessage;
    }
}
