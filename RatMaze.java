import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class RatMaze {
	static List<String> paths;
	static boolean[][] visited;

	public static void getPathsAux(int[][] maze, int r, int c, String path) {
		int m = maze.length, n = maze[0].length;
		if (r < 0 || r >= m || c < 0 || c >= n || maze[r][c] == 0 || visited[r][c]) {
			return;
		}
		if (r == m-1 && c == n-1) {
			paths.add(path);
			return;
		}
		visited[r][c] = true;
		getPathsAux(maze, r, c+1, path + 'R');
		getPathsAux(maze, r-1, c, path + 'U');
		getPathsAux(maze, r, c-1, path + 'L');
		getPathsAux(maze, r+1, c, path + 'D');
		visited[r][c] = false;
	}

	public static void getPaths(int[][] maze) { // O(3^(n^2)) time, O(|path|*|paths|) space
		int m = maze.length, n = maze[0].length;
		visited = new boolean[m][n];
		paths = new ArrayList<>();
		getPathsAux(maze, 0, 0, "");
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		int m = fs.nextInt(), n = fs.nextInt();
		int[][] maze = new int[m][n];
		for (int i = 0; i < m; ++i) {
			for (int j = 0; j < n; ++j) {
				maze[i][j] = fs.nextInt();
			}
		}

		getPaths(maze);

		System.out.println(paths);
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
