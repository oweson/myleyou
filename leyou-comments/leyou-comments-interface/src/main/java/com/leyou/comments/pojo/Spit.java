package com.leyou.comments.pojo;

/**
 * @Author: 98050
 * @Time: 2018-11-26 16:46
 * @Feature:
 */

/**
 * 访问服务类
 */
public class Spit {
    private String _id;

    private String content;
   // 谁评论的
    private String userid;

    private String nickname;
  // 评论的访客
    private Integer visits;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getVisits() {
        return visits;
    }

    public void setVisits(Integer visits) {
        this.visits = visits;
    }

    @Override
    public String toString() {
        return "Spit{" +
                "_id='" + _id + '\'' +
                ", content='" + content + '\'' +
                ", userid='" + userid + '\'' +
                ", nickname='" + nickname + '\'' +
                ", visits=" + visits +
                '}';
    }
}
