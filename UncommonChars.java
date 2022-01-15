import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class UncommonChars {
	public static String getUncommonChars(String sa, String sb) { // O(m+n) time, O(m+n) space
		Set<Character> setA = new HashSet<>();
		for (char c : sa.toCharArray()) {
			setA.add(c);
		}
		Set<Character> setB = new HashSet<>();
		for (char c : sb.toCharArray()) {
			setB.add(c);
		}
		StringBuilder res = new StringBuilder();
		for (char c : setA) {
			if (!setB.contains(c)) {
				res.append(c);
			}
		}
		for (char c : setB) {
			if (!setA.contains(c)) {
				res.append(c);
			}
		}
		return res.length() == 0 ? "-1" : res.toString();
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		String sa = fs.next();
		String sb = fs.next();

		System.out.println(getUncommonChars(sa, sb));
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
