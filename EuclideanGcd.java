public class EuclideanGcd {
	public static int getGcd(int a, int b) { // O(log(min(a, b))) time
		if (a == 0) {
			return b;
		}
		return getGcd(b%a, a);
	}

	public static void main(String[] args) {
		System.out.println(getGcd(4, 2));
		System.out.println(getGcd(9, 4));
		System.out.println(getGcd(81, 9));
	}
}
