package lat.sadisxz.ytdlpbotjava.bot.service;

import lat.sadisxz.ytdlpbotjava.bot.dto.UserDTO;
import lat.sadisxz.ytdlpbotjava.bot.dto.UserRequest;
import lat.sadisxz.ytdlpbotjava.bot.dto.UserResponse;
import lat.sadisxz.ytdlpbotjava.bot.exception.InexistentUserException;
import lat.sadisxz.ytdlpbotjava.bot.exception.InexistentWhitelistException;
import lat.sadisxz.ytdlpbotjava.bot.exception.InvalidRoleException;
import lat.sadisxz.ytdlpbotjava.bot.exception.UserNotFoundException;
import lat.sadisxz.ytdlpbotjava.bot.model.entity.UserEntity;
import lat.sadisxz.ytdlpbotjava.bot.model.entity.WhiteListEntity;
import lat.sadisxz.ytdlpbotjava.bot.model.enums.UserRole;
import lat.sadisxz.ytdlpbotjava.bot.repository.UserRepository;
import lat.sadisxz.ytdlpbotjava.bot.repository.WhiteListRepository;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import org.slf4j.Logger;

@Service
public class UserService {
    Logger log = LoggerFactory.getLogger(UserService.class);
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
            log.info("Validating user. ID:{}",user.id());
            if(userEntity == null) {
                throw new InexistentUserException("Inexistent user");
            }
            if(userEntity.getRole() == UserRole.GUEST) throw new InvalidRoleException("User without role allowed");
        }
    }

    public UserEntity getOrCreateUser(UserRequest userRequest) {
        log.info("Searching user. ID:{}",userRequest.id());
        UserEntity userEntity= userRepository.findById(userRequest.id())
                .orElseGet(() -> {
                    log.info("User not found");
                    log.info("Creating user. ID:{}",userRequest.id());
                    UserEntity user = new UserEntity(
                            userRequest.id(),
                            userRequest.name(),
                            userRequest.username(),
                            UserRole.GUEST
                    );
                    return userRepository.save(user);
                });
        return validateUserData(userEntity, userRequest);
    }

    public UserEntity validateUserData(UserEntity userEntity, UserRequest userRequest){
        if(!userEntity.getName().equals(userRequest.name()) || !userEntity.getUsername().equals(userRequest.username())){
            userEntity.setName(userRequest.name());
            userEntity.setUsername(userRequest.username());
            userRepository.save(userEntity);
        }
        return userEntity;
    }

    public WhiteListEntity getWhitelist(){
        return whiteListRepository
                .findByName("users")
                .orElseThrow(() ->  new InexistentWhitelistException("Whitelist not exists"));
    }

    public boolean whitelistSwitch(UserDTO user){
        UserEntity userEntity = getOrCreateUser(new UserRequest(user.id(), user.name(), user.username()));
        validateUserAdminRole(userEntity);

        WhiteListEntity whiteList = getWhitelist();
        if(whiteList.getStatus()){
            whiteList.setStatus(false);
            whiteListRepository.save(whiteList);
            log.info("Changing whitelist status. New Status:{}", false);
            return false;
        }
        whiteList.setStatus(true);
        whiteListRepository.save(whiteList);
        log.info("Changing whitelist status. New Status:{}", true);
        return true;
    }

    public List<UserResponse> userList(UserDTO user){
        log.info("Getting user list");
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
        UserEntity userToAdd = userRepository.findById(userIdToAdd).orElseThrow(()-> new UserNotFoundException(String.format("The user with id %s does not exist",userIdToAdd)));
        userToAdd.setRole(UserRole.USER);
        userRepository.save(userToAdd);
        log.info("Added user to whitelist. ID:{}",user.id());
    }

    public void removeUserToWhitelist(UserDTO user){
        UserEntity userEntity = getOrCreateUser(new UserRequest(user.id(), user.name(), user.username()));
        validateUserAdminRole(userEntity);
        Long userIdToDelete = Long.parseLong(user.message()[1]);
        userRepository.deleteById(userIdToDelete);
        log.info("Removed user to whitelist. ID:{}",user.id());
    }

    public void validateUserAdminRole(UserEntity userEntity){
        log.info("Validating role for user {}",userEntity.getId());
        if(userEntity.getRole() != UserRole.ADMIN && userEntity.getRole() != UserRole.OWNER) throw new InvalidRoleException("Invalid role");
    }
}
