package e;

public class Role {
    public static final String DEF = "user";
    private String code;
    private String description;

    public Role(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }
}
