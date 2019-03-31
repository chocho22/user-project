package utils;

import javax.servlet.http.HttpServletRequest;

public class URICommand {
	public static String getCommand(HttpServletRequest req) {
		String rPath = req.getContextPath() + "/";
		String uri = req.getRequestURI();
		uri = uri.substring(rPath.length());
		return uri.substring(0, uri.indexOf("/"));
	}
}
