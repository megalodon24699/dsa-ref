import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class FloodFill {
	public static boolean isValid(Pair<Integer, Integer> pt, Pair<Integer, Integer> bounds) {
		return 0 <= pt.first && pt.first < bounds.first && 0 <= pt.second && pt.second < bounds.second;
	}

	public static boolean isSameColor(int[][] matrix, Pair<Integer, Integer> p, int color) {
		Pair<Integer, Integer> bounds = Pair.of(matrix.length, matrix[0].length);
		return isValid(p, bounds) && matrix[p.first][p.second] == color;
	}

	public static void floodFill(int[][] matrix, Pair<Integer, Integer> start, int color) { // O(m*n) time, O(m*n) space
		int original = matrix[start.first][start.second];
		Deque<Pair<Integer, Integer>> queue = new ArrayDeque<>();
		queue.add(start);
		matrix[start.first][start.second] = color;
		while (!queue.isEmpty()) {
			Pair<Integer, Integer> pt = queue.remove();
			Pair<Integer, Integer> right, top, left, bottom;
			right = Pair.of(pt.first + 1, pt.second);
			top = Pair.of(pt.first, pt.second + 1);
			left = Pair.of(pt.first - 1, pt.second);
			bottom = Pair.of(pt.first, pt.second - 1);
			if (isSameColor(matrix, right, original)) {
				matrix[right.first][right.second] = color;
				queue.add(right);
			}
			if (isSameColor(matrix, top, original)) {
				matrix[top.first][top.second] = color;
				queue.add(top);
			}
			if (isSameColor(matrix, left, original)) {
				matrix[left.first][left.second] = color;
				queue.add(left);
			}
			if (isSameColor(matrix, bottom, original)) {
				matrix[bottom.first][bottom.second] = color;
				queue.add(bottom);
			}
			matrix[pt.first][pt.second] = color;
		}
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		int m = fs.nextInt();
		int n = fs.nextInt();
		int color = fs.nextInt();
		Pair<Integer, Integer> start = Pair.of(fs.nextInt(), fs.nextInt());
		int[][] matrix = new int[m][n];
		for (int i = 0; i < m; ++i) {
			for (int j = 0; j < n; ++j) {
				matrix[i][j] = fs.nextInt();
			}
		}

		floodFill(matrix, start, color);

		for (int i = 0; i < m; ++i) {
			System.out.println(Arrays.toString(matrix[i]));
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
