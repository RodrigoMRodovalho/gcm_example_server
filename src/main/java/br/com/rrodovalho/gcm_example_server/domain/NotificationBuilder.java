package br.com.rrodovalho.gcm_example_server.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rrodovalho on 25/01/16.
 */
public class NotificationBuilder {

        private Map<String,String> notification;

        public NotificationBuilder(){
            notification = new HashMap<>();
        }

        public NotificationBuilder addTag(String tag){
            notification.put("tag",tag);
            return this;
        }
        public NotificationBuilder addClickAction(String clickAction){
            notification.put("click_action",clickAction);
            return this;
        }

        public NotificationBuilder addBodyLocKey(String bodyLocKey){
            notification.put("body_loc_key",bodyLocKey);
            return this;
        }
        public NotificationBuilder addTitleLocArgs(String titleLocArgs){
            notification.put("body_loc_args",titleLocArgs);
            return this;
        }

        public NotificationBuilder addTitleLocKey(String titleLocKey){
            notification.put("title_loc_key",titleLocKey);
            return this;
        }
        public NotificationBuilder addBodyLocArgs(String bodyLocArgs){
            notification.put("body_loc_args",bodyLocArgs);
            return this;
        }

        public NotificationBuilder addTitile(String title){
            notification.put("title",title);
            return this;
        }
        public NotificationBuilder addBody(String body){
            notification.put("body",body);
            return this;
        }

        public NotificationBuilder addIcon(String icon){
            notification.put("icon",icon);
            return this;
        }
        public NotificationBuilder addSound(String sound){
            notification.put("sound",sound);
            return this;
        }

        public Map<String,String> build(){
            return notification;
        }

}

