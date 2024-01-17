public interface IAuthenticationService {
    String generateHash(String password);
    void createRecord(Customer customer, String password);
    boolean isValid(Customer customer, String password);
}
