package br.com.rrodovalho.gcm_example_server.controller;

import br.com.rrodovalho.gcm_example_server.GcmExampleServerApplication;
import br.com.rrodovalho.gcm_example_server.domain.PushMessageContent;
import br.com.rrodovalho.gcm_example_server.domain.User;
import br.com.rrodovalho.gcm_example_server.model.NotificationManager;
import br.com.rrodovalho.gcm_example_server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by rrodovalho on 14/01/16.
 */
@RestController
public class MainController {

    @Autowired
    //private UserDAO userDAO;
    private UserService userDAO;

    @RequestMapping(value="/user/register",method = RequestMethod.POST)
    public void registerUser(@RequestBody User user){
        user.setCurrentDate();
        int rowsAffected = userDAO.saveUser(user);
        if(rowsAffected>0){
            //hanle success
        }
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public @ResponseBody User getUser(String name) {
        return userDAO.getUser(name);
    }

    @RequestMapping(value = "/send-push",method = RequestMethod.GET)
    public void sendPushNotification(String name){

        User user = userDAO.getUser(name);
        if(user!=null){

            Map<String,String> data = new HashMap<>();
            data.put("title","Pusvvzh");
            data.put("message","Notification");

            ArrayList<String> registrationIDs = new ArrayList<>();
            registrationIDs.add(user.getRegistrationID());

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
            NotificationManager notificationManager =
                    new NotificationManager(pushMessageContent, GcmExampleServerApplication.SENDER_API_KEY);

            notificationManager.sendNotification();

            //collapse_key - caso o device esteja desligado, e voce mande mtas mensagens
            // , o gcm n garante ordem de entrega
            //  entao, se utilizar essa flag, ele vai guardar no servidor do gcm somente a ultima mensagem
            // dessa tag



        }

    }

}
