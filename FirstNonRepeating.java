import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class FirstNonRepeating {
	// Complexities are on per-character basis
	public static String getFirstNonRepeating(String s) { // O(1) time, O(1) space
		StringBuilder sb = new StringBuilder();
		int[] hist = new int[26];
		List<Character> nonRepeating = new LinkedList<>();
		for (char c : s.toCharArray()) {
			int i = c - 'a';
			if (hist[i] == 0) {
				nonRepeating.add(c);
			} else if (hist[i] == 1) {
				nonRepeating.remove(Character.valueOf(c)); // I assume this step to be O(1) (if I implemented DLL)
			}
			++hist[i];
			sb.append(nonRepeating.isEmpty() ? '#' : nonRepeating.get(0)); // get is assumed to be O(1)
		}
		return sb.toString();
	}

	public static String getFirstNonRepeatingQueue(String s) { // O(n) time, O(1) space (O(1) time amortized?)
		StringBuilder sb = new StringBuilder();
		int[] hist = new int[26];
		Deque<Character> queue = new ArrayDeque<>();
		for (char c : s.toCharArray()) {
			++hist[c - 'a'];
			queue.add(c);
			while (!queue.isEmpty() && hist[queue.getFirst() - 'a'] > 1) {
				queue.remove();
			}
			sb.append(queue.isEmpty() ? '#' : queue.getFirst());
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		String s = fs.next();
		System.out.println(getFirstNonRepeatingQueue(s));
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
