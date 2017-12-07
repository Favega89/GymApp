package service.Idaos;

import java.util.List;

import org.springframework.stereotype.Service;

import model.Client;



public interface InterfaceClientDao extends InterfaceDao<Client,Integer,String>{
	   @Override
	    public void add(Client n);

	    @Override
	    public void modify(Integer i,Client n);
                          
	    @Override          
	    public void delete(Client a,Integer i);

	    @Override
	    public List<Client> getAll();
                   
	    @Override   
	    public List<Client> get(String s);
}
