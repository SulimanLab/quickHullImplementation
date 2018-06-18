package quickHull1;

import java.util.LinkedList;
import java.util.Scanner;

public class impTheHull {
	Point p = new Point();
	Scanner reader;
	Point[] points;
	LinkedList<Point> Pmaxs = new LinkedList<Point>();
	int size = 1;

	public Point[] sort(Point[] arr) {
		Point temp = null;

		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j].getX() <= arr[i].getX()) {
					temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}

		return arr;
	}

	public LinkedList<Point> findHull(Point p1, Point p2, Point[] px) {
		if (px.length < 2)
			return null;
		Point Pmax = null;
		//
		if (px.length < 2) {
			System.out.println("im null2***************");
			return null;
		}

		Point[] leftOfLine = getLeftOfLine(p1, p2, px);
		if (leftOfLine.length < 2) {
			System.out.println("im null>>>>>>>>>>><<<<<<<<<<<");
			return null;
		}
		if (leftOfLine.length == 1) {
			if (leftOfLine[1] != null) {
				Pmax = leftOfLine[1];
				if (!Pmaxs.contains(Pmax))
					Pmaxs.add(Pmax);
			}
			System.out.println("im null>>>>>>>>>>>");
			return null;
		}

		Pmax = findPmax(p1, p2, leftOfLine);
		if (!Pmaxs.contains(Pmax))
			Pmaxs.add(Pmax);

		findHull(p1, Pmax, leftOfLine);
		findHull(Pmax, p2, leftOfLine);

		return Pmaxs;
	}

	public Point findPmax(Point p1, Point p2, Point[] A) {
		double max = Math.sqrt(((p2.getX() - p1.getX()) * (A[0].getY() - p1.getY()))
				- ((A[0].getX() - p1.getX()) * (p2.getY() - p1.getY())));
		Point Pmax = A[0];
		double area;
		System.out.println(A.length);
		for (int i = 0; i < A.length; i++) {
			System.out.println(i);
			if (A[i] != null) {
				area = Math.sqrt((p2.getX() - p1.getX()) * (A[i].getY() - p1.getY())
						- (A[i].getX() - p1.getX()) * (p2.getY() - p1.getY()));
				if (area > max) {
					Pmax = A[i];
					max = area;
				}
			}

		}

		return Pmax;
	}

	public LinkedList<Point> LinkedPmaxs() {
		return Pmaxs;
	}

	public Point[] getLeftOfLine(Point p1, Point p2, Point[] A) {

		size = 1;
		Point[] left = new Point[A.length];
		double check = -1;
		int j = 0;
		for (int i = 0; i < A.length; i++) {
			if (p1.equals(A[i]) || p2.equals(A[i]))
				continue;

			if (A[i] != null) {
				check = ((p2.getX() - p1.getX()) * (A[i].getY() - p1.getY()))
						- ((p2.getY() - p1.getY()) * (A[i].getX() - p1.getX()));
				System.out.println(check);
				if (check >= 0) {
					left[j++] = A[i];
				}
			}
		}

		for (int i = 0; i < left.length; i++) {
			if (left[i] != null) {
				size++;
			} else
				break;
		}

		Point[] leftOfLine = new Point[size];
		for (int i = 0; i < size; i++) {
			leftOfLine[i] = left[i];
		}
		return leftOfLine;
	}

	// public Point[] getRightOfLine(Point p1, Point p2, Point[] A) {
	// // double vx = getVectorX(p1, p2);
	// // double vy = getVectorY(p1, p2);
	// // double lpp = lengthBetweenTwoPoint(p1, p2);
	// size = 1;
	// Point[] left = new Point[A.length];
	// double check = 0;
	// int j = 0;
	// for (int i = 0; i < A.length - 1; i++) {
	// // double ux = getVectorX(p1, A[i]);
	// // double uy = getVectorY(p1, A[i]);
	// check = (p2.getX() - p1.getX()) * (A[i].getY() - p1.getY())
	// - (p2.getY() - p1.getY()) * (A[i].getX() - p1.getX());
	// System.out.println(check + "<<<<<<<<<<<<<<<check");
	// if (check > 0) {
	// left[j++] = A[i];
	// }
	// }
	// System.out.println(left.length + "length of left");
	//
	// for (int i = 0; i < left.length; i++) {
	// if (left[i] != null) {
	// size++;
	// } else
	// break;
	// }
	// System.out.println(size + "im the size");
	// Point[] leftOfLine = new Point[size];
	// for (int i = 0; i < size; i++) {
	// leftOfLine[i] = left[i];
	// System.out.println(i);
	// }
	// return leftOfLine;
	// // size = 1;
	// // Point[] right = new Point[A.length];
	// // double check = 0;
	// // int j = 0;
	// // for (int i = 1; i < A.length - 1; i++) {
	// // // double ux = getVectorX(p1, A[i]);
	// // // double uy = getVectorY(p1, A[i]);
	// // check = (p2.getX() - p1.getX()) * (A[i].getY() - p1.getY())
	// // - (p2.getY() - p1.getY()) * (A[i].getX() - p1.getX());
	// // System.out.println(check + "<<<<<<<<<<<<<<<check");
	// // if (check < 0) {
	// // right[j++] = A[i];
	// // }
	// // }
	// // System.out.println(right.length + "length of left");
	// //
	// // for (int i = 0; i < right.length; i++) {
	// // if (right[i] != null) {
	// // size++;
	// // } else
	// // break;
	// // }
	// // System.out.println(size + "im the size");
	// // Point[] rightOfLine = new Point[size];
	// // for (int i = 0; i < size; i++) {
	// // rightOfLine[i] = right[i];
	// // System.out.println(i);
	// // }
	// // return rightOfLine;
	// }

	// public double lengthBetweenTwoPoint(Point p1, Point p2) {
	// System.out.println(p1.getX() + "im x of p1 ");
	// System.out.println(p1.getY() + "im y of p1 ");
	// System.out.println(p2.getX() + "im x of p2 ");
	// System.out.println(p2.getY() + "im y of p2 ");
	//
	// double t1 = ((p2.getX() - p1.getX()) ^ 2);
	// double t2 = ((p2.getY() - p1.getY()) ^ 2);
	// double tt = Math.sqrt(t1 + t2);
	// double x = tt;
	// System.out.println(x + "<<<<<<<x");
	// return x;
	//
	// }

	// public double getVectorX(Point p1, Point p2) { // <X,Y>
	// return (p2.getX() - p1.getX());
	// }
	//
	// public double getVectorY(Point p1, Point p2) { // <X,Y>
	// return (p2.getY() * p2.getY());
	// }
	//
	// public double calcVector(double ux, double uy, double vx, double vy) {
	// return (ux * vx) + (uy * vy);
	// }
	//
	// public double areaOfTriangle(double a, double b, double c) {
	// double HalfOfTriangleParameter = (a + b + c) / 2;
	// return (HalfOfTriangleParameter * (HalfOfTriangleParameter - a) *
	// (HalfOfTriangleParameter - b)
	// * (HalfOfTriangleParameter - c)) * 1 / 2;
	// }
	//
	// public double getTheta(double Vector, double lengthBetweenTwoPoint) {
	//
	// return Math.sin(Vector / lengthBetweenTwoPoint);
	//
	// }
	public Point[] addPointsAndSort(int size) {

		reader = new Scanner(System.in);

		points = new Point[size];

		for (int i = 0; i < points.length; i++) {
			System.out.println("Enter x and y for point " + i);

			points[i] = new Point(reader.nextInt(), reader.nextInt());

		}

		return sort(points);
	}

}
