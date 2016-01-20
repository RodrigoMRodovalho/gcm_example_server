package br.com.rrodovalho.gcm_example_server.controller;

import br.com.rrodovalho.gcm_example_server.GcmExampleServerApplication;
import br.com.rrodovalho.gcm_example_server.domain.PushMessageContent;
import br.com.rrodovalho.gcm_example_server.domain.User;
import br.com.rrodovalho.gcm_example_server.model.NotificationManager;
import br.com.rrodovalho.gcm_example_server.service.UserService;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;


/**
 * Created by rrodovalho on 14/01/16.
 */
@RestController
public class MainController {

    @Autowired
    private UserService userDAO;


    @RequestMapping(value="/user/register",method = RequestMethod.POST)
    public void registerUser(@RequestBody User user){
        user.setRegistrationDate(new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis()));
        int rowsAffected = userDAO.saveUser(user);
        if(rowsAffected>0){
            System.out.println("User saved");
        }
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public @ResponseBody User getUser(String name) {
        return userDAO.getUserByName(name);
    }

    @RequestMapping(value = "/send-push",method = RequestMethod.POST)
    public @ResponseBody String sendPushNotification(@RequestBody String content){

        JsonParser parser = new JsonParser();
        JsonObject o = parser.parse(content).getAsJsonObject();

        ArrayList<String> registrationIDs = new ArrayList<>();
        if(o.get("toAll").getAsBoolean()){
            List<User> users = userDAO.getAllUsers();
            for(int i=0;i<users.size();i++){
                registrationIDs.add(users.get(i).getRegistrationID());
            }
        }
        else{
            JsonArray userIDs = o.getAsJsonArray("user_ids");
            String regID = null;
            for(int i=0;i<userIDs.size();i++){
                regID = userDAO.getRegistrationIDByUserID(userIDs.get(i).getAsInt());
                if(regID!=null){
                    registrationIDs.add(regID);
                }
            }
        }

        if(registrationIDs.size()>0){

            Map<String,String> data = new HashMap<>();
            data.put("title",o.get("title").getAsString());
            data.put("message",o.get("message").getAsString());


            PushMessageContent pushMessageContent =
                    new PushMessageContent("gcm_example"
                            ,false
                            ,PushMessageContent.MAX_MESSAGE_TTL
                            ,GcmExampleServerApplication.GCM_EXAMPLE_B2C_PACKAGE
                            ,false
                            ,data
                            ,null
                            ,registrationIDs
                    );

            NotificationManager notificationManager = new NotificationManager(userDAO,pushMessageContent, GcmExampleServerApplication.SENDER_API_KEY);

            return notificationManager.sendNotification();

            //collapse_key - caso o device esteja desligado, e voce mande mtas mensagens
            // , o gcm n garante ordem de entrega
            //  entao, se utilizar essa flag, ele vai guardar no servidor do gcm somente a ultima mensagem
            // dessa tag
        }

        return "{\"error\":\"No users found\"}";
    }

}
