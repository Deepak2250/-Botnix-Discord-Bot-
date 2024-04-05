package com.discordbotbydanix.Bot;

import com.discordbotbydanix.Bot.MessageReceived.AnimeMessages;
import com.discordbotbydanix.Bot.MessageReceived.MessageRecieve;
import com.discordbotbydanix.Bot.commands.AllCommands;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

public class JavaBot {

    private final ShardManager shardManager;

    public Dotenv getConfig() {
        return config;
    }

    private final Dotenv config;
    JavaBot() {

        config  = Dotenv.configure().load();
        String token = config.get("TOKEN");
        System.out.println(token);

        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);
        builder.enableIntents(GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MESSAGE_REACTIONS, GatewayIntent.GUILD_MEMBERS, GatewayIntent.MESSAGE_CONTENT);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.watching("Anime"));
        shardManager = builder.build();
    }

        private void addingEventListeners() {

            shardManager.addEventListener(new MessageRecieve());
            shardManager.addEventListener(new AnimeMessages());
            shardManager.addEventListener(new AllCommands());

        }

    // Add shutdown hook to stop the bot when the program exits
        private void addShutDownHook() {
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                if (shardManager != null) {
                    shardManager.shutdown();
                }
            }));
        }

    public ShardManager getShardManager() {
        return shardManager;
    }


    public static void main(String[] args) {

            JavaBot javaBot = new JavaBot();
            javaBot.addShutDownHook();
            javaBot.addingEventListeners();



    }
    }
