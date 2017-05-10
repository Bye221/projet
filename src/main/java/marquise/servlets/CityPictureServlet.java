package marquise.servlets;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import marquise.services.InformationLibrary;



@WebServlet("/citypicture")
public class CityPictureServlet extends AbstractGenericServlet2 {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer cityId = Integer.parseInt(req.getParameter("id"));
		Path picturePath = InformationLibrary.getInstance().getPicturePatch(cityId);
		
		Files.copy(picturePath, resp.getOutputStream());
	}

	
}
