package service.Idaos;

import java.util.List;

public interface InterfaceDao <O,I,S>{
	void add(O o);
	
    void modify(I i,O o);
    
    void delete(O o, I i);
    
    List<O> getAll();
    
    List <O> get(S s);
}
