package lat.sadisxz.ytdlpbotjava.bot.handler.builder;

import lat.sadisxz.ytdlpbotjava.bot.handler.command.FormatExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;

import java.nio.file.Path;


@Component
public class DocumentBuilder {
    private final Logger log = LoggerFactory.getLogger(DocumentBuilder.class);
    private final FormatExecutor formatExecutor;

    public DocumentBuilder(FormatExecutor formatExecutor) {
        this.formatExecutor = formatExecutor;
    }

    public SendDocument sendDocument(Path path, Long chatId, String[] message){
        log.info("Processing document command. UserId:{}",chatId.toString());
        SendDocument sendDocument= new SendDocument();
        sendDocument.setChatId(chatId);
        sendDocument.setDocument(formatExecutor.sendFormatToUser(path,chatId, message));
        return sendDocument;
    }
}
