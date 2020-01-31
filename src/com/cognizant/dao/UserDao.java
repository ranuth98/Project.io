package com.cognizant.dao;

import java.util.List;

import com.cognizant.model.User;

public interface UserDao {
    // changed Dao functionalities
    public boolean createUser(User user) throws UserNameNotAvailableException;

    public User getUser(String userName);

    public User getUser(long userId);

    public List<User> getAllUsers();

    public boolean updateUser(User user);

}
