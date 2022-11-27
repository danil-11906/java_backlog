import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Game {

	private static final int COUNT_EPOCH = 100;
	private static final double SPEED = 0.001;
	private static final String WEIGHTFILE = "v2.csv";
	private static final String XDATAFILE = "/home/yuliya/Desktop/sysanalisis/xdata.csv";
	private static final String YDATAFILE = "/home/yuliya/Desktop/sysanalisis/ydata04.csv";

	private double[][] pat;
	private double[] r;

	private double[] x;
	private double[] y;

	private double[][] wxh;
	private double[] why;
	private double[] h;
	private double[] f_h;

	public Game() {
		loadData();

		x = new double[pat[0].length];
		y = new double[pat.length];
		h = new double[2];
		f_h = new double[2];
		wxh = new double[x.length][h.length];
		why = new double[h.length];

		init();
		
		start_error();

		study();

		for (int p = 0; p < pat.length; p++) {
			for (int i = 0; i < x.length; i++) {
				x[i] = pat[p][i];
			}
			cy();
			System.out.println("y = " + y[p]);
		}
	}


	public void init() {
		System.out.println("Init");

		for (int i = 0; i < wxh.length; i++) {
			for (int j = 0; j < wxh[i].length; j++) {
				wxh[i][j] = Math.random() * 0.3 + 0.1;
				System.out.println("wxh[" + i + "][" + j + "] = " + wxh[i][j]);
			}
		}


		System.out.println("Finish");
		for (int i = 0; i < why.length; i++) {
			why[i] = Math.random() * 0.3 + 0.1;
			System.out.println("why[" + i + "] = " + why[i]);
		}
	}
	public double cy() {
		double y = 0;
		for (int i = 0; i < h.length; i++) {
			h[i] = 0;
			for (int j = 0; j < x.length; j++) {
				h[i] += x[j] * wxh[j][i];
			}
			f_h[i] = sigmaFunction(h[i]);
		}
		
		for (int i = 0; i < h.length; i++) {
			y += f_h[i] * why[i];
		}

		return sigmaFunction(y);
	}

	public void study() {
		double[] err = new double [h.length];
		double gEr = 10000;

		for (int epoch = 0; gEr > 0.001 && epoch < COUNT_EPOCH; epoch++) {
			gEr = 0;
			
			for (int p = 0; p < pat.length; p++) {
				System.arraycopy(pat[p], 0, x, 0, x.length);

				y[p] = cy();

				gEr += (r[p] - y[p]) * (r[p] - y[p]);

				for (int i = 0; i < x.length; i++) {
					for (int j = 0; j < h.length; j++) {
						wxh[i][j] = wxh[i][j] - (SPEED * dQ_by_dW_ij(i, j));
					}
				}

				for (int i = 0; i < h.length; i++) {
					why[i] = why[i] - (SPEED * dQ_by_dW_i(i));
				}
					
			}
			System.out.println("Epoch = " + epoch + " gErr = " + gEr);
		}

		System.out.println("************************************************ ");

		System.out.println("");
		for (int i = 0; i < wxh.length; i++) {
			for (int j = 0; j < wxh[i].length; j++) {
			System.out.println("wxh[" + i + "][" + j + "] = " + wxh[i][j]);
			}
		}

		System.out.println("");
		for (int i = 0; i < why.length; i++) {
			System.out.println("why[" + i + "] = " + why[i]);
		}
	}
	
	public void start_error() {
		double gEr = 0;
			
		for (int p = 0; p < pat.length; p++) {

			System.arraycopy(pat[p], 0, x, 0, x.length);

			y[p] = cy();

			gEr += (r[p] - y[p]) * (r[p] - y[p]);

		}
		System.out.println("gErr = " + gEr);
	}

	public static void main(String[] args) {
		Game perceptron = new Game();
	}


	public void loadData() {
		try {
			List<String> x_lines =
					Files.readAllLines(Paths.get(XDATAFILE));

			pat = new double[x_lines.size()][2];

			for (int i = 0; i < x_lines.size(); i++) {
				String[] x_pair = x_lines.get(i).split(";");
				pat[i][0] = Double.parseDouble(x_pair[0]);
				pat[i][1] = Double.parseDouble(x_pair[1]);
			}

			List<String> y_lines =
					Files.readAllLines(Paths.get(YDATAFILE));

			r = new double[y_lines.size()];

			for (int i=0; i<y_lines.size();i++) {
				r[i] = Double.parseDouble(y_lines.get(i));
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println("Number format exception");
			System.exit(-1);
		}
	}

	public double sigmaFunction(double x) {
		return (1 / (1 + Math.exp(-x)));
	}

	private double dQ_by_dW_i(int i) {
		double result = 0;

		for(int p = 0; p < pat.length; p++) {
			result += (y[p] - r[p])*y[p]*(1 - y[p])*f_h[i];
		}

		return result;
	}

	private double dQ_by_dW_ij(int i, int j) {
		double result = 0;

		for (int p = 0; p < pat.length; p++) {
			result += (y[p] - r[p]) * y[p] * (1 - y[p]) * f_h[j]*(1 - f_h[j])*why[j] * x[i];
		}

		return result;
	}
}