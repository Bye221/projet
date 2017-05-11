package marquise.projos;

import java.time.LocalDate;

public class CommentaireArticle {
	
	private Integer id;
	private String pseudo;
	private String commentaire;
	
	
	
	public CommentaireArticle(Integer id, String pseudo, String commentaire) {
		super();
		this.id = id;
		this.pseudo = pseudo;
		this.commentaire = commentaire;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	

	
	
	
}
