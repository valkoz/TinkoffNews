package com.tinkoff.fintech.news.tinkoffnews.db;


import android.arch.persistence.room.TypeConverter;

import com.tinkoff.fintech.news.tinkoffnews.model.PublicationDate;

public class DateConverter {

    @TypeConverter
    public static PublicationDate toPublicationDate(Long timestamp) {
        return new PublicationDate(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(PublicationDate publicationDate) {
        return publicationDate.getMilliseconds();
    }
}
