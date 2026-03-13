package lat.sadisxz.ytdlpbotjava.bot.dash;

import lat.sadisxz.ytdlpbotjava.worker.FormatCollector;
import lat.sadisxz.ytdlpbotjava.worker.command.FormatOptionsCommand;
import org.springframework.stereotype.Component;

@Component
public class FormatOptionsBoard {
    private final FormatOptionsCommand formatOptionsCommand;
    private final FormatCollector formatCollector;

    public FormatOptionsBoard(FormatOptionsCommand formatOptionsCommand, FormatCollector formatCollector) {
        this.formatOptionsCommand = formatOptionsCommand;
        this.formatCollector = formatCollector;
    }

    public String formatOptionsBoard(Long chatId, String[] message){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("""
                ***ɪᴅ - ᴇxᴛ - ʀᴇꜱᴏʟᴜᴛɪᴏɴ - ꜰᴘꜱ***
                """);
        formatCollector.formatOptions(formatOptionsCommand.processorExecutor(chatId,message)).forEach(a->{
            stringBuilder.append("`").append(a.id()).append("`___ - ");
            stringBuilder.append(a.ext()).append(" - ");
            stringBuilder.append(a.resolution()).append(" - ");
            stringBuilder.append(a.fps()).append("___\n");
        });
        return stringBuilder.toString();
    }
}
