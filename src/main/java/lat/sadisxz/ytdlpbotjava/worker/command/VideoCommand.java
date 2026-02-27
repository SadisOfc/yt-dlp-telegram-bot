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
        commandList.add("bv*[vcodec^=avc1]+ba[acodec^=mp4a]/b[ext=mp4][vcodec^=avc1]");
        commandList.add("--merge-output-format");
        commandList.add("mp4");
        commandList.add("--recode-video");
        commandList.add("mp4");
        commandList.add("--postprocessor-args");
        commandList.add("ffmpeg:-c:v libx264 -profile:v high -level 4.0 -pix_fmt yuv420p -preset medium -crf 23 -movflags +faststart -c:a aac -b:a 128k -r 30");
        commandList.add("-o");
        commandList.add(downloaderProperties.base_path()+chatId+"/mp4/%(title)s.%(ext)s");
        commandList.add(content[1]);
        return commandList;
    }
}
