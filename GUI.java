import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
public class GUI extends JFrame implements ActionListener {
    private JLabel nameLabel, ageLabel, seatLabel, ticketLabel;
    private JTextField nameField, ageField, seatField;
    private JComboBox<String> ticketTypeCombo;
    private JTextArea displayArea;
    private JButton addButton, showAllButton, searchButton, exitButton;
    private JPanel panel;
    private Stadium stadium;
    private static final String[] TICKET_TYPES = {"VIP", "Regular", "Economy"};
    private static final String DATA_FILE = "audience_data.txt";

    public GUI() {
        super("Stadium Audience Management System");
        this.setSize(900, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        stadium = new Stadium("International Stadium");
       
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(180, 200, 150));
        this.add(panel);
        initializeComponents();
        addComponentsToPanel();
        
    }

    private void initializeComponents() {
        nameLabel = createLabel("Name:", 30, 20);
        ageLabel = createLabel("Age:", 30, 60);
        seatLabel = createLabel("Seat Number:", 30, 100);
        ticketLabel = createLabel("Ticket Type:", 30, 140);
        
        nameField = createTextField(150, 20, 200);
        ageField = createTextField(150, 60, 50);
        seatField = createTextField(150, 100, 100);
        
        ticketTypeCombo = new JComboBox<>(TICKET_TYPES);
        ticketTypeCombo.setBounds(150, 140, 100, 25);
        
        addButton = createButton("Add Audience", 30, 180, 150, Color.GREEN);
        showAllButton = createButton("Show All", 200, 180, 150, Color.BLUE);
        searchButton = createButton("Search by Seat", 30, 220, 150, Color.ORANGE);
        exitButton = createButton("Exit", 200, 220, 150, Color.RED);
        
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        //displayArea.setFont(new Font("Consolas", Font.PLAIN, 14));
    }

    private JLabel createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 120, 25);
        //label.setFont(new Font("Consolas", Font.BOLD, 14));
        return label;
    }

    private JTextField createTextField(int x, int y, int width) {
        JTextField field = new JTextField();
        field.setBounds(x, y, width, 25);
        return field;
    }

    private JButton createButton(String text, int x, int y, int width, Color bgColor) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, 30);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.addActionListener(this);
        return button;
    }

    private void addComponentsToPanel() {
        panel.add(nameLabel);
        panel.add(ageLabel);
        panel.add(seatLabel);
        panel.add(ticketLabel);
        
        panel.add(nameField);
        panel.add(ageField);
        panel.add(seatField);

        panel.add(ticketTypeCombo);
        panel.add(addButton);
        panel.add(showAllButton);
        panel.add(searchButton);
        //panel.add(saveBtn);
        //panel.add(loadBtn);
        panel.add(exitButton);

        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBounds(30, 290, 520, 430);
        panel.add(scrollPane);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            addAudience();
        } else if (e.getSource() == showAllButton) {
            showAllAudience();
        } else if (e.getSource() == searchButton) {
            searchAudience();
        } else if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }

    private void addAudience() {
        try {
            String name = nameField.getText().trim();
            int age = Integer.parseInt(ageField.getText().trim());
            String seat = seatField.getText().trim();
            String ticketType = (String) ticketTypeCombo.getSelectedItem();
            
            if (name.isEmpty() || seat.isEmpty()) {
                displayArea.setText("Name and seat number cannot be empty!");
                return;
            }
            
            Audience audience = new Audience(name, age, seat, ticketType);
            if (stadium.addAudience(audience)) {
                displayArea.setText("Audience added successfully!\n");
                displayArea.append("Name: " + name + "\n");
                displayArea.append("Age: " + age + "\n");
                displayArea.append("Seat: " + seat + "\n");
                displayArea.append("Ticket Type: " + ticketType + "\n");
                clearFields();
            } else {
                displayArea.setText("Could not add audience (^-^)");
            }
        } catch (NumberFormatException ex) {
            displayArea.setText("Invalid age! Please enter a number.");
        }
    }

    private void showAllAudience() {
        displayArea.setText("All Audience Members in " + stadium.getStadiumName() + ":\n");
        for (Audience a : stadium.getAudienceList()) {
            if (a != null) {
                displayArea.append("-------------------\n");
                displayArea.append("Name: " + a.getName() + "\n");
                displayArea.append("Age: " + a.getAge() + "\n");
                displayArea.append("Seat: " + a.getSeatNumber() + "\n");
                displayArea.append("Ticket: " + a.getTicketType() + "\n");
                if (a instanceof MembershipAudience) {
                    MembershipAudience ma = (MembershipAudience)a;
                    displayArea.append("Membership: " + ma.getMembershipLevel() + "\n");
                    displayArea.append("Discount: " + ma.getDiscounts() + "%\n");
                }
            }
        }
    }

    private void searchAudience() {
        String seat = seatField.getText().trim();
        if (seat.isEmpty()) {
            displayArea.setText("Please enter a seat number to search!");
            return;
        }
        
        Audience found = stadium.searchAudience(seat);
        displayArea.setText("Search results for seat " + seat + ":\n");
        
        if (found != null) {
            displayArea.append("Name: " + found.getName() + "\n");
            displayArea.append("Age: " + found.getAge() + "\n");
            displayArea.append("Seat: " + found.getSeatNumber() + "\n");
            displayArea.append("Ticket: " + found.getTicketType() + "\n");
            if (found instanceof MembershipAudience) {
                MembershipAudience ma = (MembershipAudience)found;
                displayArea.append("Membership: " + ma.getMembershipLevel() + "\n");
                displayArea.append("Discount: " + ma.getDiscounts() + "%\n");
            }
        } else {
            displayArea.append("Audience not found.");
        }
    }

    private void saveData() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_FILE))) {
            for (Audience a : stadium.getAudienceList()) {
                if (a != null) {
                    String line = a.getName() + "," + a.getAge() + "," + 
                                 a.getSeatNumber() + "," + a.getTicketType();
                    
                    if (a instanceof MembershipAudience) {
                        MembershipAudience ma = (MembershipAudience)a;
                        line += "," + ma.getDiscounts() + "," + ma.getMembershipLevel() + ",M";
                    } else {
                        line += ",A"; // Mark as regular audience
                    }
                    
                    writer.println(line);
                }
            }
            displayArea.setText("Data saved successfully to " + DATA_FILE);
        } catch (IOException ex) {
            displayArea.setText("Error saving data: " + ex.getMessage());
        }
    }

    private void loadData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
            stadium = new Stadium("National Stadium");
            
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    String name = parts[0];
                    int age = Integer.parseInt(parts[1]);
                    String seat = parts[2];
                    String ticketType = parts[3];

                    if (parts.length == 7 && parts[6].equals("M")) {
                        double discount = Double.parseDouble(parts[4]);
                        String membership = parts[5];
                        MembershipAudience ma = new MembershipAudience(
                            name, age, seat, ticketType, discount, membership);
                        stadium.addAudience(ma);
                    } else {
                        Audience a = new Audience(name, age, seat, ticketType);
                        stadium.addAudience(a);
                    }
                }
            }
            displayArea.setText("Data loaded successfully from " + DATA_FILE);
            showAllAudience();
        } catch (FileNotFoundException ex) {
            displayArea.setText("Data file not found. Creating new file when you save.");
        } catch (IOException | NumberFormatException ex) {
            displayArea.setText("Error loading data: " + ex.getMessage());
        }
    }

    private void clearFields() {
        nameField.setText("");
        ageField.setText("");
        seatField.setText("");
        ticketTypeCombo.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GUI gui = new GUI();
            gui.setVisible(true);
        });
    }
}


