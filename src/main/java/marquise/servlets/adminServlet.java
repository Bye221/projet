package marquise.servlets;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import marquise.projos.Utilisateur;
import marquise.services.InformationLibrary;

@WebServlet("/admin")
public class adminServlet extends AbstractGenericServlet {
	
	private int idRecherche;
	private int idInformation;

	/**
	 * 
	 */
	private static final long serialVersionUID = -4835451077585731550L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("UTF-8");
		TemplateEngine templateEngine = this.createTemplateEngine(req);
		
		WebContext context = new WebContext(req, resp, req.getServletContext());
		
		context.setVariable("utilisateurs", InformationLibrary.getInstance().listUtilisateurs());
		
		
		if(idRecherche != 0){
			context.setVariable("idUtils", InformationLibrary.getInstance().getUtilisateur(idRecherche));
		}
		//else
		//if(idInformation != 0){
		//	context.setVariable("idInfos", InformationLibrary.getInstance().getInformation(idInformation));
		//}

		templateEngine.process("admin/admin", context, resp.getWriter());
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		idRecherche = Integer.parseInt(req.getParameter("identifiant"));
		//idInformation = Integer.parseInt(req.getParameter("identifiantInformation"));
		
		resp.sendRedirect("admin");
	
	}
	
	


	
	

}
