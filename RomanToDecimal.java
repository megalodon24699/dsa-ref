import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class RomanToDecimal {
	public static int getValue(char c) {
		switch (c) {
			case 'I':
				return 1;
			case 'V':
				return 5;
			case 'X':
				return 10;
			case 'L':
				return 50;
			case 'C':
				return 100;
			case 'D':
				return 500;
			case 'M':
				return 1000;
		}
		return -1;
	}

	public static int romanToDecimal(String s) { // O(n) time, O(1) space
		int dec = 0;
		for (int i = 0; i < s.length()-1; ++i) {
			int currVal = getValue(s.charAt(i));
			int nextVal = getValue(s.charAt(i+1));
			if (currVal < nextVal) {
				dec -= currVal;
			} else {
				dec += currVal;
			}
		}
		dec += getValue(s.charAt(s.length()-1));
		return dec;
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		String s = fs.next();
		System.out.println(romanToDecimal(s));
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
