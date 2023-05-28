import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.IllegalFormatException;
import java.util.Random;

public class Gridwalker2 implements ActionListener {
    private JFrame walker;
    private JButton[][] buttons; 
    private boolean[][] visited; // Keep track of visited buttons
    int counter = 0;//counter to keep track of steps.
    
    public Gridwalker2() {
        // Prompt user to enter the number of rows
        String rowInput = JOptionPane.showInputDialog(null, "Enter number of rows:");
        int numRows = Integer.parseInt(rowInput);

        // Prompt user to enter the number of columns
        String colInput = JOptionPane.showInputDialog(null, "Enter number of columns:");
        int numCols = Integer.parseInt(colInput);

        // I created the JFrame and set its layout to a GridLayout with the dimensions of users rows X cols
        this.walker = new JFrame();
        walker.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        walker.setTitle("Grid Walker");
        
        // I created an array to hold the buttons, and an array to hold the visted button.
        buttons = new JButton[numRows][numCols]; 
        visited = new boolean[numRows][numCols];
        
        //Choose a random location for the target button
        Random random = new Random();
        int targetRow = random.nextInt(numRows);
        int targetCol = random.nextInt(numCols);

        //I created a JPanel and added it to the JFrame.
        JPanel panel = new JPanel(new GridLayout(numRows, numCols));
        walker.getContentPane().add(panel);
        
        //I used a nested for loop to create the buttons and add them to the panel
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
            	JButton button;
            	//If I is the same number as the Target row and J is the same number as the Target Col, it sets the O to that position this changes everytime the game is played
            	if (i == targetRow && j == targetCol) {
            	    button = new JButton("\u25CE");
            	    button.setActionCommand("target");
    	            button.addActionListener(this);
    	            panel.add(button); 
                }else {
            	    // Once the target button is placed the rest of the buttons are placed at a random location using the switch function
            	    int direction = random.nextInt(4);
            	    char arrow;
            	    //I used the switch statement to change between the different buttons at random
            	    switch (direction) {
            	        case 0:
            	            arrow = '\u2190'; // left arrow
            	            button = new JButton(Character.toString(arrow));//JButtons take in a string so to adjust the char I used a toString method to convert it almost like casting.
            	            button.setActionCommand("left");//set action commands to the directions of the arrows.
            	            button.addActionListener(this); 
            	            panel.add(button); 
            	            break;
            	        case 1:
            	            arrow = '\u2191'; // up arrow
            	            button = new JButton(Character.toString(arrow));
            	            button.setActionCommand("up");
            	            button.addActionListener(this);            	           
            	            panel.add(button); 
            	            break;
            	        case 2:
            	            arrow = '\u2192'; // right arrow
            	            button = new JButton(Character.toString(arrow));
            	            button.setActionCommand("right");
            	            button.addActionListener(this); 
            	            panel.add(button); 
            	            break;
            	        default:
            	            arrow = '\u2193'; // down arrow
            	            button = new JButton(Character.toString(arrow));
            	            button.setActionCommand("down");
            	            button.addActionListener(this);
            	            panel.add(button); 
            	            break;
            	    }
            	 }
            	//Assigned the button to the array
            	buttons[i][j] = button;
            }
        }
        //Add the grid panel to the JFrame and made the frame visible
        walker.pack();
        walker.setVisible(true);
    }  
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        clickedButton.setBackground(Color.YELLOW);
        clickedButton.setBorderPainted(false);
        clickedButton.setOpaque(true);
        //I passed in the values r and c to corresdence to the button array so I can input them into the visted array.
        int r = -1;
        int c = -1;
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[0].length; j++) {
                if (clickedButton == buttons[i][j]) {
                    r = i;
                    c = j;
                    break;
                }
            }
        }
        //set visted to false because no buttons have been visited yet.
	    visited[r][c] = false;
	    boolean time = true;
	    while (time) {
	        String actionCommand = clickedButton.getActionCommand();
	        if (actionCommand.equals("up")) {
	            r--;
	            counter++;
	        } 
	        else if (actionCommand.equals("down")) {
	            r++;
	            counter++;
	        } 
	        else if (actionCommand.equals("left")) {
	            c--;
	            counter++;
	        } 
	        else if (actionCommand.equals("right")) {
	            c++;
	            counter++;
	        }
	        //When target button is reached the path highlights yellow and the target is megenta, it counts the steps took to get to the target and closes the program after.
	        else if(actionCommand.equals("target")) {
	            clickedButton.setBackground(Color.MAGENTA);
	            clickedButton.setBorderPainted(false);
	            clickedButton.setOpaque(true);
	            JOptionPane.showMessageDialog(null, " You finished in " + counter + " steps ");
	            time = false;
	            System.exit(0);
	        }
	        //If statement displayed to let the user know that there paths went out of bounds and left the grid, four conditionals to represent the four different ways it could leave the grid.
	        if (r < 0 || r >= buttons.length || c < 0 || c >= buttons[0].length) {
	            clickedButton.setBackground(Color.RED);
	            JOptionPane.showMessageDialog(null, "You left the grid!");
	            time = false;
	            System.exit(0);
	        }
	        //else statement to highlight the path before the grid was left.
	        else {
	            clickedButton = buttons[r][c];
	            clickedButton.setBackground(Color.YELLOW);
	            clickedButton.setBorderPainted(false);
	            clickedButton.setOpaque(true);
	        }  
	        //if statement to let the user know if there path was visited already meaning the path crossed itself as a point was visited twice.
	        if (visited[r][c]) {
	            clickedButton.setBackground(Color.RED);
	            JOptionPane.showMessageDialog(null, "You crossed your own path!");
	            time = false;
	            System.exit(0);
	        } 
	        //else if in place to hightlight the path yellow before it crossed.
	        else if(!visited[r][c]){
	              clickedButton = buttons[r][c];
	              clickedButton.setBackground(Color.YELLOW);
	              visited[r][c] = true;
	        } 
	        else {
	            clickedButton.setBackground(Color.YELLOW);
	            clickedButton.setBorderPainted(false);
	            clickedButton.setOpaque(true);
	            clickedButton = buttons[r][c];
	        }
	    }
    }
    public static void main(String[] args)throws IllegalFormatException, IOException {
        Gridwalker2 walk = new Gridwalker2();
    }
}