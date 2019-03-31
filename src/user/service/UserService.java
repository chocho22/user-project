package user.service;

import java.util.Map;

public interface UserService {
	public int insertUser(Map<String,String> user);
	public Map<String,String> login(String uiId);
}
