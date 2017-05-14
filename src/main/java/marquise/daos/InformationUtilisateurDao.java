package marquise.daos;

import java.time.LocalDate;
import java.util.List;

import marquise.projos.InformationUtilisateur;



public interface InformationUtilisateurDao {
	//Liste des tout les utilisateurs et leurs informations, test OK
	public List<InformationUtilisateur> listInformationsUtilisateurs();
	
	// Retourne les informations d'un utilisateur via son id, test OK
	public InformationUtilisateur getInformationUtilisateurById(Integer id);
	
	
	// Retourne les informations d'un utilisateur via son nom, test OK
	public InformationUtilisateur getInformationUtilisateurByName(String nom);
	
	//Ajoute un utilsateur et des informations
	public InformationUtilisateur addInformationUtilisateur(
			
			String nom,
			String prenom,
			String sexe,
			LocalDate date,
			Integer tarif,
			String numSecu,
			String adresse);
	
	//Supprime un utilisateur et toute ses information
	public void deleteInformationUtilisateur(Integer id);
	
	//Met à jour un utilisateur et ses informations
	public InformationUtilisateur updateInformationUtilisateur(String nom, String prenom);
	

}
