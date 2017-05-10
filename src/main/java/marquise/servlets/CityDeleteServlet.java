package marquise.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import marquise.services.InformationLibrary;



@WebServlet("/deletecity")
public class CityDeleteServlet extends AbstractGenericServlet2 {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String confirm = req.getParameter("confirm");
		Integer idCity = Integer.parseInt(req.getParameter("id"));
		////	InformationLibrary.getInstance().deleteCity(idCity);
			resp.sendRedirect("home2");
		//} else {
		//	TemplateEngine templateEngine = this.createTemplateEngine(req);
			
			//context.setVariable("city", InformationLibrary.getInstance().getCity(idCity));
			
			//templateEngine.process("confirmdelete", context, resp.getWriter());
		//}
	}

	
}
