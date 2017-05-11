package marquise.services;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.Part;

import marquise.daos.ArticleDao;
import marquise.daos.CommentaireArticleDao;
import marquise.daos.ImageDao;
import marquise.daos.CommentaireDao;
import marquise.daos.ElementsSiteDao;
import marquise.daos.InformationDao;
import marquise.daos.UtilisateurDao;
import marquise.daos.IdentifiantDao;
import marquise.daos.impl.ArticleDaoImpl;
import marquise.daos.impl.CommentaireArticleDaoImpl;
import marquise.daos.impl.CommentaireDaoImpl;
import marquise.daos.impl.InformationDaoImpl;
import marquise.daos.impl.UtilisateurDaoImpl;
import marquise.daos.impl.IdentifiantDaoImpl;
import marquise.projos.Article;
import marquise.projos.Image;
import marquise.projos.Commentaire;
import marquise.projos.CommentaireArticle;
import marquise.projos.ElementsSite;
import marquise.projos.Identifiant;
import marquise.projos.Information;
import marquise.projos.Utilisateur;
import marquise.projos.Identifiant;

public class InformationLibrary {
	
	// Library qui permet d'exploiter les methodes dans les sevlets et d'afficher les données avec thymeleaf
	private static class InformationLibraryHolder{
		private final static InformationLibrary instance = new InformationLibrary();
		
	}
	
	public static InformationLibrary getInstance(){
		return InformationLibraryHolder.instance;
	}
	// Definition de toutes les methodes utilisées ici 
	
	private InformationDao informationDao = new InformationDaoImpl();
	private UtilisateurDao utilisateurDao = new UtilisateurDaoImpl();
	private CommentaireDao commentaireDao = new CommentaireDaoImpl();
	private ArticleDao articleDao = new ArticleDaoImpl();
	private IdentifiantDao identifiantDao = new IdentifiantDaoImpl();
	private ImageDao imageDao = new ImageDao();
	private static final String PICTURE_MAIN_DIRECTORY = "/Users/louiscauvray/git/projet/src/main/resources";
	private ElementsSiteDao elementsSiteDao = new ElementsSiteDao();
	private CommentaireArticleDao commentaireArticleDao = new  CommentaireArticleDaoImpl();
	

	private InformationLibrary() {
	}

	public List<Information> listInformations() {
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

	
	//Methode qui permet de voir tout les commentaires des articles du site
	public List<CommentaireArticle> listCommentairesArticles(){
		return commentaireArticleDao.listCommentairesArticles();
		
	}
	
	//Methode qui permet d'ajouter un commentaire
	public CommentaireArticle addCommentaireArticle(String pseudo, String commentaire){
		return commentaireArticleDao.addCommentaireArticle(pseudo, commentaire);
		
	}
	
	
	//Methode pour supprimer un commentaire
	
	public void deleteCommentaireArticle(int id) {
		commentaireArticleDao.deleteCommentaireArticle(id);
	}
	
	//Methode pour appeler les image et les chemins des images
	
	public List<Image> listAllImages() {
		
			return imageDao.listImages();
	}
	
	public Image getImage(Integer id) {
		if(id == null) {
			throw new IllegalArgumentException("Image id must be provided.");
		}
		return imageDao.getImage(id);
	}
	
	public void addImage(Image newImage, InputStream is) throws IOException {
		if(newImage == null){
			throw new IllegalArgumentException("An image must be provided.");
		}
		if(newImage.getName() == null || "".equals(newImage.getName())) {
			throw new IllegalArgumentException("An image must have a name.");
		}
		if(newImage.getSummary() == null || "".equals(newImage.getSummary())) {
			throw new IllegalArgumentException("An image must have a summary.");
		}
		if(is == null){
			throw new IllegalArgumentException("An image must contain a picture.");
		}
		
		//Path picturePath = Paths.get(PICTURE_MAIN_DIRECTORY, picture.getSubmittedFileName());
		
		imageDao.addImage(newImage, is);
		
		
		//Files.copy(picture.getInputStream(), picturePath);
		
		
	}
	
	public Path getPicturePatch(Integer imageId) {
		String picturePathString = imageDao.getPicturePath(imageId);
		if(picturePathString == null) {
			return getDefaultPicturePath();
		} else {
			Path picturePath = Paths.get(imageDao.getPicturePath(imageId));
			if(Files.exists(picturePath)) {
				return picturePath;
			} else {
				return getDefaultPicturePath();
			}
		}
		
	}
	
	public InputStream getPicture(Integer i){
		InputStream is =  imageDao.getPicture(i);
		try {
			if(is == null || is.available() == 0){
				return getDefaultPicture();
			}else {
				return is;
			}
		} catch (IOException e) {
			return getDefaultPicture();
		}
	}
	
	private InputStream getDefaultPicture() {
		return this.getClass().getClassLoader().getResourceAsStream("city-no-photo.png");
	}

	private Path getDefaultPicturePath() {
		try {
			return Paths.get(this.getClass().getClassLoader().getResource("city-no-photo.png").toURI());
		} catch (URISyntaxException e) {
			return null;
		}
	}
	
	// ElementsSite Dao
		public void modifierElementTexte(String idElement, String contenuElement) {
			elementsSiteDao.modifierElementTexte(idElement, contenuElement);
		}
		
		public void modifierElementImage(String idElement, String contenuElement, String cheminElement) {
			elementsSiteDao.modifierElementImage(idElement, contenuElement, cheminElement);
		}
		
		public ElementsSite getElementById(String id) {
			return elementsSiteDao.getElementById(id) ;
		}
}
