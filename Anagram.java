import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Anagram {
	public static boolean isAnagram(String a, String b) {
		if (a.length() != b.length()) {
			return false;
		}
		int n = a.length();
		int[] hist = new int[26];
		for (int i = 0; i < n; ++i) {
			hist[a.charAt(i) - 'a']++;
			hist[b.charAt(i) - 'a']--;
		}
		for (int i = 0; i < 26; ++i) {
			if (hist[i] != 0) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		String a = fs.next();
		String b = fs.next();

		System.out.println(isAnagram(a, b));
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
