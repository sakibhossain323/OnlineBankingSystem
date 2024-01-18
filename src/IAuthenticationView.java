public interface IAuthenticationView {
    void register(ICustomerService customerService, IAuthenticationService authenticationService);
    Customer login(ICustomerService customerService, IAuthenticationService authenticationService);
}
