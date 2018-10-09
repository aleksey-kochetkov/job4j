package e;

import java.util.List;

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
    void updateAd(Ad ad);
    Image findImageById(int id);
}
