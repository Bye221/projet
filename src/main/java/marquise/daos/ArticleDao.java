package marquise.daos;
import java.time.LocalDate;
import java.util.List;

import marquise.projos.Article;



public interface ArticleDao {
	
	
	// Methode qui va renvoyer la liste de tout les articles du site
	public List<Article> listArticles();
	
	
	// Methode pour ajouter des article au site
	public Article addArticle(String title, String texte, LocalDate datePublication, String auteur);

}
