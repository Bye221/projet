package marquise.daos;

import java.util.List;
import marquise.projos.Utilisateur;

public interface UtilisateurDao {
	
	
	
	public List<Utilisateur> listUtilisateurs();
	
	public Utilisateur getUtilisateur(String nom);
	
	public Utilisateur addUtilisateur(String nom);

}
