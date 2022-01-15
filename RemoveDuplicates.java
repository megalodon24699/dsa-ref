import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class RemoveDuplicates {
	public static String removeDuplicates(String s) { // O(n) time, O(n) space (O(1) if only a predefined set of chars)
		Set<Character> set = new HashSet<>();
		String result = "";
		for (char c : s.toCharArray()) {
			if (!set.contains(c)) {
				set.add(c);
				result += c;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		String s = fs.next();

		System.out.println(removeDuplicates(s));
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
