package com.discordbotbydanix.Bot.MessageReceived.joke;

import com.google.gson.JsonObject;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.PrivateChannel;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.interactions.commands.CommandInteraction;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class EmbedJokes implements JokeInterfaces.discordResponse {

    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    boolean error = true;
    String question = null;
    String answer;

    @Override
    public void embededResponse(JokeApiResponse jokeApiResponse, Guild guild, TextChannel textChannel, PrivateChannel privateChannel, CommandInteraction interaction) {
        JokeApiFetcher jokeApiFetcher = new JokeApiFetcher();
        JsonObject jsonObject = jokeApiResponse.apiResponse(jokeApiFetcher);



        // Handle the case when no response is received

        if (jsonObject == null) {

            if (textChannel != null || privateChannel != null) {
                interaction.deferReply().queue();
                interaction.getHook().sendMessage("No Jokes Found! Try Again").queue();
            }
            return;
        }

        /// Checking The Error If The Error Is True Then Return

        if (jsonObject.has("error") && jsonObject.get("error").equals(error)) {

            if (textChannel != null || privateChannel != null) {
                interaction.deferReply().queue();
                interaction.getHook().sendMessage("No Jokes Founded ! Try Again").queue();
            }
        } else {

            /// Getting The Question

            if (jsonObject.has("setup") && !jsonObject.get("setup").isJsonNull()) {
                question = jsonObject.get("setup").getAsString();
            }

            if (jsonObject.has("delivery") && !jsonObject.get("delivery").isJsonNull()) {
                answer = jsonObject.get("delivery").getAsString();
            }

            // Sending To The TextChannels and Private Channels

            if (textChannel != null) {
                interaction.deferReply().queue();
                interaction.getHook().sendMessage(question + "\n" + answer).queue();

            }
            else if (privateChannel!=null) {
                interaction.deferReply().queue();
                interaction.getHook().sendMessage(question + "\n" + answer).queue();
            }

        }

    }

    }
