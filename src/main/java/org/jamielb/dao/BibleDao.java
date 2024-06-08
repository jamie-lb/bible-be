package org.jamielb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jamielb.exception.BibleDataException;

import io.agroal.api.AgroalDataSource;
import io.quarkus.agroal.DataSource;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class BibleDao {

    @Inject
    @DataSource("bible")
    AgroalDataSource datasource;

    public List<String> getBookNames() {
        String sql = "SELECT title FROM books";
        try (Connection connection = datasource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet results = statement.executeQuery()) {
            List<String> bookNames = new ArrayList<>();
            while (results.next()) {
                bookNames.add(results.getString("title"));
            }
            return bookNames;
        } catch (SQLException e) {
            throw new BibleDataException(e);
        }
    }

}
