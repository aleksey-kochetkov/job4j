package e;

import java.util.List;
import java.util.Date;

public interface Repository extends AutoCloseable {
    User createUser(String login, String name, String password);
    Mark createMark(Mark mark);
    User findUserByLogin(String login);
    List<Mark> findAllMarks();
    Model createModel(Model model);
    List<Model> findModelsByMarkId(int markId);
    CarBody createCarBody();
    List<CarBody> findAllCarBodies();
    Engine createEngine();
    List<Engine> findAllEngines();
    Transmission createTransmission();
    List<Transmission> findAllTransmissions();
    void createAd(Ad ad);
    Ad findAdById(int id);
    List<Ad> findAllAds();
    List<Ad> findAds(Integer markId, Date begin, Date end,
                                                         Boolean closed);
    void updateAd(Ad ad);
    Image findImageById(int id);
}
