package Repository;
import java.util.HashMap;
import java.util.ArrayList;

public class BaseRepository<T,ID> {
    HashMap<ID, T> Map = new HashMap<>();
    protected ArrayList<T> List = new ArrayList<>();

    public void findById(ID id){
        return T;
    }
    public void findAll(){

    }
    public void save(T entity){
    }
    public getId(T entity){
        return ID;
    }
}
