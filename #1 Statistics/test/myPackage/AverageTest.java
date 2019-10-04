/*
 * Statistics
 * Author: Fátima Rojo del Prado
 */

package myPackage;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import myPackage.DivideByZeroException;
import myPackage.Statistics;

public class AverageTest {

	private Statistics st;

	/**
	 * This method is executed before each test method of the class
	 * in order to initialize the Statistics instance
	 */
	@Before
	public void initialize_average_test() {
		st = new Statistics();
	}

	/**
	 * Tests that average of 0 elements cannot be calculated and throws an exception
	 * @throws DivideByZeroException
	 */
	@Test(expected = DivideByZeroException.class)
	public void zero_elements_throws_exception() throws DivideByZeroException {
		st.average();
	}

	/**
	 * Tests that average of 1 element is the element itself
	 * @throws DivideByZeroException
	 */
	@Test
	public void one_element_returns_itself() throws DivideByZeroException {
		st.add(2.00);
		assertEquals(2.00, st.average(), 0.01);
	}

	/**
	 * Tests that average of elements {2,4,6,8} is 5
	 * @throws DivideByZeroException
	 */
	@Test
	public void values_2468_returns_5() throws DivideByZeroException {
		st.add(2.00);
		st.add(4.00);
		st.add(6.00);
		st.add(8.00);
		assertEquals(5.00, st.average(), 0.01);

	}

	/**
	 * Tests that average of elements {1,2,2,3,3,3} is 2.33
	 * @throws DivideByZeroException
	 */
	@Test
	public void values_122333_returns_233() throws DivideByZeroException {
		st.add(1.00);
		st.add(2.00);
		st.add(2.00);
		st.add(3.00);
		st.add(3.00);
		st.add(3.00);
		assertEquals(2.33, st.average(), 0.01);
	}
}
