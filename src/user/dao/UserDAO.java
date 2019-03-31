package user.dao;

import java.util.Map;

public interface UserDAO {
	public int insertUser(Map<String,String> user);
	public Map<String,String> selectUserById(String uiId);
}
