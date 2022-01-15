import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class SpiralTraversal {
	public static void printSpiral(int[][] matrix, int r, int c) { // O(r*c) time, O(1) space
		int i = 0, j = 0;
		while (i < r && j < c) {
			for (int k = j; k < c; ++k) {
				System.out.print(matrix[i][k] + " ");
			}
			++i;
			for (int k = i; k < r; ++k) {
				System.out.print(matrix[k][c-1] + " ");
			}
			--c;
			if (i < r) {
				for (int k = c-1; k >= i; --k) {
					System.out.print(matrix[r-1][k] + " ");
				}
				--r;
			}
			if (j < c) {
				for (int k = r-1; k >= i; --k) {
					System.out.print(matrix[k][j] + " ");
				}
				++j;
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		int r = fs.nextInt();
		int c = fs.nextInt();
		int[][] matrix = new int[r][c];
		for (int i = 0; i < r; ++i) {
			for (int j = 0; j < c; ++j) {
				matrix[i][j] = fs.nextInt();
			}
		}

		printSpiral(matrix, r, c);
	}

	static class FastScanner {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer("");

		String next() {
			while (!st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		int nextInt() {
			return Integer.parseInt(next());
		}

		long nextLong() {
			return Long.parseLong(next());
		}
	}
}
