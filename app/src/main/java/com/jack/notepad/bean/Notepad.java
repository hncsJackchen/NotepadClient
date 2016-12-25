package com.jack.notepad.bean;

/**
 * Created by Administrator on 2016/12/25.
 * 笔记实体类
 */
public class Notepad implements Comparable<Notepad>{
    //自增id
    private int id;
    //用户id
    private int uid;
    //时间
    private long createTime;
    //标题
    private String title;
    //内容
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @Override
    public String toString() {
        return "Notepad{" +
                "id=" + id +
                ", uid=" + uid +
                ", createTime=" + createTime +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public int compareTo(Notepad another) {
        return (int)(another.getCreateTime()-this.getCreateTime());
    }
}
