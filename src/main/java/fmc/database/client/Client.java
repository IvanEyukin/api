package fmc.database.client;

import fmc.database.request.TaskRequest;
import fmc.database.request.UserRequest;
import fmc.dto.Task;
import fmc.utils.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Client {

    private Utils utils = new Utils();
    private String dbPath = "db/database.db"; 
    private final static String dbDriver = "jdbc:sqlite:";

    private String checkNull(String value) {
        if (value != null && !value.isEmpty()) {
            return value;
        } else {
            return null;
        }
    }

    private Connection connect() throws SQLException {
        Connection conn = DriverManager.getConnection(dbDriver.concat(dbPath));
        return conn;
    }

    public String checkPassword(String login) throws SQLException {
        String password = "";
        Connection conn = connect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(String.format(UserRequest.SELECT, login));
        while (rs.next()) {
            password = rs.getString("password");
        }
        conn.close();
        return password;
    }

    public boolean checkUser(String login) throws SQLException {
        boolean result = false;
        Connection conn = connect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(String.format(UserRequest.SELECT, login));
        if (rs.next()) {
            result =  true;
        } 
        conn.close();
        return result;
    }
    
    public void addUser(String login, String password, LocalDateTime createDate) throws SQLException {
        Connection conn = connect();
        PreparedStatement pstmt = conn.prepareStatement(UserRequest.INSERT);
        pstmt.setString(1, login);
        pstmt.setString(2, password);
        pstmt.setLong(3, utils.converLocalDateTimeToLong(createDate));
        pstmt.executeUpdate();
        conn.close();
    }

    public void updateUser(String login, String password, String role) throws SQLException {
        Connection conn = connect();
        PreparedStatement pstmt = conn.prepareStatement(UserRequest.UPDATE);
        pstmt.setString(1, password);
        pstmt.setString(2, checkNull(role));
        pstmt.setString(3, login);
        pstmt.executeUpdate();
        conn.close();
    }

    public int addTask(String creator, String title, String content, LocalDateTime createDate) throws SQLException {
        Connection conn = connect();
        conn.setAutoCommit(false);
        PreparedStatement pstmt = conn.prepareStatement(TaskRequest.INSERT);
        pstmt.setString(1, creator);
        pstmt.setString(2, title);
        pstmt.setString(3, checkNull(content));
        pstmt.setLong(4, utils.converLocalDateTimeToLong(createDate));
        pstmt.executeUpdate();

        Statement stmt = conn.createStatement();
        ResultSet generatedKeys = stmt.executeQuery("SELECT last_insert_rowid()");
        int id = generatedKeys.getInt(1);
        conn.commit();
        conn.close();
        return id;
    }

    public void updateTask(int id, String title, String content) throws SQLException {
        Connection conn = connect();
        PreparedStatement pstmt = conn.prepareStatement(TaskRequest.UPDATE);
        pstmt.setString(1, title);
        pstmt.setString(2, checkNull(content));
        pstmt.setInt(3, id);
        pstmt.executeUpdate();
        conn.close();
    }

    public List<Task> getTaskList() throws SQLException {
        List<Task> taskList = new ArrayList<Task>();
        Connection conn = connect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(TaskRequest.SELECT);
        while (rs.next()) {
            Task task = new Task();
            task.setId(rs.getInt("id"));
            task.setCreator(rs.getString("creator"));
            task.setTitle(rs.getString("title"));
            task.setContent(rs.getString("content"));
            task.setCreationDate(utils.converIntToLocalDateTime(rs.getInt("creationDate")));
            taskList.add(task);
        }
        conn.close();
        return taskList;
    }

    public void deleteTask(int id) throws SQLException {
        Connection conn = connect();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(String.format(TaskRequest.DELETE, id));
        conn.close();
    }
}