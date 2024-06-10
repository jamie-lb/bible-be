package org.jamielb.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jamielb.model.businessobjects.Testament;

public class TestamentMapper {

    private TestamentMapper () {
    }

    public static Testament mapRow(ResultSet results) throws SQLException {
        Testament testament = new Testament();
        testament.setId(results.getInt("testament_id"));
        testament.setDescription(results.getString("testament_description"));
        return testament;
    }

}
