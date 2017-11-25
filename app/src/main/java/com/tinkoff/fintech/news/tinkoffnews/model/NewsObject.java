package com.tinkoff.fintech.news.tinkoffnews.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.tinkoff.fintech.news.tinkoffnews.db.DateConverter;

@Entity
public class NewsObject {
    @PrimaryKey
    private Long id;

    private String name;
    private String text;

    @TypeConverters(DateConverter.class)
    private PublicationDate publicationDate;

    public NewsObject(Long id, String name, String text, PublicationDate publicationDate) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.publicationDate = publicationDate;
    }

    public Long getId() {
        return id;
    }

    public PublicationDate getPublicationDate() {
        return publicationDate;
    }

    public String getText() {
        return text;
    }

    public String getName() {
        return name;
    }
}