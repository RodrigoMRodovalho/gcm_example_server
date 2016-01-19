package br.com.rrodovalho.gcm_example_server.service;

import br.com.rrodovalho.gcm_example_server.domain.User;
import br.com.rrodovalho.gcm_example_server.persistence.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by rrodovalho on 19/01/16.
 */
@Service
public class UserService {


    @Autowired
    private UserMapper userMapper;


    public int saveUser(User userToAdd) {
        return userMapper.saveUser(userToAdd);
    }

    public User getUser(String name) {
        return userMapper.getUser(name);
    }

    public void deleteUserByRegistrationID(String regID){
        userMapper.deleteUserByRegistrationID(regID);
    }
}
