package br.com.rrodovalho.gcm_example_server.controller;

import br.com.rrodovalho.gcm_example_server.domain.User;
import br.com.rrodovalho.gcm_example_server.model.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Created by rrodovalho on 14/01/16.
 */
@RestController
public class MainController {

    @Autowired
    private UserDAO userDAO;

    @RequestMapping(value="/user/register",method = RequestMethod.POST)
    public void registerUser(@RequestBody User user){
        user.setCurrentDate();
        userDAO.save(user);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public @ResponseBody User getUser(String name) {
        return userDAO.findByName(name);
    }

}
