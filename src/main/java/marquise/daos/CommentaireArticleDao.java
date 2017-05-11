package marquise.daos;
import java.util.List;

import marquise.projos.CommentaireArticle;
import marquise.projos.Utilisateur;

public interface CommentaireArticleDao {
	
	
	//Methode qui permet de voir tout les commentaires des articles du site
	public List<CommentaireArticle> listCommentairesArticles();
	//Methode qui permet d'ajouter un commentaire
	public CommentaireArticle addCommentaireArticle(String pseudo, String commentaire);
	//Methode pour supprimer un commentaire
	public void deleteCommentaireArticle(int id);

}
