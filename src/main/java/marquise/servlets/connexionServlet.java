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
		utilisateursAutorises.put("login1@hei.fr", "56091ee47ac9eea454fef713a894cbea49223a8796cc3775:9c7d053b7afc496255c131591ff45a11d95ef23bf14b8b59");
		visiteursAutorises.put("login2@hei.fr", "ab439e9b72770c25d78562c8f0eda0cd0dd432e4df5c1ea3:762e1d76fd7b0f168cde2282f01d9a49693b0b112f535cda");
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
