package e.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;
import java.util.List;
import java.util.Map;
import e.Period;
import e.domain.User;
import e.domain.Mark;
import e.domain.Model;
import e.domain.CarBody;
import e.domain.Engine;
import e.domain.Transmission;
import e.domain.Ad;
import e.domain.Image;
import e.repository.AdRepository;
import e.repository.ImageRepository;
import e.repository.UserRepository;
import e.repository.MarkRepository;
import e.repository.ModelRepository;
import e.repository.CarBodyRepository;
import e.repository.EngineRepository;
import e.repository.TransmissionRepository;

@Service
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

    public Ad[][] findAds(Optional<Integer> markId, String periodString,
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
}
