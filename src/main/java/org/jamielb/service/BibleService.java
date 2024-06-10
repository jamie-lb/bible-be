package org.jamielb.service;

import java.util.List;

import org.jamielb.dao.BibleDao;
import org.jamielb.model.businessobjects.BibleVersion;
import org.jamielb.model.businessobjects.Book;
import org.jamielb.model.businessobjects.Verse;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class BibleService {

    @Inject
    BibleDao dao;

    public List<Book> getAllBooks() {
        return dao.getAllBooks();
    }

    public List<BibleVersion> getAllVersions() {
        return dao.getAllVersions();
    }

    public List<Verse> getVersionVerses(String versionCode) {
        return dao.getVersionVerses(versionCode);
    }

    public Verse getVerse(String versionCode, int bookId, int chapterNumber, int verseNumber) {
        return dao.getVerse(versionCode, bookId, chapterNumber, verseNumber);
    }

}
