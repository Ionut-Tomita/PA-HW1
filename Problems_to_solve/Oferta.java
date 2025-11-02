import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.StringTokenizer;

public class Oferta {
	private static int minPrice = Integer.MAX_VALUE;
	private static int kthPrice = -1;

	public static void main(String[] args) {
		String inputPath = "oferta.in";
		String outputPath = "oferta.out";
		try {
			MyScanner scanner = new MyScanner(new FileReader(inputPath));
			PrintWriter fout = new PrintWriter((outputPath));


			int N = scanner.nextInt();
			int K = scanner.nextInt();
			int[] prices = new int[N];


			for (int i = 0; i < N; i++) {
				prices[i] = scanner.nextInt();
			}










			// Scrierea rezultatului
			fout.println(kthPrice);


			fout.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + e.getMessage());
		}
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
