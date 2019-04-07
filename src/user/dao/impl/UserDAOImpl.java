package user.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import db.DBCon;
import user.dao.UserDAO;

public class UserDAOImpl implements UserDAO {
	private String insertUser = "insert into user_info " +
			" (ui_num,ui_name,ui_id,ui_pwd,ui_email) "+
			" values(seq_ui_num.nextval,?,?,?,?) ";
	private String selectUserById = "select * from user_info where ui_id=?";
	
	@Override
	public int insertUser(Map<String, String> user) {
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(insertUser);
			ps.setString(1, user.get("ui_name"));
			ps.setString(2, user.get("ui_id"));
			ps.setString(3, user.get("ui_pwd"));
			ps.setString(4, user.get("ui_email"));
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCon.close();
		}
		return 0;
	}

	@Override
	public Map<String, String> selectUserById(String uiId) {
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(selectUserById);
			ps.setString(1, uiId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Map<String,String> user = new HashMap<>();
				user.put("ui_num",rs.getString("ui_num"));
				user.put("ui_name",rs.getString("ui_name"));
				user.put("ui_id",rs.getString("ui_id"));
				user.put("ui_pwd",rs.getString("ui_pwd"));
				user.put("ui_email",rs.getString("ui_email"));
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCon.close();
		}
		return null;
	}
	
//	public static void main(String[] args) {
//		UserDAO udao = new UserDAOImpl();
//		Map<String,String> user = new HashMap<>();
//		user.put("ui_name","이초롱");
//		user.put("ui_id","chorong");
//		user.put("ui_pwd","chorong");
//		user.put("ui_email","chorong@naver.com");
//		System.out.println(udao.insertUser(user));
//		
//		System.out.println(udao.selectUserById("chorong"));
//	}
}
