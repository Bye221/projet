package marquise.daos;

import java.util.List;

import marquise.projos.Information;

public interface InformationDao {
	
	public List<Information> listFilms();
	
	public Information getFilm(Integer id);
	
	public Information addFilm(Information film);
	
	

}
