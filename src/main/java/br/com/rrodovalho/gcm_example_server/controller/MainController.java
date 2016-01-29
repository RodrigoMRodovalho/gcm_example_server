package br.com.rrodovalho.gcm_example_server.controller;

import br.com.rrodovalho.gcm_example_server.GcmExampleServerApplication;
import br.com.rrodovalho.gcm_example_server.domain.NotificationBuilder;
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

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public @ResponseBody List<User> getAllUsers() {
        List<User> u = userDAO.getAllUsers();
        return u;
    }

    @RequestMapping(value = "/send-push",method = RequestMethod.POST)
    public @ResponseBody String sendPushNotification(@RequestBody String content){

        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(content).getAsJsonObject();

        ArrayList<String> registrationIDs = new ArrayList<>();
        if(jsonObject.get("toAll").getAsBoolean()){
            List<User> users = userDAO.getAllUsers();
            if(users!=null) {
                for (int i = 0; i < users.size(); i++) {
                    registrationIDs.add(users.get(i).getRegistrationID());
                }
            }
        }
        else{
            JsonArray userIDs = jsonObject.getAsJsonArray("user_ids");
            String regID;
            for(int i=0;i<userIDs.size();i++){
                regID = userDAO.getRegistrationIDByUserID(userIDs.get(i).getAsInt());
                if(regID!=null){
                    registrationIDs.add(regID);
                }
            }
        }

        if(registrationIDs.size()>0){

            JsonObject payload = jsonObject.get("data").getAsJsonObject();

            Map<String,String> data = new HashMap<>();
            data.put("title",payload.get("title").getAsString());
            data.put("message",payload.get("message").getAsString());

            payload = jsonObject.get("notification").getAsJsonObject();

            Map<String,String> notificationContent =
                    new NotificationBuilder()
                        .addTitile(payload.get("title").getAsString())
                        .addBody(payload.get("body").getAsString())
                        .addIcon(payload.get("icon").getAsString())
                        .build();

            PushMessageContent pushMessageContent =
                    PushMessageContent.builder()
                        .collapse_key("gcm_example")
                        .delay_while_idle(false)
                        .dry_run(false)
                        .time_to_live(PushMessageContent.MAX_MESSAGE_TTL)
                        .restricted_package_name(GcmExampleServerApplication.GCM_EXAMPLE_B2C_PACKAGE)
                        .data(data)
                        .registration_ids(registrationIDs)
                        .notification(notificationContent)
                        .build();

            NotificationManager notificationManager = new NotificationManager(userDAO,pushMessageContent, GcmExampleServerApplication.SENDER_API_KEY);

            return notificationManager.sendNotification();

        }

        return "{\"error\":\"No users found\"}";
    }

}
