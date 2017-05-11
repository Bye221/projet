package marquise.daos;
import java.util.List;

import marquise.projos.Commentaire;
import marquise.projos.Utilisateur;

public interface CommentaireDao {
	
	
	//Methode qui permet de voir tout les commentaire du site
	public List<Commentaire> listCommentaires();
	//Methode qui permet d'ajouter un commentaire
	public Commentaire addCommentaire(String email, String commentaire);

}
