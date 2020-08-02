package com.pzombade.shoppingcart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

@SpringBootApplication
public class ShoppingCartApplication {
	public static void main(String[] args)  {
		SpringApplication.run(ShoppingCartApplication.class, args);
		String discountFilePath = "";
		try {
			discountFilePath = args[0];
			CalculatorUtil.loadDiscounts(discountFilePath);
			Scanner sc = new Scanner(System.in);
			boolean exit = false;
			String customerType = "";
			int billingAmount = 0;

			do{
				System.out.println("Plase enter customer type");
				customerType = "@"+sc.next();
				if(CalculatorUtil.isValidUserType(customerType)){
					System.out.println("Plase enter billing amount");
					billingAmount = sc.nextInt();
					System.err.println("-----------------------------------------------");
					CalculatorUtil.calculateTotalAmount(customerType, billingAmount);
				}else{
					System.out.println("Invalid user " + customerType );
					System.out.println("Please choose from: " + CalculatorUtil.getUserTypes());
				}

			}
			while (!exit);

		} catch (FileNotFoundException e) {
			System.err.println("###############################################################");
			System.err.println("Invalid discount file path. Please provide correct path. " + discountFilePath);
			System.err.println("###############################################################");
		} catch (IOException e) {
			System.err.println("###############################################################");
			System.err.println("Could not load discount file : " + discountFilePath);
			System.err.println("###############################################################");
		} catch (ArrayIndexOutOfBoundsException e){
			System.err.println("###############################################################");
			System.err.println("Please provide discount file path as 1st argument to run the application.");
			System.err.println("###############################################################");
		}


	}
}
