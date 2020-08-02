package com.pzombade.shoppingcart;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ShoppingCartApplicationTests {

	@Test
	void contextLoads() {


	}

	@BeforeAll
	static void setup() {
		try {
			CalculatorUtil.loadDiscounts("C:\\projects\\other\\assignment\\Sincro\\shopping-cart\\src\\test\\regular-customer-test.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("@BeforeAll - executes once before all test methods in this class");
	}

	@Test
	void getUserTypes() {
		assertTrue(CalculatorUtil.getUserTypes().contains("@Regular"));
		assertTrue(CalculatorUtil.getUserTypes().contains("@Premium"));
		assertFalse(CalculatorUtil.getUserTypes().contains("@InValidUser"));
	}

	@Test
	void checkSlabs() {
		String slab = "5000:10000:10";
		DiscountSlab expectedSlab = new DiscountSlab(5000,10000,10,false);
		DiscountSlab actualSlab = CalculatorUtil.parseRow(slab);
		assertEquals(expectedSlab,actualSlab);

		assertEquals(5000,actualSlab.getMin());
		assertEquals(10000,actualSlab.getMax());
		assertEquals(10,actualSlab.getDiscountPercentage());
	}

	@Test
	void calculateTotalAmount() {
		assertEquals(5000,CalculatorUtil.calculateTotalAmount("Regular",5000));
		assertEquals(9500,CalculatorUtil.calculateTotalAmount("Regular",10000));
		assertEquals(13500,CalculatorUtil.calculateTotalAmount("Regular",15000));

		assertEquals(3600,CalculatorUtil.calculateTotalAmount("Premium",4000));
		assertEquals(7000,CalculatorUtil.calculateTotalAmount("Premium",8000));
		assertEquals(10200,CalculatorUtil.calculateTotalAmount("Premium",12000));
		assertEquals(15800,CalculatorUtil.calculateTotalAmount("Premium",20000));
	}


}
