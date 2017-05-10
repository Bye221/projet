package marquise.services;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.Part;

import marquise.daos.ArticleDao;
import marquise.daos.CityDao;
import marquise.daos.CommentaireDao;
import marquise.daos.InformationDao;
import marquise.daos.UtilisateurDao;
import marquise.daos.IdentifiantDao;
import marquise.daos.impl.ArticleDaoImpl;
import marquise.daos.impl.CommentaireDaoImpl;
import marquise.daos.impl.InformationDaoImpl;
import marquise.daos.impl.UtilisateurDaoImpl;
import marquise.daos.impl.IdentifiantDaoImpl;
import marquise.projos.Article;
import marquise.projos.City;
import marquise.projos.Commentaire;
import marquise.projos.Identifiant;
import marquise.projos.Information;
import marquise.projos.Utilisateur;
import marquise.projos.Identifiant;

public class InformationLibrary {
	
	
	private static class InformationLibraryHolder{
		private final static InformationLibrary instance = new InformationLibrary();
		
	}
	
	public static InformationLibrary getInstance(){
		return InformationLibraryHolder.instance;
	}
	
	private InformationDao informationDao = new InformationDaoImpl();
	private UtilisateurDao utilisateurDao = new UtilisateurDaoImpl();
	private CommentaireDao commentaireDao = new CommentaireDaoImpl();
	private ArticleDao articleDao = new ArticleDaoImpl();
	private IdentifiantDao identifiantDao = new IdentifiantDaoImpl();
	private CityDao cityDao = new CityDao();
	private static final String PICTURE_MAIN_DIRECTORY = "/Users/louiscauvray/Pictures";

	private InformationLibrary() {
	}

	public List<Information> listFilms() {
		return informationDao.listInformations();
	}

	public Information getInformation(Integer id) {
		return informationDao.getInformation(id);
	}

	public Information addInformation(Information information) {
		return informationDao.addInformation(information);
	}

	public List<Utilisateur> listUtilisateurs() {
		return utilisateurDao.listUtilisateurs();
	}

	public Utilisateur getUtilisateur(Integer id) {
		return utilisateurDao.getUtilisateur(id);
	}
	public Utilisateur getUtilisateurByNom(String nom){
		return utilisateurDao.getUtilisateurByNom(nom);
	}

	public Utilisateur addUtilisateur(String nom, String prenom) {
		return utilisateurDao.addUtilisateur(nom, prenom);
	}
	
	public List<Commentaire> listCommentaires(){
		return commentaireDao.listCommentaires();
	}

	public Commentaire addCommentaire(String email ,String commentaire){
		return commentaireDao.addCommentaire(email, commentaire);
	}

	public List<Article> listArticles(){
		return articleDao.listArticles();
		
	}

	public Article addArticle(String title, String texte, LocalDate datePublication, String auteur) {
		return articleDao.addArticle(title, texte, datePublication, auteur);
	}
	
	public Identifiant getIdentifiant(String login, String motDePasse){
		return identifiantDao.getIdentifiant(login, motDePasse);
		
	}
	
	public List<City> listAllCities() {
		
			return cityDao.listCities();
	}
	
	public City getCity(Integer id) {
		if(id == null) {
			throw new IllegalArgumentException("City id must be provided.");
		}
		return cityDao.getCity(id);
	}
	
	public void addCity(City newCity, Part picture) throws IOException {
		if(newCity == null){
			throw new IllegalArgumentException("A city must be provided.");
		}
		if(newCity.getName() == null || "".equals(newCity.getName())) {
			throw new IllegalArgumentException("A city must have a name.");
		}
		if(newCity.getSummary() == null || "".equals(newCity.getSummary())) {
			throw new IllegalArgumentException("A city must have a summary.");
		}
		if(picture == null){
			throw new IllegalArgumentException("A city must have a picture.");
		}
		
		Path picturePath = Paths.get(PICTURE_MAIN_DIRECTORY, picture.getSubmittedFileName());
		
		cityDao.addCity(newCity, picturePath.toString());
		
		
		Files.copy(picture.getInputStream(), picturePath);
		
		
	}
	
	public Path getPicturePatch(Integer cityId) {
		String picturePathString = cityDao.getPicturePath(cityId);
		if(picturePathString == null) {
			return getDefaultPicturePath();
		} else {
			Path picturePath = Paths.get(cityDao.getPicturePath(cityId));
			if(Files.exists(picturePath)) {
				return picturePath;
			} else {
				return getDefaultPicturePath();
			}
		}
		
	}
	
	private Path getDefaultPicturePath() {
		try {
			return Paths.get(this.getClass().getClassLoader().getResource("city-no-photo.png").toURI());
		} catch (URISyntaxException e) {
			return null;
		}
	}
}
