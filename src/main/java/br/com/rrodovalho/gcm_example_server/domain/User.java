package br.com.rrodovalho.gcm_example_server.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created by rrodovalho on 14/01/16.
 */

@Data
@AllArgsConstructor
public class User{

    private long id;
    private String registrationID;
    private String name;
    private java.sql.Timestamp registrationDate;

}

