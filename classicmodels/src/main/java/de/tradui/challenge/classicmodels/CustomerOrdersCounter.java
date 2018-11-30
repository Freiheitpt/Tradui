package de.tradui.challenge.classicmodels;

import java.util.Collections;
import java.util.HashMap;

//Selfnote: would be better to implement own comparable and use treeset!
public class CustomerOrdersCounter implements Comparable<CustomerOrdersCounter> {
	private String id;
	private String customerCountry;
	private int customerOrders = 0;
	
	private static HashMap<String, CustomerOrdersCounter> customerOrdersCount = new HashMap<String, CustomerOrdersCounter>();
	private static HashMap<String, Integer> countryOrdersCount = new HashMap<String, Integer>();
	
	/*
	 * Constructor
	 */
	public CustomerOrdersCounter(String id, String country) {
		this.id = id;
		this.customerCountry = country;
		
		this.addToCustomerOrdersCount();
		this.addToCountryOrdersCount();
	}

	/*
	 * increment the number of orders of current costumer.
	 * @param amount - the ammount to increment
	 * 
	 */
	public void incOrders(int amount) {
		this.customerOrders += amount;
	}
	
	
	/*
	 * Returns the costumer ID.
	 * @return Costumer ID.
	 * 
	 */
	public String getCustomerID() {
		return this.id;
	}
	
	/*
	 * Returns the costumer Country.
	 * @return Costumer Country.
	 * 
	 */
	public String getCustomerCountry() {
		return this.customerCountry;
	}
	
	
	/*
	 * Returns the costumer orders counter.
	 * @return Costumer orders counter.
	 * 
	 */
	public int getOrdersCounter() {
		return this.customerOrders;
	}
	
	/*
	 * Add the current CustomerOrdersCounter to the order counter list (HashMap).
	 * 
	 */
	private void addToCustomerOrdersCount() {
		if (customerOrdersCount.containsKey(this.id)) {
			customerOrdersCount.get(id).incOrders(1);
		} else {
			customerOrdersCount.put(this.id, this);
			this.incOrders(1);
		}
	}
	
	
	/*
	 * Add the current CustomerOrdersCounter to the country orders counter list (HashMap).
	 * 
	 */
	private void addToCountryOrdersCount() {
		if (countryOrdersCount.containsKey(this.customerCountry)) {
			countryOrdersCount.put(this.customerCountry, countryOrdersCount.get(this.customerCountry)+1);
		} else {
			countryOrdersCount.put(this.customerCountry, 1);
		}
	}
	
	
	/*
	 * Returns the Customer Counter list.
	 * @return Costumer counter list.
	 * 
	 */
	public static HashMap<String, CustomerOrdersCounter> getCustomerCounterList() {
		return customerOrdersCount;
	}
	
	
	/*
	 * Returns the Country Orders Counter list.
	 * @return Costumer counter list.
	 * 
	 */
	public static HashMap<String, Integer> getCountryOrdersCounterList() {
		return countryOrdersCount;
	}
	
	
	/*
	 * Returns the top N customers by order volume.
	 * @param n - the number of customers by country.
	 * @return Costumer counter list.
	 * 
	 */
	public static HashMap<String,CustomerOrdersCounter> getTopCustomersByCountryAndVolumeOrder(int n) {
		HashMap<String, HashMap<String, CustomerOrdersCounter>> subResult = new HashMap<String, HashMap<String, CustomerOrdersCounter>>();
		HashMap<String, CustomerOrdersCounter> helper;
		
		//Iterate and check if current country exists
		for (String tmp : customerOrdersCount.keySet()) {	
			if (subResult.containsKey(customerOrdersCount.get(tmp).getCustomerCountry())) {
				//if the country exists, get that list of orders by customer
				helper = subResult.get(customerOrdersCount.get(tmp).getCustomerCountry());
				
				//if max number of customers required is not reached, then just add.
				if(helper.size() < n) {
					helper.put(customerOrdersCount.get(tmp).getCustomerID(), customerOrdersCount.get(tmp));
				} else {
					//Iterate and find a smaller volume order. It is enough to find the first one. <= WRONG. CHECK AND FIX! I HOPE IS FIXED!
					CustomerOrdersCounter min = Collections.min(helper.values());
					if(min.getOrdersCounter() < customerOrdersCount.get(tmp).getOrdersCounter()) {
						helper.remove(min.getCustomerID());
						helper.put(customerOrdersCount.get(tmp).getCustomerID(), customerOrdersCount.get(tmp));
					}
				}
			} else {
				//if country does not exist in the list, then no customer, of that country, exists in the list either.
				//Just add to the list.
				helper = new HashMap<String, CustomerOrdersCounter>();
				helper.put(customerOrdersCount.get(tmp).getCustomerID(), customerOrdersCount.get(tmp));
				subResult.put(customerOrdersCount.get(tmp).getCustomerCountry(), helper);
			}
		}
		
		
		HashMap<String, CustomerOrdersCounter> finalResult = new HashMap<String, CustomerOrdersCounter>();
		for(String tmp : subResult.keySet()) {
			helper = subResult.get(tmp);
			for(String tmp2 : helper.keySet()) {
				finalResult.put(helper.get(tmp2).getCustomerID(), helper.get(tmp2));
			}
		}

		return finalResult;
	}
	
	@Override
	public String toString() {
		return String.format("Customer %5s%3sfrom %-15s has a volume order of %5d", this.id, "", this.customerCountry, this.customerOrders);
	}

	@Override
	public int compareTo(CustomerOrdersCounter arg0) {
		return Integer.compare(this.customerOrders, arg0.getOrdersCounter());
	}
}
