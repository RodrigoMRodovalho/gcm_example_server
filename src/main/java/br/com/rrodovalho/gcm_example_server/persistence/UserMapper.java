package br.com.rrodovalho.gcm_example_server.persistence;

import br.com.rrodovalho.gcm_example_server.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by rrodovalho on 19/01/16.
 */
public interface UserMapper {

        @Insert("insert into user (name, registrationid,registration_date) values(#{name}, #{registrationID},#{registrationDate})")
        public int saveUser(User user);

        @Select("SELECT * FROM user")
        public List<User> getAllUsers();

        @Select("SELECT * FROM user WHERE name like #{name}")
        public User getUserByName(String name);

        @Select("SELECT registrationid FROM user WHERE id = #{id}")
        public String getRegistrationIDByUserID(int id);

        @Update("UPDATE user SET registrationid = #{param2} WHERE registrationid like #{param1}")
        public int updateUserByRegistrationID(String param1,String param2);

        @Delete("DELETE FROM user WHERE registrationid like #{registrationid}")
        public int deleteUserByRegistrationID(String registrationid);


}
