import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class MinimizeSop {
	public static int getMinSop(int[] u, int[] v, int n) { // O(n*log(n)) time, O(1) space
		sort(u, false);
		sort(v, true);
		int sop = 0;
		for (int i = 0; i < n; ++i) {
			sop += u[i] * v[i];
		}
		return sop;
	}

	public static void sort(int[] arr, boolean reverse) {
		List<Integer> l = new ArrayList<>(arr.length);
		for (int i : arr) {
			l.add(i);
		}
		if (reverse) {
			Collections.sort(l, Collections.reverseOrder());
		} else {
			Collections.sort(l);
		}
		for (int i = 0; i < arr.length; ++i) {
			arr[i] = l.get(i);
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

		System.out.println(getMinSop(u, v, n));
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
