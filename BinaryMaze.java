import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BinaryMaze {
	public static boolean canInclude(boolean[][] maze, int x, int y, boolean[][] visited) {
		int r = maze.length, c = maze[0].length;
		return 0 <= x && x < r && 0 <= y && y < c && maze[x][y] && !visited[x][y];
	}

	public static int getShortestDistance(boolean[][] maze, Pair<Integer, Integer> dest) { // O(m*n) time, O(m*n) space
		// Can be generalized to start from any source
		if (!maze[0][0] || !maze[dest.first][dest.second]) {
			return -1;
		}
		
		int m = maze.length, n = maze[0].length;
		boolean[][] visited = new boolean[m][n];
		Deque<Pair<Pair<Integer, Integer>, Integer>> queue = new ArrayDeque<>();
		
		visited[0][0] = true;
		queue.add(Pair.of(Pair.of(0, 0), 0));

		int[] rowOffset = {1, 0, -1, 0};
		int[] colOffset = {0, 1, 0, -1};
		
		while (!queue.isEmpty()) {
			Pair<Pair<Integer, Integer>, Integer> p = queue.remove();
			Pair<Integer, Integer> pt = p.first;
			int distance = p.second;
			if (pt.equals(dest)) {
				return distance;
			}
			for (int i = 0; i < 4; ++i) {
				int x = pt.first + rowOffset[i], y = pt.second + colOffset[i];
				if (canInclude(maze, x, y, visited)) {
					visited[x][y] = true;
					queue.add(Pair.of(Pair.of(x, y), distance + 1));
				}
			}
		}

		return -1;
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		int m = fs.nextInt();
		int n = fs.nextInt();
		int dx = fs.nextInt(), dy = fs.nextInt();
		boolean[][] maze = new boolean[m][n];
		for (int i = 0; i < m; ++i) {
			for (int j = 0; j < n; ++j) {
				maze[i][j] = (fs.nextInt() == 0) ? false : true;
			}
		}

		System.out.println(getShortestDistance(maze, Pair.of(dx, dy)));
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
