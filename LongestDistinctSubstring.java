import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class LongestDistinctSubstring {
	public static boolean isDistinct(String s, int l, int r) {
		boolean[] seen = new boolean[26];
		for (int i = l; i <= r; ++i) {
			int c = s.charAt(i) - 'a';
			if (seen[c]) {
				return false;
			}
			seen[c] = true;
		}
		return true;
	}

	public static int getLds(String s) { // O(n^3) time, O(1) space
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < s.length(); ++i) {
			for (int j = i; j < s.length(); ++j) {
				if (isDistinct(s, i, j)) {
					max = Math.max(max, j-i+1);
				}
			}
		}
		return max;
	}

	public static int getLdsOptimized(String s) { // O(n) time, O(1) space
		boolean[] seen = new boolean[26];
		int n = s.length();
		if (n == 0 || n == 1) {
			return n;
		}
		int l = 0, max = 1;
		for (int r = 0; r < n; ++r) {
			int c = s.charAt(r) - 'a';
			if (seen[c]) {
				while (s.charAt(l) != s.charAt(r)) {
					seen[s.charAt(l)-'a'] = false;
					++l;
				}
				++l;
			} else {
				seen[c] = true;
			}
			max = Math.max(max, r-l+1);
		}
		return max;
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		String s = fs.next();
		System.out.println(getLdsOptimized(s));
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
}
