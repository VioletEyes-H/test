package com.bula.gzh.Entity;

import java.io.Serializable;
import java.util.Date;

public class quanmama implements Serializable {
    private static final long serialVersionUID = 1L;
    String article_title;
    String article_mall;
    String article_pic;
    String article_mall_icon;
    String article_mall_icon2;
    String article_vicetitle;
    String article_link;
    String article_format_date;
    Date article_begintime;
    Date article_endtime;

    public String getArticle_pic() {
        return article_pic;
    }

    public void setArticle_pic(String article_pic) {
        this.article_pic = article_pic;
    }

    public String getArticle_title() {
        return article_title;
    }

    public void setArticle_title(String article_title) {
        this.article_title = article_title;
    }

    public String getArticle_mall() {
        return article_mall;
    }

    public void setArticle_mall(String article_mall) {
        this.article_mall = article_mall;
    }

    public String getArticle_mall_icon() {
        return article_mall_icon;
    }

    public void setArticle_mall_icon(String article_mall_icon) {
        this.article_mall_icon = article_mall_icon;
    }

    public String getArticle_mall_icon2() {
        return article_mall_icon2;
    }

    public void setArticle_mall_icon2(String article_mall_icon2) {
        this.article_mall_icon2 = article_mall_icon2;
    }

    public String getArticle_vicetitle() {
        return article_vicetitle;
    }

    public void setArticle_vicetitle(String article_vicetitle) {
        this.article_vicetitle = article_vicetitle;
    }

    public String getArticle_link() {
        return article_link;
    }

    public void setArticle_link(String article_link) {
        this.article_link = article_link;
    }

    public String getArticle_format_date() {
        return article_format_date;
    }

    public void setArticle_format_date(String article_format_date) {
        this.article_format_date = article_format_date;
    }

    public Date getArticle_begintime() {
        return article_begintime;
    }

    public void setArticle_begintime(Date article_begintime) {
        this.article_begintime = article_begintime;
    }

    public Date getArticle_endtime() {
        return article_endtime;
    }

    public void setArticle_endtime(Date article_endtime) {
        this.article_endtime = article_endtime;
    }

    @Override
    public String toString() {
        return "quanmama{" +
                "article_title='" + article_title + '\'' +
                ", article_mall='" + article_mall + '\'' +
                ", article_mall_icon='" + article_mall_icon + '\'' +
                ", article_mall_icon2='" + article_mall_icon2 + '\'' +
                ", article_vicetitle='" + article_vicetitle + '\'' +
                ", article_link='" + article_link + '\'' +
                ", article_format_date='" + article_format_date + '\'' +
                ", article_begintime=" + article_begintime +
                ", article_endtime=" + article_endtime +
                '}';
    }
}
