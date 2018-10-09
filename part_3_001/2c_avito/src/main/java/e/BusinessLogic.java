package e;

import java.util.List;
import java.util.Map;

public class BusinessLogic {
    private static final int L = 3;
    private final Repository repository;

    BusinessLogic(Repository repository) {
        this.repository = repository;
    }

    void closeRepository() {
        try {
            this.repository.close();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    User findUserByLogin(String login) {
        return this.repository.findUserByLogin(login);
    }

    List<Mark> findAllMarks() {
        return this.repository.findAllMarks();
    }

    List<Model> findModelsByMarkId(int markId) {
        return this.repository.findModelsByMarkId(markId);
    }

    List<Model> findModelsByMarkId(String markId) {
        return this.findModelsByMarkId(Integer.parseInt(markId));
    }

    List<CarBody> findAllCarBodies() {
        return this.repository.findAllCarBodies();
    }

    List<Engine> findAllEngines() {
        return this.repository.findAllEngines();
    }

    List<Transmission> findAllTransmissions() {
        return this.repository.findAllTransmissions();
    }

    Ad createAd(User operator, Map<String, Object> parameters) {
        Ad result = new Ad.Builder(operator,
                             new Model(Integer.parseInt(
                                      (String) parameters.get("model"))),
                             new CarBody(Integer.parseInt(
                                    (String) parameters.get("carBody"))),
                             new Engine(Integer.parseInt(
                                     (String) parameters.get("engine"))),
                             new Transmission(Integer.parseInt(
                               (String) parameters.get("transmission"))),
                       Integer.parseInt((String) parameters.get("year")),
                         Integer.parseInt((String) parameters.get("km")))
          .withPrice(Integer.parseInt((String) parameters.get("price")))
          .withImage(new Image((byte[]) parameters.get("image")))
          .build();
        this.repository.createAd(result);
        return result;
    }

    Ad findAdById(String id) {
        return this.repository.findAdById(Integer.parseInt(id));
    }

    Ad[][] findAllAds() {
        List<Ad> ads = this.repository.findAllAds();
        Ad[][] result = null;
        if (ads.size() > 0) {
            result = this.toArray(ads);
        }
        return result;
    }

    private Ad[][] toArray(List<Ad> ads) {
        int y = (int) Math.ceil((double) ads.size() / L);
        Ad[][] result = new Ad[y][];
        y = 0;
        result[y] = new Ad[L];
        int x = 0;
        for (Ad a : ads) {
            if (x == L) {
                x = 0;
                result[++y] = new Ad[L];
            }
            result[y][x] = a;
            x++;
        }
        return result;
    }

    Ad updateAd(User operator, String id,
                                        Map<String, Object> parameters) {
        Ad result = new Ad.Builder(operator,
                             new Model(Integer.parseInt(
                                      (String) parameters.get("model"))),
                             new CarBody(Integer.parseInt(
                                    (String) parameters.get("carBody"))),
                             new Engine(Integer.parseInt(
                                     (String) parameters.get("engine"))),
                             new Transmission(Integer.parseInt(
                               (String) parameters.get("transmission"))),
                       Integer.parseInt((String) parameters.get("year")),
                         Integer.parseInt((String) parameters.get("km")))
          .withId(Integer.parseInt(id))
          .withClosed(((String) parameters.get("closed")) == null
                                                          ? false : true)
          .withPrice(Integer.parseInt((String) parameters.get("price")))
          .withImage(new Image((byte[]) parameters.get("image")))
          .build();
        this.repository.updateAd(result);
        return result;
    }

    Image findImageById(int id) {
        return this.repository.findImageById(id);
    }

    boolean isCredential(String login, String password) {
        User user = this.repository.findUserByLogin(login);
        return user != null && password.equals(user.getPassword().trim());
    }
}
