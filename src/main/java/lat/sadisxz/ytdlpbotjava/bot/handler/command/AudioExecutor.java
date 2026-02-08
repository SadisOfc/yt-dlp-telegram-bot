package lat.sadisxz.ytdlpbotjava.bot.handler.commands;

import lat.sadisxz.ytdlpbotjava.bot.handler.FileLocator;
import lat.sadisxz.ytdlpbotjava.worker.DownloaderService;
import lat.sadisxz.ytdlpbotjava.worker.command.AudioCommand;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.InputFile;

import java.nio.file.Path;

@Component
public class AudioExecutor {
    private final DownloaderService downloaderService;
    private final FileLocator fileLocator;
    private final AudioCommand audioCommand;

    public AudioExecutor(DownloaderService downloaderService, FileLocator fileLocator, AudioCommand audioCommand) {
        this.downloaderService = downloaderService;
        this.fileLocator = fileLocator;
        this.audioCommand = audioCommand;
    }

    public InputFile sendAudioToUser(Path path, Long chatId, String[] message){
        downloaderService.download(audioCommand.processorExecutor(chatId, message));
        return new InputFile(fileLocator.findFile(path, chatId));
    }
}
