import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Ips {
	static List<String> ips;

	public static void getIpsAux(String s, String curr, int count) { // ? time, ? complexity
		if (s.length() == 0 && count == 4) {
			ips.add(curr.substring(0, curr.length()-1));
			return;
		}
		for (int i = 0; i < s.length(); ++i) {
			String p = s.substring(0, i+1);
			if (p.charAt(0) == '0' && p.length() > 1) {
				continue;
			}
			if (Integer.parseInt(p) < 256) {
				getIpsAux(s.substring(i+1), curr + p + '.', count+1);
			} else {
				return;
			}
		}
	}

	public static void getIps(String s) {
		ips = new ArrayList<>();
		getIpsAux(s, "", 0);
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		String s = fs.next();

		getIps(s);

		System.out.println(ips);
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
