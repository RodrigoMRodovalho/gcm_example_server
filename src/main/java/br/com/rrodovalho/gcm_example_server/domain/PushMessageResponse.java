package br.com.rrodovalho.gcm_example_server.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by rrodovalho on 17/01/16.
 */
@Data
@AllArgsConstructor
public class PushMessageResponse{

    private long multicast_id;
    private int success;
    private int failure;
    private int canonical_ids;
    private ArrayList<Map<String,String>> results;

}
