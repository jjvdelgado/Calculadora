import javax.swing.SwingUtilities;

public class Principal {
    public static void main(String[] args) {
        // Swing deve criar a janela na sua própria thread
        SwingUtilities.invokeLater(() -> new TelaCalculadora().setVisible(true));
    }
}