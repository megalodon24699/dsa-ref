import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Permutation {
	public static String swap(String s, int i, int j) {
		char[] cs = s.toCharArray();
		char t = cs[i];
		cs[i] = cs[j];
		cs[j] = t;
		return String.valueOf(cs);
	}

	public static void printPermutations(String s, int l, int r) { // O(n*n!) time, O(n) space
		if (l == r) {
			System.out.println(s);
		} else {
			for (int i = l; i <= r; ++i) {
				s = swap(s, l, i);
				printPermutations(s, l+1, r);
				s = swap(s, l, i);
			}
		}
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		String s = fs.next();
		printPermutations(s, 0, s.length()-1);
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
