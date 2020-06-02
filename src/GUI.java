import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*; // Needed for ActionListener.

class MapPanel extends JPanel implements ActionListener{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    CritterWorld world;
    JButton next;
    JButton rand;
    JButton nextPlus;
    JButton complete;
    int currentCritter;

    public MapPanel() {
        initializeWorld();
        currentCritter = 0;
        nextPlus = new JButton("NEXT+");
        nextPlus.setBackground(Color.lightGray);
        nextPlus.addActionListener(this);
        rand = new JButton("RANDOMIZE");
        rand.setBackground(Color.lightGray);
        rand.addActionListener(this);
        complete = new JButton("COMPLETE");
        complete.setBackground(Color.lightGray);
        complete.addActionListener(this);
        next = new JButton("NEXT");
        next.setBackground(Color.lightGray);
        next.addActionListener(this);
        this.add(next);
        this.add(nextPlus);
        this.add(rand);
        this.add(complete);
    }

    public void paintComponent(Graphics g) { 

        for (int row = 0; row < world.map.length; row++) {
            for (int col = 0; col < world.map[row].length; col++) {
                int topLeftX = (col + 50) + col * 70;
                int topLeftY = (row +  60) + row *  70; 
                g.drawString(world.map[row][col].toString(), topLeftX, topLeftY);
                g.drawRect( topLeftX -1, topLeftY- 13, 20,20);
            }
        }
    }

    public void initializeWorld() {
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
        world = new CritterWorld(N, T, critterStartLocations, rockLocations, escape);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
        if(e.getSource() == next){
            world.activeCritters.get(currentCritter).simulate(world.map);
            currentCritter++;
            if(currentCritter >= world.activeCritters.size()) currentCritter =0;
        }
        if(e.getSource() == complete){
            boolean isOver = false;
            while (!isOver) {
                isOver = world.nextStep();
                System.out.println(world);
                if (isOver) {
                    break;
                }
            }
        }
        if(e.getSource() == rand){
            world.shuffle();
        }
        if(e.getSource() == nextPlus){
            world.nextStep();
        }
        
        repaint();
		
	}

}

class MyFrame extends JFrame implements ActionListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    // Constructor.
    public MyFrame(int width, int height) {
        // On Mac-OS-X, you may add this for the default Java look:
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Set the title and other frame parameters.
        this.setTitle("Critter World Simulation");
        this.setResizable(true);
        this.setSize(width, height);

        Container cPane = this.getContentPane();
        cPane.setLayout(new BorderLayout());
        MapPanel map = new MapPanel();

        JButton quit = new JButton("QUIT");
        quit.setBackground(Color.RED);
        quit.addActionListener(this);

        cPane.add(quit, BorderLayout.PAGE_END);
        cPane.add(map, BorderLayout.CENTER);

        // Show the frame.
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent a) {
        System.exit(0);
    }

} // End of class "MyFrame"

public class GUI {

    public static void main(String[] argv) {
        new MyFrame(400, 450);
    }

}