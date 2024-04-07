package com.discordbotbydanix.Bot.MessageReceived.joke;

import com.google.gson.JsonObject;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.PrivateChannel;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.interactions.commands.CommandInteraction;

public class JokeInterfaces {
     interface intializtionJokeApi{
        String apiCall();
    }

    interface jsonResponse{
        JsonObject apiResponse(JokeApiFetcher jokeApiFetcher);
    }
    interface discordResponse{
         void embededResponse(JokeApiResponse jokeApiResponse , Guild guild , TextChannel textChannel , PrivateChannel privateChannel , CommandInteraction interaction);
    }
}
