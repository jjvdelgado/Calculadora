public class Calculadora {

    private int valorAtual = 0;                 // número mostrado no visor
    private int acumulador = 0;                 // primeiro número da conta
    private Operacao operacaoPendente = null;   // operação esperando o segundo número
    private boolean novoNumero = true;          // true: o próximo dígito começa um número novo
    private boolean erro = false;

    public void digitar(int digito) {
        if (erro) {
            reset();
        }
        if (novoNumero || valorAtual == 0) {
            valorAtual = digito;
            novoNumero = false;
        } else if (String.valueOf(valorAtual).length() < 3) {
            // limite de 3 dígitos apenas para números digitados
            valorAtual = valorAtual * 10 + digito;
        }
    }

    public void operar(Operacao operacao) {
        if (erro) {
            reset();
        }
        if (operacaoPendente != null && !novoNumero) {
            // conta encadeada (ex.: 2 + 3 + ...): resolve a anterior antes
            executarPendente();
            if (erro) {
                return;
            }
        } else {
            acumulador = valorAtual;
        }
        operacaoPendente = operacao;
        novoNumero = true;
    }

    public void igual() {
        if (erro || operacaoPendente == null) {
            return;
        }
        executarPendente();
        operacaoPendente = null;
    }

    public void reset() {
        valorAtual = 0;
        acumulador = 0;
        operacaoPendente = null;
        novoNumero = true;
        erro = false;
    }

    public String getTextoVisor() {
        return erro ? "Erro" : String.valueOf(valorAtual);
    }

    private void executarPendente() {
        try {
            valorAtual = operacaoPendente.aplicar(acumulador, valorAtual);
            acumulador = valorAtual;
        } catch (ArithmeticException e) {
            // divisão por zero ou estouro do int
            erro = true;
        }
        novoNumero = true;
    }
}