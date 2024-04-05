package com.discordbotbydanix.Bot.MessageReceived;

//import net.dv8tion.jda.api.EmbedBuilder;
//import net.dv8tion.jda.api.entities.User;
//import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
//import net.dv8tion.jda.api.hooks.ListenerAdapter;

//import java.awt.*;
//import java.text.SimpleDateFormat;
//import java.util.Date;

//public class EmbedMessage extends ListenerAdapter {
//    @Override
//    public void onMessageReceived(MessageReceivedEvent event) {
//
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd//MM//yyy HH:mm:ss");
//        Date date = new Date();
//
//        String[] message = event.getMessage().getContentRaw().split(" ");
//
//        if (message.length == 1 && message[0].equalsIgnoreCase("/userInfo")) {
//            event.getChannel().sendMessage("You Should Have To Add The User Name After The Command : /userInfo user").queue();
//        }
//
//        else if (message.length==2 && message[0].equalsIgnoreCase("/userInfo")) {
//
//
//            String username = message[1];
//            User user = event.getGuild().getMembersByName(username , true).get(0).getUser();
//            String avatar = user.getAvatarUrl();
//            EmbedBuilder eb = new EmbedBuilder();
//            eb.setTitle(" Welcome " +username +" To the Server");
//            eb.setColor(Color.GREEN);
//            eb.addField("Name " , event.getMember().getAsMention() , true);
//            eb.setThumbnail(avatar);
//            eb.setFooter(username + " Was Joined On " +simpleDateFormat.format(date), event.getGuild().getIconUrl());
//            event.getChannel().sendMessageEmbeds(eb.build()).queue();
//        }
//
//    }
//}
