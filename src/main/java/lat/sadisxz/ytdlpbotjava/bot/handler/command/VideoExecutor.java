package lat.sadisxz.ytdlpbotjava.bot.handler.command;

import lat.sadisxz.ytdlpbotjava.bot.handler.file.FileLocator;
import lat.sadisxz.ytdlpbotjava.worker.DownloaderService;
import lat.sadisxz.ytdlpbotjava.worker.command.VideoCommand;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.InputFile;

import java.nio.file.Path;

@Component
public class VideoExecutor {
    private final DownloaderService downloaderService;
    private final VideoCommand videoCommand;
    private final FileLocator fileLocator;

    public VideoExecutor(DownloaderService downloaderService, VideoCommand videoCommand, FileLocator fileLocator) {
        this.downloaderService = downloaderService;
        this.videoCommand = videoCommand;
        this.fileLocator = fileLocator;
    }

    public InputFile sendVideoToUser(Path pathDownloads,Long chatId, String[] message){
        downloaderService.download(videoCommand.processorExecutor(chatId, message));
        return new InputFile(fileLocator.findFile(pathDownloads,chatId));
    }
}
