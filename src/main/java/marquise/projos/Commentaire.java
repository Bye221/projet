package marquise.projos;

public class Commentaire {
	
	private Integer id;
	private String email;
	private String commentaire;
	
	
	
	public Commentaire(Integer id, String email, String commentaire) {
		super();
		this.id = id;
		this.email = email;
		this.commentaire = commentaire;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	

	
	
	
}
