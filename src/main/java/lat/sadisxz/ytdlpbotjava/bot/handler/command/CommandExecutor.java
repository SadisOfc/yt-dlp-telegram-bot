package lat.sadisxz.ytdlpbotjava.bot.handler.command;

import lat.sadisxz.ytdlpbotjava.bot.dto.UserDTO;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface CommandExecutor {
SendMessage execute(UserDTO userDTO);
}
