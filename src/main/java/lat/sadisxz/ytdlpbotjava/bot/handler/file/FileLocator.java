package lat.sadisxz.ytdlpbotjava.bot.handler.file;

import lat.sadisxz.ytdlpbotjava.bot.exception.InexistentFileException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

@Component
public class FileLocator{
    Logger log = LoggerFactory.getLogger(FileLocator.class);
    public File findFile(Path path){
        try(Stream<Path> files = Files.list(path)){
            Path video = files
                    .filter(Files::isRegularFile)
                    .findFirst()
                    .orElseThrow(()-> new InexistentFileException("The file was not found"));
            return video.toFile();
        }catch(IOException e){
            log.error("Unexpected error searching for file", e);
        }
        return null;
    }
}

