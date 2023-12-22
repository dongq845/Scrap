package com.scrap.demo.entity;

import java.util.Date;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "scrap")
public class Scrap {
    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scrap_id")
    private Long scrapID;

    @Column(name = "user_id", nullable = false)
    private Long userID;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "author", length = 128)
    private String author;

    @Column(name = "url", nullable = false, length = 255)
    private String url;

    @Column(name = "scrap_date", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date scrapDate;

    // toString
    @Override
    public String toString() {
        return "Scrap - {" +
                " scrapID='" + getScrapID() + "'" +
                ", userID='" + getUserID() + "'" +
                ", title='" + getTitle() + "'" +
                ", content='" + getContent() + "'" +
                ", author='" + getAuthor() + "'" +
                ", url='" + getUrl() + "'" +
                ", scrapDate='" + getScrapDate() + "'" +
                "}";
    }
}
