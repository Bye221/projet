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

@WebServlet("/connexion")
public class connexionServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6247300284021568743L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(req.getServletContext());	
	templateResolver.setTemplateMode(TemplateMode.HTML);
	templateResolver.setPrefix("/WEB-INF/templates/users/");
	templateResolver.setSuffix(".html");
	
	TemplateEngine templateEngine = new TemplateEngine();
	templateEngine.setTemplateResolver(templateResolver);
	
	
	
	WebContext context = new WebContext(req, resp, req.getServletContext());
	
	
	
	templateEngine.process("connexion", context, resp.getWriter());
	
		
	}
	
	

}
