package lat.sadisxz.ytdlpbotjava.bot.dto;

import lat.sadisxz.ytdlpbotjava.bot.model.enums.UserRole;

public record UserResponse(Long id, String name, String username, UserRole role) {
}
