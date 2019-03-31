package user.service.impl;

import java.util.Map;

import user.dao.UserDAO;
import user.dao.impl.UserDAOImpl;
import user.service.UserService;

public class UserServiceImpl implements UserService {
	private UserDAO udao = new UserDAOImpl();
	
	@Override
	public int insertUser(Map<String, String> user) {
		return udao.insertUser(user);
	}

	@Override
	public Map<String, String> login(String uiId) {
		return udao.selectUserById(uiId);
	}

}
