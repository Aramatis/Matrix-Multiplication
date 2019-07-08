package util;

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

}
