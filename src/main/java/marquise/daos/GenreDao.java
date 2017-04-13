package marquise.daos;
import java.util.List;

import marquise.projos.Genre;

public interface GenreDao {
	
	public List<Genre> listGenres();
	
	public Genre getGenre(Integer id);

	public Genre addGenre(String nom, String prenom);

}
