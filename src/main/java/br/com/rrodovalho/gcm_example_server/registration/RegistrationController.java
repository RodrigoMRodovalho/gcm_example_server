package br.com.rrodovalho.gcm_example_server.registration;

import br.com.rrodovalho.gcm_example_server.domain.User;
import br.com.rrodovalho.gcm_example_server.persistence.DBManager;
import org.springframework.web.bind.annotation.*;


/**
 * Created by rrodovalho on 14/01/16.
 */
@RestController
public class RegistrationController {

    @RequestMapping(value = "/user/register",method = RequestMethod.POST)
    public @ResponseBody User registerUser(@RequestBody User user){

        System.out.print("WS-Register"+user.getRegistrationId());

        //DBManager dbManager = new DBManager();
        //dbManager.insertUser(user);

        return user;
    }

}
