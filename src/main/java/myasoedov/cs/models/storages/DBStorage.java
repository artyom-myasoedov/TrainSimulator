package myasoedov.cs.models.storages;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DBStorage<T> implements Storage<T> {
    private String jdbcUrl;
    private String userName;
    private String userParol;

    public DBStorage(String jdbcUrl, String userName, String userParol) {
        this.jdbcUrl = jdbcUrl;
        this.userName = userName;
        this.userParol = userParol;
    }

    public DBStorage() {
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserParol() {
        return userParol;
    }

    public void setUserParol(String userParol) {
        this.userParol = userParol;
    }

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(getJdbcUrl(), getUserName(), getUserParol());
    }
}
