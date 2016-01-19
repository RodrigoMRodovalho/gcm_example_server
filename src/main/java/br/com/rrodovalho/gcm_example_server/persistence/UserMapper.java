package br.com.rrodovalho.gcm_example_server.persistence;

import br.com.rrodovalho.gcm_example_server.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Created by rrodovalho on 19/01/16.
 */
public interface UserMapper {
        @Select("SELECT * FROM user WHERE name like #{name}")
        public User getUser(String name);


        @Insert("insert into user (name, registrationid,registration_date) values(#{name}, #{registrationID},#{registrationDate})")
        public int saveUser(User user);

        /*@Update("")
        public int updateUserByRegistrationID(String oldRegID,String newRegID);*/


        @Delete("DELETE FROM user WHERE registrationid like #{registrationid}")
        public void deleteUserByRegistrationID(String registrationid);

}
