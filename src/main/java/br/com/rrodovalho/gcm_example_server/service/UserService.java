package br.com.rrodovalho.gcm_example_server.service;

import br.com.rrodovalho.gcm_example_server.domain.User;
import br.com.rrodovalho.gcm_example_server.persistence.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<User> getAllUsers(){return userMapper.getAllUsers(); }

    public User getUserByName(String name) {
        return userMapper.getUserByName(name);
    }

    public String getRegistrationIDByUserID(int id) {
        return userMapper.getRegistrationIDByUserID(id);
    }

    public int updateUserByRegistrationID(String oldRegID, String newRegID){
        return userMapper.updateUserByRegistrationID(oldRegID,newRegID);
    }

    public int deleteUserByRegistrationID(String regID){
        return userMapper.deleteUserByRegistrationID(regID);
    }
}
