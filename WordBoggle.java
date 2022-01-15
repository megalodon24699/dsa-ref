import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class WordBoggle {
	static boolean[][] visited;
	static List<String> words;

	static int[] colOffset = {1, 1, 0, -1, -1, -1, 0, 1};
	static int[] rowOffset = {0, 1, 1, 1, 0, -1, -1, -1};

	public static boolean getWordsAux(char[][] grid, String word, int idx, int r, int c) {
		if (idx == word.length()) {
			return true;
		}
		int m = grid.length, n = grid[0].length;
		if (0 <= r && r < m && 0 <= c && c < n && !visited[r][c] && word.charAt(idx) == grid[r][c]) {
			visited[r][c] = true;
			for (int i = 0; i < 8; ++i) {
				if (getWordsAux(grid, word, idx+1, r + rowOffset[i], c + colOffset[i])) {
					return true;
				}
			}
			visited[r][c] = false;
		}
		return false;
	}

	public static void getWords(char[][] grid, String[] ws) { // ? time, ? space
		int m = grid.length, n = grid[0].length;
		words = new ArrayList<>();
		visited = new boolean[m][n];
		for (String word : ws) {
			boolean found = false;
			for (int i = 0; i < m && !found; ++i) {
				for (int j = 0; j < n && !found; ++j) {
					if (grid[i][j] == word.charAt(0)) {
						if (getWordsAux(grid, word, 0, i, j)) {
							found = true;
							words.add(word);
							break;
						}
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		int n = fs.nextInt();
		int rs = fs.nextInt(), cs = fs.nextInt();
		String[] ws = new String[n];
		char[][] grid = new char[rs][cs];
		for (int i = 0; i < n; ++i) {
			ws[i] = fs.next();
		}
		for (int i = 0; i < rs; ++i) {
			for (int j = 0; j < cs; ++j) {
				grid[i][j] = fs.nextChar();
			}
		}

		getWords(grid, ws);

		System.out.println(words);
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

		char nextChar() {
			return next().charAt(0);
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
