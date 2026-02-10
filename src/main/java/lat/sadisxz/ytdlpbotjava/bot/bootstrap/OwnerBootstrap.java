package lat.sadisxz.ytdlpbotjava.bot.bootstrap;

import lat.sadisxz.ytdlpbotjava.bot.model.entity.UserEntity;
import lat.sadisxz.ytdlpbotjava.bot.model.enums.UserRole;
import lat.sadisxz.ytdlpbotjava.bot.repository.UserRepository;
import lat.sadisxz.ytdlpbotjava.config.TelegramBotProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class OwnerBootstrap implements CommandLineRunner {
    private final UserRepository userRepository;
    private final Long ownerId;
    private final String ownerUsername;

    public OwnerBootstrap(UserRepository userRepository, TelegramBotProperties telegramBotProperties) {
        this.userRepository = userRepository;
        ownerId = Long.parseLong(telegramBotProperties.owner_token());
        ownerUsername = telegramBotProperties.owner_username();
    }

    @Override
    public void run(String... args) throws Exception {
        if(!userRepository.existsByRole(UserRole.OWNER)){
            userRepository.save(new UserEntity(ownerId, "owner", ownerUsername, UserRole.OWNER));
        }
    }
}
