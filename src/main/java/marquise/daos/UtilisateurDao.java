package marquise.daos;
import java.util.List;

import marquise.projos.Utilisateur;

public interface UtilisateurDao {
	
	public List<Utilisateur> listUtilisateurs();
	
	public Utilisateur getUtilisateur(Integer id);

	public Utilisateur addUtilisateur(String nom, String prenom);

}
