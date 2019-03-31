package user.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.service.UserService;
import user.service.impl.UserServiceImpl;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService us = new UserServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String cmd = request.getParameter("cmd");
		if ("insert".equals(cmd)) {
			String uiId = request.getParameter("ui_id");
			String uiPwd = request.getParameter("ui_pwd");
			String uiName = request.getParameter("ui_name");
			String uiEmail = request.getParameter("ui_email");
			Map<String, String> user = new HashMap<>();
			user.put("ui_id", uiId);
			user.put("ui_pwd", uiPwd);
			user.put("ui_name", uiName);
			user.put("ui_email", uiEmail);
			if (us.insertUser(user) == 1) {
				request.setAttribute("msg", "가입 완료");
				request.setAttribute("uri", "/user/login.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("/user/join_ok.jsp");
				rd.forward(request, response);
			}
		} else if ("login".equals(cmd)) {
			String uiId = request.getParameter("ui_id");
			String uiPwd = request.getParameter("ui_pwd");
			Map<String, String> user = us.login(uiId);
			if (user != null) {
				if(user.get("ui_pwd")==uiPwd) {
					HttpSession session = request.getSession();
					session.setAttribute("logonUser", user);
					response.sendRedirect("/movie/list.jsp");
					return;
				} else {
					request.setAttribute("msg", "비밀번호를 다시 확인해주세요.");
					request.setAttribute("uri", "/user/login.jsp");
					RequestDispatcher rd = request.getRequestDispatcher("/user/login_den.jsp");
					rd.forward(request, response);
				}
			} else {
				request.setAttribute("msg", "아이디나 비밀번호를 다시 확인해주세요.");
				request.setAttribute("uri", "/user/login.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("/user/login_den.jsp");
				rd.forward(request, response);
			}
		}
	}
}
