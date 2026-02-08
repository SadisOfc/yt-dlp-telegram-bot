package lat.sadisxz.ytdlpbotjava.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "downloader")
public record DownloaderProperties(
        String base_path
) {}
