# Minecraft_Discord_Bridge-plugin
Plugin that merges Discord and Minecraft chat into one for Bukkit, Spigot, and Paper servers

# How to run
## Install the plugin
- [Download the JAR file](github.com/Wojciech-Kalota/Minecraft_Discord_Bridge-plugin/releases/download/1.21/MinecraftDiscordBridge-2.0.jardi)
- Put it in your server's plugin folder
- Restart and run the server
## Setup a Discord bot
- Go to [Developer Portal](https://discord.com/developers/applications)
- Create a new application
- Go to Settings -> Bot
- Reset the token and save it for later
- Go to Settings -> OAuth2
- In OAuth2 URL Generator select the bot scope and copy the generated URL
- Add the bot to your Discord server by pasting the copied URL into the browser
## Configure the plugin
- In the MinecraftDiscordBridge folder find config.yml
- In config.yml replace token with your bot's token
- In config.yml replace the channel-id with the Discord channel ID you want messages to be sent to (supports multiple)
- Restart and run the server 
- Enjoy :)
