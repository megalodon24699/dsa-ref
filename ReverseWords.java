import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class ReverseWords {
	public static void reverse(char[] cs, int l, int r) {
		while (l < r) {
			char t = cs[l];
			cs[l] = cs[r];
			cs[r] = t;
			++l; --r;
		}
	}

	public static String reverseWords(String s) { // O(n) time, O(n) space
		char[] cs = s.toCharArray();
		int l = 0;
		for (int r = 0; r < cs.length; ++r) {
			if (cs[r] == '.') {
				reverse(cs, l, r-1);
				l = r+1;
			}
		}
		reverse(cs, l, cs.length-1);
		reverse(cs, 0, cs.length-1);
		return String.valueOf(cs);
	}

	public static String reverseWordsSplit(String s) { // O(n) time, O(n) space
		String[] words = s.split("\\.");
		String reversed = "";
		for (int i = words.length-1; i >= 0; --i) {
			reversed += words[i] + '.';
		}
		return reversed.substring(0, reversed.length()-1);
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		String s = fs.next();
		System.out.println(reverseWordsSplit(s));
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
