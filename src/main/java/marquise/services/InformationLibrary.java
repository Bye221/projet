package marquise.services;
import java.util.List;

import marquise.daos.CommentaireDao;
import marquise.daos.InformationDao;
import marquise.daos.UtilisateurDao;
import marquise.daos.impl.CommentaireDaoImpl;
import marquise.daos.impl.InformationDaoImpl;
import marquise.daos.impl.UtilisateurDaoImpl;
import marquise.projos.Commentaire;
import marquise.projos.Information;
import marquise.projos.Utilisateur;

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

	public Utilisateur addUtilisateur(String nom, String prenom) {
		return utilisateurDao.addUtilisateur(nom, prenom);
	}
	
	public List<Commentaire> listCommentaires(){
		return commentaireDao.listCommentaires();
	}

	public Commentaire addCommentaire(String commentaire){
		return commentaireDao.addCommentaire(commentaire);
	}
	
}
