import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

public class TelaCalculadora extends JFrame {

    // A tela TEM uma Calculadora (composição)
    private final Calculadora calculadora = new Calculadora();
    private final JTextField visor = new JTextField("0");

    public TelaCalculadora() {
        super("Calculadora");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(5, 5));

        visor.setEditable(false);
        visor.setHorizontalAlignment(JTextField.RIGHT);
        visor.setFont(new Font("Arial", Font.BOLD, 30));

        add(visor, BorderLayout.NORTH);
        add(criarPainelBotoes(), BorderLayout.CENTER);

        setSize(300, 400);
        setLocationRelativeTo(null);
    }

    private JPanel criarPainelBotoes() {
        JPanel painel = new JPanel(new GridLayout(4, 4, 5, 5));

        adicionarDigito(painel, 7);
        adicionarDigito(painel, 8);
        adicionarDigito(painel, 9);
        adicionarOperacao(painel, Operacao.DIVISAO);

        adicionarDigito(painel, 4);
        adicionarDigito(painel, 5);
        adicionarDigito(painel, 6);
        adicionarOperacao(painel, Operacao.MULTIPLICACAO);

        adicionarDigito(painel, 1);
        adicionarDigito(painel, 2);
        adicionarDigito(painel, 3);
        adicionarOperacao(painel, Operacao.SUBTRACAO);

        adicionarReset(painel);
        adicionarDigito(painel, 0);
        adicionarIgual(painel);
        adicionarOperacao(painel, Operacao.SOMA);

        return painel;
    }

    private void adicionarDigito(JPanel painel, int digito) {
        JButton botao = new JButton(String.valueOf(digito));
        // Cada botão "avisa" o código quando é clicado (Observer)
        botao.addActionListener(e -> {
            calculadora.digitar(digito);
            atualizarVisor();
        });
        painel.add(botao);
    }

    private void adicionarOperacao(JPanel painel, Operacao operacao) {
        JButton botao = new JButton(operacao.getSimbolo());
        botao.addActionListener(e -> {
            calculadora.operar(operacao);
            atualizarVisor();
        });
        painel.add(botao);
    }

    private void adicionarIgual(JPanel painel) {
        JButton botao = new JButton("=");
        botao.addActionListener(e -> {
            calculadora.igual();
            atualizarVisor();
            System.out.println(calculadora.getTextoVisor());  // resultado no console
        });
        painel.add(botao);
    }

    private void adicionarReset(JPanel painel) {
        JButton botao = new JButton("C");
        botao.addActionListener(e -> {
            calculadora.reset();
            atualizarVisor();
        });
        painel.add(botao);
    }

    private void atualizarVisor() {
        visor.setText(calculadora.getTextoVisor());
    }
}