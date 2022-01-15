import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ChooseAndSwap {
	public static final int MAX_CHARS = 26;

	public static String swapChars(String s, char c, char d) {
		StringBuilder sb = new StringBuilder();
		for (char i : s.toCharArray()) {
			if (i == c) {
				sb.append(d);
			} else if (i == d) {
				sb.append(c);
			} else {
				sb.append(i);
			}
		}
		return sb.toString();
	}

	public static String getMinString(String s) { // O(n) time, O(1) space (ignoring space to swap)
		int[] idxs = new int[MAX_CHARS];
		Arrays.fill(idxs, -1);
		for (int i = 0; i < s.length(); ++i) {
			int idx = s.charAt(i) - 'a';
			if (idxs[idx] == -1) {
				idxs[idx] = i;
			}
		}
		for (int i = 0; i < s.length(); ++i) {
			int idx = s.charAt(i) - 'a';
			for (int j = 0; j < idx; ++j) {
				if (idxs[j] > i) {
					return swapChars(s, (char)(j+'a'), s.charAt(i));
				}
			}
		}
		return s;
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		String s = fs.next();

		System.out.println(getMinString(s));
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
