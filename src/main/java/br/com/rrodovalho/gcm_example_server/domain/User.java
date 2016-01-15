package br.com.rrodovalho.gcm_example_server.domain;

/**
 * Created by rrodovalho on 14/01/16.
 */
public class User {
    private String registrationId;


    public User() {}
    public User(String registrationId) {
        this.registrationId = registrationId;
    }

    public String getRegistrationId() {
        return registrationId;
    }


    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }
}
