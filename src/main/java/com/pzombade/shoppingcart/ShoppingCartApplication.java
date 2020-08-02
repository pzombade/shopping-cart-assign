package com.pzombade.shoppingcart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;
import java.io.IOException;

@SpringBootApplication
public class ShoppingCartApplication {
	public static void main(String[] args)  {
		SpringApplication.run(ShoppingCartApplication.class, args);
		String discountFilePath = "";
		try {
			discountFilePath = args[0];
			CalculatorUtil.loadDiscounts(discountFilePath);
			CalculatorUtil.getTotalAmount( "Premium",4000);
			CalculatorUtil.getTotalAmount( "Premium",8000);
			CalculatorUtil.getTotalAmount( "Premium",12000);
			CalculatorUtil.getTotalAmount( "Premium",20000);
			CalculatorUtil.getTotalAmount( "Regular",5000);
			CalculatorUtil.getTotalAmount( "Regular",10000);
			CalculatorUtil.getTotalAmount( "Regular",15000);
		} catch (FileNotFoundException e) {
			System.err.println("Invalid discount file path. Please provide correct path. " + discountFilePath);
		} catch (IOException e) {
			System.err.println("Could not load discount file : " + discountFilePath);
		} catch (ArrayIndexOutOfBoundsException e){
			System.err.println("Please provide discount file path as 1st argument to run the application.");
		}


	}
}
