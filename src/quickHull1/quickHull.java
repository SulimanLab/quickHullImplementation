package quickHull1;

import java.awt.Frame;
import java.lang.reflect.Array;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class quickHull {

	@SuppressWarnings("deprecation")
	public static void main(String args[]) {

		quickHull q = new quickHull();

		LinkedList<Point> Pmaxs = new LinkedList<Point>();
		LinkedList<Point> Pmaxs2 = new LinkedList<Point>();
		Scanner reader = new Scanner(System.in);
		// Point points[] = new Point[6];

		impTheHull im = new impTheHull();

		//
		// points[0] = new Point(0, 0);
		//
		// points[1] = new Point(0, 4);
		//
		// points[2] = new Point(-4, 0);
		//
		// points[3] = new Point(5, 0);
		//
		// points[4] = new Point(0, -6);
		//
		// points[5] = new Point(1, 0);

		System.out.println("please enter the number of points ");
		int size = reader.nextInt();
		Point p[] = new Point[size];
		p = im.addPointsAndSort(size);

		System.out.println(p.length + ">>>");
		for (Point point : p) {
			System.out.println(point.getX());
		}
		
		try {

			Pmaxs = new LinkedList<Point>(im.findHull(p[0], p[p.length - 1], p));

			Pmaxs2 = new LinkedList<Point>(im.findHull(p[p.length - 1], p[0], p));

			Pmaxs.add(p[0]);

			Pmaxs.add(p[p.length - 1]);

			// for (int i = 0; i < Pmaxs2.size(); i++) {
			// if (!Pmaxs.contains(Pmaxs2.get(i)))
			// Pmaxs.add(Pmaxs2.get(i));
			// // System.out.println("Point(" + Pmaxs2.get(i).getX() + "," +
			// // Pmaxs2.get(i).getY() + ")2");
			// }

			for (int i = 0; i < Pmaxs2.size(); i++) {

				if (Pmaxs.contains(Pmaxs2.get(i)))
					Pmaxs2.remove(i--);

			}
//			for (int i = 0; i < Pmaxs2.size(); i++) {
//
//				if (Pmaxs.contains(Pmaxs2.get(i)))
//					Pmaxs2.remove(i);
//
//			}
			
			Point min = Pmaxs.get(0);
			int mini = 0;
			for (int i = 1; i < Pmaxs.size(); i++) {
				if (Pmaxs.get(i).getY() < min.getY()) {
					min = Pmaxs.get(i);
					mini = i;
				}
			}

			Point max = Pmaxs2.get(0);
			int maxi = 0;
			for (int i = 1; i < Pmaxs2.size(); i++) {
				if (Pmaxs2.get(i).getY() > max.getY()) {
					max = Pmaxs2.get(i);
					maxi = i;
				}
			}
			if (max.getY() > min.getY()) {
				Pmaxs.add(Pmaxs2.get(maxi));
				Pmaxs2.remove(maxi);
			}

//			for (int i = 0; i < Pmaxs.size() - 1; i++) {
//				for (int j = i + 1; j < Pmaxs.size(); j++) {
//					if (Pmaxs.get(i).getX() == Pmaxs.get(j).getX() && Pmaxs.get(i).getY() == Pmaxs.get(j).getY()) {
//						Pmaxs.remove(j);
//					}
//				}
//			}
			
			Point [] ppp1 = new Point[Pmaxs.size()];
			Point [] ppp2 = new Point[Pmaxs.size()];
			for (int i = 0; i< ppp1.length;i++){
				ppp1[i] = Pmaxs.get(i);
			}
			ppp2 = im.sort(ppp1);
			// for (int i = 0; i<Pmaxs.size();i++){
			// if (Pmaxs.contains(Pmaxs.get(i)))
			// Pmaxs.remove(i);
			// }

			// for (int i = 0; i < Pmaxs.size(); i++) {
			//
			// if (Pmaxs2.contains(Pmaxs.get(i)))
			// Pmaxs.remove(i);
			//
			// }

			double[] drawPs = new double[((Pmaxs.size() * 2) + (Pmaxs2.size() * 2)) + 2];
			System.out.println((Pmaxs.size() * 2) + (Pmaxs2.size() * 2) + " ===== " + drawPs.length);
			int j = 0;

			for (int i = 0; i < Pmaxs.size(); i++) {
				drawPs[i + (j++)] = Pmaxs.get(i).getX();
				drawPs[i + j] = Pmaxs.get(i).getY();
			}
			System.out.println(drawPs.length);
			j = drawPs.length - 3;
			for (int i = 0; i < Pmaxs2.size(); i++) {

				drawPs[j--] = Pmaxs2.get(i).getY();
				drawPs[(j--)] = Pmaxs2.get(i).getX();
				System.out.println(Pmaxs2.get(i).getX() + "im x");

			}
			double temp, temp2;
			for (int l = 0; l < Pmaxs.size() * 2; l += 2) {
				for (int s = l + 2; s < Pmaxs.size() * 2 - 2; s += 2) {
					if (drawPs[l] > drawPs[s]) {
						temp = drawPs[s];
						temp2 = drawPs[s + 1];
						drawPs[s] = drawPs[l];
						drawPs[s + 1] = drawPs[l + 1];
						drawPs[l] = temp;
						drawPs[l + 1] = temp2;
					}
				}
			}

			for (int l = Pmaxs.size() * 2; l < (Pmaxs2.size() * 2) + (Pmaxs.size() * 2); l += 2) {
				System.out.println(l + " <><><>SAD<AS>D<AS>D<AS>D<A>SD<AS>D<A>S<D>AS<D>A");
				for (int s = l + 2; s < (Pmaxs2.size() * 2) + (Pmaxs.size() * 2); s += 2) {
					System.out.println(s + " <><><>SAD<AS>D<AS>D<AS>D<A>SD<AS>D<A>S<D>AS<D>Asdfasdf");
					if (drawPs[l] < drawPs[s]) {
						temp = drawPs[s];
						temp2 = drawPs[s + 1];
						drawPs[s] = drawPs[l];
						drawPs[s + 1] = drawPs[l + 1];
						drawPs[l] = temp;
						drawPs[l + 1] = temp2;
					}
				}
			}
			drawPs[drawPs.length - 2] = drawPs[0];
			drawPs[drawPs.length - 1] = drawPs[1];
			for (int i = 0; i < drawPs.length; i++) {
				System.out.println(drawPs[i] + "," + drawPs[++i]);
			}

			Frame f = new SimpleGraph(drawPs);
			f.show();

			for (int i = 0; i < Pmaxs.size(); i++) {
				System.out.println("Point(" + Pmaxs.get(i).getX() + "," + Pmaxs.get(i).getY() + ")1");
			}

			for (int i = 0; i < Pmaxs2.size(); i++) {
				System.out.println("Point(" + Pmaxs2.get(i).getX() + "," + Pmaxs2.get(i).getY() + ")2");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			System.out.println("there is no convexHull for these points");
		}

	}
}