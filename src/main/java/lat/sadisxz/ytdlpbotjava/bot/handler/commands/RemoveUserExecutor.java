package lat.sadisxz.ytdlpbotjava.bot.handler.commands;

import lat.sadisxz.ytdlpbotjava.bot.dash.AdminBoard;
import lat.sadisxz.ytdlpbotjava.repository.UserRegistry;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class RemoveUserExecutor {
    private final UserRegistry userRegistry;

    public RemoveUserExecutor(UserRegistry userRegistry) {
        this.userRegistry = userRegistry;
    }

    public SendMessage removeUser(Long chatId, Long removeUserId){
        SendMessage sendMessage = new SendMessage();
        userRegistry.removeUser(removeUserId);
        sendMessage.setText("""
                𝙳𝚎𝚕𝚎𝚝𝚎𝚍 𝚞𝚜𝚎𝚛""");
        sendMessage.setChatId(chatId);
        sendMessage.enableMarkdown(true);
        return sendMessage;
    }
}
