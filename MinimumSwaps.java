import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class MinimumSwaps {
	public static int getMinSwaps(int[] arr, int n) { // O(n*log(n)) time, O(n) space
		Map<Integer, Integer> map = new HashMap<>(); // Optimization to speed-up the lookup
		for (int i = 0; i < n; ++i) {
			map.put(arr[i], i);
		}
		int swaps = 0;
		int[] tmp = arr.clone();
		sort(tmp);
		for (int i = 0; i < n; ++i) {
			if (arr[i] != tmp[i]) {
				++swaps;
				int idx = map.get(tmp[i]);
				int t = arr[idx];
				arr[idx] = arr[i];
				arr[i] = t;
				map.put(arr[idx], idx);
				map.put(arr[i], i);
			}
		}
		return swaps;
	}

	public static int getMinSwapsGraph(int[] arr, int n) {
		List<Pair<Integer, Integer>> l = new ArrayList<>();
		for (int i = 0; i < n; ++i) {
			l.add(Pair.of(arr[i], i));
		}
		Collections.sort(l, new Comparator<Pair<Integer, Integer>>() {
			public int compare(Pair<Integer, Integer> p, Pair<Integer, Integer> q) {
				return p.first - q.first;
			}
		});
		boolean[] visited = new boolean[n];
		int swaps = 0;
		for (int i = 0; i < n; ++i) {
			if (visited[i] || l.get(i).second == i) {
				continue;
			}
			int cycleSize = 0, j = i;
			while (!visited[j]) {
				visited[j] = true;
				j = l.get(j).second;
				++cycleSize;
			}
			swaps += cycleSize - 1;
		}
		return swaps;
	}

	public static void sort(int[] arr) {
		List<Integer> l = new ArrayList<>();
		for (int i : arr) {
			l.add(i);
		}
		Collections.sort(l);
		for (int i = 0; i < arr.length; ++i) {
			arr[i] = l.get(i);
		}
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		int n = fs.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; ++i) {
			arr[i] = fs.nextInt();
		}

		System.out.println(getMinSwapsGraph(arr, n));
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
