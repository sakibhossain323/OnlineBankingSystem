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
        Customer customer = customerService.createCustomer(name, phone, email);
        authenticationService.createRecord(customer, readField("Password"));
        System.out.println("Registered Successfully...");
    }

    @Override
    public Customer login(ICustomerService customerService, IAuthenticationService authenticationService) {
        String phone = readField("phone");

        return null;
    }
}
