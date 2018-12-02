package e;

import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Date;

public interface AdRepository extends CrudRepository<Ad, Integer> {
    List<Ad> findByModelMarkId(Integer markId);
    List<Ad> findByCreateDtBetween(Date begin, Date end);
    List<Ad> findByClosed(boolean closed);
    List<Ad> findByModelMarkIdAndCreateDtBetween(Integer markId, Date begin, Date end);
    List<Ad> findByModelMarkIdAndClosed(Integer markId, boolean closed);
    List<Ad> findByCreateDtBetweenAndClosed(Date begin, Date end, boolean closed);
    List<Ad> findByModelMarkIdAndCreateDtBetweenAndClosed(
                   Integer markId, Date begin, Date end, boolean closed);
}
