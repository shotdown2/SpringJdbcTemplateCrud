package com.jdbc.dao;

import java.util.List;

import com.jdbc.models.User;

public interface UserDao {

	public List<User> listAll();

	public void insert(User user);

	public void update(User user);

	public void delete(int id);

	public User findById(int id);

}
