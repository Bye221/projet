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

@WebServlet("/clubAdmin")
public class clubAdminServlet extends AbstractGenericServlet {
	
	private int idRecherche;
	private int idInformation;

	/**
	 * 
	 */
	private static final long serialVersionUID = -4835451077585731550L;

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
		
		

		templateEngine.process("admin/clubAdmin", context, resp.getWriter());
		resp.setCharacterEncoding("UTF-8");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String texte = req.getParameter("texte");
		String auteur =req.getParameter("auteur");
		
		String releaseDateAsString = req.getParameter("releaseDate");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate releaseDate = LocalDate.parse(releaseDateAsString, formatter);
		String title = req.getParameter("title");
		Article newArticle = new Article(null, title, texte, null, auteur);
		Article addedArticle = InformationLibrary.getInstance().addArticle(title, texte , releaseDate, auteur);
		
		
		
		resp.sendRedirect("clubAdmin");
	
	}
	
	


	
	

}
