import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Servere {

	public static int nr_servers = 0;
	public static List<Double> powers = new ArrayList<>();
	public static List<Double> currents = new ArrayList<>();

	public static void main(String[] args) {
		String inputPath = "servere.in";
		String outputPath = "servere.out";
		try {

			MyScanner fin = new MyScanner(new FileReader(inputPath));
			final PrintWriter fout = new PrintWriter((outputPath));

			// read the input
			nr_servers = fin.nextInt();
			for (int i = 0; i < nr_servers; i++) {
				powers.add(fin.nextDouble());
			}
			for (int i = 0; i < nr_servers; i++) {
				currents.add(fin.nextDouble());
			}

			// find the minimum and maximum current
			double min = currents.get(0);
			double max = min;

			for (int i = 1; i < nr_servers; i++) {
				if (currents.get(i) < min) {
					min = currents.get(i);
				}
				if (currents.get(i) > max) {
					max = currents.get(i);
				}
			}

			// average current
			double middle = (min + max) / 2;

			double best_power = getBestPower(min, max, middle);

			fout.println(String.format("%.1f", best_power));

			fout.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + e.getMessage());
		}
	}

	private static double getBestPower(double left, double right, double middle) {

		if (Math.abs(right - left) < 0.01) {
			return calculateMaxSystemPower(powers, currents, middle);
		}
		// left and  right side of the middle
		double leftSide = calculateMaxSystemPower(powers, currents, middle - 1);
		double rightSide = calculateMaxSystemPower(powers, currents, middle + 1);

		if (leftSide > rightSide) {
			// search in the left half
			return getBestPower(left, middle, (left + middle) / 2);
		} else {
			// search in the right half
			return getBestPower(middle, right, (middle + right) / 2);
		}
	}

	/*
	 * Calculate the maximum power of the system
	 * return the maximum power
	 */
	private static double calculateMaxSystemPower(
			List<Double> powers, List<Double> currents, double avgCurrent) {

		double maxSystemPower = Double.POSITIVE_INFINITY;

		for (int i = 0; i < nr_servers; i++) {
			double finalPower = powers.get(i) - Math.abs(currents.get(i) - avgCurrent);

			if (finalPower < maxSystemPower) {
				maxSystemPower = finalPower;
			}
		}

		return maxSystemPower;
	}
}

class MyScanner {
	private BufferedReader br;
	private StringTokenizer st;

	public MyScanner(Reader reader) {
		br = new BufferedReader(reader);
	}

	public String next() {
		while (st == null || !st.hasMoreElements()) {
			try {
				st = new StringTokenizer(br.readLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return st.nextToken();
	}

	public int nextInt() {
		return Integer.parseInt(next());
	}

	public long nextLong() {
		return Long.parseLong(next());
	}

	public double nextDouble() {
		return Double.parseDouble(next());
	}

	public String nextLine() {
		String str = "";
		try {
			str = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}
}

