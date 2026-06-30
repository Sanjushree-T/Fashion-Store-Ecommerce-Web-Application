package com.fashionstore.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fashionstore.dao.UsersDAO;
import com.fashionstore.model.Users;
import com.fashionstore.util.DBConnection;

public class UsersDAOImpl implements UsersDAO {

    private Connection connection;

    // REGISTER USER
    private static final String INSERT_USER_SQL = """
            INSERT INTO users
            (full_name, email, phone, password,
             address1, address2, city, state,
             pincode, country)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;

    // LOGIN USER
    private static final String LOGIN_USER_SQL = """
            SELECT * FROM users
            WHERE email = ? AND password = ?
            """;

    // CHECK EMAIL EXISTS
    private static final String CHECK_EMAIL_SQL = """
            SELECT * FROM users
            WHERE email = ?
            """;

    // GET USER BY ID
    private static final String GET_USER_BY_ID_SQL = """
            SELECT * FROM users
            WHERE user_id = ?
            """;

    // GET ALL USERS
    private static final String GET_ALL_USERS_SQL = """
            SELECT * FROM users
            """;

    // UPDATE USER
    private static final String UPDATE_USER_SQL = """
            UPDATE users
            SET full_name = ?,
                phone = ?,
                address1 = ?,
                address2 = ?,
                city = ?,
                state = ?,
                pincode = ?,
                country = ?
            WHERE user_id = ?
            """;

    // DELETE USER
    private static final String DELETE_USER_SQL = """
            DELETE FROM users
            WHERE user_id = ?
            """;

    public UsersDAOImpl() {

        // DATABASE CONNECTION
        // DATABASE NAME = fashion
        connection = DBConnection.getConnection();
    }

    // COMMON METHOD
    private Users extractUserFromResultSet(ResultSet rs) throws Exception {

        Users user = new Users();

        user.setUserId(rs.getInt("user_id"));
        user.setFullName(rs.getString("full_name"));
        user.setEmail(rs.getString("email"));
        user.setPhone(rs.getString("phone"));
        user.setPassword(rs.getString("password"));
        user.setAddress1(rs.getString("address1"));
        user.setAddress2(rs.getString("address2"));
        user.setCity(rs.getString("city"));
        user.setState(rs.getString("state"));
        user.setPincode(rs.getString("pincode"));
        user.setCountry(rs.getString("country"));
        user.setCreatedAt(rs.getTimestamp("created_at"));

        return user;
    }

    // REGISTER USER
    @Override
    public boolean registerUser(Users user) {

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(INSERT_USER_SQL);

            pstmt.setString(1, user.getFullName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPhone());
            pstmt.setString(4, user.getPassword());
            pstmt.setString(5, user.getAddress1());
            pstmt.setString(6, user.getAddress2());
            pstmt.setString(7, user.getCity());
            pstmt.setString(8, user.getState());
            pstmt.setString(9, user.getPincode());
            pstmt.setString(10, user.getCountry());

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // LOGIN USER
    @Override
    public Users loginUser(String email, String password) {

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(LOGIN_USER_SQL);

            pstmt.setString(1, email);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return extractUserFromResultSet(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // CHECK EMAIL EXISTS
    @Override
    public boolean emailExists(String email) {

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(CHECK_EMAIL_SQL);

            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();

            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // GET USER BY ID
    @Override
    public Users getUserById(int userId) {

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(GET_USER_BY_ID_SQL);

            pstmt.setInt(1, userId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return extractUserFromResultSet(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // GET ALL USERS
    @Override
    public List<Users> getAllUsers() {

        List<Users> usersList = new ArrayList<>();

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(GET_ALL_USERS_SQL);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                usersList.add(extractUserFromResultSet(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return usersList;
    }

    // UPDATE USER
    @Override
    public boolean updateUser(Users user) {

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(UPDATE_USER_SQL);

            pstmt.setString(1, user.getFullName());
            pstmt.setString(2, user.getPhone());
            pstmt.setString(3, user.getAddress1());
            pstmt.setString(4, user.getAddress2());
            pstmt.setString(5, user.getCity());
            pstmt.setString(6, user.getState());
            pstmt.setString(7, user.getPincode());
            pstmt.setString(8, user.getCountry());
            pstmt.setInt(9, user.getUserId());

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // DELETE USER
    public boolean deleteUser(int userId) {

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(DELETE_USER_SQL);

            pstmt.setInt(1, userId);

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

	@Override
	public Users getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Users getUserByPhone(String phone) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updatePassword(int userId, String newPassword) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean phoneExists(String phone) {
		// TODO Auto-generated method stub
		return false;
	}
}