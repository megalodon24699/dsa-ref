import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class RecursivelyRemoveAdjacent {
	public static boolean hasDuplicates(String s) {
		for (int i = 0; i < s.length()-1; ++i) {
			if (s.charAt(i) == s.charAt(i+1)) {
				return true;
			}
		}
		return false;
	}

	public static String removeAdjacent(String s) { // ??
		StringBuilder sb = new StringBuilder();
		while (hasDuplicates(s)) {
			sb.setLength(0);
			s = ' ' + s + ' ';
			for (int i = 1; i < s.length()-1; ++i) {
				if (s.charAt(i-1) != s.charAt(i) && s.charAt(i) != s.charAt(i+1)) {
					sb.append(s.charAt(i));
				}
			}
			s = sb.toString();
		}
		return s;
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		String s = fs.next();
		System.out.println(removeAdjacent(s));
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
