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

@WebServlet("/admin")
public class adminServlet2 extends AbstractGenericServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1130146518940644874L;
	private int idRecherche;
	private int idInformation;
	private int idUser;
	private String nom;

	

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
		
		TemplateEngine templateEngine = this.createTemplateEngine(req);
		
		WebContext context = new WebContext(req, resp, req.getServletContext());
		
		context.setVariable("utilisateurs", InformationLibrary.getInstance().listInformationsUtilisateurs());
		
		
		context.setVariable("nom", InformationLibrary.getInstance().getInformationUtilisateurByName(nom));
		
		if(idRecherche != 0){
			
			context.setVariable("idUtils", InformationLibrary.getInstance().getUtilisateur(idRecherche));
		}

		templateEngine.process("admin/admin", context, resp.getWriter());
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		nom = req.getParameter("nom");

		InformationLibrary.getInstance().getInformationUtilisateurByName(nom);
		
		
		resp.sendRedirect("admin");
		
	
	}
	
	


	
	

}
