
package DAOimplement;

/**
 *
 * @author HP
 */
import java.util.List;

import model.DataMusic;

public interface MusicImplement {
    public void insert(DataMusic dm);
    public void update(DataMusic dm);
    public void delete(String judul);
    public List<DataMusic>getAll();
    
}
