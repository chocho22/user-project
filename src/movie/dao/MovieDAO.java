package movie.dao;

import java.util.List;
import java.util.Map;

public interface MovieDAO {
	public List<Map<String,String>> selectMovieList();
	public Map<String, String> selectMovieByNum(int miNum);
	public int insertMovie(Map<String, String> movie);
}
