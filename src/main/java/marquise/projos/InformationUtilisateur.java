package marquise.projos;

import java.time.LocalDate;

public class InformationUtilisateur {
	
	private Integer id;
	private String nom;
	private String prenom;
	private String sexe;
	private LocalDate date;
	private Integer tarif;
	private String numSecu;
	private String adresse;
	
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getSexe() {
		return sexe;
	}


	public void setSexe(String sexe) {
		this.sexe = sexe;
	}


	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}


	public Integer getTarif() {
		return tarif;
	}


	public void setTarif(Integer tarif) {
		this.tarif = tarif;
	}


	public String getNumSecu() {
		return numSecu;
	}


	public void setNumSecu(String numSecu) {
		this.numSecu = numSecu;
	}


	public String getAdresse() {
		return adresse;
	}


	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}


	public InformationUtilisateur(Integer id, String nom, String prenom, String sexe, LocalDate date, Integer tarif,
			String numSecu, String adresse) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.sexe = sexe;
		this.date = date;
		this.tarif = tarif;
		this.numSecu = numSecu;
		this.adresse = adresse;
	}
	
	

}
