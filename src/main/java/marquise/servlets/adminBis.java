package marquise.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import marquise.services.InformationLibrary;


@WebServlet("/adminBis")
public class adminBis extends AbstractGenericServlet {
	
	int idRecherche;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		TemplateEngine templateEngine = this.createTemplateEngine(req);
		
		WebContext context = new WebContext(req, resp, req.getServletContext());
		
		resp.setCharacterEncoding("UTF-8");
		
		context.setVariable("utilisateurs", InformationLibrary.getInstance().listUtilisateurs());
		

		if(idRecherche != 0){
			context.setVariable("idUtils", InformationLibrary.getInstance().getUtilisateur(idRecherche));
		}

		templateEngine.process("admin/admin", context, resp.getWriter());
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		idRecherche = Integer.parseInt(req.getParameter("identifiant"));
		//idInformation = Integer.parseInt(req.getParameter("identifiantInformation"));
		
		resp.sendRedirect("admin");
	
	}
	
	

}
