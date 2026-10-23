public class Calculadora {

    private int valorAtual = 0;                 // número que aparece no visor
    private int acumulador = 0;                 // primeiro número da conta
    private Operacao operacaoPendente = null;   // operação esperando o segundo número
    private boolean novoNumero = true;          // true = próximo dígito começa um número novo
    private boolean erro = false;               // divisão por zero ou estouro

    // chamado quando clica em um número
    public void digitar(int digito) {
        if (erro) {
            reset();
        }

        // verifica se é um número novo ou se o visor está em zero
        if (novoNumero || valorAtual == 0) {
            valorAtual = digito;
            novoNumero = false;
        }
        // senão continua o número, se ainda tiver menos de 3 dígitos
        else if (String.valueOf(valorAtual).length() < 3) {
            valorAtual = valorAtual * 10 + digito;
        }
    }

    // chamado quando clica nas operacoes
    public void operar(Operacao operacao) {
        if (erro) {
            reset();
        }

        // verifica se já tem uma conta pendente e o segundo número já foi digitado
        if (operacaoPendente != null && !novoNumero) {
            executarPendente();
            if (erro) {
                return;
            }
        }
        else {
            acumulador = valorAtual;
        }

        // guarda a operação e prepara para digitar o próximo número
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

    // faz a conta
    private void executarPendente() {
        try {
            valorAtual = operacaoPendente.aplicar(acumulador, valorAtual);
            // o resultado vira o primeiro número da próxima conta
            acumulador = valorAtual;
        } catch (ArithmeticException e) {
            erro = true;
        }
        novoNumero = true;
    }
}