package edu.tamu.scholars.middleware.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "vivo.triplestore")
public class TriplestoreConfig {

    private TriplestoreType type = TriplestoreType.SDB;

    private String directory;

    private String layoutType = "layout2/hash";

    private String databaseType = "MySQL";

    private String datasourceUrl;

    private String username;

    private String password;

    private boolean jdbcStream = true;

    private int jdbcFetchSize = 512;

    private boolean streamGraphAPI = true;

    private boolean annotateGeneratedSQL = true;

    public TriplestoreConfig() {

    }

    public TriplestoreType getType() {
        return type;
    }

    public void setType(TriplestoreType type) {
        this.type = type;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getLayoutType() {
        return layoutType;
    }

    public void setLayoutType(String layoutType) {
        this.layoutType = layoutType;
    }

    public String getDatabaseType() {
        return databaseType;
    }

    public void setDatabaseType(String databaseType) {
        this.databaseType = databaseType;
    }

    public String getDatasourceUrl() {
        return datasourceUrl;
    }

    public void setDatasourceUrl(String datasourceUrl) {
        this.datasourceUrl = datasourceUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isJdbcStream() {
        return jdbcStream;
    }

    public void setJdbcStream(boolean jdbcStream) {
        this.jdbcStream = jdbcStream;
    }

    public int getJdbcFetchSize() {
        return jdbcFetchSize;
    }

    public void setJdbcFetchSize(int jdbcFetchSize) {
        this.jdbcFetchSize = jdbcFetchSize;
    }

    public boolean isStreamGraphAPI() {
        return streamGraphAPI;
    }

    public void setStreamGraphAPI(boolean streamGraphAPI) {
        this.streamGraphAPI = streamGraphAPI;
    }

    public boolean isAnnotateGeneratedSQL() {
        return annotateGeneratedSQL;
    }

    public void setAnnotateGeneratedSQL(boolean annotateGeneratedSQL) {
        this.annotateGeneratedSQL = annotateGeneratedSQL;
    }

    public enum TriplestoreType {
        SDB, TDB
    }

}
