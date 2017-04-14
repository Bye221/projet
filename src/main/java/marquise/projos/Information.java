package marquise.projos;

import java.time.LocalDate;

public class Information {
	
	private Integer id;
	private String sexe;
	private LocalDate dateNaissance;
	private Utilisateur utilisateur;
	private Integer tarif;
	private String numSecu;
	private String adresse;
	
	
	public Information(Integer id, String sexe, LocalDate dateNaissance, Utilisateur utilisateur, Integer tarif, String numSecu,
			String adresse) {
		super();
		this.id = id;
		this.sexe = sexe;
		this.dateNaissance = dateNaissance;
		this.utilisateur = utilisateur;
		this.tarif = tarif;
		this.numSecu = numSecu;
		this.adresse = adresse;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getSexe() {
		return sexe;
	}


	public void setSexe(String sexe) {
		this.sexe = sexe;
	}


	public LocalDate getDateNaissance() {
		return dateNaissance;
	}


	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
	}


	public Utilisateur getUtilisateur() {
		return utilisateur;
	}


	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
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
	
	
	
	
	
	
	

}
