package movie.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import movie.service.MovieService;
import movie.service.impl.MovieServiceImpl;
import utils.Command;

@WebServlet("/MovieServlet")
public class MovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MovieService ms = new MovieServiceImpl();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = Command.getCmd(request);
		if("list".equals(cmd)) {
			List<Map<String,String>> movieList = ms.selectMovieList();
			request.setAttribute("movieList", movieList);
			RequestDispatcher rd = request.getRequestDispatcher("/views/movie/list");
			rd.forward(request, response);
		} else {
			try {
				int miNum = Integer.parseInt(cmd);
				Map<String, String> selectMovie = ms.selectMovieByNum(miNum);
				request.setAttribute("selectMovie", selectMovie);
				RequestDispatcher rd = request.getRequestDispatcher("/views/movie/view");
				rd.forward(request, response);
			} catch(Exception e) {
				throw new ServletException("올바른 상세조회 값이 아닙니다.");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd = Command.getCmd(request);
		if("insert".equals(cmd)) {
			HttpSession hs = request.getSession();
			if(hs.getAttribute("user") == null) {
				Command.goResultPage(request, response, "/user/login.jsp", "로그인 하세요.");
				return;
			}
			Map<String,String> movie = Command.getSingleMap(request);
			String msg = "영화 등록 실패";
			String url = "/movie/list";
			if(ms.insertMovie(movie) == 1) {
				msg = "영화 등록 성공";
			}
			Command.goResultPage(request, response, url, msg);
		}
	}
}
