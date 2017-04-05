package marquise.projos;

public class Information {
	

	
	private char numeroSecu;
	private Integer jours; 
	private Integer mois;
	private double annees;
	private double numRue;
	private char rue;
	private double codePostal;
	private char ville;
	private String complements;
	private Boolean cotisation;
	private Boolean certificat;
	private double tarif;
	private Integer utilisateurs_idUtilisateurs;
	
	
	
	public Information(char numeroSecu, Integer jours, Integer mois, double annees, double numRue, char rue,
			double codePostal, char ville, String complements, Boolean cotisation, Boolean certificat, double tarif,
			Integer utilisateurs_idUtilisateurs) {
		super();
		this.numeroSecu = numeroSecu;
		this.jours = jours;
		this.mois = mois;
		this.annees = annees;
		this.numRue = numRue;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.complements = complements;
		this.cotisation = cotisation;
		this.certificat = certificat;
		this.tarif = tarif;
		this.utilisateurs_idUtilisateurs = utilisateurs_idUtilisateurs;
	}
	public char getNumeroSecu() {
		return numeroSecu;
	}
	public void setNumeroSecu(char numeroSecu) {
		this.numeroSecu = numeroSecu;
	}
	public Integer getJours() {
		return jours;
	}
	public void setJours(Integer jours) {
		this.jours = jours;
	}
	public Integer getMois() {
		return mois;
	}
	public void setMois(Integer mois) {
		this.mois = mois;
	}
	public double getAnnees() {
		return annees;
	}
	public void setAnnees(double annees) {
		this.annees = annees;
	}
	public double getNumRue() {
		return numRue;
	}
	public void setNumRue(double numRue) {
		this.numRue = numRue;
	}
	public char getRue() {
		return rue;
	}
	public void setRue(char rue) {
		this.rue = rue;
	}
	public double getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(double codePostal) {
		this.codePostal = codePostal;
	}
	public char getVille() {
		return ville;
	}
	public void setVille(char ville) {
		this.ville = ville;
	}
	public String getComplements() {
		return complements;
	}
	public void setComplements(String complements) {
		this.complements = complements;
	}
	public Boolean getCotisation() {
		return cotisation;
	}
	public void setCotisation(Boolean cotisation) {
		this.cotisation = cotisation;
	}
	public Boolean getCertificat() {
		return certificat;
	}
	public void setCertificat(Boolean certificat) {
		this.certificat = certificat;
	}
	public double getTarif() {
		return tarif;
	}
	public void setTarif(double tarif) {
		this.tarif = tarif;
	}
	public Integer getUtilisateurs_idUtilisateurs() {
		return utilisateurs_idUtilisateurs;
	}
	public void setUtilisateurs_idUtilisateurs(Integer utilisateurs_idUtilisateurs) {
		this.utilisateurs_idUtilisateurs = utilisateurs_idUtilisateurs;
	}
	
	


}
