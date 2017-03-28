package com.capgemini.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dataaccess.dao.ProfileDao;
import com.capgemini.dataaccess.dao.UserDao;
import com.capgemini.service.UserService;
import com.capgemini.service.mapper.UserMapper;
import com.capgemini.service.to.UserTO;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	@Autowired
	ProfileDao profileDao;

	@Override
	public UserTO readUser(Long id) {
		return UserMapper.map(userDao.findOne(id));
	}

	@Override
	@Transactional(readOnly = false)
	public UserTO updateUser(UserTO user) {
		return UserMapper.map(userDao.update(UserMapper.map(user)));
	}

}
