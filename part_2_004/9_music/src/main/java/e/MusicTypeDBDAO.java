package e;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MusicTypeDBDAO implements MusicTypeDAO {
    private final DBUnitOfWork work;

    public MusicTypeDBDAO(UnitOfWork work) {
        this.work = (DBUnitOfWork) work;
    }

    public List<MusicType> findAllMusicTypes() {
        List<MusicType> result = new ArrayList<>();
        Connection connection = this.work.getConnection();
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(
                                           "SELECT * FROM music_type")) {
            while (rs.next()) {
                result.add(this.newMusicType(rs));
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return result;
    }

    @Override
    public MusicType findMusicTypeByCode(String code) {
        Connection connection = this.work.getConnection();
        try (PreparedStatement prepared = connection.prepareStatement(
                            "SELECT * FROM music_type WHERE code = ?")) {
            prepared.setString(1, code);
            ResultSet rs = prepared.executeQuery();
            rs.next();
            return this.newMusicType(rs);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    private MusicType newMusicType(ResultSet rs) throws SQLException {
        return new MusicType(rs.getString("code"),
                                               rs.getString("descript"));
    }
}
