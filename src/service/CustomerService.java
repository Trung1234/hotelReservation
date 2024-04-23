package service;

import java.util.ArrayList;
import java.util.Collection;

import model.Customer;

public class CustomerService {
	private static Collection<Customer> customers = new ArrayList<Customer>();
	
	public static Collection<Customer> getAllCustomers() {
		return customers;
	}
	/**
	 * add customer to list 
	 * @param email
	 * @param firstName
	 * @param lastName
	 */
	public static  void addCustomer(String email, String firstName, String lastName) {
		Customer customer = new Customer(firstName, lastName, email);
		customers.add(customer);
	}
	
	
	/**
	 * get Customer by email
	 * @param customerEmail
	 * @return
	 */
	public static Customer getCustomer(String customerEmail) {
		Customer result = null;
		for(Customer customer : customers) {
			if(customerEmail.equals(customer.getEmail())) {
				result = customer;
			}
		}
		return result;
	}
}
