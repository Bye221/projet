package marquise.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import marquise.projos.Article;
import marquise.projos.CommentaireArticle;
import marquise.services.InformationLibrary;


@WebServlet("/club")
public class clubServlet extends AbstractGenericServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		TemplateEngine templateEngine = this.createTemplateEngine(req);
		
		resp.setCharacterEncoding("UTF-8");
		
		
		WebContext context = new WebContext(req, resp, req.getServletContext());
		
		context.setVariable("articles", InformationLibrary.getInstance().listArticles());

		templateEngine.process("users/club", context, resp.getWriter());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String pseudo = req.getParameter("pseudo");
		String texte =req.getParameter("texte");
		
		CommentaireArticle newCommentaireArticle = new CommentaireArticle(null, texte, texte);
		CommentaireArticle addedCommentaireArticle =InformationLibrary.getInstance().addCommentaireArticle(pseudo, texte);
	
		resp.sendRedirect("club");
	}
	
	
	
	

}
