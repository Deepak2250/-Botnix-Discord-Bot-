package com.discordbotbydanix.Bot.commands;

import com.discordbotbydanix.Bot.MessageReceived.AnimeMessages;
import com.discordbotbydanix.Bot.MessageReceived.joke.EmbedJokes;
import com.discordbotbydanix.Bot.MessageReceived.joke.JokeApiResponse;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.PrivateChannel;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.CommandInteraction;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AllCommands extends ListenerAdapter {

    AnimeMessages animeMessages = new AnimeMessages();
    EmbedBuilder embedBuilder = new EmbedBuilder();
    EmbedBuilder embedBuilder1 = new EmbedBuilder();
    String owner = "danix_225";

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

        Guild guild = event.getGuild();
        CommandInteraction interaction = event.getInteraction();

        if (event.getName().equals("owner")) {
            ownerCommamd(event);
        } else if (event.getName().equalsIgnoreCase("anime")) {
            animeStoryCommand(event);
        } else if (event.getName().equalsIgnoreCase("topAnime")) {
            topAnimeCommand(event, guild, interaction);
        } else if (event.getName().equalsIgnoreCase("upcomingAnime")) {
            upcomingAnimeCommand(event, guild, interaction);
        } else if (event.getName().equalsIgnoreCase("topManga")) {
            topMangaCommand(event, guild, interaction);
        } else if (event.getName().equalsIgnoreCase("horror")) {
            horrorAnimeCommand(event, guild, interaction);
        } else if (event.getName().equalsIgnoreCase("romance")) {
            romanticAnimeCommand(event, guild, interaction);
        } else if (event.getName().equalsIgnoreCase("action")) {
            actionAnimeCommand(event, guild, interaction);
        } else if (event.getName().equalsIgnoreCase("fantasy")) {
            fantasyAnimeCommand(event, guild, interaction);
        } else if (event.getName().equalsIgnoreCase("mystery")) {
            mysteryAnimeCommand(event, guild, interaction);
        } else if (event.getName().equalsIgnoreCase("sports")) {
            sportsAnimeCommand(event, guild, interaction);
        } else if (event.getName().equalsIgnoreCase("hentai")) {
            hentaiAnimeCommand( interaction);
        }
        else if (event.getName().equalsIgnoreCase("botinfo")) {
            botInfoCommand(event);
        } else if (event.getName().equalsIgnoreCase("sci-fi")) {
            scifiAnimeCommand(event , guild , interaction);
        } else if (event.getName().equalsIgnoreCase("psychological")) {
            psychologicalAnimeCommand(event , guild , interaction);
        } else if (event.getName().equalsIgnoreCase("school")) {
            schoolAnimeCommand(event , guild , interaction);
        } else if (event.getName().equalsIgnoreCase("shounen")) {
            shounenAnimeCommand(event , guild , interaction);
        } else if (event.getName().equalsIgnoreCase("time-travel")) {
            timetravelAnimeCommand(event , guild , interaction);
        } else if (event.getName().equalsIgnoreCase("space")) {
            spaceAnimeCommand(event , guild , interaction);
        } else if (event.getName().equalsIgnoreCase("mythology")) {
            mythalogyAnimeCommand(event , guild , interaction);
        } else if (event.getName().equalsIgnoreCase("suspense")) {
            suspenseAnimeCommand(event , guild , interaction);
        } else if (event.getName().equalsIgnoreCase("drama")) {
            dramaAnimeCommand(event , guild , interaction);
        }
        else if (event.getName().equalsIgnoreCase("joke")) {
            EmbedJokes embedJokes = new EmbedJokes();

            if (event.getChannel() instanceof TextChannel){
                TextChannel textChannel = (TextChannel) event.getChannel();
                JokeApiResponse response = new JokeApiResponse();

                embedJokes.embededResponse(response , guild , textChannel , null , interaction);
            }
            else if (event.getChannel() instanceof PrivateChannel) {
                PrivateChannel privateChannel = (PrivateChannel) event.getChannel();
                JokeApiResponse response = new JokeApiResponse();
                embedJokes.embededResponse(response , null , null , privateChannel , interaction);
            }
        }

    }

    /// Command For Anime Stories OR Summary

    private void animeStoryCommand(SlashCommandInteractionEvent event) {
        OptionMapping optionMapping = event.getOption("anime");
        String animeName = optionMapping.getAsString();

        Guild guild = event.getGuild();
        AnimeMessages animeMessages = new AnimeMessages();
        CommandInteraction commandInteraction = event.getInteraction();

        if (event.getChannel() instanceof TextChannel) {
            TextChannel textChannel = (TextChannel) event.getChannel();
            animeMessages.fetchAnimeDetails(animeName, guild, commandInteraction, null, textChannel);
        } else if (event.getChannel() instanceof PrivateChannel) {
            PrivateChannel privateChannel = (PrivateChannel) event.getChannel();
            animeMessages.fetchAnimeDetails(animeName, guild, commandInteraction, privateChannel, null);
        }
    }


    private void topAnimeCommand(SlashCommandInteractionEvent event, Guild guild, CommandInteraction interaction) {

        checkingChannelAnime("https://api.jikan.moe/v4/top/anime", event, guild, interaction);

    }

    private void upcomingAnimeCommand(SlashCommandInteractionEvent event, Guild guild, CommandInteraction interaction) {
        checkingChannelAnime("https://api.jikan.moe/v4/seasons/upcoming", event, guild, interaction);
    }

    private void topMangaCommand(SlashCommandInteractionEvent event, Guild guild, CommandInteraction interaction) {
        checkingChannelManga("https://api.jikan.moe/v4/top/manga", event, guild, interaction);
    }

    //// Upcoming Manga


    /// Horror Anime

    private void horrorAnimeCommand(SlashCommandInteractionEvent event, Guild guild, CommandInteraction interaction) {
        checkingChannelAnime("https://api.jikan.moe/v4/anime?genres=14", event, guild, interaction);
    }

    /// Romance Anime
    private void romanticAnimeCommand(SlashCommandInteractionEvent event, Guild guild, CommandInteraction interaction) {
        checkingChannelAnime("https://api.jikan.moe/v4/anime?genres=22", event, guild, interaction);
    }

    /// Action Anime

    private void actionAnimeCommand(SlashCommandInteractionEvent event, Guild guild, CommandInteraction interaction) {
        checkingChannelAnime("https://api.jikan.moe/v4/anime?genres=1", event, guild, interaction);
    }

    /// Fantasy Anime

    private void fantasyAnimeCommand(SlashCommandInteractionEvent event, Guild guild, CommandInteraction interaction) {
        checkingChannelAnime("https://api.jikan.moe/v4/anime?genres=10", event, guild, interaction);
    }

    // Mystery Anime

    private void mysteryAnimeCommand(SlashCommandInteractionEvent event, Guild guild, CommandInteraction interaction) {
        checkingChannelAnime("https://api.jikan.moe/v4/anime?genres=7", event, guild, interaction);
    }

    /// Sports Anime

    private void sportsAnimeCommand(SlashCommandInteractionEvent event, Guild guild, CommandInteraction interaction) {
        checkingChannelAnime("https://api.jikan.moe/v4/anime?genres=30", event, guild, interaction);
    }

    /// Hentai Anime

    private void hentaiAnimeCommand(CommandInteraction interaction) {
        String gifUrl = "https://media4.giphy.com/media/nYyMa2jGupNok7b5zT/giphy.gif?cid=6c09b952s6ff3mwj60e0l20cx2omdc8hqb18hen023ikjimj&ep=v1_internal_gif_by_id&rid=giphy.gif&ct=g";
        interaction.deferReply().queue();
        interaction.getHook().sendMessage(gifUrl).queue();
    }

    private void scifiAnimeCommand(SlashCommandInteractionEvent event, Guild guild, CommandInteraction interaction) {
        checkingChannelAnime("https://api.jikan.moe/v4/anime?genres=24", event, guild, interaction);
    }

    private void psychologicalAnimeCommand(SlashCommandInteractionEvent event, Guild guild, CommandInteraction interaction) {
        checkingChannelAnime("https://api.jikan.moe/v4/anime?genres=40", event, guild, interaction);
    }

    private void schoolAnimeCommand(SlashCommandInteractionEvent event, Guild guild, CommandInteraction interaction) {
        checkingChannelAnime("https://api.jikan.moe/v4/anime?genres=23", event, guild, interaction);
    }

    private void shounenAnimeCommand(SlashCommandInteractionEvent event, Guild guild, CommandInteraction interaction) {
        checkingChannelAnime("https://api.jikan.moe/v4/anime?genres=27", event, guild, interaction);
    }

    private void timetravelAnimeCommand(SlashCommandInteractionEvent event, Guild guild, CommandInteraction interaction) {
        checkingChannelAnime("https://api.jikan.moe/v4/anime?genres=78", event, guild, interaction);
    }

    private void spaceAnimeCommand(SlashCommandInteractionEvent event, Guild guild, CommandInteraction interaction) {
        checkingChannelAnime("https://api.jikan.moe/v4/anime?genres=29", event, guild, interaction);
    }

    private void mythalogyAnimeCommand(SlashCommandInteractionEvent event, Guild guild, CommandInteraction interaction) {
        checkingChannelAnime("https://api.jikan.moe/v4/anime?genres=6", event, guild, interaction);
    }

    private void suspenseAnimeCommand(SlashCommandInteractionEvent event, Guild guild, CommandInteraction interaction) {
        checkingChannelAnime("https://api.jikan.moe/v4/anime?genres=41", event, guild, interaction);
    }

    private void dramaAnimeCommand(SlashCommandInteractionEvent event, Guild guild, CommandInteraction interaction) {
        checkingChannelAnime("https://api.jikan.moe/v4/anime?genres=8", event, guild, interaction);
    }

    /// Owner

    private void ownerCommamd(SlashCommandInteractionEvent event) {
        if (event.getChannel() instanceof TextChannel) {
            embedBuilder.setTitle("Owner");
            embedBuilder.setDescription("The Bot is Made By " + " @" + owner + " On 10/April/2024");
            embedBuilder.setThumbnail("https://i.imgur.com/HeXSLKf.gif");
            embedBuilder.addField("GITHUB :", "https://github.com/Deepak2250", false);
            embedBuilder.addField("Discord :", "https://discordapp.com/users/1115219012759072928", false);
            embedBuilder.setFooter("@API Used : Jikan API");
            embedBuilder.setColor(Color.blue);
            event.replyEmbeds(embedBuilder.build()).queue();
        } else if (event.getChannel() instanceof PrivateChannel) {
            embedBuilder.setTitle("Owner");
            embedBuilder.setDescription("The Bot is Made By " + "@" + owner + " On 10/April/2024");
            embedBuilder.setThumbnail("https://i.imgur.com/HeXSLKf.gif");
            embedBuilder.addField("GITHUB :", "https://github.com/Deepak2250", false);
            embedBuilder.addField("Discord :", "https://discordapp.com/users/1115219012759072928", false);
            embedBuilder.setFooter("@API Used : Jikan API");
            embedBuilder.setColor(Color.blue);
            event.replyEmbeds(embedBuilder.build()).queue();
        }
    }

    private void botInfoCommand(SlashCommandInteractionEvent event) {
        if (event.getChannel() instanceof TextChannel) {
            {
                embedBuilder1.setTitle("Botnix");
                embedBuilder1.setDescription("Botnix Bot is your friendly companion for all things anime-related. Whether you're a seasoned otaku or just starting your anime journey, this bot is here to enhance your anime experience. With Botnix, you can quickly access information about your favorite anime series, characters, genres, and much more.");
                embedBuilder1.setThumbnail("https://cdn.dribbble.com/users/678328/screenshots/2393089/media/aee866cbdfee26dc3ff1ebfd8d200249.gif");
                event.replyEmbeds(embedBuilder1.build()).queue();
            }
        }
        else if (event.getChannel() instanceof PrivateChannel){
            embedBuilder1.setTitle("Botnix");
            embedBuilder1.setDescription("Botnix Bot is your friendly companion for all things anime-related. Whether you're a seasoned otaku or just starting your anime journey, this bot is here to enhance your anime experience. With Botnix, you can quickly access information about your favorite anime series, characters, genres, and much more.");
            embedBuilder1.setThumbnail("https://cdn.dribbble.com/users/678328/screenshots/2393089/media/aee866cbdfee26dc3ff1ebfd8d200249.gif");
            event.replyEmbeds(embedBuilder1.build()).queue();
        }
    }

    private void checkingChannelManga(String url, SlashCommandInteractionEvent event, Guild guild, CommandInteraction interaction) {
        if (event.getChannel() instanceof TextChannel) {
            TextChannel textChannel = (TextChannel) event.getChannel();
            animeMessages.mangaApiCallAndProcess(guild, interaction, url, null, textChannel);
        } else if (event.getChannel() instanceof PrivateChannel) {
            PrivateChannel privateChannel = (PrivateChannel) event.getChannel();
            animeMessages.mangaApiCallAndProcess(guild, interaction, url, privateChannel, null);
        }
    }

    private void checkingChannelAnime(String url, SlashCommandInteractionEvent event, Guild guild, CommandInteraction interaction) {
        if (event.getChannel() instanceof TextChannel) {
            TextChannel textChannel = (TextChannel) event.getChannel();
            animeMessages.animeApiCallAndProcess(guild, interaction, url, null, textChannel);
        } else if (event.getChannel() instanceof PrivateChannel) {
            PrivateChannel privateChannel = (PrivateChannel) event.getChannel();
            animeMessages.animeApiCallAndProcess(guild, interaction, url, privateChannel, null);
        }
    }


    @Override
    public void onGuildReady(GuildReadyEvent event) {
        commandsList(event.getJDA());
    }

    @Override
    public void onGuildJoin(GuildJoinEvent event) {
        commandsList(event.getJDA());
    }

    @Override
    public void onReady(ReadyEvent event) {
        commandsList(event.getJDA());
    }


    /// CommandList That Takes All The Commands
    private void commandsList(JDA getJda) {
        List<CommandData> commandData = new ArrayList<>();
        commandData.add(Commands.slash("owner", "The Owner of The Bot.."));

        OptionData optionData1 = new OptionData(OptionType.STRING, "anime", "Synopsis of Every Anime", true);
        commandData.add(Commands.slash("anime", "Synopsis of 100+ Anime").addOptions(optionData1));

        commandData.add(Commands.slash("topanime", "Display The Top Anime On Internet"));
        commandData.add(Commands.slash("upcominganime", "Display The Upcoming Anime"));
        commandData.add(Commands.slash("topmanga", "Display The Top Manga On Internet"));
        commandData.add(Commands.slash("upcomingmanga", "Display The Upcoming Manga"));
        commandData.add(Commands.slash("horror", "Suggest You The Best Horror Anime"));
        commandData.add(Commands.slash("romance", "Suggest You The Best Romantic Anime"));
        commandData.add(Commands.slash("action", "Suggest You The Best Action Anime"));
        commandData.add(Commands.slash("fantasy", "Suggest You The Best Fantasy Anime"));
        commandData.add(Commands.slash("mystery", "Suggest You The Best Mystery Anime"));
        commandData.add(Commands.slash("sports", "Suggest You The Best Sports Anime"));
        commandData.add(Commands.slash("hentai", "Suggest You The Best Hentai Anime"));
        commandData.add(Commands.slash("botinfo", "The Info About Me "));
        commandData.add(Commands.slash("sci-fi" , "Suggest You The Best Sci-Fi Anime"));
        commandData.add(Commands.slash("psychological" ,"Suggest You The Best Psychological Anime" ));
        commandData.add(Commands.slash("school" ,"Suggest You The Best School Anime" ));
        commandData.add(Commands.slash("shounen" ,"Suggest You The Best Shounen Anime" ));
        commandData.add(Commands.slash("time-travel" ,"Suggest You The Best Time Travel Anime" ));
        commandData.add(Commands.slash("space" ,"Suggest You The Best Space Anime" ));
        commandData.add(Commands.slash("mythology" ,"Suggest You The Best Mythology Anime" ));
        commandData.add(Commands.slash("suspense" ,"Suggest You The Best Suspense Anime" ));
        commandData.add(Commands.slash("drama" ,"Suggest You The Best Drama Anime" ));
        commandData.add(Commands.slash("joke" , "Provides You The Best and Cursed Jokes"));
        getJda.updateCommands().addCommands(commandData).queue();

    }
}
