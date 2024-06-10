package org.jamielb.model.businessobjects;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BibleVersion {

    private String versionCode;
    private String versionName;

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        BibleVersion that = (BibleVersion) o;
        return Objects.equals(versionCode, that.versionCode);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(versionCode);
    }

}
