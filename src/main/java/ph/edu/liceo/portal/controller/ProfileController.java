package ph.edu.liceo.portal.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ph.edu.liceo.portal.MainApp;
import ph.edu.liceo.portal.model.Student;

public class ProfileController {

    @FXML private Label initialsLabel;
    @FXML private Label nameLabel;
    @FXML private Label studentNoLabel;
    @FXML private Label courseLabel;
    @FXML private Label emailLabel;

    public void setStudent(Student student) {
        if (student != null) {
            nameLabel.setText(student.getFullName());
            studentNoLabel.setText(student.getStudentNo());
            courseLabel.setText(student.getCourse() + " - Year " + student.getYearLevel());
            emailLabel.setText(student.getEmail());

            String[] parts = student.getFullName().trim().split("\\s+");
            if (parts.length >= 2) {
                String initials = "" + parts[0].charAt(0) + parts[parts.length - 1].charAt(0);
                initialsLabel.setText(initials.toUpperCase());
            } else if (parts.length == 1 && !parts[0].isEmpty()) {
                initialsLabel.setText(parts[0].substring(0, 1).toUpperCase());
            }
        }
    }

    @FXML
    private void handleLogout() {
        MainApp.showLogin();
    }
}