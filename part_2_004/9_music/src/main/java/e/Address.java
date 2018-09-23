package e;

public class Address {
    private String userLogin;
    private String address;

    public Address(String userLogin, String address) {
        this.userLogin = userLogin;
        this.address = address;
    }

    public String getUserLogin() {
        return this.userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getAddress() {
        return this.address;
    }
}
