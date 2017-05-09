package marquise.daos;
import java.util.List;

import marquise.projos.Utilisateur;

public interface UtilisateurDao {
	
	public List<Utilisateur> listUtilisateurs();
	
	public Utilisateur getUtilisateur(Integer id);
	
	public Utilisateur getUtilisateurByNom(String nom);

	public Utilisateur addUtilisateur(String nom, String prenom);
	
	public void deleteUtilisateur(String nom, String prenom);

}
