public class AuthenticationService implements IAuthenticationService {
    IAuthenticationRepository authenticationRepository;

    public AuthenticationService(IAuthenticationRepository authenticationRepository) {
        this.authenticationRepository = authenticationRepository;
    }

    @Override
    public String generateHash(String password) {
        return password;
    }

    @Override
    public void createRecord(Customer customer, String password) {
        String identifier = customer.getPhone();
        String passwordHash = generateHash(password);
        authenticationRepository.createRecord(identifier, passwordHash);
    }

    @Override
    public boolean isValid(Customer customer, String password) {
        String identifier = customer.getPhone();
        String passwordHash = generateHash(password);
        return authenticationRepository.isValid(identifier, passwordHash);
    }
}
