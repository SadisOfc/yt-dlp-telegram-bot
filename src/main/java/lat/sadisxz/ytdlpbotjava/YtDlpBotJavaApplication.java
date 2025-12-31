package lat.sadisxz.ytdlpbotjava;

import lat.sadisxz.ytdlpbotjava.bot.TelegramBot;
import lat.sadisxz.ytdlpbotjava.config.DownloaderProperties;
import lat.sadisxz.ytdlpbotjava.config.TelegramBotProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;

@SpringBootApplication
@EnableConfigurationProperties({TelegramBotProperties.class, DownloaderProperties.class})
public class YtDlpBotJavaApplication {

    public YtDlpBotJavaApplication(TelegramBot telegramBot) throws TelegramApiException{
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(telegramBot);
    }
    public static void main(String[] args) throws TelegramApiException, IOException, InterruptedException {
        SpringApplication.run(YtDlpBotJavaApplication.class, args);

    }
}
