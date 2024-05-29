package Service;

import Model.Customer;
import Repository.IAuthenticationRepository;

public class AuthenticationService implements IAuthenticationService {
    IAuthenticationRepository authenticationRepository;

    public AuthenticationService(IAuthenticationRepository authenticationRepository) {
        this.authenticationRepository = authenticationRepository;
    }

    @Override
    public String generateHash(String password)
    {
        int shift = 5;
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < password.length(); i++)
        {
            char charAtPosition = password.charAt(i);

            if (Character.isLetter(charAtPosition))
            {
                char base = Character.isLowerCase(charAtPosition) ? 'a' : 'A';
                charAtPosition = (char) ((charAtPosition - base + shift) % 26 + base);
            }
            result.append(charAtPosition);
        }

        return result.toString();
    }

    @Override
    public void createRecord(Customer customer, String password)
    {
        String passwordHash = generateHash(password);

        authenticationRepository.createRecord(customer.getId(), passwordHash);
    }

    @Override
    public boolean isValid(Customer customer, String password) {
        int identifier = customer.getId();
        String passwordHash = generateHash(password);
        return authenticationRepository.isValid(identifier, passwordHash);
    }
}
