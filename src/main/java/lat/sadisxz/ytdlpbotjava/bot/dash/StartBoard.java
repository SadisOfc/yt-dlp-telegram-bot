package lat.sadisxz.ytdlpbotjava.bot.dash;

import lat.sadisxz.ytdlpbotjava.bot.model.UserDTO;
import lat.sadisxz.ytdlpbotjava.config.TelegramBotProperties;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class StartBoard {
    private final TelegramBotProperties telegramBotProperties;

    public StartBoard(TelegramBotProperties telegramBotProperties) {
        this.telegramBotProperties = telegramBotProperties;
    }

    public String startBotEnglish(UserDTO user){
        return String.format("""
                ***彡 ꜰᴀꜱᴛᴅʟʙᴏᴛ  - @%s 彡***
                ———————————————————————————
                ***ᴜꜱᴇʀ:*** @%s
                ***ɪᴅ:*** `%s`
                ***ꜱᴛᴀᴛᴜꜱ:*** `%s`""",telegramBotProperties.username(),user.username(),user.id(),user.status());
    }

    public InlineKeyboardMarkup startEnglishMarkup(){
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("""
                ᴇɴɢʟɪꜱʜ""");
        button1.setCallbackData("startEnglish");

        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText("""
                ꜱᴘᴀɴɪꜱʜ""");
        button2.setCallbackData("startSpanish");


        List<InlineKeyboardButton> l1 = new ArrayList<>(Arrays.asList(button1,button2));
        List<List<InlineKeyboardButton>> keyboardMarkup = new ArrayList<>(List.of(l1));
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(keyboardMarkup);

        return inlineKeyboardMarkup;
    }

}
