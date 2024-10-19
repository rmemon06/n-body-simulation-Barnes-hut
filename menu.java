


	import java.awt.BorderLayout;
	import java.awt.Color;
	import java.awt.Component;
	import java.awt.Cursor;
	import java.awt.Dimension;
	import java.awt.EventQueue;
	import java.awt.FlowLayout;
	import java.awt.Font;
	import java.awt.Graphics;
	import java.awt.GridBagLayout;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;

	import javax.swing.BorderFactory;
	import javax.swing.Box;
	import javax.swing.BoxLayout;
	import javax.swing.JButton;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JPanel;
	import javax.swing.SwingUtilities;

	public class menu {


	    private JFrame frame;

	    int x;
	    int y;
	    int r = 300;
	    int ballR = 15;
	    double currentAngle;
	    Thread thread;

	    public static void main(String[] args) {
	     
	            menu mainMenu = new menu();
	            mainMenu.initialise(400, 340, 0);
	            mainMenu.startAnimation();
	    }

	    public menu() {
	    }

	    public class Panel extends JPanel {
	        @Override
	        public void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            this.setBackground(Color.black);
	            g.setColor(Color.white);
	            g.drawOval(x - r, y - r, r * 2, r * 2);
	            g.setColor(Color.white);
	            g.drawOval(375, 325, 50, 50);
	            g.fillOval(375, 325, 50, 50);
	            int ballX, ballY;
	            ballX = (int) (r * Math.cos(currentAngle * Math.PI / 180));
	            ballY = (int) (r * Math.sin(currentAngle * Math.PI / 180));
	            g.setColor(Color.PINK);
	            g.fillOval(x + ballX - ballR, y + ballY - ballR, ballR * 2, ballR * 2);
	        }
	    }

	    private void initialise(int x, int y, int angle) {
	    	 this.x = x;
	    	    this.y = y;
	    	    this.currentAngle = angle;

	    	    frame = new JFrame();
	    	    frame.setBounds(100, 100, 800, 800);
	    	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	    frame.setLayout(new BorderLayout());
	    	    frame.setLocationRelativeTo(null);
	    	    // Create the animation panel
	    	    Panel animationPanel = new Panel();
	    	    frame.add(animationPanel, BorderLayout.CENTER);
	    	    
	    	    // You can add your title and button here
	    	    JLabel titleLabel = new JLabel("Gravitational Simulation");
	    	    titleLabel.setForeground(Color.white);
	    	    titleLabel.setFont(new Font("CENTER_BASELINE", Font.BOLD, 25));

	    	    JButton enterSimButton = new JButton("Enter Simulation");
	    	    enterSimButton.setFont(new Font("Arial", Font.PLAIN, 18));
	            enterSimButton.setForeground(Color.WHITE);
	            enterSimButton.setBackground(Color.gray); // Change to a nice color
	            enterSimButton.setFocusPainted(false); // Remove focus border
	            enterSimButton.setBorderPainted(false); // Remove border
	            enterSimButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Change cursor on hover
	            
	            JButton Login = new JButton("Login");
	            Login.setFont(new Font("Arial", Font.PLAIN, 18));
	            Login.setForeground(Color.WHITE);
	            Login.setBackground(Color.gray); // Change to a nice color
	            Login.setFocusPainted(false); // Remove focus border
	            Login.setBorderPainted(false); // Remove border
	            Login.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Change cursor on hover
	            
	           
	            

	    	    JPanel titleButtonPanel = new JPanel();
	    	    titleButtonPanel.setLayout(new BoxLayout(titleButtonPanel, BoxLayout.Y_AXIS)); // Stack components vertically
	    	    titleButtonPanel.add(titleLabel);
	    	    titleButtonPanel.add(enterSimButton);
	    	    titleButtonPanel.add(Box.createRigidArea(new Dimension(0, 0)));

	    	    titleButtonPanel.add(Login);

	    	    // Center components horizontally
	    	    titleButtonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
	    	    titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	    	    enterSimButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	    	    Login.setAlignmentX(Component.CENTER_ALIGNMENT);
	    	 
	    	    enterSimButton.addActionListener(new ActionListener() {
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                	frame.dispose();
	                	GravitationSimulation simulation = new 	GravitationSimulation(null); // null as the user hasnt logged in
	                }
	            });
	    	    
	    	  

	    	    Login.addActionListener(new ActionListener() {
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                	frame.dispose();
	                	logins.main(null);	//calling the login page
	                	
	                }
	            });
	    	   

	    	    titleButtonPanel.setBackground(Color.BLACK);
	    	    frame.add(titleButtonPanel, BorderLayout.NORTH);

	    	    frame.setVisible(true);
	    }

	    private void startAnimation() {
	        thread = new Thread(() -> {
	            while (true) {
	                currentAngle += 2;
	                frame.repaint();
	                try {
	                    Thread.sleep(20);
	                } catch (InterruptedException ie) {
	                    System.out.println(ie.getMessage());
	                }
	            }
	        });
	        thread.start();
	    }
	}


