package com.github.bilak.usercreator.data.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.h2.tools.DeleteDbFiles;

import com.github.bilak.usercreator.data.entity.UserEntity;
import com.github.bilak.usercreator.data.exception.UserOperationException;

public class UserRepositoryImpl implements UserRepository {

  private static final String JDBC_DRIVER = "org.h2.Driver";
  private static final String DB_NAME = "users";
  private static final String DB_URL = String.format("jdbc:h2:~/%s", DB_NAME);
  private static final String SQL_CREATE_USERS_TABLE = "CREATE TABLE SUSERS (USER_ID INT PRIMARY KEY, USER_GUID VARCHAR(20), USER_NAME VARCHAR(20))";
  private static final String SQL_CREATE_USER = "INSERT INTO SUSERS (USER_ID, USER_GUID, USER_NAME) VALUES (?,?,?)";

  public UserRepositoryImpl() {
    init();
  }

  @Override
  public void addUser(int id, String guid, String name) {
    try (Connection connection = getConnection();
      PreparedStatement pstmt = connection.prepareStatement(SQL_CREATE_USER)) {
      int colIndex = 1;
      pstmt.setInt(colIndex, id);
      pstmt.setString(++colIndex, guid);
      pstmt.setString(++colIndex, name);
      pstmt.execute();
    } catch (SQLException e) {
      throw new UserOperationException("Unable to create user", e);
    }
  }

  @Override
  public Collection<UserEntity> findAll() {
    try (Connection connection = getConnection();
      Statement stmt = connection.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM SUSERS")) {
      final List<UserEntity> result = new ArrayList<>();
      while (rs.next()) {
        result.add(new UserEntity(rs.getInt("USER_ID"), rs.getString("USER_GUID"), rs.getString("USER_NAME")));
      }
      return result;
    } catch (SQLException e) {
      throw new UserOperationException("Unable to find users", e);
    }
  }

  @Override
  public void deleteAll() {
    try (Connection connection = getConnection(); Statement stmt = connection.createStatement()) {
      stmt.execute("DELETE FROM SUSERS");
    } catch (SQLException e) {
      throw new UserOperationException("Unable to delete users", e);
    }
  }

  void init() {
    DeleteDbFiles.execute("~", DB_NAME, false);
    try (Connection connection = getConnection(); Statement stmt = connection.createStatement()) {
      stmt.execute(SQL_CREATE_USERS_TABLE);
    } catch (SQLException e) {
      throw new UserOperationException("Unable to init db", e);
    }

  }

  private Connection getConnection() {
    try {
      Class.forName(JDBC_DRIVER);
      return DriverManager.getConnection(DB_URL);
    } catch (ClassNotFoundException | SQLException e) {
      throw new RuntimeException("Unable to get connection", e);
    }
  }

}
