package marquise.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import marquise.projos.Article;
import marquise.projos.Utilisateur;
import marquise.services.InformationLibrary;

@WebServlet("/supprimerAdmin")
public class supprimerAdminServlet extends AbstractGenericServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4232466095821465892L;

	/**
	 * 
	 */
	
	

	

	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		HttpSession session=req.getSession(false);
		
		if(session != null){}
		else{
			resp.sendRedirect("connexion");
			out.println("Veuillez entre un mot de passe correct");
		}
		String confirm = req.getParameter("comfirm");
		String nom = req.getParameter("nom");
		Integer idUser = Integer.parseInt(req.getParameter("id"));
		
		if("true".equals(confirm)){
			
			InformationLibrary.getInstance().deleteInformationUtilisateur(idUser);
			resp.sendRedirect("admin");
		}else{
		
		TemplateEngine templateEngine = this.createTemplateEngine(req);
		
		WebContext context = new WebContext(req, resp, req.getServletContext());
		
		
		context.setVariable("nomUtil", InformationLibrary.getInstance().getInformationUtilisateurByName(nom));
		

		templateEngine.process("admin/supprimerAdmin", context, resp.getWriter());
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		/*nom = req.getParameter("nom");

		InformationLibrary.getInstance().getInformationUtilisateurByName(nom);*/
		
		
		resp.sendRedirect("admin");
		
	
	}
	
	


	
	

}
