import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Knapsack {
	public static int getMaxValue(int[] ws, int[] vs, int n, int cap) { // O(n*cap) time, O(n* cap) space (can be optimized to O(n))
		int[][] dp = new int[n+1][cap+1];
		for (int i = 1; i <= n; ++i) {
			for (int j = 1; j <= cap; ++j) {
				if (ws[i-1] <= j) {
					dp[i][j] = Math.max(dp[i-1][j-ws[i-1]] + vs[i-1], dp[i-1][j]);
				} else {
					dp[i][j] = dp[i-1][j];
				}
			}
		}
		return dp[n][cap];
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		int n = fs.nextInt();
		int cap = fs.nextInt();
		int[] ws = new int[n];
		int[] vs = new int[n];
		for (int i = 0; i < n; ++i) {
			vs[i] = fs.nextInt();
		}
		for (int i = 0; i < n; ++i) {
			ws[i] = fs.nextInt();
		}

		System.out.println(getMaxValue(ws, vs, n, cap));
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
