import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class GetMinStack {
	static class MinStack {
		private static final int MAX = 101;
		private Deque<Integer> stack;
		private int min = MAX;

		public MinStack() {
			stack = new ArrayDeque<>();
		}

		public int peek() { // O(1) time, O(1) space
			if (stack.isEmpty()) {
				return -1;
			}
			return stack.peek() / MAX;
		}

		public void push(int data) { // O(1) time, O(1) space
			min = Math.min(min, data);
			stack.push(data * MAX + min);
		}

		public int pop() { // O(1) time, O(1) space
			if (stack.isEmpty()) {
				return -1;
			}
			int data = stack.pop() / MAX;
			min = stack.isEmpty() ? MAX : peek() % MAX;
			return data;
		}

		public int getMin() { // O(1) time, O(1) space
			return (min == MAX) ? -1 : min;
		}

		@Override
		public String toString() {
			return stack.toString();
		}
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		MinStack stack = new MinStack();
		int n = fs.nextInt();
		for (int i = 0; i < n; ++i) {
			int cmd = fs.nextInt(); // 1 -> PUSH, 2 -> POP, 3 -> MIN
			if (cmd == 1) {
				stack.push(fs.nextInt());
			} else if (cmd == 2) {
				System.out.println(stack.pop());
			} else {
				System.out.println(stack.getMin());
			}
		}
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
