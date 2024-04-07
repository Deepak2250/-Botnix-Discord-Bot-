package com.discordbotbydanix.Bot.MessageReceived.joke;


import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class JokeApiResponse implements JokeInterfaces.jsonResponse{
    @Override
    public JsonObject apiResponse(JokeApiFetcher jokeApiFetcher) {

       String response = jokeApiFetcher.apiCall();
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(response , JsonObject.class);
        System.out.println(response);
        return jsonObject;


    }
}
