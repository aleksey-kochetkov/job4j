package e;

public interface DAOFactory {
    UserDAO getUserDAO(UnitOfWork work);
    AddressDAO getAddressDAO(UnitOfWork work);
    RoleDAO getRoleDAO(UnitOfWork work);
    MusicTypeDAO getMusicTypeDAO(UnitOfWork work);
}
