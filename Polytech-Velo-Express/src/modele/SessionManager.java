package modele;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import modele.utilisateurs.Utilisateur;

public class SessionManager {
	private static HttpSession session;
	
	public static HttpSession getSession(HttpServletRequest request) {
		if(session == null)
			session = request.getSession(true);
		return session;
	}
	
	public static void startSession(HttpServletRequest request, Utilisateur user) {
		getSession(request);
		session.setAttribute("user", user);
	}
	
	public static void closeSession(HttpServletRequest request) {
		getSession(request);
		session.invalidate();
	}
}
