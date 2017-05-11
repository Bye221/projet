package marquise.servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import marquise.mdpUtil.Util;

@WebServlet("/connexion")
public class connexionServlet extends AbstractGenericServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<String, String> utilisateursAutorises;

	private Map<String, String> visiteursAutorises;
	
	

	@Override
	public void init() throws ServletException {
		utilisateursAutorises = new HashMap<>();
		visiteursAutorises = new HashMap<>();
		utilisateursAutorises.put("login1@hei.fr", "221a94b764a91dd304666e469cbf5c9a8b18dc7ae1297cca:2392b5bdec3ceeac1d028e56bd7232f0c87312a5c2f88bff");
		visiteursAutorises.put("login2@hei.fr", "c3ad03c4e273eec0cdc803f39c80d992f7d5a813d0427d8c:e4078d426a2115d646d3a323e986ca2fec17e3705ed5f603");
		//utilisateursAutorises.put(récupérer login,mot de passe crypter)

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		TemplateEngine templateEngine = this.createTemplateEngine(req);
		
		resp.setCharacterEncoding("UTF-8");
		
		WebContext context = new WebContext(req, resp, req.getServletContext());

		templateEngine.process("users/connexion", context, resp.getWriter());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String identifiantSaisi = req.getParameter("identifiant");
		String motDePasseSaisi = req.getParameter("mdp");
		try {
			if(utilisateursAutorises.containsKey(identifiantSaisi) && Util.validerMotDePasse(motDePasseSaisi, utilisateursAutorises.get(identifiantSaisi))) {
				HttpSession session = req.getSession();
				session.setAttribute("name",identifiantSaisi );
                         resp.sendRedirect("admin");				
			}else if(visiteursAutorises.containsKey(identifiantSaisi) && Util.validerMotDePasse(motDePasseSaisi, visiteursAutorises.get(identifiantSaisi))){
				HttpSession session = req.getSession();
				session.setAttribute("name",identifiantSaisi );
                         resp.sendRedirect("addimage");	
			} else
			{
				resp.sendRedirect("connexion");
			}
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
	}


		
		//req.getSession().setAttribute("utilisateurConnecteLogin", req.getParameter("identifiant"));
		//req.getSession().setAttribute("utilisateurConnecteMdp", req.getParameter("mdp"));
		//resp.sendRedirect("/connexion");
	
	
	

}
