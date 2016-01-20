package br.com.rrodovalho.gcm_example_server.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

/**
 * Created by rrodovalho on 14/01/16.
 */

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class User{

    private long id;
    private String registrationID;
    private Timestamp registrationDate;
    private String name;

}

