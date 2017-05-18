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

@WebServlet("/updateAdmin")
public class updateAdminServlet extends AbstractGenericServlet {


	/**
	 * 
	 */
	private static final long serialVersionUID = -4835451077585731550L;
	Integer id;

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
		//id = Integer.parseInt(req.getParameter("id"));
		
		resp.setCharacterEncoding("UTF-8");

		templateEngine.process("admin/updateAdmin", context, resp.getWriter());
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Integer tarif;
		id = Integer.parseInt(req.getParameter("id"));
		tarif = Integer.parseInt(req.getParameter("prix"));
		String releaseDateAsString = req.getParameter("releaseDate");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate releaseDate = LocalDate.parse(releaseDateAsString, formatter);
		String nom = req.getParameter("nom");
		String prenom = req.getParameter("prenom");
		String adresse = req.getParameter("adresse");
		String sexe = req.getParameter("sexe");
		String numSecu = req.getParameter("numSecu");
		InformationLibrary.getInstance().updateInformationUtilisateur(id, nom, prenom, sexe, releaseDate, tarif, numSecu, adresse);
		
		resp.setCharacterEncoding("UTF-8");
		resp.sendRedirect("admin");
	
	}
	
	


	
	

}
