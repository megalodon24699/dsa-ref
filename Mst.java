import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Mst {
	public static int getMin(int[] keys, boolean[] inMst) {
		int min = Integer.MAX_VALUE, idx = -1;
		for (int i = 0; i < keys.length; ++i) {
			if (!inMst[i] && min > keys[i]) {
				min = keys[i];
				idx = i;
			}
		}
		return idx;
	}

	public static int getMst(int[][] matrix, int v) { // O(v^2) time, O(v) space
		int[] keys = new int[v];
		Arrays.fill(keys, Integer.MAX_VALUE);
		int[] parent = new int[v];
		Arrays.fill(parent, -1);
		boolean[] inMst = new boolean[v];

		keys[0] = 0;
		for (int count = 0; count < v-1; ++count) {
			int idx = getMin(keys, inMst);
			inMst[idx] = true;
			for (int i = 0; i < v; ++i) {
				if (matrix[idx][i] != 0 && !inMst[i] && keys[i] > matrix[idx][i]) {
					keys[i] = matrix[idx][i];
					parent[i] = idx;
				}
			}
		}

		int weight = 0;
		for (int i = 1; i < v; ++i) {
			weight += matrix[i][parent[i]];
		}
		return weight;
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
		System.out.println(getMst(matrix, n));
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
