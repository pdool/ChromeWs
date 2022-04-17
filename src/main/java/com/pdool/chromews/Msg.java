package com.pdool.chromews;


import java.util.Objects;

public class Msg {
    private String userName;
    private String chatMsg;
    private String dataId;
    private String type;
    private String level;

    public void setType(String type) {
        this.type = type;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getChatMsg() {
        return chatMsg;
    }

    public void setChatMsg(String chatMsg) {
        this.chatMsg = chatMsg;

        if ("来了".equals(chatMsg)){
            this.type = "enter";
        }else if (chatMsg.startsWith("送出了")){
            this.type = "gift";
        }else {
            this.type = "chat";
        }

    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Msg)) return false;
        Msg msg = (Msg) o;
        return dataId.equals(msg.dataId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataId);
    }
}
