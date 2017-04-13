package marquise.daos;

import java.util.List;

import marquise.projos.Film;

public interface FilmDao {
	
	public List<Film> listFilms();
	
	public Film getFilm(Integer id);
	
	public Film addFilm(Film film);
	
	

}
