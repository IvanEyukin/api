package fmc.database.client;

import fmc.config.Config;
import fmc.database.request.TaskRequest;
import fmc.database.request.UserRequest;
import fmc.dto.Task;
import fmc.dto.User;
import fmc.utils.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Client {

    private Utils utils = new Utils();
    private final static String dbDriver = "jdbc:sqlite:";

    private String checkNull(String value) {
        if (value != null && !value.isEmpty()) {
            return value;
        } else {
            return null;
        }
    }

    private Connection connect() throws SQLException {
        Connection conn = DriverManager.getConnection(dbDriver.concat(Config.dbPath));
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
    
    public void addUser(User user) throws SQLException {
        Connection conn = connect();
        PreparedStatement pstmt = conn.prepareStatement(UserRequest.INSERT);
        pstmt.setString(1, checkNull(user.getName()));
        pstmt.setString(2, user.getLogin());
        pstmt.setString(3, user.getPassword());
        pstmt.setLong(4, utils.converLocalDateTimeToLong(user.getCreationDate()));
        pstmt.setString(5, checkNull(user.getRole()));
        pstmt.executeUpdate();
        conn.close();
    }

    public void updateUser(User user) throws SQLException {
        Connection conn = connect();
        PreparedStatement pstmt = conn.prepareStatement(UserRequest.UPDATE);
        pstmt.setString(1, checkNull(user.getName()));
        pstmt.setString(2, user.getPassword());
        pstmt.setString(3, checkNull(user.getRole()));
        pstmt.setString(4, user.getLogin());
        pstmt.executeUpdate();
        conn.close();
    }

    public int addTask(Task task) throws SQLException {
        Connection conn = connect();
        conn.setAutoCommit(false);
        PreparedStatement pstmt = conn.prepareStatement(TaskRequest.INSERT);
        pstmt.setString(1, task.getCreator());
        pstmt.setString(2, task.getTitle());
        pstmt.setString(3, checkNull(task.getContent()));
        pstmt.setString(4, task.getStatus());
        pstmt.setLong(5, utils.converLocalDateTimeToLong(task.getCreationDate()));
        pstmt.executeUpdate();

        Statement stmt = conn.createStatement();
        ResultSet generatedKeys = stmt.executeQuery("SELECT last_insert_rowid()");
        int id = generatedKeys.getInt(1);
        conn.commit();
        conn.close();
        return id;
    }

    public void updateTask(Task task) throws SQLException {
        Connection conn = connect();
        PreparedStatement pstmt = conn.prepareStatement(TaskRequest.UPDATE);
        pstmt.setString(1, task.getTitle());
        pstmt.setString(2, checkNull(task.getContent()));
        pstmt.setString(3, task.getStatus());
        pstmt.setInt(4, task.getId());
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
            task.setStatus(rs.getString("status"));
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