import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class RottenOranges {
	public static boolean withinBounds(int i, int j, int m, int n) {
		return (0 <= i && i < m && 0 <= j && j < n);
	}

	public static int getTimeToRot(int[][] grid, int m, int n) { // O(m^2*n^2) time (how?), O(1) space
		int count = 0;
		boolean changed = true;
		while (changed) {
			changed = false;
			for (int i = 0; i < m; ++i) {
				for (int j = 0; j < n; ++j) {
					if (grid[i][j] == 2) {
						if (withinBounds(i-1, j, m, n) && grid[i-1][j] == 1) {
							grid[i-1][j] = 2;
							changed = true;
						}
						if (withinBounds(i, j+1, m, n) && grid[i][j+1] == 1) {
							grid[i][j+1] = 2;
							changed = true;
						}
						if (withinBounds(i+1, j, m, n) && grid[i+1][j] == 1) {
							grid[i+1][j] = 2;
							changed = true;
						}
						if (withinBounds(i, j-1, m, n) && grid[i][j-1] == 1) {
							grid[i][j-1] = 2;
							changed = true;
						}
					}
				}
			}
			if (changed) {
				++count;
			}
		}
		for (int i = 0; i < m; ++i) {
			for (int j = 0; j < n; ++j) {
				if (grid[i][j] == 1) {
					return -1;
				}
			}
		}
		return count;
	}

	public static int getTimeToRotOptimized(int[][] grid, int m, int n) { // O(m*n) time, O(m*n) space
		Deque<Pair<Integer, Integer>> queue = new ArrayDeque<>();
		int count = 0;
		for (int i = 0; i < m; ++i) {
			for (int j = 0; j < n; ++j) {
				if (grid[i][j] == 2) {
					queue.add(Pair.of(i, j));
				}
			}
		}
		queue.add(Pair.of(-1, -1));
		while (!queue.isEmpty()) {
			boolean changed = false;
			while (!Pair.of(-1, -1).equals(queue.getFirst())) {
				Pair<Integer, Integer> p = queue.remove();
				int i = p.first, j = p.second;
				if (withinBounds(i-1, j, m, n) && grid[i-1][j] == 1) {
					grid[i-1][j] = 2;
					queue.add(Pair.of(i-1, j));
					changed = true;
				}
				if (withinBounds(i, j+1, m, n) && grid[i][j+1] == 1) {
					grid[i][j+1] = 2;
					queue.add(Pair.of(i, j+1));
					changed = true;
				}
				if (withinBounds(i+1, j, m, n) && grid[i+1][j] == 1) {
					grid[i+1][j] = 2;
					queue.add(Pair.of(i+1, j));
					changed = true;
				}
				if (withinBounds(i, j-1, m, n) && grid[i][j-1] == 1) {
					grid[i][j-1] = 2;
					queue.add(Pair.of(i, j-1));
					changed = true;
				}
			}
			if (changed) {
				++count;
			}
			queue.remove();
			if (!queue.isEmpty()) {
				queue.add(Pair.of(-1, -1));
			}
		}
		for (int i = 0; i < m; ++i) {
			for (int j = 0; j < n; ++j) {
				if (grid[i][j] == 1) {
					return -1;
				}
			}
		}
		return count;
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		int m = fs.nextInt();
		int n = fs.nextInt();
		int[][] grid = new int[m][n];
		for (int i = 0; i < m; ++i) {
			for (int j = 0; j < n; ++j) {
				grid[i][j] = fs.nextInt();
			}
		}

		System.out.println(getTimeToRotOptimized(grid, m, n));
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
