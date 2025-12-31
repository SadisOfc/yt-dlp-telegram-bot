package lat.sadisxz.ytdlpbotjava.worker.command;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FormatOptionsCommand implements YtDlpCommand{
    @Override
    public List<String> processorExecutor(Long chatId, String[] content) {
        List<String> commandList = new ArrayList<>();
        commandList.add("yt-dlp");
        commandList.add("-F");
        commandList.add(content[1]);
        return commandList;
    }
}
