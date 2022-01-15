import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BoxStacking {
	static class Box implements Comparable<Box> {
		public final int length, width, height, area;

		public Box(int aLength, int aWidth, int aHeight) {
			length = aLength;
			width = aWidth;
			height = aHeight;
			area = length * width;
		}

		public int compareTo(Box o) {
			return o.area - area; // reverse order
		}
	}

	public static int getMaxHeight(Box[] boxes, int n) { // O(n^2) time, O(n) space
		Box[] allBoxes = new Box[3*n];
		for (int i = 0; i < n; ++i) {
			Box box = boxes[i];
			allBoxes[3*i] = new Box(box.length, box.width, box.height);
			allBoxes[3*i + 1] = new Box(box.height, box.length, box.width);
			allBoxes[3*i + 2] = new Box(box.width, box.height, box.length);
		}
		sort(allBoxes);
		int[] dp = new int[3*n];
		for (int i = 0; i < 3*n; ++i) {
			dp[i] = allBoxes[i].height;
		}
		for (int i = 1; i < 3*n; ++i) {
			for (int j = 0; j < i; ++j) {
				boolean canStack = allBoxes[j].area > allBoxes[i].area && allBoxes[j].length > allBoxes[i].length && allBoxes[j].width > allBoxes[i].width;
				if (canStack && dp[i] < dp[j] + allBoxes[i].height) {
					dp[i] = dp[j] + allBoxes[i].height;
				}
			}
		}
		int max = Integer.MIN_VALUE;
		for (int height : dp) {
			max = Math.max(max, height);
		}
		return max;
	}

	public static void sort(Box[] boxes) {
		List<Box> l = new ArrayList<>();
		for (Box box : boxes) {
			l.add(box);
		}
		Collections.sort(l);
		for (int i = 0; i < boxes.length; ++i) {
			boxes[i] = l.get(i);
		}
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		int n = fs.nextInt();
		int[] heights = new int[n];
		int[] widths = new int[n];
		int[] lengths = new int[n];
		for (int i = 0; i < n; ++i) {
			heights[i] = fs.nextInt();
		}
		for (int i = 0; i < n; ++i) {
			widths[i] = fs.nextInt();
		}
		for (int i = 0; i < n; ++i) {
			lengths[i] = fs.nextInt();
		}
		Box[] boxes = new Box[n];
		for (int i = 0; i < n; ++i) {
			boxes[i] = new Box(lengths[i], widths[i], heights[i]);
		}

		System.out.println(getMaxHeight(boxes, n));
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
