package lat.sadisxz.ytdlpbotjava.bot.service;

import jakarta.ws.rs.NotFoundException;
import lat.sadisxz.ytdlpbotjava.bot.dto.UserDTO;
import lat.sadisxz.ytdlpbotjava.bot.dto.UserRequest;
import lat.sadisxz.ytdlpbotjava.bot.dto.UserResponse;
import lat.sadisxz.ytdlpbotjava.bot.model.entity.UserEntity;
import lat.sadisxz.ytdlpbotjava.bot.model.entity.WhiteListEntity;
import lat.sadisxz.ytdlpbotjava.bot.model.enums.UserRole;
import lat.sadisxz.ytdlpbotjava.bot.repository.UserRepository;
import lat.sadisxz.ytdlpbotjava.bot.repository.WhiteListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final WhiteListRepository whiteListRepository;

    public UserService(UserRepository userRepository, WhiteListRepository whiteListRepository) {
        this.userRepository = userRepository;
        this.whiteListRepository = whiteListRepository;
    }

    public void validateUserAccess(UserDTO user){
        WhiteListEntity whitelist = getWhitelist();
        UserEntity userEntity = getOrCreateUser(new UserRequest(user.id(), user.name(), user.username()));
        if(user.message()[0].equalsIgnoreCase("/start")){
            return;
        }

        if(whitelist.getStatus()){
            if(userEntity == null) {
                throw new NotFoundException("Inexistent user");
            }
            if(userEntity.getRole() == UserRole.GUEST) throw new NotFoundException("No pasa de la whitelist");
        }
    }

    public UserEntity getOrCreateUser(UserRequest user) {
        return userRepository.findById(user.id())
                .orElseGet(() -> {
                    UserEntity userEntity = new UserEntity(
                            user.id(),
                            user.name(),
                            user.username(),
                            UserRole.GUEST
                    );
                    return userRepository.save(userEntity);
                });
    }

    public WhiteListEntity getWhitelist(){
        return whiteListRepository
                .findByName("users")
                .orElseThrow(() ->  new NotFoundException("Whitelist not exists"));
    }

    public boolean whitelistSwitch(UserDTO user){
        UserEntity userEntity = getOrCreateUser(new UserRequest(user.id(), user.name(), user.username()));
        validateUserAdminRole(userEntity);

        WhiteListEntity whiteList = getWhitelist();
        if(whiteList.getStatus()){
            whiteList.setStatus(false);
            whiteListRepository.save(whiteList);
            return false;
        }
        whiteList.setStatus(true);
        whiteListRepository.save(whiteList);
        return true;
    }

    public List<UserResponse> userList(UserDTO user){
        UserEntity userEntity = getOrCreateUser(new UserRequest(user.id(), user.name(), user.username()));
        validateUserAdminRole(userEntity);
        return userRepository.findAll().stream()
                .map(u-> new UserResponse(u.getId(),u.getName(), u.getUsername(), u.getRole()))
                .toList();
    }

    public void addUserToWhitelist(UserDTO user){
        UserEntity userEntity = getOrCreateUser(new UserRequest(user.id(), user.name(), user.username()));
        validateUserAdminRole(userEntity);

        Long userIdToAdd = Long.parseLong(user.message()[1]);
        UserEntity userToAdd = userRepository.findById(userIdToAdd).orElseThrow(()-> new NotFoundException("Ese man no existe en la whitelist"));
        userToAdd.setRole(UserRole.USER);
        userRepository.save(userToAdd);
    }

    public void removeUserToWhitelist(UserDTO user){
        UserEntity userEntity = getOrCreateUser(new UserRequest(user.id(), user.name(), user.username()));
        validateUserAdminRole(userEntity);
        Long userIdToDelete = Long.parseLong(user.message()[1]);
        userRepository.deleteById(userIdToDelete);
    }

    public void validateUserAdminRole(UserEntity userEntity){
        if(userEntity.getRole() != UserRole.ADMIN && userEntity.getRole() != UserRole.OWNER) throw new RuntimeException("Permisos inv[alidos");
    }
}
