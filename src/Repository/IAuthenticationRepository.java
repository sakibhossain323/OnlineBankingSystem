package Repository;

public interface IAuthenticationRepository {
    void createRecord(int identifier, String passwordHash);
    boolean isValid(int identifier, String passwordHash);
}
