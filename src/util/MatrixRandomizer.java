package util;

import java.util.Random;

public class MatrixRandomizer {

	private final int origin, bound;

	/**
	 * Creates an instance of a randomizer for square matrices of integers.
	 *
	 * @param origin Lower integer to generate, inclusive.
	 * @param bound Higher integer to generate, inclusive.
	 */
	public MatrixRandomizer(int origin, int bound) {
		this.origin = origin;
		this.bound = bound + 1;
	}

	/**
	 * Creates a new square matrix of integers, with random numbers between the origin and bound of this randomizer.
	 *
	 * @param n Size of the square matrix to be created.
	 * @return A new square matrix with randomized integers, of n x n size.
	 */
	public int[][] createRandMatrix(int n) {
		Random rng = new Random();
		int[][] matrix = new int[n][];
		// NOTE: According to java 12 documentation, calling "rng.ints(n, origin, bound)" is about as random as
		// calling "rng.nextInt(bound) - origin" n times, and both should be at least (if not more) random than
		// creating a new random generator for each number
		for (int i = 0; i < n; i++) {
			matrix[i] = rng.ints(n, origin, bound).toArray();
		}
		return matrix;
	}

	/**
	 * Changes randomly chosen numbers in the given matrix.
	 *
	 * @param matrix Matrix to be changed.
	 * @param change_number Amount of numbers to be changed in the matrix.
	 */
	public void changeRandValues(int[][] matrix, int change_number) {
		Random rng = new Random();
		int matrix_size = matrix.length;
		int x ,y, rand_int;
		for (int i = 0; i < change_number; i++) {
			x = rng.nextInt(matrix_size);
			y = rng.nextInt(matrix_size);
			rand_int = rng.nextInt(bound - origin) + origin;
			matrix[x][y] = rand_int;
		}
	}

}
