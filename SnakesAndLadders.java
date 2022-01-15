import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class SnakesAndLadders {
	public static final int BOARD_SIZE = 100;

	public static int getMinThrows(int[] moves) {
		boolean[] visited = new boolean[BOARD_SIZE];
		Deque<Pair<Integer, Integer>> queue = new ArrayDeque<>();
		queue.add(Pair.of(0, 0));
		visited[0] = true;
		while (!queue.isEmpty()) {
			Pair<Integer, Integer> p = queue.remove();
			int v = p.first, d = p.second;
			if (v == BOARD_SIZE - 1) {
				return d;
			}
			for (int i = v+1; i <= Math.min(v+6, BOARD_SIZE - 1); ++i) {
				if (!visited[i]) {
					visited[i] = true;
					queue.add(Pair.of(moves[i] == -1 ? i : moves[i], d + 1));
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		int n = fs.nextInt();
		int[] arr = new int[2*n];
		for (int i = 0; i < 2*n; ++i) {
			arr[i] = fs.nextInt();
		}
		int[] moves = new int[BOARD_SIZE];
		Arrays.fill(moves, -1);
		for (int i = 0; i < 2*n; i += 2) {
			moves[arr[i]] = arr[i+1];
		}

		System.out.println(getMinThrows(moves));
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
