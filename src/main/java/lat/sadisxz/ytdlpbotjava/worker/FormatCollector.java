package lat.sadisxz.ytdlpbotjava.worker;

import lat.sadisxz.ytdlpbotjava.bot.dto.FormatDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class FormatCollector {
    private final Logger log = LoggerFactory.getLogger(FormatCollector.class);

    public List<FormatDTO> formatOptions(List<String> commands){
        ProcessBuilder pb = new ProcessBuilder(commands);
        List<String> availableOptions = new ArrayList<>();
        pb.redirectErrorStream(true);
        boolean sentinel = false;

        try{
            Process process = pb.start();
            BufferedReader bf = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while((line=bf.readLine())!=null){
                if(line.contains("EXT RESOLUTION FPS") || line.contains("ID")){
                    sentinel=true;
                    continue;
                }
                if (sentinel && !line.isBlank() && !line.startsWith("─")) {
                    availableOptions.add(line);
                }
                System.out.println(line);
            }
            process.waitFor();
        }catch(IOException | InterruptedException e){
            log.error("The option couldn't be processed with the available formats", e);
        }
        availableOptions.removeFirst();
        return availableOptions.stream().map(line->{
            String[] format = line.trim().split("\\s+");
            String id = format[0];
            String ext = format.length > 1 ? format[1] : null;
            String resolution = format.length > 2 ? format[2] : null;
            String fps = format.length > 3 && !format[3].contains("|") ? format[3] : null;
            return new FormatDTO(id,ext,resolution,fps);
        }).toList();
    }
}
