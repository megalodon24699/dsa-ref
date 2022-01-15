import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class MatrixPath {
	public static int longestFrom(int i, int j, int[][] matrix, int[][] dp) {
		int n = matrix.length;
		if (dp[i][j] != 0) {
			return dp[i][j];
		}
		int x = 1;
		if (j < n-1 && matrix[i][j+1] == matrix[i][j] + 1) {
			x = 1 + longestFrom(i, j+1, matrix, dp);
		} else if (i > 0 && matrix[i-1][j] == matrix[i][j] + 1) {
			x = 1 + longestFrom(i-1, j, matrix, dp);
		} else if (j > 0 && matrix[i][j-1] == matrix[i][j] + 1) {
			x = 1 + longestFrom(i, j-1, matrix, dp);
		} else if (i < n-1 && matrix[i+1][j] == matrix[i][j] + 1) {
			x = 1 + longestFrom(i+1, j, matrix, dp);
		}
		dp[i][j] = x;
		return x;
	}

	public static int getLongestPath(int[][] matrix, int n) { // O(n^2) time, O(n^2) space
		int longestPath = Integer.MIN_VALUE;
		int[][] dp = new int[n][n];
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				dp[i][j] = longestFrom(i, j, matrix, dp);
				longestPath = Math.max(longestPath, dp[i][j]);
			}
		}
		return longestPath;
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		int n = fs.nextInt();
		int[][] matrix = new int[n][n];
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				matrix[i][j] = fs.nextInt();
			}
		}

		System.out.println(getLongestPath(matrix, n));
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

	static class Pair<F extends Comparable<F>, S extends Comparable<S>> implements Comparable<Pair<F, S>> {
		public final F first;
		public final S second;

		Pair(F aFirst, S aSecond) {
			first = aFirst;
			second = aSecond;
		}

		@Override
		public int compareTo(Pair<F, S> o) {
			int t = first.compareTo(o.first);
			if (t == 0) {
				t = second.compareTo(o.second);
			}
			return t;
		}

		@Override
		public int hashCode() {
			return (31 + first.hashCode()) * 31 + second.hashCode();
		}

		@Override
		public boolean equals(Object o) {
			if (!(o instanceof Pair)) {
				return false;
			}
			if (o == this) {
				return true;
			}
			Pair p = (Pair)o;
			return first.equals(p.first) && second.equals(p.second);
		}

		@Override
		public String toString() {
			return "{ " + first + ", " + second + " }";
		}

		public static <F extends Comparable<F>, S extends Comparable<S>> Pair<F, S> of(F aFirst, S aSecond) {
			return new Pair<>(aFirst, aSecond);
		}
	}
}
