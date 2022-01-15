import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Sudoku {
	public static boolean canEnter(int[][] board, int r, int c, int x) {
		int n = board.length;
		for (int i = 0; i < n; ++i) {
			if (board[r][i] == x) {
				return false;
			}
		}
		for (int i = 0; i < n; ++i) {
			if (board[i][c] == x) {
				return false;
			}
		}
		int box = (int)Math.sqrt(n);
		int rs = r - r % box, cs = c - c % box;
		for (int i = rs; i < rs + box; ++i) {
			for (int j = cs; j < cs + box; ++j) {
				if (board[i][j] == x) {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean solve(int[][] board) { // O(9^(n^2)) time, O(n^2) space
		int n = board.length;
		boolean isSolved = true;
		int r = -1, c = -1;
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				if (board[i][j] == 0) {
					isSolved = false;
					r = i; c = j;
					break;
				}
			}
			if (!isSolved) {
				break;
			}
		}
		if (isSolved) {
			return true;
		}
		for (int x = 1; x <= n; ++x) {
			if (canEnter(board, r, c, x)) {
				board[r][c] = x;
				if (solve(board)) {
					return true;
				} else {
					board[r][c] = 0;
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		int n = fs.nextInt();
		int[][] board = new int[n][n];
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				board[i][j] = fs.nextInt();
			}
		}

		solve(board);

		for (int[] row : board) {
			System.out.println(Arrays.toString(row));
		}
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
