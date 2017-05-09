package marquise.services;
import java.time.LocalDate;
import java.util.List;

import marquise.daos.ArticleDao;
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
	
}
