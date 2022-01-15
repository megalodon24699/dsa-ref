import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class MeetingRoom {
	public static int getMeetings(int[] start, int[] finish, int n) { // O(n*log(n)) time, O(n) space (due to the list), can be made O(1)
		List<Pair<Integer, Integer>> meets = new ArrayList<>(n);
		for (int i = 0; i < n; ++i) {
			meets.add(Pair.of(start[i], finish[i]));
		}
		Collections.sort(meets, new Comparator<Pair<Integer, Integer>>() {
			public int compare(Pair<Integer, Integer> p, Pair<Integer, Integer> q) {
				return p.second - q.second;
			}
		});
		int count = 1, lastSelectedIdx = 0;
		for (int i = 1; i < n; ++i) {
			Pair<Integer, Integer> p = meets.get(lastSelectedIdx), q = meets.get(i);
			if (q.first > p.second) {
				++count;
				lastSelectedIdx = i;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		int n = fs.nextInt();
		int[] start = new int[n];
		int[] finish = new int[n];
		for (int i = 0; i < n; ++i) {
			start[i] = fs.nextInt();
		}
		for (int i = 0; i < n; ++i) {
			finish[i] = fs.nextInt();
		}

		System.out.println(getMeetings(start, finish, n));
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
