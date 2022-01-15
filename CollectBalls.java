import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class CollectBalls {
	public static int getMaxBalls(int[] u, int[] v, int m, int n) { // O(m+n) time, O(1) space
		int first = 0, second = 0;
		int i = 0, j = 0;
		while (i < m && j < n) {
			if (u[i] < v[j]) {
				first += u[i];
				++i;
			} else if (u[i] > v[j]) {
				second += v[j];
				++j;
			} else {
				first += u[i]; second += v[j];
				if (first > second) {
					second = first;
				} else {
					first = second;
				}
				++i; ++j;
			}
		}
		while (i < m) {
			first += u[i++];
		}
		while (j < n) {
			second += v[j++];
		}
		return first >= second ? first : second;
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		int m = fs.nextInt();
		int n = fs.nextInt();
		int[] u = new int[m];
		int[] v = new int[n];
		for (int i = 0; i < m; ++i) {
			u[i] = fs.nextInt();
		}
		for (int i = 0; i < n; ++i) {
			v[i] = fs.nextInt();
		}

		System.out.println(getMaxBalls(u, v, m, n));
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
