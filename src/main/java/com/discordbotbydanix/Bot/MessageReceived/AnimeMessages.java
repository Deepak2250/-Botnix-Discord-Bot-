package com.discordbotbydanix.Bot.MessageReceived;

import com.google.gson.*;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.PrivateChannel;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.exceptions.ErrorResponseException;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.CommandInteraction;
import okhttp3.*;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class AnimeMessages extends ListenerAdapter {

    /// OKHttp Client Library Object Initialization
    OkHttpClient okHttpClient = new OkHttpClient();

    public void fetchAnimeDetails(String animeName, Guild guild, CommandInteraction interaction, PrivateChannel privateCahnnel, TextChannel textChannel) {


//        try {
//            String encodedAnimeName = URLEncoder.encode(animeName, StandardCharsets.UTF_8.toString());
//            URL url = new URL("https://api.jikan.moe/v4/anime?q=" + encodedAnimeName);
//            System.out.println(encodedAnimeName);
//            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//
//            httpURLConnection.setRequestMethod("GET");
//            int connectionResponse = httpURLConnection.getResponseCode();
//            System.out.println(connectionResponse);
//
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
//            StringBuilder stringBuilder = new StringBuilder();
//            String line;
//
//            while ((line = bufferedReader.readLine()) != null) {
//                stringBuilder.append(line);
//            }

        try {
            String encodedAnimeName = URLEncoder.encode(animeName, StandardCharsets.UTF_8).toString();
            String url = "https://api.jikan.moe/v4/anime?q=" + encodedAnimeName;

            Request request = new Request.Builder().url(url).build();

            Response response = okHttpClient.newCall(request).execute();
            System.out.println("Response is " + response.code());

            if (!response.isSuccessful()) {
                System.out.println("The Api is Response is " + response);
            } else {
                String responseBody = response.body().string();
                Gson gson = new Gson();
                JsonObject jsonObject = gson.fromJson(responseBody, JsonObject.class);
                processSpecificAnimeData(jsonObject, guild, interaction, privateCahnnel, textChannel);
                System.out.println(url);
                System.out.println(encodedAnimeName);
            }
        } catch (IOException e) {
            throw new RuntimeException("The Error is :" + e);
        }


//            String synopsis = "Unknown";
//            String image = null;
//            String englishTitle = "Unknown";
//            String airedDate = "Unknown";
//            String episodes = "Unknown";
//            String rating = "Unknown";
//
//
//            Gson gson = new Gson();
//            JsonObject jsonObject = gson.fromJson(stringBuilder.toString(), JsonObject.class);
//
//            if (!jsonObject.has("data") || !jsonObject.get("data").isJsonNull() || jsonObject.get("data").getAsJsonArray().size() == 0) {
//                interaction.deferReply().complete();
//                interaction.getHook().sendMessage("Sorry, no data available for the provided anime name.").queue();
//                return;
//            } else {
//                JsonObject data = jsonObject.get("data").getAsJsonArray().get(0).getAsJsonObject();
//
//                /// Checking The synopsis
//
//                if (data.has("synopsis") && !data.get("synopsis").isJsonNull()) {
//                    synopsis = data.get("synopsis").getAsString();
//                }
//
//                /// Checking The Image is Null or Not
//
//                if (data.has("images") && !data.get("images").isJsonNull() && data.get("images").getAsJsonObject().has("jpg")) {
//                    JsonObject jpgObject = data.get("images").getAsJsonObject().get("jpg").getAsJsonObject();
//
//                    if (jpgObject.has("image_url") && !jpgObject.get("image_url").isJsonNull()) {
//                        image = jpgObject.get("image_url").getAsString();
//                    }
//                }
//
//                if (image == null) {
//                    // Replace this with your default image URL or handle it as needed
//                    image = "https://st2.depositphotos.com/2493575/5398/i/450/depositphotos_53989081-stock-photo-black-texture.jpg";
//                }
//
//
//                /// Checking For The Title
//
//                JsonArray titleArray = data.getAsJsonArray("titles");
//
//                for (JsonElement jsonElement : titleArray) {
//                    JsonObject titleObject = jsonElement.getAsJsonObject();
//
//                    // Check if "type" property exists and is not null
//                    if (titleObject.has("type") && !titleObject.get("type").isJsonNull()) {
//                        String type = titleObject.get("type").getAsString();
//
//                        // Check if the title type is "English"
//                        if (type.equals("English")) {
//                            // Check if "title" property exists and is not null
//                            if (titleObject.has("title") && !titleObject.get("title").isJsonNull()) {
//                                englishTitle = titleObject.get("title").getAsString();
//                                break;
//                            }
//                        }
//                    }
//                }
//
//                /// Checking For The Aired Date
//
//                if (data.has("aired") && !data.get("aired").isJsonNull()) {
//                    JsonObject airedObject = data.getAsJsonObject("aired");
//                    if (airedObject.has("from") && !airedObject.get("from").isJsonNull()) {
//                        airedDate = airedObject.get("from").getAsString().substring(0, 10);
//                    }
//                }
//
//                /// Checking For The Episodes of the Anime That is Exist Or Not
//
//                if (data.has("episodes") && !data.get("episodes").isJsonNull()) {
//                    episodes = data.get("episodes").getAsString();
//                }
//
//                /// Checking The Rating String is Null Or Not
//
//                if (data.has("rating")) {
//                    // Check if the "rating" property is not null
//                    JsonElement ratingElement = data.get("rating");
//                    if (!ratingElement.isJsonNull()) {
//                        rating = ratingElement.getAsString();
//                    }
//                }
//
//
//                /// Checking For the Genre
//
//                JsonArray genresArray = jsonObject.getAsJsonArray("data").get(0).getAsJsonObject().getAsJsonArray("genres");
//                StringBuilder genreList = new StringBuilder();
//                genreList.append("Unknown");
//
//                for (int i = 0; i < genresArray.size(); i++) {
//                    String genre = genresArray.get(i).getAsJsonObject().get("name").getAsString();
//
//                    if (!genre.isEmpty()) {
//                        genreList.append(genre);
//
//                        if (i < genresArray.size() - 1) {
//                            genreList.append(", ");
//                        }
//                    }
//                }
//
//
//                // Checking For The Rank
//
//                String rank = "Unknown";
//
//                if (data.has("rank") && !data.get("rank").isJsonNull()) {
//                    rank = data.get("rank").getAsString();
//                }
//
//                /// Method To Show the Data To The Channels
//
//                sendAnimeDetailsEmbedToChannel(englishTitle, synopsis, image, airedDate, episodes, rating, genreList, rank, guild, interaction, textChannel, privateCahnnel);
//
//            }
//
//            bufferedReader.close();

        // }
//        catch (IOException e) {
//            interaction.deferReply().complete();
//            interaction.getHook().sendMessage("Sorry, the bot is currently offline.").queue();
//            e.printStackTrace();
//        }
//
//
    }


    /// Function To Fetch Data From Api and Also Returns StringBuilder.toString

    public String fetchAnimeDataFromApi(String apiUrl) {

        StringBuilder responseBody = null;
        System.out.println(apiUrl);

        try {
            Request request = new Request.Builder().url(apiUrl).build();

            Response response = okHttpClient.newCall(request).execute();
            System.out.println("Response is " + response.code());

            if (!response.isSuccessful()) {
                System.out.println("The Api is Response is " + response);
            } else {
                responseBody = new StringBuilder(response.body().string());


            }

        }
        catch (SocketTimeoutException e) {
            // Handle timeout exception
            System.err.println("Timeout occurred while making the HTTP request: " + e.getMessage());
            // Inform the user or retry the request
        }

        catch (IOException e) {
            throw new RuntimeException("The Error is :" + e);
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }

        return responseBody.toString();
    }

    /// Function To Accept All The Function Related To Fetching Data of Api

    public void jsonDataConverterforAnime(String jsonResponse, Guild guild, CommandInteraction interaction, PrivateChannel privateChannel, TextChannel textChannel) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(jsonResponse, JsonObject.class);
        processSpecificAnimeData(jsonObject, guild, interaction, privateChannel, textChannel);
    }

    private void jsonDataConveterforManga(String jsonResponse, Guild guild, CommandInteraction interaction, PrivateChannel privateChannel, TextChannel textChannel) {
        Gson gson = new Gson();
        JsonObject jsonObject1 = gson.fromJson(jsonResponse, JsonObject.class);
        processSpecificMangaData(jsonObject1, guild, interaction, privateChannel, textChannel);
    }

    /// Trending Anime Response Data .....

    public void processSpecificAnimeData(JsonObject jsonObject, Guild guild, CommandInteraction interaction, PrivateChannel privateChannel, TextChannel textChannel) {

        JsonArray animeResponse = jsonObject.getAsJsonArray("data");

        int randomIndex = (int) (Math.floor(Math.random() * animeResponse.size()));

        JsonObject DataResponse = animeResponse.get(randomIndex).getAsJsonObject();

        JsonArray titleArray = DataResponse.getAsJsonArray("titles");

        System.out.println(DataResponse);

        if(animeResponse==null && animeResponse.size()==0){
            System.out.println("No Anime data found in the response.");
            return;
        }
        if (titleArray == null) {
            System.out.println("No titles found for the Anime.");
            return;
        }

        String englishTitle = null;
        String url = "";
        String synopsis = "Unknown";
        String airedDate = "Unknown";
        String episodes = "Unknown";
        String rank = "Unknown";
        String rating = "Unknown";
        String source = "Unknown";

        for (JsonElement jsonElement : titleArray) {
            JsonObject titleObject = jsonElement.getAsJsonObject();

            if (titleObject.has("type") && !titleObject.get("type").isJsonNull()) {
                String type = titleObject.get("type").getAsString();

                if (type.equals("English")) {
                    if (titleObject.has("title") && !titleObject.get("title").isJsonNull()) {
                        englishTitle = titleObject.get("title").getAsString();
                        break; // Found English title, no need to continue iterating
                    }
                }
            }
        }

        // If English title is still null, use the title from the main JSON object
        if (englishTitle == null && DataResponse.has("title") && !DataResponse.get("title").isJsonNull()) {
            englishTitle = DataResponse.get("title").getAsString();
        }

        System.out.println(englishTitle);

        if (DataResponse.has("images") && !DataResponse.get("images").isJsonNull() && DataResponse.get("images").getAsJsonObject().has("jpg")) {
            JsonObject jpgObject = DataResponse.get("images").getAsJsonObject().get("jpg").getAsJsonObject();

            if (jpgObject.has("image_url") && !jpgObject.get("image_url").isJsonNull()) {
                url = jpgObject.get("image_url").getAsString();
            }
        }

        if (url == null) {
            // Replace this with your default image URL or handle it as needed
            url = "https://st2.depositphotos.com/2493575/5398/i/450/depositphotos_53989081-stock-photo-black-texture.jpg";
        }


        /// Checking The synopsis

        if (DataResponse.has("synopsis") && !DataResponse.get("synopsis").isJsonNull()) {
            synopsis = DataResponse.get("synopsis").getAsString();
        }

        /// Checking For The Aired Date

        if (DataResponse.has("aired") && !DataResponse.get("aired").isJsonNull()) {
            JsonObject airedObject = DataResponse.getAsJsonObject("aired");
            if (airedObject.has("from") && !airedObject.get("from").isJsonNull()) {
                airedDate = airedObject.get("from").getAsString().substring(0, 10);
            }
        }

        //// Checking For the availability of the episodes

        if (DataResponse.has("episodes") && !DataResponse.get("episodes").isJsonNull()) {
            episodes = DataResponse.get("episodes").getAsString();
        }

        /// Checking For the Genre

        JsonArray genereArray = DataResponse.getAsJsonArray("genres");
        StringBuilder genreList = new StringBuilder();

        for (int i = 0; i < genereArray.size(); i++) {
            String genre = genereArray.get(i).getAsJsonObject().get("name").getAsString();

            if (!genre.isEmpty()) {
                genreList.append(genre);
                if (i < genereArray.size() - 1) {
                    genreList.append(", ");
                }
            }
        }

        //Checking The Rating

        if (DataResponse.has("rating")) {
            // Check if the "rating" property is not null
            JsonElement ratingElement = DataResponse.get("rating");
            if (!ratingElement.isJsonNull()) {
                rating = ratingElement.getAsString();
            }
        }

        /// Checking For Rank

        if (DataResponse.has("rank") && !DataResponse.get("rank").isJsonNull()) {
            rank = DataResponse.get("rank").getAsString();
        }


        /// Checking For source

        if (DataResponse.has("source") && !DataResponse.get("source").isJsonNull()) {
            source = DataResponse.get("source").getAsString();
        }

        sendAnimeDetailsEmbedToChannel(englishTitle, synopsis, url, airedDate, source, episodes, rating, genreList, rank, guild, interaction, textChannel, privateChannel);
    }


    /// Checking For the Manga Data

    private void processSpecificMangaData(JsonObject jsonObject, Guild guild, CommandInteraction interaction, PrivateChannel privateChannel, TextChannel textChannel) {
        JsonArray mangaResponse = jsonObject.getAsJsonArray("data");

        int randomIndex = (int) (Math.floor(Math.random() * mangaResponse.size()));

        JsonObject MangaDataResponse = mangaResponse.get(randomIndex).getAsJsonObject();

        JsonArray titleArray = MangaDataResponse.getAsJsonArray("titles");

        String englishTitle = "Unknown";
        String url = "";
        String synopsis = "Unknown";
        String rank = "Unknown";
        String authors = "Unknown";
        String chapters = "Unknown";
        String volumes = "Unknown";
        String mangaType = "Unknown";

        if(mangaResponse==null && mangaResponse.size()==0){
            System.out.println("No manga data found in the response.");
            return;
        }
        if (titleArray == null) {
            System.out.println("No titles found for the manga.");
            return;
        }

        for (JsonElement jsonElement : titleArray) {
            JsonObject titleObject = jsonElement.getAsJsonObject();

            if (titleObject.has("type") && !titleObject.get("type").isJsonNull()) {
                String type = titleObject.get("type").getAsString();

                if (type.equals("English")) {
                    if (titleObject.has("title") && !titleObject.get("title").isJsonNull()) {
                        englishTitle = titleObject.get("title").getAsString();
                        break; // Found English title, no need to continue iterating
                    }
                }
            }
        }

// If English title is still null, use the title from the main JSON object

        if (englishTitle == null && MangaDataResponse.has("title") && !MangaDataResponse.get("title").isJsonNull()) {
            englishTitle = MangaDataResponse.get("title").getAsString();

        }

        System.out.println(englishTitle);

        if (MangaDataResponse.has("images") && !MangaDataResponse.get("images").isJsonNull() && MangaDataResponse.get("images").getAsJsonObject().has("jpg")) {
            JsonObject jpgObject = MangaDataResponse.get("images").getAsJsonObject().get("jpg").getAsJsonObject();

            if (jpgObject.has("image_url") && !jpgObject.get("image_url").isJsonNull()) {
                url = jpgObject.get("image_url").getAsString();
            }
        }

        if (url == null) {
            // Replace this with your default image URL or handle it as needed
            url = "https://st2.depositphotos.com/2493575/5398/i/450/depositphotos_53989081-stock-photo-black-texture.jpg";
        }


        /// Checking The synopsis

        if (MangaDataResponse.has("synopsis") && !MangaDataResponse.get("synopsis").isJsonNull()) {
            synopsis = MangaDataResponse.get("synopsis").getAsString();
        }

        /// Checking For Rank

        if (MangaDataResponse.has("rank") && !MangaDataResponse.get("rank").isJsonNull()) {
            rank = MangaDataResponse.get("rank").getAsString();
        }

        /// Checking For Authors

        if (MangaDataResponse.has("authors") && !MangaDataResponse.get("authors").isJsonNull()) {
            JsonArray authorsArray = MangaDataResponse.getAsJsonArray("authors");

            // Check if the authors array is not empty
            if (authorsArray.size() > 0) {
                // Get the first author object from the array
                JsonObject firstAuthor = authorsArray.get(0).getAsJsonObject();

                // Check if the author object has the "name" attribute
                if (firstAuthor.has("name") && !firstAuthor.get("name").isJsonNull()) {
                    authors = firstAuthor.get("name").getAsString();
                }
            }
        }

        System.out.println("Author: " + authors);

        /// Checking For Chapters

        if (MangaDataResponse.has("chapters") && !MangaDataResponse.get("chapters").isJsonNull()) {
            chapters = MangaDataResponse.get("chapters").getAsString();
        }

        /// Checking For Volumes

        if (MangaDataResponse.has("volumes") && !MangaDataResponse.get("volumes").isJsonNull()) {
            volumes = MangaDataResponse.get("volumes").getAsString();
        }

        // Checking For The Type

        if (MangaDataResponse.has("type") && !MangaDataResponse.get("type").isJsonNull()) {
            mangaType = MangaDataResponse.get("type").getAsString();
        }

        /// Checking For the Genre

        JsonArray genereArray = MangaDataResponse.getAsJsonArray("genres");
        StringBuilder genreList = new StringBuilder();

        for (int i = 0; i < genereArray.size(); i++) {
            String genre = genereArray.get(i).getAsJsonObject().get("name").getAsString();

            if (!genre.isEmpty()) {
                genreList.append(genre);
                if (i < genereArray.size() - 1) {
                    genreList.append(", ");
                }
            }
        }

        sendMangaDetailsEmbedToChannel(englishTitle, synopsis, url, chapters, volumes, authors, mangaType, genreList, rank, guild, interaction, textChannel, privateChannel);
    }


    ///Embed Data To The Channel For Anime


    public void sendAnimeDetailsEmbedToChannel(String title, String synposis, String image, String airedDate, String source, String episodes, String rating, StringBuilder genreList, String rank, Guild guild, CommandInteraction interaction, TextChannel textChannel, PrivateChannel privateChannel) {

        String baseUrl = "https://api.jikan.moe/v3/search/anime?q=";
        String encodedTitle = URLEncoder.encode(title, StandardCharsets.UTF_8);
        String apiUrl = baseUrl + encodedTitle;

        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle(title);
        eb.setDescription(synposis);
        eb.setThumbnail(image);
        eb.setUrl(apiUrl);
        eb.addField("Aired At : ", airedDate, false);
        eb.addField("Episodes : ", episodes, false);
        eb.addField("Rating : ", rating, false);
        eb.addField("Source : ", source, false);

        eb.addField("Genre : ", genreList.toString(), false);
        eb.addField("Rank", rank, false);


        try {
            if (textChannel != null) {
                interaction.deferReply().queue();
                interaction.getHook().sendMessageEmbeds(eb.build()).queue();
            } else if (privateChannel != null) {
                interaction.deferReply().queue();
                interaction.getHook().sendMessageEmbeds(eb.build()).queue();
            } else if (interaction != null) {
                interaction.deferReply().queue();
                interaction.getHook().sendMessage("The Data is Not Provided By the Server").queue();
            }
            else if (guild != null && guild.getDefaultChannel() != null) {
                guild.getDefaultChannel().asTextChannel().sendMessage("No Data Found").queue();
            } else {
                // If no valid context is found, print the message to the console
                System.out.println("No valid context found to send message.");
            }

        } catch (ErrorResponseException e) {
            // Handle the ErrorResponseException
            if (e.getErrorCode() == 10062) {
                System.err.println("Unknown interaction: " + e.getMessage());
                // Additional handling for this specific error code if needed
            } else {
                System.err.println("Error sending anime details embed: " + e.getMessage());
                e.printStackTrace();
            }

        } catch (Exception e) {
            // Handle other exceptions
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /// Adding The sendMangaDetailsEmbedToChannel

    private void sendMangaDetailsEmbedToChannel(String title, String synposis, String image, String chapters, String volumes, String author, String mangaType, StringBuilder genreList, String rank, Guild guild, CommandInteraction interaction, TextChannel textChannel, PrivateChannel privateChannel) {

        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle(title);
        eb.setDescription(synposis);
        eb.setThumbnail(image);
        eb.setUrl("https://myanimelist.net/anime/16498/" + title.replaceAll(" ", "_"));
        eb.addField("MangaType : ", mangaType, false);
        eb.addField("Chapters : ", chapters, false);
        eb.addField("Volumes : ", volumes, false);
        eb.addField("Rank", rank, false);
        eb.addField("Genre : ", genreList.toString(), false);
        eb.addField("Author : ", author, false);


        try {
            if (textChannel != null) {
                interaction.deferReply().queue();
                interaction.getHook().sendMessageEmbeds(eb.build()).queue();
            } else if (privateChannel != null) {
                interaction.deferReply().queue();
                interaction.getHook().sendMessageEmbeds(eb.build()).queue();
            } else if (interaction != null) {

                interaction.deferReply().queue();
                interaction.getHook().sendMessage("The Data is Not Provided By the Server").queue();

            }
        } catch (ErrorResponseException e) {
            // Handle the ErrorResponseException
            System.err.println("Error sending Manga details embed: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            // Handle other exceptions
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void animeApiCallAndProcess(Guild guild, CommandInteraction interaction, String url, PrivateChannel privateChannel, TextChannel textChannel) {
        String jsonResponse = fetchAnimeDataFromApi(url);
        jsonDataConverterforAnime(jsonResponse, guild, interaction, privateChannel, textChannel);
    }

    public void mangaApiCallAndProcess(Guild guild, CommandInteraction interaction, String url, PrivateChannel privateChannel, TextChannel textChannel) {
        String jsonResponse = fetchAnimeDataFromApi(url);
        jsonDataConveterforManga(jsonResponse, guild, interaction, privateChannel, textChannel);
    }
}

