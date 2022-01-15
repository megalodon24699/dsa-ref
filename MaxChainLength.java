import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class MaxChainLength {
	public static int getMaxChainLength(List<Pair<Integer, Integer>> pairs, int n) { // O(n^2) time, O(n) space
		int[] dp = new int[n];
		Arrays.fill(dp, 1);
		dp[0] = 1;
		Collections.sort(pairs);
		for (int i = 1; i < n; ++i) {
			for (int j = 0; j < i; ++j) {
				if (pairs.get(j).second < pairs.get(i).first && dp[j] + 1 > dp[i]) {
					dp[i] = dp[j] + 1;
				}
			}
		}
		return dp[n-1];
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		int n = fs.nextInt();
		List<Pair<Integer, Integer>> pairs = new ArrayList<>(n);
		for (int i = 0; i < n; ++i) {
			pairs.add(Pair.of(fs.nextInt(), fs.nextInt()));
		}

		System.out.println(getMaxChainLength(pairs, n));
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
