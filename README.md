# yt-dlp Telegram Bot
[![Static Badge](https://img.shields.io/badge/Bot_Api-6.9-blue)
](https://mvnrepository.com/artifact/org.telegram/telegrambots/6.9.7.1)
[![Static Badge](https://img.shields.io/badge/yt--dlp-2025.12.28-red)](https://github.com/yt-dlp)
[![Static Badge](https://img.shields.io/badge/JDK-21-orange)
](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)
[![Static Badge](https://img.shields.io/badge/Spring_Boot-4.0.1-green)
](https://spring.io/projects/spring-boot)
[![Static Badge](https://img.shields.io/badge/ffmpeg-green)
](https://www.ffmpeg.org/)
[![Static Badge](https://img.shields.io/badge/Docker-blue)
](https://www.docker.com/)

Telegram bot developed in Java with Spring Boot that allows downloading multimedia content from multiple platforms using yt-dlp.

## Status
> **Note:** This project is functional and stable, but still under active development.

## Getting Started
### Prerequisites
- Java 21
- yt-dlp
- ffmpeg
- Telegram Bot token and allowed user ID

The Telegram user ID can be obtained using the [@userinfobot](https://web.telegram.org/k/#@userinfobot) bot.

### Running the Telegram bot
1. **Clone the repository:**
   ```bash
   git clone https://github.com/SadisOfc/yt-dlp-telegram-bot
   cd yt-dlp-telegram-bot
   ```
2. **Set your telegram tokens:**
   ```bash
   export BOT_TOKEN=your_bot_token
   export BOT_USERNAME=your_bot_username
   export OWNER_TOKEN=your_user_id
   export DOWNLOADS_DIRECTORY=/downloads/
   ```
3. **Run the Bot:**
   ```bash
   ./gradlew bootrun
   ```
To run the bot using Docker, environment variables must be defined in a `.env`
file following the `.env.example` provided in the repository.

## License
MIT License, see [LICENSE](https://github.com/SadisOfc/yt-dlp-telegram-bot/blob/main/LICENSE) for details.