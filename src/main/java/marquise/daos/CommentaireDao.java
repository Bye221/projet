package marquise.daos;
import java.util.List;

import marquise.projos.Commentaire;
import marquise.projos.Utilisateur;

public interface CommentaireDao {
	
	public List<Commentaire> listCommentaires();

	public Commentaire addCommentaire(String email, String commentaire);

}
