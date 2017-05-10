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

@WebServlet("/adminRechercheNom")
public class adminRechercheNomServlet extends AbstractGenericServlet {
	
	private int idRecherche;
	private int idInformation;
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -4835451077585731550L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		HttpSession session=req.getSession(false);
		resp.setCharacterEncoding("UTF-8");
		if(session != null){}
		else{
			resp.sendRedirect("connexion");
			out.println("Veuillez entre un mot de passe correct");
		}
		resp.setCharacterEncoding("UTF-8");
		TemplateEngine templateEngine = this.createTemplateEngine(req);
		
		WebContext context = new WebContext(req, resp, req.getServletContext());
		
		resp.setCharacterEncoding("UTF-8");
		
		
		
		context.setVariable("utilisateurs", InformationLibrary.getInstance().listUtilisateurs());
		
		resp.setCharacterEncoding("UTF-8");
		
		
		if(idRecherche != 0){
		
			context.setVariable("idUtils", InformationLibrary.getInstance().getUtilisateur(idRecherche));
		}

		templateEngine.process("admin/adminRechercheNom", context, resp.getWriter());
		resp.setCharacterEncoding("UTF-8");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		
		idRecherche = Integer.parseInt(req.getParameter("identifiant"));
		
		resp.setCharacterEncoding("UTF-8");
		resp.sendRedirect("adminRechercheNom");
	
	}
	
	


	
	

}
