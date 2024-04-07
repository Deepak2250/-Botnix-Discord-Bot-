package com.discordbotbydanix.Bot.MessageReceived.joke;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;


public class JokeApiFetcher implements JokeInterfaces.intializtionJokeApi {

    private String[] jokeType = {"Programming", "Misc", "Dark", "Pun", "Spooky", "Any"};
    private String[] blackListType = {"", "nsfw", "religious", "political", "racist", "sexist", "explicit"};

    private String url = getUrl("https://v2.jokeapi.dev/joke");

    @Override
    public String apiCall() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        String responseBody = null;

        try {
            Response response = okHttpClient.newCall(request).execute();

            if (!response.isSuccessful()) {
                System.out.println("The Response is Bad or Non Executable");
            } else {
                responseBody = response.body().string();

            }
        } catch (IOException e) {
            e.getMessage();
        }
        return responseBody;
    }

    private String getUrl(String url) {

        try {
            int jokeTypeEndPoint = (int) Math.floor(Math.random() * jokeType.length);
            String getJokeType = jokeType[jokeTypeEndPoint];
            System.out.println(getJokeType);

            int blackListEndpoint = (int) Math.floor(Math.random() * blackListType.length);
            String getBlacklistType = blackListType[blackListEndpoint];
            System.out.println(getBlacklistType);

            url += "/" + getJokeType + "?blacklistFlags=" + getBlacklistType + "&type=twopart";

        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }

        return url;
    }

}
