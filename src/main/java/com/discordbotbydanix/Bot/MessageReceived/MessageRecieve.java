
package com.discordbotbydanix.Bot.MessageReceived;


import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageRecieve extends ListenerAdapter {

    // User Joined The Server

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {

        /// Checking Up the User's Joining Date

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyy HH:mm:ss");
        Date date = new Date();

        /// Info About The User

        String avatar = event.getUser().getAvatarUrl();
        String memberName = event.getUser().getEffectiveName();

        /// EmbedBuilder Class To Make The Interactive Visualization

        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle(" Welcome " +memberName +" To the Server");
        eb.setColor(Color.GREEN);
        eb.addField("Name " , event.getMember().getAsMention() , true);
        eb.setDescription(memberName + " Was Joined @ " +simpleDateFormat.format(date));
        eb.setThumbnail(avatar);
        eb.setFooter("Don't Break Any Server Rule OtherWise The Mods Will Break You", event.getGuild().getIconUrl());

        eb.setImage("https://w.wallhaven.cc/full/z8/wallhaven-z8w9qg.png");
        // TextChannel To get The Info Of the Channel That In which Channel The Person Joined

        TextChannel textChannel = event.getGuild().getTextChannelsByName("welcome" ,true).stream().findFirst().orElse(null);
        // Check if a text channel was found
        if (textChannel != null) {
            textChannel.sendMessageEmbeds(eb.build()).queue();
        } else {
            System.out.println("No text channels found in the guild.");
        }
    }


    /// User Left The Server


    @Override
    public void onGuildMemberRemove(GuildMemberRemoveEvent event) {
        String avatar = event.getUser().getAvatarUrl();
        String member = event.getUser().getAsMention();
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle(member + "Left");
        embedBuilder.setImage(avatar);

        TextChannel textChannel = event.getGuild().getTextChannelsByName("general" ,true).stream().findFirst().orElse(null);

        if (textChannel!=null){
            textChannel.sendMessageEmbeds(embedBuilder.build()).queue();
        }
        else {
            System.out.println("No text channels found in the guild.");
        }
    }

//    @Override
//    public void onMessageReactionAdd(MessageReactionAddEvent event) {
//        String user = event.getUser().getEffectiveName();
//        String emoji = event.getReaction().getEmoji().getAsReactionCode();
//        TextChannel textChannel = event.getGuild().getTextChannelsByName("AnimeDetails" , true).stream().findFirst().orElse(null);
//        String jumpUrl = event.getJumpUrl();
//
//        String message = user + " Reacted with the  " + emoji  + " in the " + textChannel + " Channel";
//
//        if (textChannel!=null){
//            event.getChannel().sendMessage(message);
//
//        }
//        else {
//            event.getChannel().sendMessage("No AnimeDetails Channel Found");
//        }
//    }


//    @Override
//    public void onMessageReceived(MessageReceivedEvent event) {
//
//        if (!event.getAuthor().isBot()) {
//            String[] message = event.getMessage().getContentRaw().split(" ");
//
//            if (message[0].equalsIgnoreCase("/getRole") && event.getAuthor().getId().equals("1115219012759072928")){
//                Member owner = event.getMember().getGuild().getMemberById("1115219012759072928");
//
//                if (owner!=null){
//                    event.getGuild().addRoleToMember(owner , event.getGuild().getRoleById("1115219012759072928")).queue();
//                    event.getChannel().sendMessage("Role granted to bot owner!").queue();
//                }
//                else {
//                    event.getChannel().sendMessage("Bot owner not found!").queue();
//                }
//            }
//        }
//
//
//    }
}

