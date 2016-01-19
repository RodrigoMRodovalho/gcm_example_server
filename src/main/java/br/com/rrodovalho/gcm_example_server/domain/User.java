package br.com.rrodovalho.gcm_example_server.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created by rrodovalho on 14/01/16.
 */

/*@Entity
@Table(name = "user")*/
public class User{

    //@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //@NotNull
    private String registrationID;

    //@NotNull
    private String name;

    //@NotNull
    private java.sql.Timestamp registrationDate;

    public User() { }


    public User(long id) {
        this.id = id;
    }

    public User(String registrationID, String name) {
        this.registrationID = registrationID;
        this.name = name;
        this.registrationDate = new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRegistrationID() {
        return registrationID;
    }

    public void setRegistrationID(String registrationID) {
        this.registrationID = registrationID;
    }

    public void setCurrentDate(){
        this.registrationDate = new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Timestamp registrationDate) {
        this.registrationDate = registrationDate;
    }
}

