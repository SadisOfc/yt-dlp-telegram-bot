package lat.sadisxz.ytdlpbotjava.worker.command;

import lat.sadisxz.ytdlpbotjava.config.DownloaderProperties;
import lat.sadisxz.ytdlpbotjava.config.TelegramBotProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FormatDownloadCommand implements YtDlpCommand{
    private final DownloaderProperties downloaderProperties;

    public FormatDownloadCommand(DownloaderProperties downloaderProperties) {
        this.downloaderProperties = downloaderProperties;
    }

    @Override
    public List<String> processorExecutor(Long chatId, String[] content) {
        List<String> commands = new ArrayList<>();
        commands.add("yt-dlp");
        commands.add("-f");
        commands.add(content[1]);
        commands.add("-o");
        commands.add(downloaderProperties.base_path()+chatId+"/format/%(title)s.%(ext)s");
        commands.add(content[2]);
        return commands;
    }
}
