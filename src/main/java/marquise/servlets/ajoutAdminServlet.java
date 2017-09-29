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

import marquise.services.InformationLibrary;

@WebServlet("/ajoutAdmin")
public class ajoutAdminServlet extends AbstractGenericServlet {


	
	private static final long serialVersionUID = -510595116912171226L;

	
	Integer tarif;

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
		
		templateEngine.process("admin/ajoutAdmin", context, resp.getWriter());
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		tarif = Integer.parseInt(req.getParameter("tarif"));
		String releaseDateAsString = req.getParameter("releaseDate");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate releaseDate = LocalDate.parse(releaseDateAsString, formatter);
		String nom = req.getParameter("nom");
		String prenom = req.getParameter("prenom");
		String adresse = req.getParameter("adresse");
		String sexe = req.getParameter("sexe");
		String numSecu = req.getParameter("numSecu");
		InformationLibrary.getInstance().addInformationUtilisateur(nom, prenom, sexe, releaseDate, tarif, numSecu, adresse);
	
		resp.sendRedirect("admin");
	
	}
	
	


	
	

}
