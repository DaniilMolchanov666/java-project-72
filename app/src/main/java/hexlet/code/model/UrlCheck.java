package hexlet.code.model;

import java.sql.Timestamp;

public class UrlCheck {

    private long id;

    private long urlId;

    private int statusCode;

    private String title;

    private String h1;

    private String description;

    private Timestamp createdAt;

    public UrlCheck(long urlId) {
        this.urlId = urlId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUrlId() {
        return urlId;
    }

    public void setUrlId(long urlId) {
        this.urlId = urlId;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getH1() {
        return h1;
    }

    public void setH1(String h1) {
        this.h1 = h1;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
