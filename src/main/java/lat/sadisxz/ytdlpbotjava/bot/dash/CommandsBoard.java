package lat.sadisxz.ytdlpbotjava.bot.dash;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class CommandsBoard {
    public SendMessage sendCommands(Long chatId){
        String txt = String.format("""
                ***/ᴠɪᴅ [ᴜʀʟ]***
                Downloads and sends the video in the highest available quality.
                
                ***/ᴀᴜᴅ [ᴜʀʟ]***
                Downloads and sends the audio in the highest available quality.
                
                ***/ꜰᴏʀᴍᴀᴛ [ᴜʀʟ]***
                Shows all available formats for the provided URL, including video resolutions and audio-only options.
                
                ***/ꜰᴏʀᴍᴀᴛ_ᴅ [ꜰᴏʀᴍᴀᴛ_ɪᴅ] [ᴜʀʟ]***
                Downloads the content using the specified format.
                You can choose:
                - video+audio to download both
                - video or audio to download only one
                The file is sent as a document with the selected specifications.
                
                ***/ᴄᴏᴍᴍᴀɴᴅꜱ***
                Displays the full list of available commands with a brief explanation for each one.
                
                ***/ɪɴꜰᴏ***
                Shows information about the bot, including the technology stack used and the GitHub repository.
                
                ***/ꜱᴛᴀʀᴛ***
                Displays basic information about the current user.
                
                ***/ᴀᴅᴅ_ᴜꜱᴇʀ [ᴜꜱᴇʀ_ɪᴅ]***
                Adds a user to the whitelist. Administrator only. Only whitelisted users can use restricted commands.
                
                ***/ʀᴇᴍᴏᴠᴇ_ᴜꜱᴇʀ [ᴜꜱᴇʀ_ɪᴅ]***
                Removes a user from the whitelist, revoking access to restricted commands. Administrator only.
                """);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(txt);
        return sendMessage;
    }
}
