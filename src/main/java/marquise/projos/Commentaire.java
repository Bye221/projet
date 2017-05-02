package marquise.projos;

import java.time.LocalDate;

public class Commentaire {
	
	private Integer id;
	private String commentaire;
	
	
	
	public Commentaire(Integer id, String commentaire) {
		super();
		this.id = id;
		this.commentaire = commentaire;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	
	
	
	
}
