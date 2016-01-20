package br.com.rrodovalho.gcm_example_server.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by rrodovalho on 16/01/16.
 */
@Data
@AllArgsConstructor
public class PushMessageContent {

    //Reference
    //https://developers.google.com/cloud-messaging/http-server-ref#downstream
    public static final int MAX_MESSAGE_TTL = 4 * 7 * 24 * 60 * 60;

    private String collapse_key;
    private boolean delay_while_idle;
    private int time_to_live;   //Máximo (4 * 7 * 24 * 60 * 60) - 4 semanas
    private String restricted_package_name;
    private boolean dry_run;
    private Map<String,String> data;
    private Map<String,String> notification;
    private ArrayList<String> registration_ids;

}
