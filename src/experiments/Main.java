package experiments;

import util.BoolLongPair;
import util.MatrixOperations;
import util.MatrixRandomizer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Main {

	private static final int[] m_range = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
	private static final int[] k_range = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

	private static void createFile(String name, long[][] array) {
		StringBuilder output = new StringBuilder();
		// Writing headers
		output.append("k,error,time,\n");
		// Writing data
		for (long[] row : array) {
			for (long l : row) {
				output.append(l);
				output.append(",");
			}
			output.append(System.lineSeparator());
		}
		try (FileWriter writer = new FileWriter(name); BufferedWriter bw = new BufferedWriter(writer)) {
			String output_str = output.toString();
			bw.write(output_str);
		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
	}

	private static void cPrimeExecution(int[][] a, int[][] b, int[][] c, int[][] c_prime, int m, long c_time) {
		System.out.println(String.format("- Experiment with m = %d started.", m));
		String name = "results/execution_m_%d.txt";
		int range_len = k_range.length;
		// Save comparison's results
		BoolLongPair[][] results = new BoolLongPair[range_len][100];
		for (int i = 0; i < range_len; i++) {
			boolean error = false;
			System.out.println(String.format("  - Running with k = %d.", k_range[i]));
			// Run the probabilistic algorithm 100 times
			for (int j = 0; j < 100; j++) {
				results[i][j] = MatrixOperations.probabilisticEqualsAB(a, b, c_prime, k_range[i]);
				// If even one time the algorithm returns true, this set has error
				if (results[i][j].getBool()) {
					error = true;
				}
			}
			// If this set has no error, break the loop
			if (!error) {
				results = Arrays.copyOf(results, i + 1);
				break;
			}
		}
		System.out.println("- Obtaining results...");
		// The first value is k, the second is the error rate (in percentage) and the third is the average time
		long[][] real_results = new long[results.length + 1][];
		// First result is the baseline: the time a deterministic algorithm takes:
		// Multiplying a x b (saved in c_time) and then checking that against c_prime
		BoolLongPair baseline = MatrixOperations.equalsAB(c, c_prime);
		real_results[0] = new long[]{0, 0, c_time + baseline.getLong()};
		// Calculate error rates and average times
		for (int i = 0; i < results.length; i++) {
			long[] aux = {k_range[i], 0, 0};
			long prom = 0;
			for (int j = 0; j < 100; j++) {
				BoolLongPair res = results[i][j];
				if (res.getBool()) {
					aux[1] += 1;
				}
				prom += res.getLong();
			}
			aux[2] = prom / 100;
			real_results[i + 1] = aux;
		}
		System.out.println("- Saving results...");
		// Save results to a file
		createFile(String.format(name, m), real_results);
		System.out.println(String.format("- Finished m = %d experiment.", m));
	}

	public static void main(String[] args) {
		// Create the matrices
		System.out.println("Creating Matrices:");
		MatrixRandomizer randomizer = new MatrixRandomizer(-10000, 10000);
		int[][] matrix_a = randomizer.createRandMatrix(1000);
		System.out.println("- Matrix A done.");
		int[][] matrix_b = randomizer.createRandMatrix(1000);
		System.out.println("- Matrix B done.");
		// Create c and measure its creation time
		long c_time = System.nanoTime();
		int[][] matrix_c = MatrixOperations.multiplyAB(matrix_a, matrix_b);
		c_time = System.nanoTime() - c_time;
		System.out.println(String.format("- Matrix C done in %d nanoseconds.", c_time));
		// Create C' matrices
		int[][][] c_primes = new int[m_range.length][][];
		System.out.println("- Matrixes C\' started:");
		for (int i = 0; i < m_range.length; i++) {
			// For each m, create a C' that differs in 100 * m values
			c_primes[i] = MatrixOperations.matrixDeepCopy(matrix_c);
			randomizer.changeRandValues(c_primes[i], 100 * m_range[i]);
			System.out.println(String.format("  - Matrix C\' with m = %d done.", m_range[i]));
		}
		// Execute experiments for every C' matrix
		System.out.println("Starting Experiments:");
		for (int m_index = 0; m_index < m_range.length; m_index++) {
			cPrimeExecution(matrix_a, matrix_b, matrix_c, c_primes[m_index], m_range[m_index], c_time);
		}
	}

}
