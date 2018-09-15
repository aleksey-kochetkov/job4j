package e;

public class City {
    private Country country;
    private String code;
    private String name;

    public City(String code, String name, Country country) {
        this.code = code;
        this.name = name;
        this.country = country;
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public Country getCountry() {
        return this.country;
    }
}
