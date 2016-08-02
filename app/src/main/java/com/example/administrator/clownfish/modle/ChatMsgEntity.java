package com.example.administrator.clownfish.modle;

/**
 * Created by hzwq on 2016/8/1.
 */
public class ChatMsgEntity {

    /**
     * name : wangqing
     * time : 19290228
     * message : 123435
     * isComMeg : true
     */

    private String name;
    private String time;
    private String message;
    private boolean isComMeg;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isIsComMeg() {
        return isComMeg;
    }

    public void setIsComMeg(boolean isComMeg) {
        this.isComMeg = isComMeg;
    }
}
