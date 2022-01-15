import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class SpecialKeyboard {
	public static int getOptimalLength(int n) { // ? complexity
		if (n < 7) {
			return n;
		}
		int max = Integer.MIN_VALUE;
		for (int i = n-3; i >= 1; --i) {
			max = Math.max(max, (n-i-1) * getOptimalLength(i));
		}
		return max;
	}

	public static int getOptimalLengthOptimized(int n) { // O(n^2) time, O(n) space
		if (n < 7) {
			return n;
		}
		int[] screen = new int[n];
		for (int i = 0; i < 6; ++i) {
			screen[i] = i+1;
		}
		for (int i = 6; i < n; ++i) {
			for (int j = n-3; j >= 1; --j) {
				screen[i] = Math.max(screen[i], (i-j-1) * screen[j]);
			}
		}
		return screen[n-1];
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		int n = fs.nextInt();
		System.out.println(getOptimalLengthOptimized(n));
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
