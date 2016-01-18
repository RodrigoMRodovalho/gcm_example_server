package br.com.rrodovalho.gcm_example_server.domain;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by rrodovalho on 16/01/16.
 */
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
    private ArrayList<String> registration_ids;

    public PushMessageContent(String collapse_key, boolean delay_while_idle, int time_to_live, String restricted_package_name, boolean dry_run, Map<String, String> data, ArrayList<String> registration_ids) {

        this.collapse_key = collapse_key;
        this.delay_while_idle = delay_while_idle;
        this.time_to_live = time_to_live;
        this.restricted_package_name = restricted_package_name;
        this.dry_run = dry_run;
        this.data = data;
        this.registration_ids = registration_ids;
    }

    public String getCollapse_key() {
        return collapse_key;
    }

    public void setCollapse_key(String collapse_key) {
        this.collapse_key = collapse_key;
    }

    public boolean isDelay_while_idle() {
        return delay_while_idle;
    }

    public void setDelay_while_idle(boolean delay_while_idle) {
        this.delay_while_idle = delay_while_idle;
    }

    public int getTime_to_live() {
        return time_to_live;
    }

    public void setTime_to_live(int time_to_live) {
        this.time_to_live = time_to_live;
    }

    public String getRestricted_package_name() {
        return restricted_package_name;
    }

    public void setRestricted_package_name(String restricted_package_name) {
        this.restricted_package_name = restricted_package_name;
    }

    public boolean isDry_run() {
        return dry_run;
    }

    public void setDry_run(boolean dry_run) {
        this.dry_run = dry_run;
    }


    public ArrayList<String> getRegistration_ids() {
        return registration_ids;
    }

    public void setRegistration_ids(ArrayList<String> registration_ids) {
        this.registration_ids = registration_ids;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }
}
