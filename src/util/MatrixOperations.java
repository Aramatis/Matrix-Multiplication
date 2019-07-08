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
	 * @return If all elements in a are equal to the corresponding elements in b.
	 */
	public static boolean equalsAB(int[][] a, int[][] b) {
		int n = a.length;
		boolean result = true;
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++){
				result = result && (a[i][j] == b[i][j]);
			}
		}
		return result;
	}

}
