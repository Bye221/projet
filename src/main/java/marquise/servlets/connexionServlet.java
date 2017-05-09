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
	
	

	@Override
	public void init() throws ServletException {
		utilisateursAutorises = new HashMap<>();
		utilisateursAutorises.put("login1@hei.fr", "6bd107f44b7f9e87f3fcaa733c5c8f2205aa65e11494e2bf:676cd37b4e095adce9d717698ace6c080815a95e27c59ef1");
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
			}else{
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
