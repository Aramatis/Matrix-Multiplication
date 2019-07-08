package util;

import java.util.Arrays;
import java.util.Random;

public class MatrixOperations {

	/**
	 * Multiplies 2 matrices of the same size.
	 *
	 * @param a 1st matrix to be multiplied.
	 * @param b 2nd matrix to be multiplied.
	 * @return Matrix c = a x b.
	 */
	public static int[][] multiplyAB(int[][] a, int[][] b) {
		int n = a.length;
		int[][] result = new int[n][n];
		for (int i = 0; i < n; i++) {
			// Select row from a
			for (int j = 0; j < n; j++) {
				// Select column from b
				for (int k = 0; k < n; k++) {
					// Complete the result
					result[i][j] += a[i][k] * b[k][j];
				}
			}
		}
		return result;
	}

	/**
	 * Checks if 2 matrices of the same size are equal.
	 *
	 * @param a 1st matrix to be compared.
	 * @param b 2nd matrix to be compared.
	 * @return BoolLongPair where the boolean is if the matrices are equal and the long the execution time.
	 */
	public static BoolLongPair equalsAB(int[][] a, int[][] b) {
		int n = a.length;
		boolean result = true;
		long beginning = System.nanoTime();
		for (int i = 0; i < n && result; i++) {
			for (int j = 0; j < n && result; j++) {
				result = a[i][j] == b[i][j];
			}
		}
		long end = System.nanoTime();
		return new BoolLongPair(result, end - beginning);
	}

	/**
	 * Checks if a x b = c using a probabilistic algorithm.
	 *
	 * @param a 1st matrix of the multiplication.
	 * @param b 2nd matrix of the multiplication.
	 * @param c Matrix to check equality to a x b.
	 * @param k Amount of repetitions for the probabilistic algorithm.
	 * @return BoolLongPair where the boolean is if a x b = c and the long the execution time.
	 */
	public static BoolLongPair probabilisticEqualsAB(int[][] a, int[][] b, int[][] c, int k) {
		int n = c.length;
		Random rng = new Random();
		int[] selected_cols;
		boolean result = true;

		// NOTE: According to java 12 documentation, calling "rng.ints(n, origin, bound)" is about as random as
		// calling "rng.nextInt(bound) - origin" n times, and both should be at least (if not more) random than
		// creating a new random generator for each number

		long beginning = System.nanoTime();
		for (int f = 0; f < k && result; f++) {
			selected_cols = rng.ints(n, 0, 2).toArray();
			int acc;
			for (int i = 0; i < n && result; i++) {
				for (int j = 0; j < n; j++) {
					if (selected_cols[j] == 0) {
						continue;
					}
					acc = 0;
					for (int l = 0; l < n && result; l++) {
						acc += a[i][l] * b[l][j];
					}
					if (acc != c[i][j]) {
						result = false;
					}
				}
			}
		}
		long end = System.nanoTime();

		return new BoolLongPair(result, end - beginning);
	}

	/**
	 * Creates a deep copy of a given square integer matrix.
	 *
	 * @param matrix Square matrix to be deep copied.
	 * @return A deep copy of the matrix.
	 */
	public static int[][] matrixDeepCopy(int[][] matrix) {
		int len = matrix.length;
		int[][] copy = new int[len][];
		for (int i = 0; i < len; i++) {
			copy[i] = Arrays.copyOf(matrix[i], len);
		}
		return copy;
	}

}
