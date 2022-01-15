import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class CheckPath {
	public static boolean canInclude(boolean[][] maze, int x, int y, boolean[][] visited) {
		int r = maze.length, c = maze[0].length;
		return 0 <= x && x < r && 0 <= y && y < c && maze[x][y] && !visited[x][y];
	}

	public static boolean getShortestDistance(boolean[][] maze, Pair<Integer, Integer> src, Pair<Integer, Integer> dest) { // O(n^2) time, O(n^2) space
		int n = maze.length;
		boolean[][] visited = new boolean[n][n];
		Deque<Pair<Integer, Integer>> queue = new ArrayDeque<>();
		
		visited[src.first][src.second] = true;
		queue.add(Pair.of(src.first, src.second));

		int[] rowOffset = {1, 0, -1, 0};
		int[] colOffset = {0, 1, 0, -1};
		
		while (!queue.isEmpty()) {
			Pair<Integer, Integer> p = queue.remove();
			if (p.equals(dest)) {
				return true;
			}
			for (int i = 0; i < 4; ++i) {
				int x = p.first + rowOffset[i], y = p.second + colOffset[i];
				if (canInclude(maze, x, y, visited)) {
					visited[x][y] = true;
					queue.add(Pair.of(x, y));
				}
			}
		}

		return false;
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		int n = fs.nextInt();
		int sx = 0, sy = 0, dx = 0, dy = 0;
		boolean[][] maze = new boolean[n][n];
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				int k = fs.nextInt();
				if (k == 1) {
					sx = i; sy = j;
					maze[i][j] = true;
				} else if (k == 2) {
					dx = i; dy = j;
					maze[i][j] = true;
				} else {
					maze[i][j] = (k == 0) ? false : true;
				}
			}
		}

		System.out.println(getShortestDistance(maze, Pair.of(sx, sy), Pair.of(dx, dy)));
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
