package lat.sadisxz.ytdlpbotjava.bot.handler.cleaner;

import org.springframework.stereotype.Component;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

@Component
public class FileCleaner {
    public String delete(Path dir){
        if (!Files.exists(dir) || !Files.isDirectory(dir)) {
            return String.format("Nada que eliminar: %s", dir); // nada que borrar
        }

        try (Stream<Path> files = Files.list(dir)) {
            files.forEach(path -> {
                try {
                    Files.deleteIfExists(path); // borra archivo o subarchivo
                } catch (IOException e) {
                    e.printStackTrace(); // o loguea error
                }
            });
            return String.format("Se eliminó un archivo del directorio: %s", dir);
        } catch (IOException e) {
            e.printStackTrace();
            return String.format("No se pudo eliminar el archivo: %s", dir);
        }
    }
}
