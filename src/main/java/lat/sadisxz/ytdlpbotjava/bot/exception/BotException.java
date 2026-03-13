package lat.sadisxz.ytdlpbotjava.bot.exception;

import lat.sadisxz.ytdlpbotjava.bot.dto.ExceptionDTO;

public class BotException extends RuntimeException{
    private ExceptionDTO details;

    public BotException(String message){
        super(message);
    }
    public BotException(String message, Throwable cause){
        super(message, cause);
    }
    public BotException(String message, ExceptionDTO details, Throwable cause){
        super(message,cause);
        this.details = details;
    }

    public ExceptionDTO getDetails(){
        return details;
    }
}
