import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class EggDropping {
	public static int getMinTrials(int n, int k) { // O(n*k^2) time, O(n*k) space
		int[][] dp = new int[n+1][k+1];
		for (int i = 1; i <= n; ++i) {
			dp[i][0] = 0;
			dp[i][1] = 1;
		}
		for (int i = 1; i <= k; ++i) {
			dp[1][i] = i;
		}
		for (int i = 2; i <= n; ++i) {
			for (int j = 2; j <= k; ++j) {
				dp[i][j] = Integer.MAX_VALUE;
				for (int x = 1; x <= j; ++x) {
					dp[i][j] = Math.min(dp[i][j], Math.max(dp[i-1][x-1], dp[i][j-x]) + 1);
				}
			}
		}
		return dp[n][k];
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		int n = fs.nextInt(), k = fs.nextInt();

		System.out.println(getMinTrials(n, k));
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
