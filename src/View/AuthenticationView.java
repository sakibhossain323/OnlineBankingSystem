package View;

import Model.Customer;
import Service.IAuthenticationService;
import Service.ICustomerService;

public class AuthenticationView implements IAuthenticationView {

    ICustomerService customerService;
    IAuthenticationService authenticationService;

    // ANSI escape codes for colors
    private static final String RESET = "\u001B[0m";
    private static final String BLACK = "\u001B[30m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String PURPLE = "\u001B[35m";
    private static final String CYAN = "\u001B[36m";
    private static final String WHITE = "\u001B[37m";

    public AuthenticationView(ICustomerService customerService, IAuthenticationService authenticationService) {
        this.customerService = customerService;
        this.authenticationService = authenticationService;
    }

    @Override
    public void register() {
        System.out.println(YELLOW + "Registration" + RESET);
        String name = Reader.readString("Name: ");
        String address = Reader.readString("Address: ");
        String email = Reader.readString("Email: ");
        String phone = Reader.readString("Phone: ");
        Customer customer = customerService.createCustomer(name, phone, email, address);

        String password = Reader.readString("Password: ");
        authenticationService.createRecord(customer, password);

        System.out.println(GREEN + "Registered Successfully. Your ID is: " + customer.getId() + "\n" + RESET);
    }

    @Override
    public Customer login() {
        System.out.println(YELLOW + "Login" + RESET);
        int ID = Reader.readInt("Customer ID: ");
        String password = Reader.readString("Password: ");

        //String phone = Reader.readString("phone");
        //Customer customer = customerService.getCustomerByPhone(phone);

        Customer customer = customerService.getCustomerById(ID);

        if (authenticationService.isValid(customer, password)) {
            System.out.println(GREEN + "Login successful!" + RESET);
            return customer;
        }
        throw new RuntimeException(RED + "Invalid credentials" + RESET);
    }
}