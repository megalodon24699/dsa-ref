import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class GetMiddleSum {
	public static int getMid(int[] arr, int n) {
		if (n%2 != 0) {
			return 2 * arr[n/2];
		} else {
			return arr[n/2 - 1] + arr[n/2];
		}
	}

	public static int getMidSum(int[] u, int[] v, int n) { // O(log(n)) time, O(1) space (can be implemented iteratively)
		if (n == 0) {
			return 0;
		}
		if (n == 1) {
			return u[0] + v[0];
		}
		if (n == 2) {
			return Math.max(u[0], v[0]) + Math.min(u[1], v[1]);
		}

		int mu = getMid(u, n), mv = getMid(v, n);
		if (mu == mv) {
			return mu;
		} else if (mu < mv) {
			int offset = (n%2 != 0) ? n/2 - 1: n/2;
			return getMidSum(Arrays.copyOfRange(u, offset, n), v, n-offset);
		} else {
			int offset = (n%2 != 0) ? n/2 - 1: n/2;
			return getMidSum(Arrays.copyOfRange(v, offset, n), u, n-offset);
		}
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		int n = fs.nextInt();
		int[] u = new int[n];
		int[] v = new int[n];
		for (int i = 0; i < n; ++i) {
			u[i] = fs.nextInt();
		}
		for (int i = 0; i < n; ++i) {
			v[i] = fs.nextInt();
		}

		System.out.println(getMidSum(u, v, n));
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
