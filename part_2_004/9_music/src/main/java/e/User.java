package e;

import java.util.List;

public class User {
    private String login;
    private String password;
    private String name;
    private Address address;
    private Role role;
    private List<MusicType> musicTypes;

    public User(String login, String password, String name,
                 Address address, Role role, List<MusicType> musicTypes) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.address = address;
        this.role = role;
        this.musicTypes = musicTypes;
    }

    public String getLogin() {
        return this.login;
    }

    public String getPassword() {
        return this.password;
    }

    public String getName() {
        return this.name;
    }

    public Address getAddress() {
        return this.address;
    }

    public Role getRole() {
        return this.role;
    }

    public List<MusicType> getMusicTypes() {
        return this.musicTypes;
    }

    public void setMusicType(List<MusicType> musicTypes) {
        this.musicTypes = musicTypes;
    }

    public boolean hasMusicTypeCode(String code) {
        boolean result = false;
        for (MusicType t : this.musicTypes) {
            if (t.getCode().equals(code)) {
                result = true;
                break;
            }
        }
        return result;
    }
}
