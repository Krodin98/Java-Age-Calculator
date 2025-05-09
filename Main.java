import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Period;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        // Create the main frame
        JFrame frame = new JFrame("Age Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new BorderLayout());

        // Create panels
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        JPanel resultPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel();

        // Create date selection components
        JLabel yearLabel = new JLabel("Year:");
        Integer[] years = new Integer[100];
        for (int i = 0; i < 100; i++) {
            years[i] = LocalDate.now().getYear() - i;
        }
        JComboBox<Integer> yearComboBox = new JComboBox<>(years);

        JLabel monthLabel = new JLabel("Month:");
        String[] months = { "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December" };
        JComboBox<String> monthComboBox = new JComboBox<>(months);

        JLabel dayLabel = new JLabel("Day:");
        Integer[] days = new Integer[31];
        for (int i = 0; i < 31; i++) {
            days[i] = i + 1;
        }
        JComboBox<Integer> dayComboBox = new JComboBox<>(days);

        // Add components to input panel
        inputPanel.add(yearLabel);
        inputPanel.add(yearComboBox);
        inputPanel.add(monthLabel);
        inputPanel.add(monthComboBox);
        inputPanel.add(dayLabel);
        inputPanel.add(dayComboBox);

        // Create result label
        JLabel resultLabel = new JLabel("Enter your birthdate and click Calculate", JLabel.CENTER);
        resultPanel.add(resultLabel, BorderLayout.CENTER);

        // Create calculate button
        JButton calculateButton = new JButton("Calculate Age");
        buttonPanel.add(calculateButton);

        // Add action listener to the button
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int year = (Integer) yearComboBox.getSelectedItem();
                int month = monthComboBox.getSelectedIndex() + 1; // Months are 1-based
                int day = (Integer) dayComboBox.getSelectedItem();

                LocalDate today = LocalDate.now();
                LocalDate birthday = LocalDate.of(year, month, day);
                Period p = Period.between(birthday, today);

                resultLabel.setText("You are " + p.getYears() + " years, " +
                        p.getMonths() + " months, and " +
                        p.getDays() + " days old.");
            }
        });

        // Add panels to frame
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(resultPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Display the frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}