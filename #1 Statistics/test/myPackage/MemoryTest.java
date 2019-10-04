/*
 * Statistics
 * Author: Fátima Rojo del Prado
 */

package myPackage;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import myPackage.Statistics;

public class MemoryTest {

	private Statistics st;

	/**
	 * This method is executed before each test method of the class
	 * in order to initialize the Statistics instance
	 */
	@Before
	public void inicialize_memory_test() {
		st = new Statistics();
	}
	
	/**
	 * Tests that 1 element is added correctly
	 */
	@Test
	public void add_1_element_return_size_1() {
		st.add(1.00);
		assertEquals(1, st.getMemorySize());
	}
	
	/**
	 * Tests that 6 elements are added correctly
	 */
	@Test
	public void add_6_elements_return_size_6() {
		st.add(1.00);
		st.add(2.00);
		st.add(2.00);
		st.add(3.00);
		st.add(3.00);
		st.add(3.00);
		assertEquals(6, st.getMemorySize());
	}
	
	/**
	 * Tests aux does not add element 1 already added
	 */
	@Test
	public void add_2_equal_elements_return_aux_size_1() {
		st.add(1.00);
		st.add(1.00);
		assertEquals(1, st.getAuxSize());
	}
	
	/**
	 * Test aux does not add elements already added
	 */
	@Test
	public void add_3_different_elements_return_aux_size_3() {
		st.add(1.00);
		st.add(2.00);
		st.add(2.00);
		st.add(3.00);
		st.add(3.00);
		st.add(3.00);
		assertEquals(3, st.getAuxSize());
	}
	
	/**
	 * Tests the number of copies of element 1
	 */
	@Test
	public void frequency_of_element_1_return_1() {
		st.add(1.00);
		st.add(2.00);
		assertEquals(1, st.frequency(1.00));
	}
	
	/**
	 * Tests the number of copies of element 2
	 */
	@Test
	public void frequency_of_element_2_return_2() {
		st.add(1.00);
		st.add(2.00);
		st.add(2.00);
		assertEquals(2, st.frequency(2.00));
	}
	
	/**
	 * Tests the method which prints the histogram
	 */
	@Test
	public void print_histogram() {
		st.histogram();
	}
}
