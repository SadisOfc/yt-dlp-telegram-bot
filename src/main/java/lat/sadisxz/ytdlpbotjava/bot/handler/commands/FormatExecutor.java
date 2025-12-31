package lat.sadisxz.ytdlpbotjava.bot.handler.commands;

import lat.sadisxz.ytdlpbotjava.bot.handler.FileLocator;
import lat.sadisxz.ytdlpbotjava.worker.DownloaderService;
import lat.sadisxz.ytdlpbotjava.worker.command.FormatDownloadCommand;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.InputFile;

import java.nio.file.Path;

@Component
public class FormatExecutor {
    private final DownloaderService downloaderService;
    private final FileLocator fileLocator;
    private final FormatDownloadCommand formatDownloadCommand;

    public FormatExecutor(DownloaderService downloaderService, FileLocator fileLocator, FormatDownloadCommand formatDownloadCommand) {
        this.downloaderService = downloaderService;
        this.fileLocator = fileLocator;
        this.formatDownloadCommand = formatDownloadCommand;
    }

    public InputFile sendFormatToUser(Path pathDownloads, Long chatId, String[] message){
        downloaderService.download(formatDownloadCommand.processorExecutor(chatId, message));
        return new InputFile(fileLocator.findFile(pathDownloads,chatId));
    }
}
