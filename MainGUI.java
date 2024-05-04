//package NegBinomDist;
import javax.swing.*;
//import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.*;
import java.awt.event.*;

public class MainGUI extends JFrame {
    private JTextField maxGamesField, winsNeededField, probabilityField;
    private JButton calculateButton;
    private JLabel resultLabel;

    public MainGUI() {
        setTitle("Mets v Yankees");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Set look and feel
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create input fields
        maxGamesField = new JTextField(10);
        winsNeededField = new JTextField(10);
        probabilityField = new JTextField(10);
        calculateButton = new JButton("Calculate"); // Initialize JButton with label "Calculate"
        resultLabel = new JLabel("Result: ");

        // Create calculate button
        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateProbability(); // Call method to perform calculation
            }
        });
        // Create result label
        resultLabel = new JLabel("Result: ");
        
        // Create panel and add components
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));
        panel.add(new JLabel("Max Number of Games:"));
        panel.add(maxGamesField);
        panel.add(new JLabel("Wins Needed to Win the Series:"));
        panel.add(winsNeededField);
        panel.add(new JLabel("Probability of Mets Winning a Single Game vs Yankees (0 to 1):"));
        panel.add(probabilityField);
        panel.add(calculateButton);
        panel.add(resultLabel);

        // Add panel to the frame
        add(panel);
    }


    private void calculateProbability() {
        try {
            // Read input from GUI components
            double p = Double.parseDouble(probabilityField.getText());
            int r = Integer.parseInt(winsNeededField.getText());
            int maxGames = Integer.parseInt(maxGamesField.getText());

            // Call method from NegativeBinomialDistribution to perform calculation
            double probability = NegBinomDist.probabilityOfWinningSeries(p, r, maxGames);

            // Display the result in GUI
            resultLabel.setText("Result: " + probability*100 + " %");
        } catch (NumberFormatException ex) {
            // Handle invalid input
            resultLabel.setText("Invalid input. Please enter valid numbers.");
        }
    }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainGUI gui = new MainGUI();
                gui.setVisible(true);
            }
        });
    }
}