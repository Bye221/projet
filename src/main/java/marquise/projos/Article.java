package marquise.projos;

import java.time.LocalDate;

public class Article {
	
	private Integer id;
	private String title;
	private String texte;
	private LocalDate datePublication;
	private String auteur;
	
	



	public Article(Integer id,String title, String texte, LocalDate datePublication, String auteur) {
		super();
		this.id = id;
		this.title = title;
		this.texte = texte;
		this.datePublication = datePublication;
		this.auteur = auteur;
	}



	public String getAuteur() {
		return auteur;
	}



	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getTexte() {
		return texte;
	}



	public void setTexte(String texte) {
		this.texte = texte;
	}



	public LocalDate getDatePublication() {
		return datePublication;
	}



	public void setDatePublication(LocalDate datePublication) {
		this.datePublication = datePublication;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
