package utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Command {
	private static final String RESULT_PATH = "/msg/result.jsp";
//	private static final Gson JSON = new Gson();

	public static String getCmd(HttpServletRequest request) throws ServletException {
		String uri = request.getRequestURI();
		String cmd = request.getParameter("cmd");
		if (cmd != null) {
			return cmd;
		}
		int idx = uri.lastIndexOf("/");
		if (idx == 0) {
			throw new ServletException("올바르지 않은 요청입니다.");
		}
		return uri.substring(idx+1);
	}

	public static void goResultPage(HttpServletRequest request,
			HttpServletResponse response, String url, String msg) 
					throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(RESULT_PATH);
		request.setAttribute("url", url);
		request.setAttribute("msg", msg);
		rd.forward(request,response);
	}
	
	public static void goPage(HttpServletRequest request, 
			HttpServletResponse response, String url) 
					throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}
	
	public static Map<String,String> getSingleMap(HttpServletRequest request) {
		Map<String,String> pMap = new HashMap<>();
		Map<String,String[]> map = request.getParameterMap();
		Iterator<String> it = map.keySet().iterator();
		while(it.hasNext()) {
			String key = it.next();
			String value = map.get(key)[0];
			pMap.put(key,value);
		}
		return pMap;
	}
	
	public static <T> void printJSON(HttpServletResponse response, T obj) throws IOException {
		response.setContentType("application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
//		pw.print(JSON.toJson(obj));
	}
}
