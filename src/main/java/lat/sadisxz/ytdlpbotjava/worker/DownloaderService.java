package lat.sadisxz.ytdlpbotjava.worker;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Component
public class DownloaderService {

    public Integer download(List<String> commands){
        ProcessBuilder pb = new ProcessBuilder(commands);
        pb.redirectErrorStream(true);
        try{
            Process process = pb.start();
            BufferedReader bf = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while((line=bf.readLine())!=null){
                System.out.println(line);
            }
            int codeProcess = process.waitFor();
            return codeProcess;
        }catch(IOException | InterruptedException e){
            e.printStackTrace();
        }
        return null;
    }
}
