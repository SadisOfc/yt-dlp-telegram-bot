package lat.sadisxz.ytdlpbotjava.bot.repository;

import lat.sadisxz.ytdlpbotjava.bot.model.entity.WhiteListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WhiteListRepository extends JpaRepository<WhiteListEntity, Long> {
    boolean existsByName(String name);
    Optional<WhiteListEntity> findByName(String name);
}
