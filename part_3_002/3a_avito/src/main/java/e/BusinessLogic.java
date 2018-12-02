package e;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BusinessLogic {
    private static final int LENGTH = 3;
    @Autowired
    private AdRepository adRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MarkRepository markRepository;
    @Autowired
    private ModelRepository modelRepository;
    @Autowired
    private CarBodyRepository carBodyRepository;
    @Autowired
    private EngineRepository engineRepository;
    @Autowired
    private TransmissionRepository transmissionRepository;

// Этот блок оставил себе на память. Тут была проблема - checkstyle
// ругался на более чем 7 параметров, и я понял, что можно убрать
// конструктор и заменить использованием @Autowired (выше).
//    public BusinessLogic(AdRepository adRepository,
//          ImageRepository imageRepository, UserRepository userRepository,
//          MarkRepository markRepository, ModelRepository modelRepository,
//                                     CarBodyRepository carBodyRepository,
//                                       EngineRepository engineRepository,
//                         TransmissionRepository transmissionRepository) {
//        this.adRepository = adRepository;
//        this.imageRepository = imageRepository;
//        this.userRepository = userRepository;
//        this.markRepository = markRepository;
//        this.modelRepository = modelRepository;
//        this.carBodyRepository = carBodyRepository;
//        this.engineRepository = engineRepository;
//        this.transmissionRepository = transmissionRepository;
//    }

    public User findUserByLogin(String login) {
        return this.userRepository.findOne(login);
    }

    public List<Mark> findAllMarks() {
        return (List<Mark>) this.markRepository.findAll();
    }

    public List<Model> findModelsByMarkId(int markId) {
        return this.modelRepository.findByMarkId(markId);
    }

    public List<Model> findModelsByMarkId(String markId) {
        return this.findModelsByMarkId(Integer.parseInt(markId));
    }

    public List<CarBody> findAllCarBodies() {
        return (List<CarBody>) this.carBodyRepository.findAll();
    }

    public List<Engine> findAllEngines() {
        return (List<Engine>) this.engineRepository.findAll();
    }

    public List<Transmission> findAllTransmissions() {
        return (List<Transmission>)
                                   this.transmissionRepository.findAll();
    }

    public Ad createAd(User operator, Map<String, Object> parameters) {
        Ad result = new Ad.Builder(operator,
                this.modelRepository.findOne(Integer.parseInt(
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
          .build();
        this.adRepository.save(result);
        Image image = new Image((byte[]) parameters.get("image"));
        image.setAdId(result.getId());
        this.imageRepository.save(image);
        return result;
    }

    public Ad findAdById(String id) {
        return this.adRepository.findOne(Integer.parseInt(id));
    }

//    public Ad[][] findAllAds() {
//        List<Ad> ads = this.repository.findAllAds();
//        Ad[][] result = null;
//        if (ads.size() > 0) {
//            result = this.toArray(ads);
//        }
//        return result;
//    }

    Ad[][] findAds(Optional<Integer> markId, String periodString,
                                                      String notClosed) {
        Period period = new Period().decode(periodString);
        Boolean closed = notClosed == null || notClosed.length() == 0
                                                          ? null : false;
        List<Ad> ads;
        if (!markId.isPresent() && period.getBegin() == null
                                                     && closed == null) {
            ads = (List<Ad>) this.adRepository.findAll();
        } else if (period.getBegin() == null && closed == null) {
            ads = this.adRepository.findByModelMarkId(markId.get());
        } else if (!markId.isPresent() && closed == null) {
            ads = this.adRepository.findByCreateDtBetween(
                                     period.getBegin(), period.getEnd());
        } else if (!markId.isPresent() && period.getBegin() == null) {
            ads = this.adRepository.findByClosed(closed);
        } else if (closed == null) {
            ads = this.adRepository.findByModelMarkIdAndCreateDtBetween(
                       markId.get(), period.getBegin(), period.getEnd());
        } else if (period.getBegin() == null) {
            ads = this.adRepository.findByModelMarkIdAndClosed(
                                                   markId.get(), closed);
        } else if (!markId.isPresent()) {
            ads = this.adRepository.findByCreateDtBetweenAndClosed(
                             period.getBegin(), period.getEnd(), closed);
        } else {
            ads = this.adRepository
                           .findByModelMarkIdAndCreateDtBetweenAndClosed(
               markId.get(), period.getBegin(), period.getEnd(), closed);
        }
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
                this.modelRepository.findOne(Integer.parseInt(
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
          .build();
        this.adRepository.save(result);
        Image image = new Image((byte[]) parameters.get("image"));
        if (image.getBytes() != null) {
            image.setAdId(result.getId());
            this.imageRepository.save(image);
        }
        return result;
    }

    public Image findImageById(int id) {
        return this.imageRepository.findOne(id);
    }

    public boolean isCredential(String login, String password) {
        User user = this.userRepository.findOne(login);
        return user != null && password.equals(user.getPassword().trim());
    }
}
