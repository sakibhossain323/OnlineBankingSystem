import java.util.Scanner;

public class AuthenticationView implements IAuthenticationView {

    private String readField(String fieldName)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter "+fieldName+": ");
        return scanner.nextLine();
    }

    @Override
    public void register(ICustomerService customerService, IAuthenticationService authenticationService) {
        String name = readField("name");
        String phone = readField("phone");
        String email = readField("email");
        customerService.createCustomer(name, phone, email);
    }

    @Override
    public Customer login(ICustomerService customerService, IAuthenticationService authenticationService) {
        return null;
    }
}
