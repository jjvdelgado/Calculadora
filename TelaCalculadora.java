import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridLayout;

// herda um jframe
public class TelaCalculadora extends JFrame {

    // a tela tem uma calculadora
    private final Calculadora calculadora = new Calculadora();
    private final JTextField visor = new JTextField("0");

    // monta a janela: visor em cima e botões embaixo
    public TelaCalculadora() {
        super(Estilo.TITULO);
        setDefaultCloseOperation(EXIT_ON_CLOSE);    // fecha o programa junto com a janela
        setLayout(new BorderLayout(5, 5));

        visor.setEditable(false);                   // só dá pra usar os botões
        visor.setHorizontalAlignment(JTextField.RIGHT);
        visor.setFont(Estilo.FONTE_VISOR);
        visor.setBackground(Estilo.COR_FUNDO_VISOR);
        visor.setForeground(Estilo.COR_TEXTO_VISOR);

        add(visor, BorderLayout.NORTH);
        add(criarPainelBotoes(), BorderLayout.CENTER);

        setSize(Estilo.LARGURA, Estilo.ALTURA);
        setLocationRelativeTo(null);                // centraliza na tela
    }

    // cria o grid 4x4 com os botões
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

    // botão de número
    private void adicionarDigito(JPanel painel, int digito) {
        JButton botao = new JButton(String.valueOf(digito));

        // quando clicar, manda o dígito pra calculadora e atualiza o visor
        botao.addActionListener(e -> {
            calculadora.digitar(digito);
            atualizarVisor();
        });
        Estilo.estilizarBotao(botao, Estilo.COR_NUMEROS);
        painel.add(botao);
    }

    // botão das operacoes
    private void adicionarOperacao(JPanel painel, Operacao operacao) {
        JButton botao = new JButton(operacao.getSimbolo());
        botao.addActionListener(e -> {
            calculadora.operar(operacao);
            atualizarVisor();
        });
        Estilo.estilizarBotao(botao, Estilo.COR_OPERACOES);
        painel.add(botao);
    }

    // botão de igual
    private void adicionarIgual(JPanel painel) {
        JButton botao = new JButton("=");
        botao.addActionListener(e -> {
            calculadora.igual();
            atualizarVisor();
            System.out.println(calculadora.getTextoVisor());
        });
        Estilo.estilizarBotao(botao, Estilo.COR_IGUAL);
        painel.add(botao);
    }

    // reseta tudo
    private void adicionarReset(JPanel painel) {
        JButton botao = new JButton("C");
        botao.addActionListener(e -> {
            calculadora.reset();
            atualizarVisor();
        });
        Estilo.estilizarBotao(botao, Estilo.COR_RESET);
        painel.add(botao);
    }

    // pega o texto da calculadora e coloca no visor
    private void atualizarVisor() {
        visor.setText(calculadora.getTextoVisor());
    }
}