package e;

import java.util.List;
import java.util.Date;

public interface Repository extends AutoCloseable {
    User findUserByLogin(String login);
    List<Mark> findAllMarks();
    List<Model> findModelsByMarkId(int markId);
    List<CarBody> findAllCarBodies();
    List<Engine> findAllEngines();
    List<Transmission> findAllTransmissions();
    void createAd(Ad ad);
    Ad findAdById(int id);
    List<Ad> findAllAds();
    List<Ad> findAds(Integer markId, Date begin, Date end,
                                                         Boolean closed);
    void updateAd(Ad ad);
    Image findImageById(int id);
}
