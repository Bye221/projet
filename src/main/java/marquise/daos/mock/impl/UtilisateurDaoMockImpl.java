package marquise.daos.mock.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import marquise.daos.UtilisateurDao;
import marquise.projos.Utilisateur;

public class UtilisateurDaoMockImpl implements UtilisateurDao {
	
	private TreeMap<Integer, Utilisateur> utilisateursList;
	
	public UtilisateurDaoMockImpl(){
		utilisateursList = new TreeMap<Integer, Utilisateur>();
		utilisateursList.put(1, new Utilisateur(1, "Auvray", "Louis-Come"));
		utilisateursList.put(2, new Utilisateur(2, "Tamisier", "Axel"));
		utilisateursList.put(1, new Utilisateur(3, "Trump", "Donald"));
		
	}

	@Override
	public List<Utilisateur> listeUtilisateurs() {
		
		return new ArrayList<Utilisateur>(utilisateursList.values());
	}

	@Override
	public Utilisateur getUtilisateur(String nom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utilisateur addUtilisateur(Integer id, String nom, String Prenom) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
