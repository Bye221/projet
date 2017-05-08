package marquise.projos;

public class Identifiant {
	
	int id;
	String login;
	String motDePasse;
	
	
	public Identifiant(String login, String motDePasse) {
		super();
		this.id = id;
		this.login = login;
		this.motDePasse = motDePasse;
		
		
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id=id;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getMotDePasse() {
		return motDePasse;
	}
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	
	
	
	

}
