package quickHull1;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

//import SimpleGraph.GraphCanvas;
class SimpleGraph extends JFrame {
	private final int WIDTH = 960;
	private final int HEIGHT = 960;
	private Container drawable;
	private JPanel canvas;

	public SimpleGraph(double[] someData) {
		super("Quickhull algorithm, By Mohammed and Suliman");
		drawable = getContentPane();
		canvas = new GraphCanvas(someData);
		drawable.add(canvas);
		setSize(WIDTH, HEIGHT);
	}

	public class GraphCanvas extends JPanel {
		private double[] data;
		private int points;
		private double[] XData;
		private double[] YData;

		public GraphCanvas(double[] someData) {
			super();
			data = someData;
			points = data.length / 2;
			XData = new double[points];
			YData = new double[points];
			for (int i = 0; i < points; i++) {
				XData[i] = data[i * 2];
				YData[i] = data[i * 2 + 1];
			}
		}

		Line2D line;
		Polygon p = new Polygon(); 
		public void paint(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			int j = 3, k = 4;
			for (int i = 0; i < points - 1; i++) {
				int x0 = (int) (XData[i]);
				int x1 = (int) (XData[i + 1]);
				int y0 = (int) (YData[i]);
				int y1 = (int) (YData[i + 1]);
				// g2.drawLine(x0, y0, x1, y1);;
				line = new Line2D.Double(x0, y0, x1, y1);
				p.addPoint(100, 100);
				AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(360), line.getX1(), line.getY1());
				g2.draw(at.createTransformedShape(line));
//				g2.draw(p);

				// write point axis
				// if (i == 0)
				// g2.drawString(("" + x0 + ", " + y0), x0+100 - 20, y0+100 +
				// 10);
				g2.drawString(("" + x1 + ", " + y1), x1 - 20, y1 + 10);
				// g2.drawString(("" + x0 + ", " + y0), x0 - 20, y0 + 10);
				if (!(i == points - 2))
					g2.drawString(("" + x1 + ", " + y1), x1 - 20, y1 + 10);
			}
		}
	}

	// public static void main(String[] args) {
	// double[] d = { 50, 50, 180, 130, 200, 150, 100, 100, 150, 50, 100, 200.0,
	// 250.0, 150.0, 230.0, 180.0, 150.0,
	// 150.0, 200.0, 100.0 };
	// double quickhullPoints[] = { 50, 50, 150, 50, 200.0, 100.0, 250.0, 150.0,
	// 230.0, 180.0, 100, 200.0, 50, 50 };
	// // Frame f = new SimpleGraph(quickhullPoints);
	// // f.show();
	// }
}
