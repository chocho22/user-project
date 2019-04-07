package movie.service.impl;

import java.util.List;
import java.util.Map;

import movie.dao.MovieDAO;
import movie.dao.impl.MovieDAOImpl;
import movie.service.MovieService;

public class MovieServiceImpl implements MovieService {
	private MovieDAO mdao = new MovieDAOImpl();
	
	@Override
	public List<Map<String, String>> selectMovieList() {
		return mdao.selectMovieList();
	}

	@Override
	public Map<String, String> selectMovieByNum(int miNum) {
		return mdao.selectMovieByNum(miNum);
	}

	@Override
	public int insertMovie(Map<String, String> movie) {
		return mdao.insertMovie(movie);
	}
}
