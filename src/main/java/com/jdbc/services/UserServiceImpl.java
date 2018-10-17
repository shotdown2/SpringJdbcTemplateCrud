package com.jdbc.services;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdbc.dao.UserDao;
import com.jdbc.models.User;

@Service
@WebService
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@WebMethod(operationName = "allUsersList")
	@WebResult(name = "item")
	public List<User> listAll() {
		return userDao.listAll();
	}

	public void insert(User user) {
		userDao.insert(user);
	}

	public void update(User user) {
		userDao.update(user);
	}

	public void delete(int id) {
		userDao.delete(id);
	}

	public User findById(int id) {
		return userDao.findById(id);
	}

}
