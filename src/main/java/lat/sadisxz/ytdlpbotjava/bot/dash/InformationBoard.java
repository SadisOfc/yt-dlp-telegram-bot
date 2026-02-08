package lat.sadisxz.ytdlpbotjava.bot.dash;

import lat.sadisxz.ytdlpbotjava.config.TelegramBotProperties;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class InformationBoard {

    private final TelegramBotProperties telegramBotProperties;

    public InformationBoard(TelegramBotProperties telegramBotProperties) {
        this.telegramBotProperties = telegramBotProperties;
    }

    public String informationEnglish(){
        return String.format("""
                ***彡 ꜰᴀꜱᴛᴅʟʙᴏᴛ - @%s 彡***
                ———————————————————————————
                ***ꜱᴛᴀᴄᴋ ᴜꜱᴇᴅ:***
                - 𝐽𝑎𝑣𝑎 𝟸𝟷 (<a href="https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html">link</a>)
                - 𝑆𝑝𝑟𝑖𝑛𝑔 𝐵𝑜𝑜𝑡 (<a href="https://spring.io/projects/spring-boot">link</a>)
                - 𝑇𝑒𝑙𝑒𝑔𝑟𝑎𝑚𝐵𝑜𝑡𝑠 𝐴𝑃𝐼 (<a href="https://github.com/rubenlagus/TelegramBots?tab=readme-ov-file">link</a>)
                - 𝑦𝑡-𝑑𝑙𝑝 (<a href="https://github.com/yt-dlp/yt-dlp">link</a>)
                - 𝐹𝐹𝑚𝑝𝑒𝑔 (<a href="https://www.ffmpeg.org/">link</a>)
                - 𝐷𝑜𝑐𝑘𝑒𝑟 (<a href="https://www.docker.com/">link</a>)""",telegramBotProperties.username());
    }

    public InlineKeyboardMarkup informationMarkup(){
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("""
        ɢɪᴛʜᴜʙ ʀᴇᴘᴏꜱɪᴛᴏʀʏ""");
        button1.setCallbackData("github");
        button1.setUrl("https://github.com/SadisOfc/yt-dlp-telegram-bot");

        List<InlineKeyboardButton> l1 = new ArrayList<>(List.of(button1));
        List<List<InlineKeyboardButton>> keyboardMarkup = new ArrayList<>(List.of(l1));
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(keyboardMarkup);

        return inlineKeyboardMarkup;
    }
}
