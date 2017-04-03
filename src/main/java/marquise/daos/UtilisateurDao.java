package marquise.daos;

import java.util.List;
import marquise.projos.Utilisateur;

public interface UtilisateurDao {
	
	
	
	public List<Utilisateur> listeUtilisateurs();
	
	public Utilisateur getUtilisateur(String nom);
	
	public Utilisateur addUtilisateur(Integer id, String nom, String Prenom);

}
