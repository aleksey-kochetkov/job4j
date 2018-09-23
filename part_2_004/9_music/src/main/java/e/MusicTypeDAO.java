package e;

import java.util.List;

public interface MusicTypeDAO {
    List<MusicType> findAllMusicTypes();
    MusicType findMusicTypeByCode(String code);
}
