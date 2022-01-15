import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class RelativeSorting {
	public static int getFirstIdx(int[] arr, int x) {
		int idx = Arrays.binarySearch(arr, x);
		if (idx < 0) {
			return -1;
		} else {
			while (idx > 0 && arr[idx-1] == arr[idx]) {
				--idx;
			}
			return idx;
		}
	}

	public static void relativeSort(int[] u, int[] v, int m, int n) { // O((m+n)*log(m)) time, O(m) space
		int[] t = u.clone();
		boolean[] seen = new boolean[m];
		Arrays.sort(t);
		int k = 0;
		for (int i : v) {
			int idx = getFirstIdx(t, i);
			if (idx == -1) {
				continue;
			}
			for (int j = idx; j < m && t[j] == i; ++j) {
				u[k++] = t[j];
				seen[j] = true;
			}
		}
		for (int i = 0; i < m; ++i) {
			if (!seen[i]) {
				u[k++] = t[i];
			}
		}
	}

	public static void relativeSortHash(int[] u, int[] v, int m, int n) { // O((m+n)*log(m)) time, O(m) space
		Map<Integer, Integer> map = new TreeMap<>();
		for (int i : u) {
			if (map.containsKey(i)) {
				map.put(i, map.get(i) + 1);
			} else {
				map.put(i, 1);
			}
		}
		int k = 0;
		for (int i : v) {
			if (map.containsKey(i)) {
				int t = map.get(i);
				for (int j = 0; j < t; ++j) {
					u[k++] = i;
				}
				map.remove(i);
			}
		}
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			int i = entry.getKey(), t = entry.getValue();
			for (int j = 0; j < t; ++j) {
				u[k++] = i;
			}
		}
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

		relativeSortHash(u, v, m, n);

		System.out.println(Arrays.toString(u));
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
