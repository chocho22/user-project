package movie.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DBCon;
import movie.dao.MovieDAO;

public class MovieDAOImpl implements MovieDAO {
	private static final String SELECT_MOVIE_LIST = "select * from movie_info";
	private static final String SELECT_MOVIE_BY_NUM = "select * from movie_info where mi_num=?";
	private static final String INSERT_MOVIE = "insert into movie_info( " +
			" mi_num,mi_name,mi_year,mi_national,mi_vendor,mi_director) " +
			" values(seq_mi_num.nextval,?,?,?,?,?) ";
	
	public List<Map<String,String>> selectMovieList() {
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(SELECT_MOVIE_LIST);
			ResultSet rs = ps.executeQuery();
			List<Map<String,String>> movieList = new ArrayList<>();
			while(rs.next()) {
				Map<String,String> movie = new HashMap<>();
				movie.put("miNum", rs.getString("mi_num"));
				movie.put("miName", rs.getString("mi_name"));
				movie.put("miYear", rs.getString("mi_year"));
				movie.put("miNational", rs.getString("mi_national"));
				movie.put("miVendor", rs.getString("mi_vendor"));
				movie.put("miDirector", rs.getString("mi_director"));
				movieList.add(movie);
			}
			return movieList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCon.close();
		}
		return null;
	}

	@Override
	public Map<String, String> selectMovieByNum(int miNum) {
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(SELECT_MOVIE_BY_NUM);
			ps.setInt(1, miNum);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Map<String,String> movie = new HashMap<>();
				movie.put("miNum",rs.getString("mi_num"));
				movie.put("miName",rs.getString("mi_name"));
				movie.put("miYear",rs.getString("mi_year"));
				movie.put("miNational",rs.getString("mi_national"));
				movie.put("miVendor",rs.getString("mi_vendor"));
				movie.put("miDirector",rs.getString("mi_director"));
				return movie;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCon.close();
		}
		return null;
	}

	@Override
	public int insertMovie(Map<String, String> movie) {
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(INSERT_MOVIE);
			ps.setString(1, movie.get("miName"));
			ps.setString(2, movie.get("miYear"));
			ps.setString(3, movie.get("miNational"));
			ps.setString(4, movie.get("miVendor"));
			ps.setString(5, movie.get("miDirector"));
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCon.close();
		}
		return 0;
	}
}
