package e;


import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class HbRepositoryTest {
    private Repository repository = new HbRepository();

    @Test
    public void whenFindUserByLogin() {
        User user = this.repository.createUser("файнд",
                                         "Файнд", "файнд");
        String result = this.repository.findUserByLogin("файнд")
                                                              .getName();
        assertThat(result, is(user.getName()));
    }

    @Test
    public void whenFindAllMarks() {
        int before = this.repository.findAllMarks().size();
        this.repository.createMark(new Mark());
        this.repository.createMark(new Mark());
        List<Mark> marks = this.repository.findAllMarks();
        assertEquals(before + 2, marks.size());
    }

    @Test
    public void whenFindModelsByMarkId() {
        Mark mark = this.repository.createMark(new Mark());
        this.repository.createModel(new Model(mark));
        mark = this.repository.createMark(new Mark());
        this.repository.createModel(new Model(mark));
        this.repository.createModel(new Model(mark));
        List<Model> models = this.repository.findModelsByMarkId(
                                                           mark.getId());
        assertEquals(2, models.size());
    }

    @Test
    public void whenFindAllCarBodies() {
        this.repository.createCarBody();
        this.repository.createCarBody();
        List<CarBody> carBodies = this.repository.findAllCarBodies();
        assertEquals(2, carBodies.size());
    }

    @Test
    public void whenFindAllEngines() {
        this.repository.createEngine();
        this.repository.createEngine();
        List<Engine> engines = this.repository.findAllEngines();
        assertEquals(2, engines.size());
    }

    @Test
    public void whenFindAllTransmissions() {
        this.repository.createTransmission();
        this.repository.createTransmission();
        List<Transmission> transmissions =
                                  this.repository.findAllTransmissions();
        assertEquals(2, transmissions.size());
    }

    @Test
    public void whenCreateAd() {
        Mark mark = new Mark();
        Ad ad = new Ad.Builder(new User("криэйт", "Криэйт", "криэйт"),
                            new Model(mark), new CarBody(), new Engine(),
                                        new Transmission(), 2018, 201800)
                              .withImage(new Image(new byte[0])).build();
        this.repository.createAd(ad);
        assertEquals(ad.getId(),
                         this.repository.findAdById(ad.getId()).getId());
    }

    @Test
    public void whenFindAllAds() {
        int before = this.repository.findAllAds().size();
        Mark mark = new Mark();
        Ad ad = new Ad.Builder(new User("олэдз", "Олэдз", "олэдз"),
                            new Model(mark), new CarBody(), new Engine(),
                                        new Transmission(), 2019, 201900)
                              .withImage(new Image(new byte[1])).build();
        this.repository.createAd(ad);
        ad = new Ad.Builder(new User("секонд", "Секонд", "секонд"),
                            new Model(mark), new CarBody(), new Engine(),
                                        new Transmission(), 2020, 202000)
                              .withImage(new Image(new byte[2])).build();
        this.repository.createAd(ad);
        List<Ad> ads = this.repository.findAllAds();
        assertEquals(before + 2, ads.size());
    }

    @Test
    public void whenFindAds() {
        Mark one = this.repository.createMark(new Mark());
        Mark two = this.repository.createMark(new Mark());
        Model model = this.repository.createModel(new Model(one));
        Ad ad = new Ad.Builder(
                         new User("эдзбаймак", "Эдзбаймак", "эдзбаймак"),
                                      model, new CarBody(), new Engine(),
                                        new Transmission(), 2000, 200000)
                              .withImage(new Image(new byte[3])).build();
        this.repository.createAd(ad);
        model = this.repository.createModel(new Model(one));
        ad = new Ad.Builder(new User("ризалт", "Ризалт", "ризалт"),
                                      model, new CarBody(), new Engine(),
                                        new Transmission(), 2001, 200100)
                              .withImage(new Image(new byte[4])).build();
        this.repository.createAd(ad);
        model = this.repository.createModel(new Model(two));
        ad = new Ad.Builder(new User("энадэ", "Энадэ", "энадэ"),
                                      model, new CarBody(), new Engine(),
                                        new Transmission(), 2002, 200200)
                              .withImage(new Image(new byte[4])).build();
        this.repository.createAd(ad);
        List<Ad> ads = this.repository.findAds(one.getId(), null, null,
                                                                   null);
        assertEquals(2, ads.size());
    }

    @Test
    public void whenUpdateAd() {
        Mark mark = new Mark();
        Ad ad = new Ad.Builder(new User("апдэйт", "Апдэйт", "апдэйт"),
                            new Model(mark), new CarBody(), new Engine(),
                                        new Transmission(), 2003, 200300)
                              .withImage(new Image(new byte[5])).build();
        this.repository.createAd(ad);
        ad.setYear(2004);
        this.repository.updateAd(ad);
        assertEquals(2004,
                       this.repository.findAdById(ad.getId()).getYear());
    }

    @Test
    public void whenFindImageById() {
        byte[] bytes = {1, 2, 3, 4, 5};
        Ad ad = new Ad.Builder(
                         new User("имидж", "Имидж", "имидж"),
                                new Model(), new CarBody(), new Engine(),
                                        new Transmission(), 2005, 200500)
                              .withImage(new Image(new byte[5])).build();
        this.repository.createAd(ad);
        ad = new Ad.Builder(
                         new User("видбайтс", "Видбайтс", "видбайтс"),
                                new Model(), new CarBody(), new Engine(),
                                        new Transmission(), 2006, 200600)
                                    .withImage(new Image(bytes)).build();
        this.repository.createAd(ad);
        assertThat(this.repository.findImageById(ad.getId()).getBytes(),
                                                              is(bytes));
    }
}
