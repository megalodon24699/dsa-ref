import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class KthElementSorted {
	public static int upperBound(int[] arr, int x) {
		int idx = Arrays.binarySearch(arr, x);
		if (idx >= 0) {
			while (idx < arr.length && arr[idx] == x) {
				++idx;
			}
			return idx;
		}
		return -idx-1;
	}

	public static int getKthElement(int[] u, int[] v, int m, int n, int k) { // O(log(MAX_VALUE)*(log(m)+log(n))) time, O(1) space
		int l = Math.min(u[0], v[0]), r = Math.max(u[m-1], v[n-1]);
		while (l <= r) {
			int mid = l + (r-l)/2;
			int c = upperBound(u, mid);
			c += upperBound(v, mid);
			if (c < k) {
				l = mid+1;
			} else {
				r = mid-1;
			}
		}
		return l;
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		int m = fs.nextInt(), n = fs.nextInt(), k = fs.nextInt();
		int[] u = new int[m];
		int[] v = new int[n];
		for (int i = 0; i < m; ++i) {
			u[i] = fs.nextInt();
		}
		for (int i = 0; i < n; ++i) {
			v[i] = fs.nextInt();
		}

		System.out.println(getKthElement(u, v, m, n, k));
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
