package org.jamielb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jamielb.dao.mapper.BookMapper;
import org.jamielb.dao.mapper.VerseMapper;
import org.jamielb.dao.mapper.VersionMapper;
import org.jamielb.dao.queries.BibleQueries;
import org.jamielb.model.businessobjects.BibleVersion;
import org.jamielb.model.businessobjects.Book;
import org.jamielb.model.businessobjects.Verse;
import org.jamielb.model.exception.BibleDataException;

import io.agroal.api.AgroalDataSource;
import io.quarkus.agroal.DataSource;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class BibleDao {

    @Inject
    @DataSource("bible")
    AgroalDataSource datasource;

    public List<BibleVersion> getAllVersions() {
        String sql = BibleQueries.getAllVersions();
        try (Connection connection = datasource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet results = statement.executeQuery()) {
            List<BibleVersion> versions = new ArrayList<>();
            while (results.next()) {
                versions.add(VersionMapper.mapRow(results));
            }
            return versions;
        } catch (SQLException e) {
            throw new BibleDataException(e);
        }
    }

    public List<Book> getAllBooks() {
        String sql = BibleQueries.getAllBooks();
        try (Connection connection = datasource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet results = statement.executeQuery()) {
            List<Book> books = new ArrayList<>();
            while (results.next()) {
                books.add(BookMapper.mapRow(results));
            }
            return books;
        } catch (SQLException e) {
            throw new BibleDataException(e);
        }
    }

    public List<Verse> getVersionVerses(String versionCode) {
        String sql = BibleQueries.getVersionVerses();
        try (Connection connection = datasource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, versionCode);
            try (ResultSet results = statement.executeQuery()) {
                List<Verse> verses = new ArrayList<>();
                while (results.next()) {
                    verses.add(VerseMapper.mapRow(results));
                }
                return verses;
            }
        } catch (SQLException e) {
            throw new BibleDataException(e);
        }
    }

    public Verse getVerse(String versionCode, int bookId, int chapterNumber, int verseNumber) {
        String sql = BibleQueries.getVerse();
        try (Connection connection = datasource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            int index = 0;
            statement.setString(++index, versionCode);
            statement.setInt(++index, bookId);
            statement.setInt(++index, chapterNumber);
            statement.setInt(++index, verseNumber);
            try (ResultSet results = statement.executeQuery()) {
                if (results.next()) {
                    return VerseMapper.mapRow(results);
                }
                throw new BibleDataException("Could not find verse with version: %1$s, book: %2$s, chapter: %3$s, and verse number: %4$s".formatted(versionCode, bookId, chapterNumber, verseNumber));
            }
        } catch (SQLException e) {
            throw new BibleDataException(e);
        }
    }

    public List<Integer> getBookChapters(String versionCode, int bookId) {
        String sql = BibleQueries.getBookChapters();
        try (Connection connection = datasource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            int index = 0;
            statement.setString(++index, versionCode);
            statement.setInt(++index, bookId);
            try (ResultSet results = statement.executeQuery()) {
                List<Integer> chapters = new ArrayList<>();
                while (results.next()) {
                    chapters.add(results.getInt("chapter_number"));
                }
                return chapters;
            }
        } catch (SQLException e) {
            throw new BibleDataException(e);
        }
    }

    public List<Integer> getChapterVerses(String versionCode, int bookId, int chapterNumber) {
        String sql = BibleQueries.getChapterVerses();
        try (Connection connection = datasource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            int index = 0;
            statement.setString(++index, versionCode);
            statement.setInt(++index, bookId);
            statement.setInt(++index, chapterNumber);
            try (ResultSet results = statement.executeQuery()) {
                List<Integer> verseNumbers = new ArrayList<>();
                while (results.next()) {
                    verseNumbers.add(results.getInt("verse_number"));
                }
                return verseNumbers;
            }
        } catch (SQLException e) {
            throw new BibleDataException(e);
        }
    }

    public Verse getNextVerse(String versionCode, int bookId, int chapterNumber, int verseNumber) {
        return getAdjacentVerse(BibleQueries.getNextVerse(), versionCode, bookId, chapterNumber, verseNumber);
    }

    private Verse getAdjacentVerse(String sql, String versionCode, int bookId, int chapterNumber, int verseNumber) {
        try (Connection connection = datasource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            int index = 0;
            statement.setString(++index, versionCode);
            statement.setInt(++index, bookId);
            statement.setInt(++index, chapterNumber);
            statement.setInt(++index, verseNumber);
            statement.setString(++index, versionCode);
            statement.setInt(++index, bookId);
            statement.setInt(++index, chapterNumber);
            statement.setString(++index, versionCode);
            statement.setInt(++index, bookId);
            try (ResultSet results = statement.executeQuery()) {
                return results.next() ? VerseMapper.mapRow(results) : null;
            }
        } catch (SQLException e) {
            throw new BibleDataException(e);
        }
    }

    public Verse getPreviousVerse(String versionCode, int bookId, int chapterNumber, int verseNumber) {
        return getAdjacentVerse(BibleQueries.getPreviousVerse(), versionCode, bookId, chapterNumber, verseNumber);
    }

}
