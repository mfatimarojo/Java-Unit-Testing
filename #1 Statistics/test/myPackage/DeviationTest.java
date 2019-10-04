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

public class DeviationTest {

	private Statistics st;

	/**
	 * This method is executed before each test method of the class
	 * in order to initialize the Statistics instance
	 */
	@Before
	public void inicialize_deviation_test() {
		st = new Statistics();
	}

	/**
	 * Tests that standard deviation of 0 elements cannot be calculated and throws an exception
	 * @throws DivideByZeroException
	 * @throws NegativeSquareRootException 
	 */
	@Test(expected = DivideByZeroException.class)
	public void zero_elements_throws_exception() throws DivideByZeroException {
		st.sd();
	}
	
	/**
	 * Tests that standard deviation of 1 element is zero
	 * @throws DivideByZeroException
	 * @throws NegativeSquareRootException 
	 */
	@Test
	public void one_element_returns_0() throws DivideByZeroException {
		st.add(2.00);
		assertEquals(0.00, st.sd(), 0.01);
	}
	
	/**
	 * Tests that standard deviation of elements {2,4,6,8} is 2.24
	 * @throws DivideByZeroException
	 * @throws NegativeSquareRootException 
	 */
	@Test
	public void values_2468_returns_223606797749979() throws DivideByZeroException {
		st.add(2.00);
		st.add(4.00);
		st.add(6.00);
		st.add(8.00);
		assertEquals(2.24, st.sd(), 0.01);
	}

	/**
	 * Tests that standad deviation of elements {1,2,2,3,3,3} is 0.75
	 * @throws DivideByZeroException
	 * @throws NegativeSquareRootException 
	 */
	@Test
	public void values_122333_returns_07453634460225875() throws DivideByZeroException {
		st.add(1.00);
		st.add(2.00);
		st.add(2.00);
		st.add(3.00);
		st.add(3.00);
		st.add(3.00);
		assertEquals(0.75, st.sd(), 0.01);
	}

}
