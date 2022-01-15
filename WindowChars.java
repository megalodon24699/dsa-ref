import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

import java.util.Arrays;

public class WindowChars {
	public static int getMinWindow(String s, String p) { // O(n) time, O(1) space
		int[] pHist = new int[26];
		int pCount = 0;
		for (char c : p.toCharArray()) {
			++pHist[c - 'a'];

			// if (pHist[c - 'a'] == 1) { // unique (can also be used to find min substring containing all chars of the string)
			// 	continue;
			// }
			// pHist[c - 'a'] = 1;
			// ++pCount;
		}
		System.out.println(pCount);
		int[] sHist = new int[26];
		int min = Integer.MAX_VALUE, l = 0;
		int count = 0;
		for (int r = 0; r < s.length(); ++r) {
			char c = s.charAt(r);
			int idx = c - 'a';
			++sHist[idx];
			if (sHist[idx] <= pHist[idx]) {
				++count;
			}
			if (count == pCount) {
				int lIdx = s.charAt(l) - 'a';
				while (sHist[lIdx] > pHist[lIdx] || pHist[lIdx] == 0) {
					if (sHist[lIdx] > pHist[lIdx]) {
						--sHist[lIdx];
					}
					lIdx = s.charAt(++l) - 'a';
				}
				min = Math.min(min, r-l+1);
			}
		}
		return min == Integer.MAX_VALUE ? -1 : min;
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		String s = fs.next();
		String p = fs.next();

		System.out.println(getMinWindow(s, p));
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
