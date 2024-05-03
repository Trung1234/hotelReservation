package service;

import java.util.HashMap;
import java.util.Objects;

import model.Customer;

/**
 * @author Trung
 */
public final class CustomerService {
	private static CustomerService instance;

	private static HashMap<String, Customer> mapCustomers;
	
	private CustomerService() {
		mapCustomers = new HashMap<>();
	}
	
	public static CustomerService getInstance() {
		if (Objects.isNull(instance)) {
			instance = new CustomerService(); 
		}
		return instance;
	}
	
	public   HashMap<String, Customer>  getAllCustomers() {
		return mapCustomers;
	}
	
	/**
	 * add customer to list 
	 * @param email
	 * @param firstName
	 * @param lastName
	 */
	public   void addCustomer(String email, String firstName, String lastName) {
		if(mapCustomers.containsKey(email)) {
			 throw new IllegalArgumentException("This email has existed in this program");
		}
		Customer customer = new Customer(firstName, lastName, email);
		mapCustomers.put(email, customer);
	}
	
	
	/**
	 * get Customer by email
	 * @param customerEmail
	 * @return
	 */
	public  Customer getCustomer(String customerEmail) {
		Customer result = null;
		for(Customer customer : mapCustomers.values()) {
			if(customerEmail.equals(customer.getEmail())) {
				result = customer;
			}
		}
		return result;
	}
}
