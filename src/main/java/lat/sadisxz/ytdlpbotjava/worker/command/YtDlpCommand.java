package lat.sadisxz.ytdlpbotjava.worker.command;

import java.util.List;

public interface YtDlpCommand {
    List<String> processorExecutor(Long chatId,String[] content);
}
