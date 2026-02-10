package lat.sadisxz.ytdlpbotjava.bot.bootstrap;

import lat.sadisxz.ytdlpbotjava.bot.model.entity.WhiteListEntity;
import lat.sadisxz.ytdlpbotjava.bot.repository.WhiteListRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class WhitelistBootstrap implements CommandLineRunner {
    private final WhiteListRepository whiteListRepository;

    public WhitelistBootstrap(WhiteListRepository whiteListRepository) {
        this.whiteListRepository = whiteListRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if(!whiteListRepository.existsByName("users")){
            whiteListRepository.save(new WhiteListEntity("users", false));
        }
    }
}
