package lat.sadisxz.ytdlpbotjava.worker.command;

import lat.sadisxz.ytdlpbotjava.config.DownloaderProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VideoCommand implements YtDlpCommand{
    private final DownloaderProperties downloaderProperties;

    public VideoCommand(DownloaderProperties downloaderProperties) {
        this.downloaderProperties = downloaderProperties;
    }

    @Override
    public List<String> processorExecutor(Long chatId, String[] content) {
        List<String> commandList = new ArrayList<>();
        commandList.add("yt-dlp");
        commandList.add("-f");
        commandList.add("bv*+ba/b");
        commandList.add("--merge-output-format");
        commandList.add("mp4");
        commandList.add("-o");
        commandList.add(downloaderProperties.base_path()+chatId+"/mp4/%(title)s.%(ext)s");
        commandList.add(content[1]);
        return commandList;
    }
}
