import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class ShortestPath {
	public static boolean canInclude(int n, int x, int y) {
		return 0 <= x && x < n && 0 <= y && y < n;
	}

	public static int getShortestPath(int[][] matrix, int n) { // O(n^2 * log(n)), O(n^2) space
		int[][] distances = new int[n][n];
		for (int[] arr : distances) {
			Arrays.fill(arr, Integer.MAX_VALUE);
		}
		PriorityQueue<Pair<Pair<Integer, Integer>, Integer>> pq = new PriorityQueue<>(new Comparator<Pair<Pair<Integer, Integer>, Integer>>() {
			public int compare(Pair<Pair<Integer, Integer>, Integer> p, Pair<Pair<Integer, Integer>, Integer> q) {
				return p.second - q.second;
			}
		});

		int[] rowOffset = {1, 0, -1, 0};
		int[] colOffset = {0, 1, 0, -1};

		distances[0][0] = matrix[0][0];
		pq.add(Pair.of(Pair.of(0, 0), distances[0][0]));
		while (!pq.isEmpty()) {
			Pair<Integer, Integer> p = pq.poll().first;
			for (int i = 0; i < 4; ++i) {
				int x = p.first + rowOffset[i], y = p.second + colOffset[i];
				if (canInclude(n, x, y) && distances[p.first][p.second] + matrix[x][y] < distances[x][y]) {
					distances[x][y] = distances[p.first][p.second] + matrix[x][y];
					pq.add(Pair.of(Pair.of(x, y), distances[x][y]));
				}
			}
		}
		return distances[n-1][n-1];
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		int n = fs.nextInt();
		int[][] matrix = new int[n][n];
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				matrix[i][j] = fs.nextInt();
			}
		}

		System.out.println(getShortestPath(matrix, n));
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
