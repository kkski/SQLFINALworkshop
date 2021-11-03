package pl.coderslab.entity;

import java.sql.*;
import java.util.Scanner;

import static pl.coderslab.entity.DbUtil.*;

public class UserDao extends User {

    private static final String CREATE_USER_QUERY =
            "INSERT INTO users(id, username, email, password) VALUES (?, ?, ?, ?)";
    private static final String READ_USER_QUERY =
            "select * from users where id = ?";
    private static final String UPDATE_USER_QUERY =
            "UPDATE users SET email = ?, username = ?, password = ? where id = ?";
    private static final String DELETE_QUERY = "DELETE FROM users where id = ?";

    private static final String LENGTH_QUERY = "SELECT COUNT(*) FROM users";
    private static final String ID_USER_QUERY =
            "select id from users order by id desc limit 1";

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static User create(User user) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_USER_QUERY);
            statement.setInt(1, UserDao.findId()+1);
            statement.setString(2, user.getUserName());
            statement.setString(3, user.getEmail());
            statement.setString(4, hashPassword(user.getPassword()));
            statement.executeUpdate();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static User read(int userId) {
        User fromDB = new User();
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(READ_USER_QUERY);
            statement.setString(1, String.valueOf(userId));
            ResultSet rs = statement.executeQuery();
            rs.next();
            int idToUse = Integer.parseInt(rs.getString(1));
            fromDB.setId(idToUse);
            fromDB.setEmail(rs.getString(2));
            fromDB.setUserName(rs.getString(3));
            fromDB.setPassword(rs.getString(4));
            return fromDB;


        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }


    }
    public static void change(User user) {
        Scanner scan = new Scanner(System.in);
        System.out.println("What username would you like to set?");
        String username = scan.next();
        user.setUserName(username);
        System.out.println("What email would you like to set?");
        String email = scan.next();
        user.setEmail(email);
        System.out.println("What password would you like to set?");
        String password = scan.next();
        user.setPassword(password);
    }

    public static void update(User user) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(UPDATE_USER_QUERY);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, hashPassword(user.getPassword()));
            statement.setInt(4, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void delete(int userId) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement =
                     conn.prepareStatement(DELETE_QUERY);
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int findLength() {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(LENGTH_QUERY);
            ResultSet rs = statement.executeQuery();
            rs.next();
            int result = rs.getInt(1);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static void findAll() {
        User[] users = new User[findLength()];
        User u = new User();
        for (int i = 1; i < findLength()+1; i++) {
            u = UserDao.read(i);
            users[i-1] = u;
        }
        User majster = users[3];
        System.out.println(majster.getUserName());

    }

    public static int findId() {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(ID_USER_QUERY);
            ResultSet rs = statement.executeQuery();
            rs.next();
            int result = rs.getInt(1);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}


