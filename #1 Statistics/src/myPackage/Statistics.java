/*
 * Statistics
 * Author: Fátima Rojo del Prado
 */

package myPackage;

import java.util.Vector;

public class Statistics {

	private Vector<Double> memory;
	private Vector<Double> aux;

	/**
	 * Constructor method
	 */
	public Statistics() {
		memory = new Vector<Double>();
		aux = new Vector<Double>();
	}

	/**
	 * Adds a new element in memory. Add a new element in aux if aux doesn't
	 * contain it.
	 * @param n = element to be added
	 */
	public void add(Double n) {
		memory.addElement(n);
		if (!aux.contains(n))
			aux.addElement(n);
	}

	/**
	 * Gets the number of elements contained in memory
	 * @return memory size
	 */
	public int getMemorySize() {
		return memory.size();
	}

	/**
	 * Gets the number of elements contained in aux
	 * @return aux size
	 */
	public int getAuxSize() {
		return aux.size();
	}

	/**
	 * Calculates average of elements in memory
	 * @return average
	 * @throws DivideByZeroException
	 */
	public Double average() throws DivideByZeroException {
		if (memory.size() == 0)
			throw new DivideByZeroException();

		Double sum = 0.00;

		for (Double n : memory) {
			sum += n;
		}

		return (sum / memory.size());
	}

	/**
	 * Calculates standard deviation of elements in memory
	 * @return standard deviation
	 * @throws DivideByZeroException
	 * @throws NegativeSquareRootException 
	 */
	public Double sd() throws DivideByZeroException {
		if (memory.size() == 0)
			throw new DivideByZeroException();

		Double vza = 0.00;

		for (Double n : memory) {
			vza += Math.pow((n - average()), 2);
		}

		vza /= memory.size();
		
		/*
		 * We could take into account that the square root of a negative number cannot be calculated.
		 * However this will never happen as the numerator (a number to the power of 2) and the 
		 * denominator (the number of elements in memory) are always positive
		 */
		
		return Math.sqrt(vza);
	}

	/**
	 * Counts the number of copies of an element in memory
	 * @param e = element
	 * @return frequency
	 */
	public int frequency(Double e) {
		int freq = 0;
		for (Double n : memory) {
			if (n.equals(e))
				freq++;
		}
		return freq;
	}

	/**
	 * Prints the histogram which shows the elements contained in memory and the
	 * number of copies of them.
	 */
	public void histogram() {
		for (Double n : aux) {
			System.out.print("" + n + "\t|\t");
			int dup = frequency(n);
			while (dup != 0) {
				System.out.print("*");
				dup--;
			}
			System.out.println();
		}

	}
}



