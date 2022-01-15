import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Dijkstra {
	public static int getMinIdx(int[] distances, boolean[] inSpt) {
		int min = Integer.MAX_VALUE, minIdx = -1;
		for (int i = 0; i < distances.length; ++i) {
			if (!inSpt[i] && distances[i] <= min) {
				min = distances[i];
				minIdx = i;
			}
		}
		return minIdx;
	}

	public static void dijkstraMatrix(int[][] matrix, int n, int s) { // O(v^2) time, O(v) space
		boolean[] inSpt = new boolean[n];
		int[] distances = new int[n];
		Arrays.fill(distances, Integer.MAX_VALUE);
		distances[s] = 0;
		for (int i = 0; i < n-1; ++i) {
			int idx = getMinIdx(distances, inSpt);
			inSpt[idx] = true;
			for (int v = 0; v < n; ++v) {
				if (!inSpt[v] && matrix[idx][v] != 0 && distances[idx] != Integer.MAX_VALUE && distances[idx] + matrix[idx][v] < distances[v]) {
					distances[v] = distances[idx] + matrix[idx][v];
				}
			}
		}
		System.out.println(Arrays.toString(distances));
	}

	public static void dijkstraList(List<List<Pair<Integer, Integer>>> al, int n, int s) { // O(e*log(v)) time, O(v) space
		int[] distances = new int[n];
		Arrays.fill(distances, Integer.MAX_VALUE);
		distances[s] = 0;
		PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>(new Comparator<Pair<Integer, Integer>>() {
			public int compare(Pair<Integer, Integer> u, Pair<Integer, Integer> v) {
				return u.second - v.second;
			}
		});
		pq.add(Pair.of(s, 0));
		while (!pq.isEmpty()) {
			Pair<Integer, Integer> p = pq.poll();
			for (Pair<Integer, Integer> u : al.get(p.first)) {
				if (distances[p.first] + u.second < distances[u.first]) {
					distances[u.first] = distances[p.first] + u.second;
					pq.add(Pair.of(u.first, distances[u.first]));
				}
			}
		}
		System.out.println(Arrays.toString(distances));
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		// Custom driver code for this question
		int n = 3;
		List<List<Pair<Integer, Integer>>> al = new ArrayList<>(n);
		for (int i = 0; i < n; ++i) {
			al.add(new ArrayList<Pair<Integer, Integer>>());
		}

		al.get(0).add(Pair.of(1, 1));
		al.get(0).add(Pair.of(2, 6));

		al.get(1).add(Pair.of(0, 1));
		al.get(1).add(Pair.of(2, 3));

		al.get(2).add(Pair.of(0, 6));
		al.get(2).add(Pair.of(1, 3));

		dijkstraList(al, n, 2);
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
