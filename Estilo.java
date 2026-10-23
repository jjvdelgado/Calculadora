import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

public class Estilo {

    // cores dos botões
    public static final Color COR_NUMEROS = Color.WHITE;
    public static final Color COR_OPERACOES = Color.ORANGE;
    public static final Color COR_IGUAL = Color.GREEN;
    public static final Color COR_RESET = new Color(240, 100, 100);

    // cores do visor
    public static final Color COR_FUNDO_VISOR = Color.BLACK;
    public static final Color COR_TEXTO_VISOR = Color.WHITE;

    // fontes
    public static final Font FONTE_BOTAO = new Font("Arial", Font.BOLD, 20);
    public static final Font FONTE_VISOR = new Font("Arial", Font.BOLD, 30);

    // janela
    public static final String TITULO = "Calculadora";
    public static final int LARGURA = 300;
    public static final int ALTURA = 400;

    public static void estilizarBotao(JButton botao, Color cor) {
        botao.setFont(FONTE_BOTAO);
        botao.setBackground(cor);
    }
}