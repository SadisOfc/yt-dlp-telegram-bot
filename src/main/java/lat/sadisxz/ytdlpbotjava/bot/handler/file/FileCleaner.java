package lat.sadisxz.ytdlpbotjava.bot.handler.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

@Component
public class FileCleaner {
    private final Logger log = LoggerFactory.getLogger(FileCleaner.class);

    public String delete(Path dir){
        if (!Files.exists(dir) || !Files.isDirectory(dir)) {
            return String.format("Nothing for delete: %s", dir); // nada que borrar
        }
        try (Stream<Path> files = Files.list(dir)) {
            files.forEach(path -> {
                try {
                    Files.deleteIfExists(path); // borra archivo o subarchivo
                } catch (IOException e) {
                    log.error("Unexpected error when deleting a file. Path: {}", path.toString(), e);
                }
            });
            return String.format("A file was deleted from directory: %s", dir);
        } catch (IOException e) {
            log.error("Unexpected error when deleting Path", e); //wombocombo
            return String.format("The file could not be deleted: %s", dir);
        }
    }
}
