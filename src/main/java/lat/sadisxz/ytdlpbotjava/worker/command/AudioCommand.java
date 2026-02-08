package lat.sadisxz.ytdlpbotjava.worker.command;

import lat.sadisxz.ytdlpbotjava.config.DownloaderProperties;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Component
public class AudioCommand implements YtDlpCommand{
    private final DownloaderProperties downloaderProperties;

    public AudioCommand(DownloaderProperties downloaderProperties) {
        this.downloaderProperties = downloaderProperties;
    }

    @Override
    public List<String> processorExecutor(Long chatId, String[] content) {
        List<String> commandList = new ArrayList<>();
        commandList.add("yt-dlp");
        commandList.add("-x");
        commandList.add("--audio-format");
        commandList.add("mp3");
        commandList.add("--audio-quality");
        commandList.add("best");
        commandList.add("-o");
        commandList.add(downloaderProperties.base_path()+chatId+"/mp3/%(title)s.%(ext)s");
        commandList.add(content[1]);
        return commandList;
    }
}
