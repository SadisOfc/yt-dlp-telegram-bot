package lat.sadisxz.ytdlpbotjava.bot.handler.commands;

import lat.sadisxz.ytdlpbotjava.bot.dash.AdminBoard;
import lat.sadisxz.ytdlpbotjava.bot.model.UserStatus;
import lat.sadisxz.ytdlpbotjava.repository.UserRegistry;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class AddUserExecutor {
    private final UserRegistry userRegistry;
    private final AdminBoard addBoard;

    public AddUserExecutor(UserRegistry userRegistry, AdminBoard addBoard) {
        this.userRegistry = userRegistry;
        this.addBoard = addBoard;
    }

    public SendMessage addUser(Long chatId, Long addUserId, UserStatus userStatus){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(userRegistry.addUser(addUserId,userStatus) ? addBoard.addBoard(addUserId) : addBoard.addFailedBoard(addUserId));
        sendMessage.setChatId(chatId);
        sendMessage.enableMarkdown(true);
        return sendMessage;
    }
}
