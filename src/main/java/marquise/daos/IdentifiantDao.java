package marquise.daos;
import java.util.List;

import marquise.projos.Identifiant;



public interface IdentifiantDao {
	
	public List<Identifiant> listIdentifiants(); // Liste des identifiant en base de donnée

	public Identifiant addIdentifiant(String login,	String motDePasse);
	
	public Identifiant getIdentifiant(String login, String motDePasse);

}
