package modele;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import modele.utilisateurs.Utilisateur;

public class SessionManager {
	private static volatile HttpSession session;
	
	public static HttpSession getSession(HttpServletRequest request) {
		session = request.getSession(true);
		return session;
	}
	
	public static void startSession(HttpServletRequest request, Utilisateur user) {
		getSession(request);
		session.setAttribute("estConnecte", new Boolean(true));
		session.setAttribute("user", user);
	}
	
	public static void closeSession(HttpServletRequest request) {
		getSession(request);
		session.setAttribute("estConnecte", new Boolean(false));
		session.removeAttribute("user");
	}
	
	public static Boolean estConnecte(HttpServletRequest request) {
		getSession(request);
		if(session.getAttribute("estConnecte") != null)
			if(session.getAttribute("user") != null)
				return (Boolean)session.getAttribute("estConnecte");
		return new Boolean(false);
	}
	
	public static Utilisateur getUtilisateur(HttpServletRequest request) {
		getSession(request);
		if(estConnecte(request))
			if(session.getAttribute("user") != null)
				return (Utilisateur)session.getAttribute("user");
		return null;
	}
}
