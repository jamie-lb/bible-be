package org.jamielb.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jamielb.model.businessobjects.Verse;

public class VerseMapper {

    private VerseMapper() {
    }

    public static Verse mapRow(ResultSet results) throws SQLException {
        Verse verse = new Verse();
        verse.setId(results.getInt("verse_id"));
        verse.setVersion(VersionMapper.mapRow(results));
        verse.setVerseText(results.getString("verse_text"));
        verse.setBook(BookMapper.mapRow(results));
        verse.setChapterNumber(results.getInt("chapter_number"));
        verse.setVerseNumber(results.getInt("verse_number"));
        return verse;
    }

}
