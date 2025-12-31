package lat.sadisxz.ytdlpbotjava.repository;

import lat.sadisxz.ytdlpbotjava.bot.model.UserStatus;
import lat.sadisxz.ytdlpbotjava.config.TelegramBotProperties;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRegistry {
    private Map<Long, UserStatus> userStatusMap;

    public UserRegistry(TelegramBotProperties telegramBotProperties) {
        this.userStatusMap = new HashMap<>(Map.of(Long.parseLong(telegramBotProperties.owner_token()),UserStatus.OWNER));
    }

    public boolean addUser(Long id, UserStatus status){
        try{
            userStatusMap.put(id,status);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public void removeUser(Long id){
        try{
            userStatusMap.remove(id);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public Map<Long, UserStatus> getAllUsers(){
        if(userStatusMap.isEmpty()) return null;
        return new HashMap<>(Map.copyOf(userStatusMap));
    }

    public UserStatus findUserById(Long id){
        return userStatusMap.get(id);
    }
}
