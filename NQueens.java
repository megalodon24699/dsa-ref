import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class NQueens {
	static boolean[] inColumn, inLeftDiagonal, inRightDiagonal;
	static List<List<Integer>> configs;

	public static void getConfigsAux(List<List<Integer>> configs, int n, int r, List<Integer> curr) {
		if (r == n) {
			configs.add(new ArrayList<>(curr));
			return;
		}
		for (int c = 0; c < n; ++c) {
			if (inColumn[c] || inLeftDiagonal[r+c] || inRightDiagonal[r-c+n]) {
				continue;
			}
			inColumn[c] = true; inLeftDiagonal[r+c] = true; inRightDiagonal[r-c+n] = true;
			curr.set(c, r+1);
			getConfigsAux(configs, n, r+1, curr);
			inColumn[c] = false; inLeftDiagonal[r+c] = false; inRightDiagonal[r-c+n] = false;
		}
	}

	public static void getConfigs(int n) { // O(n!) time, O(n^2) space ?
		inColumn = new boolean[n];
		inLeftDiagonal = new boolean[2*n];
		inRightDiagonal = new boolean[2*n];
		configs = new ArrayList<>();

		List<Integer> curr = new ArrayList<>();
		for (int i = 0; i < n; ++i) {
			curr.add(0);
		}
		getConfigsAux(configs, n, 0, curr);
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		int n = fs.nextInt();

		getConfigs(n);

		System.out.println(configs);
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
