import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class RodCutting {
	public static int getMaxPrice(int[] prices, int n) { // O(n^2) time, O(n) space
		// Max profit by cutting the rod of different lengths
		int[] dp = new int[n+1];
		for (int i = 1; i <= n; ++i) {
			int max = Integer.MIN_VALUE;
			for (int j = 0; j < i; ++j) {
				max = Math.max(max, prices[j] + dp[i-j-1]);
			}
			dp[i] = max;
		}
		return dp[n];
	}

	public static int getMaxPieces(int x, int y, int z, int n) { // O(n) time, O(n) space
		int[] dp = new int[n+1];
		Arrays.fill(dp, -1);
		dp[0] = 0;
		for (int i = 0; i <= n; ++i) {
			if (dp[i] == -1) {
				continue;
			}
			if (i+x <= n) {
				dp[i+x] = Math.max(dp[i+x], dp[i] + 1);
			}
			if (i+y <= n) {
				dp[i+y] = Math.max(dp[i+y], dp[i] + 1);
			}
			if (i+z <= n) {
				dp[i+z] = Math.max(dp[i+z], dp[i] + 1);
			}
		}
		return dp[n] == -1 ? 0 : dp[n];
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		int n = fs.nextInt();
		int x = fs.nextInt();
		int y = fs.nextInt();
		int z = fs.nextInt();

		System.out.println(getMaxPieces(x, y, z, n));
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
