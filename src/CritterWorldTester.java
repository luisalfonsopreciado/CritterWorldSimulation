import java.util.*;

public class CritterWorldTester {

	public static void main(String[] argv) {
		test1();
		// test2 ();
		// test3 ();
		// test4 ();
	}

	static void simulateSteps(int N, int T, ArrayList<Coord> startLocations, ArrayList<Coord> rockLocations,
			Coord escapeHatch) {
		CritterWorld world = new CritterWorld(N, T, startLocations, rockLocations, escapeHatch);

		System.out.println(world);
		int t = 0;
		while (t < T) {
			boolean isOver = world.nextStep();
			System.out.println(world);
			if (isOver) {
				break;
			}
			t = t + 1;
		}
		System.out.println("Simulation time: t=" + t + " steps");
		world.printStats();
	}

	static void estimateSteps(int N, int T, ArrayList<Coord> startLocations, ArrayList<Coord> rockLocations,
			Coord escapeHatch) {
		int numRuns = 100;
		double total = 0;
		double numSamples = 0;
		for (int n = 0; n < numRuns; n++) {
			CritterWorld world = new CritterWorld(N, T, startLocations, rockLocations, escapeHatch);
			int t = 0;
			while (t < T) {
				boolean isOver = world.nextStep();
				if (isOver) {
					break;
				}
				t = t + 1;
			}
			if (t < T) {
				total += t;
				numSamples++;
			}
		}
		System.out.println("Number of samples: numRuns = " + numSamples + ":" + numRuns);
		System.out.println("Average time to escape: " + (total / numSamples));
	}

	static void test1() {
		int N = 5; // Size of grid: N x N
		int T = 100; // Max # steps to simulate (it could end earlier).

		// Place critters, rock, and escape hatch:
		ArrayList<Coord> critterStartLocations = new ArrayList<>();
		critterStartLocations.add(new Coord(0, 0));
		critterStartLocations.add(new Coord(0, 1));
		critterStartLocations.add(new Coord(0, 2));

		ArrayList<Coord> rockLocations = new ArrayList<>();
		// No rocks.

		Coord escape = new Coord(4, 4);

		simulateSteps(N, T, critterStartLocations, rockLocations, escape);
	}

	static void test2() {
		int N = 8; // Size of grid: N x N
		int T = 200; // Max # steps to simulate (it could end earlier).

		// Place critters, rock, and escape hatch:
		ArrayList<Coord> critterStartLocations = new ArrayList<>();
		critterStartLocations.add(new Coord(0, 0));
		critterStartLocations.add(new Coord(0, 1));
		critterStartLocations.add(new Coord(0, 2));
		critterStartLocations.add(new Coord(0, 3));
		critterStartLocations.add(new Coord(0, 5));

		ArrayList<Coord> rockLocations = new ArrayList<>();
		rockLocations.add(new Coord(3, 3));
		rockLocations.add(new Coord(3, 4));

		Coord escape = new Coord(7, 4);

		simulateSteps(N, T, critterStartLocations, rockLocations, escape);
	}

	static void test3() {
		int N = 5; // Size of grid: N x N
		int T = 100; // Max # steps to simulate (it could end earlier).

		// Place critters, rock, and escape hatch:
		ArrayList<Coord> critterStartLocations = new ArrayList<>();
		critterStartLocations.add(new Coord(0, 0));
		critterStartLocations.add(new Coord(0, 1));
		critterStartLocations.add(new Coord(0, 2));

		ArrayList<Coord> rockLocations = new ArrayList<>();
		// No rocks.

		Coord escape = new Coord(4, 4);

		estimateSteps(N, T, critterStartLocations, rockLocations, escape);
	}

	static void test4() {
		int N = 8; // Size of grid: N x N
		int T = 200; // Max # steps to simulate (it could end earlier).

		// Place critters, rock, and escape hatch:
		ArrayList<Coord> critterStartLocations = new ArrayList<>();
		critterStartLocations.add(new Coord(0, 0));
		critterStartLocations.add(new Coord(0, 1));
		critterStartLocations.add(new Coord(0, 2));
		critterStartLocations.add(new Coord(0, 3));
		critterStartLocations.add(new Coord(0, 5));

		ArrayList<Coord> rockLocations = new ArrayList<>();
		rockLocations.add(new Coord(3, 3));
		rockLocations.add(new Coord(3, 4));

		Coord escape = new Coord(7, 4);

		estimateSteps(N, T, critterStartLocations, rockLocations, escape);
	}

}
