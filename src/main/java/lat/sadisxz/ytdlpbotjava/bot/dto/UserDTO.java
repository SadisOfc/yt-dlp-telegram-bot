package lat.sadisxz.ytdlpbotjava.bot.dto;

import lat.sadisxz.ytdlpbotjava.bot.model.enums.UserRole;

public record UserDTO(String name, String username, Long id, UserRole role, String[] message) {
}
