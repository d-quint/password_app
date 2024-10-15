package com.pwapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * The main screen of the password application
 */
public final class Screen extends JFrame implements ActionListener {
  private static final long serialVersionUID = 1L;

  // Declare UI components
  private final JPasswordField newPasswordField;
  private final JPasswordField confirmPasswordField;
  private final JButton saveButton;
  private final JButton resetButton;
  private final JButton exitButton;

  // Custom fonts
  private final Font customFont;

  /**
   * Constructs the main screen of the password application.
   */
  public Screen() {
    super("Password Creation");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(400, 150);
    setLocationRelativeTo(null);

    // Create custom fonts
    customFont = new Font("Helvetica", Font.PLAIN, 12);

    // Set layout manager
    setLayout(new BorderLayout(10, 10));

    // Create panel for password fields
    JPanel passwordPanel = new JPanel(new GridLayout(2, 2, 5, 5));
    passwordPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    // Add New Password label and field
    JLabel newPasswordLabel = new JLabel("New Password:");
    newPasswordField = new JPasswordField(20);
    passwordPanel.add(newPasswordLabel);
    passwordPanel.add(newPasswordField);

    // Add Confirm Password label and field
    JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
    confirmPasswordField = new JPasswordField(20);
    passwordPanel.add(confirmPasswordLabel);
    passwordPanel.add(confirmPasswordField);

    // Add password panel to the center of the frame
    add(passwordPanel, BorderLayout.CENTER);

    // Create panel for buttons
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
    buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

    // Add Save button
    saveButton = new JButton("Save");
    saveButton.addActionListener(this);
    buttonPanel.add(saveButton);

    // Add Reset button
    resetButton = new JButton("Reset");
    resetButton.addActionListener(this);
    buttonPanel.add(resetButton);

    // Add Exit button
    exitButton = new JButton("Exit");
    exitButton.addActionListener(this);
    buttonPanel.add(exitButton);

    // Add button panel to the bottom of the frame
    add(buttonPanel, BorderLayout.SOUTH);

    // Apply custom font to all components
    setFontForAllComponents(this);

    // Display the main frame
    setVisible(true);
  }

  /**
   * Recursively sets the font for all components in the container.
   *
   * @param container the container whose components' fonts are to be set
   */
  private void setFontForAllComponents(Container container) {
    for (Component c : container.getComponents()) {
      c.setFont(customFont);
      if (c instanceof Container) {
        setFontForAllComponents((Container) c);
      }
    }
  }

  /**
   * Handles action events for the buttons.
   *
   * @param e the action event
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == saveButton) {
      // Get passwords from fields
      String newPassword = new String(newPasswordField.getPassword());
      String confirmPassword = new String(confirmPasswordField.getPassword());

      // Validate passwords
      if (!newPassword.equals(confirmPassword)) {
        JOptionPane.showMessageDialog(this, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
      } else {
        List<String> failedCriteria = getFailedPasswordCriteria(newPassword);
        if (failedCriteria.isEmpty()) {
          JOptionPane.showMessageDialog(this, "Password successfully created!", "Success",
              JOptionPane.INFORMATION_MESSAGE);
        } else {
          String errorMessage = "<html><b>Password does not meet the following criteria:</b><br>" +
              String.join("<br>", failedCriteria) + "</html>";
          JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
        }
      }
    } else if (e.getSource() == resetButton) {
      // Clear password fields
      newPasswordField.setText("");
      confirmPasswordField.setText("");
    } else if (e.getSource() == exitButton) {
      // Display goodbye message
      String goodbyeMessage = "<html>" +
          "<b>Thank you for using our Password Creation program!</b><br><br>" +
          "<b>Created by:</b><br>" +
          "&nbsp;&nbsp;&nbsp;&nbsp;Dane Quintano<br>" +
          "&nbsp;&nbsp;&nbsp;&nbsp;Jansen Moral<br>" +
          "&nbsp;&nbsp;&nbsp;&nbsp;Dharmveer Sandhu<br>" +
          "&nbsp;&nbsp;&nbsp;&nbsp;John Paglinawan<br><br>" +
          "of TN34" +
          "</html>";
      JOptionPane.showMessageDialog(this, goodbyeMessage, "Goodbye", JOptionPane.INFORMATION_MESSAGE);

      // Exit the application
      System.exit(0);
    }
  }

  /**
   * Validates the password against the criteria.
   *
   * @param password the password to validate
   * @return a list of failed criteria, empty if all criteria are met
   */
  private List<String> getFailedPasswordCriteria(String password) {
    List<String> failedCriteria = new ArrayList<>();

    if (password.length() < 8)
      failedCriteria.add("&gt; Must be at least 8 characters long");
    if (!Pattern.compile("[a-z]").matcher(password).find())
      failedCriteria.add("&gt; Must contain at least one lowercase letter");
    if (!Pattern.compile("[A-Z]").matcher(password).find())
      failedCriteria.add("&gt; Must contain at least one uppercase letter");
    if (!Pattern.compile("[0-9]").matcher(password).find())
      failedCriteria.add("&gt; Must contain at least one number");
    if (!Pattern.compile("[^a-zA-Z0-9]").matcher(password).find())
      failedCriteria.add("&gt; Must contain at least one special character");

    System.out.println(failedCriteria);
    return failedCriteria;
  }
}