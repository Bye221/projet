package marquise.servlets;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;


@WebServlet("/inscriptionAdmin")
public class inscriptionAdminServlet extends HttpServlet {



	

	/**
	 * 
	 */
	private static final long serialVersionUID = -911165023863333448L;

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
		
		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(req.getServletContext());
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setPrefix("/WEB-INF/templates/");
		templateResolver.setSuffix(".html");
		resp.setCharacterEncoding("UTF-8");
		
		TemplateEngine templateEngine = new TemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);
		
		WebContext context = new WebContext(req, resp, req.getServletContext());

		templateEngine.process("/admin/inscriptionAdmin", context, resp.getWriter());
	}
	
	

}
