package com.fashionstore.dao;

import java.util.List;

import com.fashionstore.model.Users;

public interface UsersDAO {

    boolean registerUser(Users user);

    Users loginUser(String email, String password);

    Users getUserById(int userId);

    Users getUserByEmail(String email);

    Users getUserByPhone(String phone);

    boolean updateUser(Users user);

    boolean updatePassword(int userId, String newPassword);

    boolean emailExists(String email);

    boolean phoneExists(String phone);

    List<Users> getAllUsers();
}
