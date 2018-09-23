package e;

public class DBDAOFactory implements DAOFactory {

    @Override
    public UserDAO getUserDAO(UnitOfWork work) {
        return new UserDBDAO(work);
    }

    @Override
    public AddressDAO getAddressDAO(UnitOfWork work) {
        return new AddressDBDAO(work);
    }

    @Override
    public RoleDAO getRoleDAO(UnitOfWork work) {
        return new RoleDBDAO(work);
    }

    @Override
    public MusicTypeDAO getMusicTypeDAO(UnitOfWork work) {
        return new MusicTypeDBDAO(work);
    }
}
