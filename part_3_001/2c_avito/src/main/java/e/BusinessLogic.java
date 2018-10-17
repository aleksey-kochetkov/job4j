package e;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BusinessLogic {
    private static final int LENGTH = 3;
    private final Repository repository;

    public BusinessLogic(Repository repository) {
        this.repository = repository;
    }

    public void closeRepository() {
        try {
            this.repository.close();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public User findUserByLogin(String login) {
        return this.repository.findUserByLogin(login);
    }

    public List<Mark> findAllMarks() {
        return this.repository.findAllMarks();
    }

    public List<Model> findModelsByMarkId(int markId) {
        return this.repository.findModelsByMarkId(markId);
    }

    public List<Model> findModelsByMarkId(String markId) {
        return this.findModelsByMarkId(Integer.parseInt(markId));
    }

    public List<CarBody> findAllCarBodies() {
        return this.repository.findAllCarBodies();
    }

    public List<Engine> findAllEngines() {
        return this.repository.findAllEngines();
    }

    public List<Transmission> findAllTransmissions() {
        return this.repository.findAllTransmissions();
    }

    public Ad createAd(User operator, Map<String, Object> parameters) {
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

    public Ad findAdById(String id) {
        return this.repository.findAdById(Integer.parseInt(id));
    }

    public Ad[][] findAllAds() {
        List<Ad> ads = this.repository.findAllAds();
        Ad[][] result = null;
        if (ads.size() > 0) {
            result = this.toArray(ads);
        }
        return result;
    }

    Ad[][] findAds(Optional<Integer> markId, String periodString,
                                                      String notClosed) {
        Period period = new Period().decode(periodString);
        Boolean closed = notClosed == null || notClosed.length() == 0
                                                          ? null : false;
        List<Ad> ads = this.repository.findAds(markId, period.getBegin(),
                                                period.getEnd(), closed);
        Ad[][] result = null;
        if (ads.size() > 0) {
            result = this.toArray(ads);
        }
        return result;
    }

    private Ad[][] toArray(List<Ad> ads) {
        int y = (int) Math.ceil((double) ads.size() / LENGTH);
        Ad[][] result = new Ad[y][];
        y = 0;
        result[y] = new Ad[LENGTH];
        int x = 0;
        for (Ad a : ads) {
            if (x == LENGTH) {
                x = 0;
                result[++y] = new Ad[LENGTH];
            }
            result[y][x] = a;
            x++;
        }
        return result;
    }

    public Ad updateAd(User operator, String id,
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
          .withClosed(((String) parameters.get("closed")) != null)
          .withPrice(Integer.parseInt((String) parameters.get("price")))
          .withImage(new Image((byte[]) parameters.get("image")))
          .build();
        this.repository.updateAd(result);
        return result;
    }

    public Image findImageById(int id) {
        return this.repository.findImageById(id);
    }

    public boolean isCredential(String login, String password) {
        User user = this.repository.findUserByLogin(login);
        return user != null && password.equals(user.getPassword().trim());
    }
}
