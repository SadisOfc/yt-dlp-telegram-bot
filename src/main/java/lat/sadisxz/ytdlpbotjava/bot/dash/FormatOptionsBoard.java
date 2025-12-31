package lat.sadisxz.ytdlpbotjava.bot.dash;

import lat.sadisxz.ytdlpbotjava.worker.FormatCollector;
import lat.sadisxz.ytdlpbotjava.worker.command.FormatOptionsCommand;
import org.springframework.stereotype.Component;

@Component
public class FormatOptionsBoard {
    private final FormatOptionsCommand formatOptionsCommand;
    private FormatCollector formatCollector;

    public FormatOptionsBoard(FormatOptionsCommand formatOptionsCommand, FormatCollector formatCollector) {
        this.formatOptionsCommand = formatOptionsCommand;
        this.formatCollector = formatCollector;
    }

    public String formatOptionsBoard(Long chatId, String[] message){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("""
                ɪᴅ - ᴇxᴛ - ʀᴇꜱᴏʟᴜᴛɪᴏɴ - ꜰᴘꜱ
                """);
        formatCollector.formatOptiones(formatOptionsCommand.processorExecutor(chatId,message)).stream().forEach(a->{

            stringBuilder.append("`"+a.id()+ "`# - ");
            stringBuilder.append(a.ext()+ " - ");
            stringBuilder.append(a.resolution()+ " - ");
            stringBuilder.append(a.fps() + "\n");
        });
        return stringBuilder.toString();
    }
}
