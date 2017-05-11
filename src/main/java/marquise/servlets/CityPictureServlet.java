package marquise.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import marquise.services.InformationLibrary;



@WebServlet("/imagepicture")
public class CityPictureServlet extends AbstractGenericServlet2 {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer imageId = Integer.parseInt(req.getParameter("id"));
		//Path picturePath = InformationLibrary.getInstance().getPicturePatch(imageId);
		InputStream is = InformationLibrary.getInstance().getPicture(imageId);
		
		IOUtils.copy(is, resp.getOutputStream());
	}

	
}
