package org.jamielb.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jamielb.model.businessobjects.Book;

public class BookMapper {

    private BookMapper() {
    }

    public static Book mapRow(ResultSet results) throws SQLException {
        Book book = new Book();
        book.setId(results.getInt("book_id"));
        book.setTitle(results.getString("book_title"));
        book.setTestament(TestamentMapper.mapRow(results));
        return book;
    }

}
