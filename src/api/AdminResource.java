package api;

import model.Customer;
import service.CustomerService;

public class AdminResource {
	public static Customer getCustomer(String email) {
		return CustomerService.getCustomer(email);
	}
}
