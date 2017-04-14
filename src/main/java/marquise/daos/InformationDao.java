package marquise.daos;

import java.util.List;

import marquise.projos.Information;

public interface InformationDao {
	
	public List<Information> listInformations();
	
	public Information getInformation(Integer id);
	
	public Information addInformation(Information information);
	
	

}
