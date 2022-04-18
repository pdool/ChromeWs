package com.pdool.chromews;





import java.util.Objects;

public class Msg {
    //  用户名
    private String name;
    //  聊天信息
    private String message;
    //  数据id
    private String id;
    //  聊天类型
    private String type;
    //  玩家等级备用
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;

        if ("来了".equals(message)){
            this.type = "enter";
        }else if (message.startsWith("送出了")){
            this.type = "gift";
        }else {
            this.type = "chat";
        }

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Msg)) return false;
        Msg msg = (Msg) o;
        return id.equals(msg.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
