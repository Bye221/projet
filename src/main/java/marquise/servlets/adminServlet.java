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
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -4835451077585731550L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		TemplateEngine templateEngine = this.createTemplateEngine(req);
		
		WebContext context = new WebContext(req, resp, req.getServletContext());
		
		context.setVariable("utilisateurs", InformationLibrary.getInstance().listUtilisateurs());
		Integer idUtilisateur = (Integer) req.getSession().getAttribute("idUtilisat");
		if(idUtilisateur != null){
			context.setVariable("idUtil", InformationLibrary.getInstance().getUtilisateur(idUtilisateur));
		}

		templateEngine.process("admin/admin", context, resp.getWriter());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getSession().setAttribute("idUtilisat", req.getParameter("identifiant"));
		resp.sendRedirect("admin");
	
	}
	
	


	
	

}
