package lat.sadisxz.ytdlpbotjava.bot.handler.sender;

import lat.sadisxz.ytdlpbotjava.bot.handler.commands.FormatExecutor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;

import java.nio.file.Path;


@Component
public class DocumentBuilder {
    private final FormatExecutor formatExecutor;

    public DocumentBuilder(FormatExecutor formatExecutor) {
        this.formatExecutor = formatExecutor;
    }

    public SendDocument sendDocument(Path path, Long chatId, String[] message){
        SendDocument sendDocument= new SendDocument();
        sendDocument.setChatId(chatId);
        sendDocument.setDocument(formatExecutor.sendFormatToUser(path,chatId, message));
        return sendDocument;
    }
}
