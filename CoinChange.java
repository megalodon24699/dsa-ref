import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CoinChange {
	public static int getWays(int[] coins, int m, int n) { // O(m*n) time, O(m*n) space (can be optimized to O(n))
		int[][] dp = new int[m][n+1];
		for (int i = 0; i < m; ++i) {
			dp[i][0] = 1;
		}
		for (int i = 0; i < m; ++i) {
			for (int j = 1; j <= n; ++j) {
				int x = (coins[i] <= j) ? dp[i][j - coins[i]] : 0;
				int y = (i > 0) ? dp[i-1][j] : 0;
				dp[i][j] = x + y;
			}
		}
		return dp[m-1][n];
	}

	public static int getMinCoins(int[] coins, int m, int n) { // O(m*n) time, O(n) space
		if (n == 0) {
			return 0;
		}
		int[] dp = new int[n+1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		for (int i = 1; i <= n; ++i) {
			for (int j = 0; j < m; ++j) {
				if (coins[j] <= i) {
					int sub = dp[i - coins[j]];
					if (sub != Integer.MAX_VALUE && sub + 1 < dp[i]) {
						dp[i] = sub + 1;
					}
				}
			}
		}
		return dp[n] == Integer.MAX_VALUE ? -1 : dp[n];
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		int m = fs.nextInt();
		int n = fs.nextInt();
		int[] coins = new int[m];
		for (int i = 0; i < m; ++i) {
			coins[i] = fs.nextInt();
		}

		System.out.println(getMinCoins(coins, m, n));
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
