package lat.sadisxz.ytdlpbotjava.bot.handler.command;

import lat.sadisxz.ytdlpbotjava.bot.dash.CommandsBoard;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class CommandListExecutor {
    private final CommandsBoard commandsBoard;

    public CommandListExecutor(CommandsBoard commandsBoard) {
        this.commandsBoard = commandsBoard;
    }

    public SendMessage execute(Long chatId){
        String txt = commandsBoard.sendCommands();
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(txt);
        return sendMessage;
    }
}
