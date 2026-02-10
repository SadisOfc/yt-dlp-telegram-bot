package lat.sadisxz.ytdlpbotjava.bot.repository;

import lat.sadisxz.ytdlpbotjava.bot.model.entity.UserEntity;
import lat.sadisxz.ytdlpbotjava.bot.model.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByRole(UserRole role);
}
