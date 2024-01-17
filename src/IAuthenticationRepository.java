public interface IAuthenticationRepository {
    void createRecord(String identifier, String passwordHash);
    boolean isValid(String identifier, String passwordHash);
}
