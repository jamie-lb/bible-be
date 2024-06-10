package org.jamielb.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jamielb.model.businessobjects.BibleVersion;

public class VersionMapper {

    private VersionMapper() {
    }

    public static BibleVersion mapRow(ResultSet results) throws SQLException {
        BibleVersion version = new BibleVersion();
        version.setVersionCode(results.getString("version_code"));
        version.setVersionName(results.getString("version_name"));
        return version;
    }

}
