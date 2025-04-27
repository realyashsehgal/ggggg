package src;

import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import src.forms.LibraryApp;
import src.managers.DatabaseInitializer;
import src.managers.DatabaseManager;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                DatabaseManager.getCreds();
                DatabaseInitializer.getCreds();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (DatabaseInitializer.initialize() == 0) {
                return;
            }
            // new LoginFrame();
            new LibraryApp();
        });
    }
}
