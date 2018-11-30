/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tradui.challenge.classicmodels;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author c.kaddatz
 */
public class ClassicModelsApp {	
	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) throws IOException {
		//Open file and instanciate CSV Parser. Set header manually and ignore header in file.
		FileReader myFReader = new FileReader(ClassicModelsApp.class.getResource("/classicmodels.csv").getPath());
		BufferedReader mbr = new BufferedReader(myFReader);
		CSVParser csvParser = new CSVParser(mbr,
				CSVFormat.DEFAULT.withHeader("customerNumber", "customerName", "contactLastName", "contactFirstName",
						"phone", "addressLine1", "addressLine2", "city", "state", "postalCode", "country",
						"salesRepEmployeeNumber", "creditLimit", "orderNumber", "orderDate", "requiredDate",
						"shippedDate", "status", "comments", "customerNumber2", "orderNumber2", "productCode",
						"quantityOrdered", "priceEach", "orderLineNumber").withIgnoreHeaderCase().withTrim().withSkipHeaderRecord());
		
		//Parse CSV file for data that matters.
		for (CSVRecord csvRecord : csvParser)
			new CustomerOrdersCounter(csvRecord.get("customerNumber"), csvRecord.get("country"));
		
		
		HashMap<String, CustomerOrdersCounter> customers = CustomerOrdersCounter.getCustomerCounterList();
		HashMap<String, Integer> countries = CustomerOrdersCounter.getCountryOrdersCounterList();
		
		
		
//		//Print out results
//		System.out.println("Customers count: " + customers.size());
//		for (String tmp : customers.keySet()) {
//			System.out.println(customers.get(tmp).toString());
//		}
//		
		System.out.println("==============================================================================================================================");
		System.out.println("Countries count: " + countries.size());
		for (String tmp : countries.keySet()) {
			System.out.printf("%15s has a total volume order of %5d\n", tmp, countries.get(tmp));
		}
		
		
		
		//Print Volume orders of top N customers.
		System.out.println("==============================================================================================================================");
		HashMap<String,CustomerOrdersCounter> tmp = CustomerOrdersCounter.getTopCustomersByCountryAndVolumeOrder(1);
		System.out.println("Results count: " + tmp.size());
		for (String val : tmp.keySet()) {
			System.out.println(tmp.get(val));
		}
		
		
		//Print Volume Ratio by country of top N customers.
		System.out.println("==============================================================================================================================");
		System.out.println("Ratio: ");
		for (String val : tmp.keySet()) {
			//System.out.println(((float)tmp.get(val).getOrdersCounter()) + "     " + ((float)countries.get(tmp.get(val).getCustomerCountry())));
			System.out.printf(tmp.get(val) + " and a country ratio of %2s%6.2f %%\n", "", ((float)tmp.get(val).getOrdersCounter())/((float)countries.get(tmp.get(val).getCustomerCountry()))*100);
		}
		
		csvParser.close();
	}

}
