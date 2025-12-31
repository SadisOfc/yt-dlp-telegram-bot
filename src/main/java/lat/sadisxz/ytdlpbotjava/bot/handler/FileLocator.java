package lat.sadisxz.ytdlpbotjava.bot.handler;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

@Component
public class FileLocator {
    public File findFile(Path path, Long chatId) {
        try(Stream<Path> files = Files.list(path)){
            Path video = files
                    .filter(Files::isRegularFile)
                    .findFirst()
                    .orElseThrow(()-> new RuntimeException("No se descargó nada"));
            return video.toFile();
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }
}

