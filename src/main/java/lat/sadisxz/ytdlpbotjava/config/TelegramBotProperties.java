package lat.sadisxz.ytdlpbotjava.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "telegram.bot")
public record TelegramBotProperties(
        String username,
        String token,
        String owner_id,
        String owner_username
){}
