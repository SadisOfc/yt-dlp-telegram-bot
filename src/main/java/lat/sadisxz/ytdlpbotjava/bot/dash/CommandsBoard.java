package lat.sadisxz.ytdlpbotjava.bot.dash;

import org.springframework.stereotype.Component;

@Component
public class CommandsBoard {
    public String sendCommands(){
        return String.format("""
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
                
                ***/ᴜꜱᴇʀ_ʟɪꜱᴛ*** (Only admin)
                List all existing bot users, along with their respective roles and IDs.
                
                ***/ᴡʜɪᴛᴇʟɪꜱᴛ_ꜱᴡɪᴛᴄʜ*** (Only admin)
                Enables or disables the whitelist. When enabled, users with the guest role will not be able to use the bot.
                
                ***/ᴀᴅᴅ_ᴜꜱᴇʀ [ᴜꜱᴇʀ_ɪᴅ]*** (Only admin)
                Adds a user to the whitelist. Administrator only. Only whitelisted users can use restricted commands.
                
                ***/ʀᴇᴍᴏᴠᴇ_ᴜꜱᴇʀ [ᴜꜱᴇʀ_ɪᴅ]*** (Only admin)
                Removes a user from the whitelist, revoking access to restricted commands. Administrator only.
                """);
    }
}
