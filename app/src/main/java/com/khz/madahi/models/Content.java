package com.khz.madahi.models;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.khz.madahi.helper.Const;

import java.io.Serializable;

@Entity(tableName = Const.TABLE_NAME_CONTENT)
public class Content implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int idContent;

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("answer")
    @Expose
    private String answer;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("subject")
    @Expose
    private String subject;

    Const.ContentType contentType;

    public Content(String id, String categoryId, String userId, String answer, String content, String subject,
            Const.ContentType contentType) {
        this.id          = id;
        this.categoryId  = categoryId;
        this.userId      = userId;
        this.answer      = answer;
        this.content     = content;
        this.subject     = subject;
        this.contentType = contentType;
    }

    public int getIdContent() {
        return idContent;
    }

    public void setIdContent(int idContent) {
        this.idContent = idContent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Const.ContentType getContentType() {
        return contentType;
    }

    public void setContentType(Const.ContentType contentType) {
        this.contentType = contentType;
    }
}