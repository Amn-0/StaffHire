import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class RecruitmentSystem extends JFrame implements ActionListener {
    private ArrayList<StaffHire> staffList = new ArrayList<>();

    private JTextField vacancyNumberField, designationField, jobTypeField, salaryField,
            hoursField, staffNameField, joiningDateField, qualificationField,
            appointedByField, wagesPerHourField, shiftsField;

    private JButton addFullTimeBtn, addPartTimeBtn, appointBtn, terminateBtn, displayBtn;

    public RecruitmentSystem() {
        setTitle("Recruitment System");
        setLayout(new GridLayout(14, 2));
        setSize(500, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Input fields
        vacancyNumberField = new JTextField();
        designationField = new JTextField();
        jobTypeField = new JTextField();
        salaryField = new JTextField();
        hoursField = new JTextField();
        wagesPerHourField = new JTextField();
        shiftsField = new JTextField();
        staffNameField = new JTextField();
        joiningDateField = new JTextField();
        qualificationField = new JTextField();
        appointedByField = new JTextField();

        add(new JLabel("Vacancy Number:")); add(vacancyNumberField);
        add(new JLabel("Designation:")); add(designationField);
        add(new JLabel("Job Type (FullTime/PartTime):")); add(jobTypeField);
        add(new JLabel("Salary (for Full-Time):")); add(salaryField);
        add(new JLabel("Hours (Fractional/Working):")); add(hoursField);
        add(new JLabel("Wages per Hour (Part-Time):")); add(wagesPerHourField);
        add(new JLabel("Shifts (Part-Time):")); add(shiftsField);
        add(new JLabel("Staff Name:")); add(staffNameField);
        add(new JLabel("Joining Date:")); add(joiningDateField);
        add(new JLabel("Qualification:")); add(qualificationField);
        add(new JLabel("Appointed By:")); add(appointedByField);

        // Buttons
        addFullTimeBtn = new JButton("Add Full-Time Staff");
        addPartTimeBtn = new JButton("Add Part-Time Staff");
        appointBtn = new JButton("Appoint Staff");
        terminateBtn = new JButton("Terminate Part-Time");
        displayBtn = new JButton("Display All");

        add(addFullTimeBtn); add(addPartTimeBtn);
        add(appointBtn); add(terminateBtn);
        add(displayBtn);

        addFullTimeBtn.addActionListener(this);
        addPartTimeBtn.addActionListener(this);
        appointBtn.addActionListener(this);
        terminateBtn.addActionListener(this);
        displayBtn.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int vacancyNumber = Integer.parseInt(vacancyNumberField.getText());
            String designation = designationField.getText();
            String jobType = jobTypeField.getText();

            if (e.getSource() == addFullTimeBtn) {
                double salary = Double.parseDouble(salaryField.getText());
                int hours = Integer.parseInt(hoursField.getText());

                FullTimeStaffHire fts = new FullTimeStaffHire(
                        vacancyNumber, designation, jobType,
                        "", "", "", "", false, salary, hours
                );
                staffList.add(fts);
                JOptionPane.showMessageDialog(this, "Full-Time staff added.");
            }

            else if (e.getSource() == addPartTimeBtn) {
                int workingHour = Integer.parseInt(hoursField.getText());
                double wages = Double.parseDouble(wagesPerHourField.getText());
                String shifts = shiftsField.getText();

                PartTimeStaffHire pts = new PartTimeStaffHire(
                        vacancyNumber, designation, jobType,
                        "", "", "", "", false,
                        workingHour, wages, shifts
                );
                staffList.add(pts);
                JOptionPane.showMessageDialog(this, "Part-Time staff added.");
            }

            else if (e.getSource() == appointBtn) {
                String staffName = staffNameField.getText();
                String joiningDate = joiningDateField.getText();
                String qualification = qualificationField.getText();
                String appointedBy = appointedByField.getText();

                boolean found = false;

                for (StaffHire s : staffList) {
                    if (s.getVacancyNumber() == vacancyNumber && !s.isJoined()) {
                        s.setStaffName(staffName);
                        s.setJoiningDate(joiningDate);
                        s.setQualification(qualification);
                        s.setAppointedBy(appointedBy);
                        s.setJoined(true);
                        found = true;
                        JOptionPane.showMessageDialog(this, "Staff appointed successfully.");
                        break;
                    }
                }

                if (!found) {
                    JOptionPane.showMessageDialog(this, "No matching unjoined vacancy found.");
                }
            }

            else if (e.getSource() == terminateBtn) {
                boolean found = false;
                for (StaffHire s : staffList) {
                    if (s instanceof PartTimeStaffHire pts && s.getVacancyNumber() == vacancyNumber) {
                        pts.terminate();
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    JOptionPane.showMessageDialog(this, "No matching Part-Time staff found.");
                }
            }

            else if (e.getSource() == displayBtn) {
                for (StaffHire s : staffList) {
                    s.display();
                    System.out.println("------------------");
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values where required.");
        }
    }

    public static void main(String[] args) {
        new RecruitmentSystem();
    }
}
