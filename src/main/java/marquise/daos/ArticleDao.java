package marquise.daos;
import java.time.LocalDate;
import java.util.List;

import marquise.projos.Article;



public interface ArticleDao {
	
	public List<Article> listArticles();

	public Article addArticle(String title, String texte, LocalDate datePublication, String auteur);

}
