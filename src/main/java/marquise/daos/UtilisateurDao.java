package marquise.daos;
import java.util.List;

import marquise.projos.Utilisateur;

public interface UtilisateurDao {
	
	public List<Utilisateur> listGenres();
	
	public Utilisateur getGenre(Integer id);

	public Utilisateur addGenre(String nom, String prenom);

}
