package e;

import java.util.List;

public class Repository {
    private final UnitOfWorkFactory unitOfWorkFactory;
    private final DAOFactory daoFactory;

    public Repository(UnitOfWorkFactory unitOfWorkFactory,
                                                 DAOFactory daoFactory) {
        this.unitOfWorkFactory = unitOfWorkFactory;
        this.daoFactory = daoFactory;
    }

    public void createUser(User user) {
        try (UnitOfWork work = this.unitOfWorkFactory.getUnitOfWork()) {
            AddressDAO addressDAO = this.daoFactory.getAddressDAO(work);
            UserDAO userDAO = this.daoFactory.getUserDAO(work);
            work.beginWork();
            userDAO.createUser(user);
            addressDAO.createAddress(user.getAddress());
            work.commitWork();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public List<User> findAllUsers() {
        try (UnitOfWork work = this.unitOfWorkFactory.getUnitOfWork()) {
            return this.daoFactory.getUserDAO(work).findAllUsers();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public User findUserByLogin(String login) {
        try (UnitOfWork work = this.unitOfWorkFactory.getUnitOfWork()) {
            return this.daoFactory.getUserDAO(work)
                                                 .findUserByLogin(login);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public void updateUser(User user) {
        try (UnitOfWork work = this.unitOfWorkFactory.getUnitOfWork()) {
            AddressDAO addressDAO = this.daoFactory.getAddressDAO(work);
            UserDAO userDAO = this.daoFactory.getUserDAO(work);
            work.beginWork();
            userDAO.updateUser(user);
            addressDAO.updateAddress(user.getAddress());
            work.commitWork();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public void deleteUser(User user) {
        try (UnitOfWork work = this.unitOfWorkFactory.getUnitOfWork()) {
            AddressDAO addressDAO = this.daoFactory.getAddressDAO(work);
            UserDAO userDAO = this.daoFactory.getUserDAO(work);
            work.beginWork();
            addressDAO.deleteAddress(user.getAddress());
            userDAO.deleteUser(user);
            work.commitWork();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public List<Role> findAllRoles() {
        try (UnitOfWork work = this.unitOfWorkFactory.getUnitOfWork()) {
            return this.daoFactory.getRoleDAO(work).findAllRoles();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public Role findRoleByCode(String code) {
        try (UnitOfWork work = this.unitOfWorkFactory.getUnitOfWork()) {
            return this.daoFactory.getRoleDAO(work).findRoleByCode(code);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public List<MusicType> findAllMusicTypes() {
        try (UnitOfWork work = this.unitOfWorkFactory.getUnitOfWork()) {
            return this.daoFactory.getMusicTypeDAO(work)
                                                   .findAllMusicTypes();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public MusicType findMusicTypeByCode(String code) {
        try (UnitOfWork work = this.unitOfWorkFactory.getUnitOfWork()) {
            return this.daoFactory.getMusicTypeDAO(work)
                                              .findMusicTypeByCode(code);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}
