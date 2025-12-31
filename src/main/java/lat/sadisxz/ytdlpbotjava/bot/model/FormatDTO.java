package lat.sadisxz.ytdlpbotjava.bot.model;

public record FormatDTO(String id, String ext, String resolution, String fps) {
    @Override
    public String toString(){
        return String.format("%s - %s - %s - %s", id, ext, resolution, fps);
    }
}
