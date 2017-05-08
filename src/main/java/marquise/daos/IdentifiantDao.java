package marquise.daos;
import java.util.List;

import marquise.projos.Identifiant;



public interface IdentifiantDao {
	
	public List<Identifiant> listIdentifiants();

	public Identifiant addIdentifiant(String login,	String motDePasse);

}
