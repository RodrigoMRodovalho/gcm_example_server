package br.com.rrodovalho.gcm_example_server.domain;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by rrodovalho on 17/01/16.
 */
public class PushMessageResponse{// extends TypeToken<ArrayList<Map<String,String>>>{

    private long multicast_id;
    private int success;
    private int failure;
    private int canonical_ids;
    private ArrayList<Map<String,String>> results;

    public PushMessageResponse(int multicast_id, int success, int failure, int canonical_ids, ArrayList<Map<String, String>> results) {
        this.multicast_id = multicast_id;
        this.success = success;
        this.failure = failure;
        this.canonical_ids = canonical_ids;
        this.results = results;
    }

    public ArrayList<Map<String, String>> getResults() {
        return results;
    }

    public void setResults(ArrayList<Map<String, String>> results) {
        this.results = results;
    }

    public long getMulticast_id() {
        return multicast_id;
    }

    public void setMulticast_id(int multicast_id) {
        this.multicast_id = multicast_id;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public int getFailure() {
        return failure;
    }

    public void setFailure(int failure) {
        this.failure = failure;
    }

    public int getCanonical_ids() {
        return canonical_ids;
    }

    public void setCanonical_ids(int canonical_ids) {
        this.canonical_ids = canonical_ids;
    }

}
