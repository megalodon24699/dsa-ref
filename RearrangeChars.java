import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class RearrangeChars {
	public static final int MAX_CHAR = 26;

	public static boolean rearrange(String s) { // O(n*log(n)) time, O(n) space (due to the string)
		PriorityQueue<Pair<Integer, Character>> pq = new PriorityQueue<>(new Comparator<Pair<Integer, Character>>() {
			public int compare(Pair<Integer, Character> x, Pair<Integer, Character> y) {
				return y.first - x.first;
			}
		});
		int[] counts = new int[MAX_CHAR];
		for (char c : s.toCharArray()) {
			++counts[c - 'a'];
		}
		for (int i = 0; i < MAX_CHAR; ++i) {
			if (counts[i] > 0) {
				pq.add(Pair.of(counts[i], (char)('a' + i)));
			}
		}
		StringBuilder sb = new StringBuilder();
		Pair<Integer, Character> last = Pair.of(0, '\0');
		while (!pq.isEmpty()) {
			Pair<Integer, Character> p = pq.poll();
			sb.append(p.second);
			if (last.first > 0) {
				pq.add(last);
			}
			last = Pair.of(p.first-1, p.second);
		}
		if (sb.length() != s.length()) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		String s = fs.next();

		System.out.println(rearrange(s));
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
