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

@WebServlet("/adminazerty")
public class adminServlet extends AbstractGenericServlet {
	
	private int idRecherche;
	private int idInformation;
	private String nom;

	/**
	 * 
	 */
	private static final long serialVersionUID = -4835451077585731550L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
<<<<<<< HEAD
		
<<<<<<< HEAD
<<<<<<< HEAD
		resp.setCharacterEncoding("UTF-8");
=======
=======
=======
		resp.setCharacterEncoding("UTF-8");
>>>>>>> louis-come
		PrintWriter out = resp.getWriter();
>>>>>>> louis-come
		HttpSession session=req.getSession(false);
		
		if(session != null){}
		else{
			resp.sendRedirect("connexion");
			out.println("Veuillez entre un mot de passe correct");
		}
		
>>>>>>> louis-come
		TemplateEngine templateEngine = this.createTemplateEngine(req);
		
		WebContext context = new WebContext(req, resp, req.getServletContext());
		
		
		
		context.setVariable("utilisateurs", InformationLibrary.getInstance().listUtilisateurs());
		
		context.setVariable("idNoms", InformationLibrary.getInstance().getUtilisateurByNom(nom));
		
		
		//if(idRecherche != 0){
		
			context.setVariable("idUtils", InformationLibrary.getInstance().getUtilisateur(idRecherche));
		//}

		templateEngine.process("admin/admin", context, resp.getWriter());
		resp.setCharacterEncoding("UTF-8");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		
		
		if (idRecherche != 0){
			
			idRecherche = Integer.parseInt(req.getParameter("identifiant"));
			
		} else {
		
		if (nom != ""){
			
			
			nom = req.getParameter("nomUtilisateur");
		}}
		
		//idRecherche = Integer.parseInt(req.getParameter("identifiant"));
		
		
		resp.sendRedirect("admin");
	
	}
	
	


	
	

}
