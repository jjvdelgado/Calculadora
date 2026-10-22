public enum Operacao {

    SOMA("+") {
        @Override
        public int aplicar(int a, int b) {
            return Math.addExact(a, b);
        }
    },

    SUBTRACAO("-") {
        @Override
        public int aplicar(int a, int b) {
            return Math.subtractExact(a, b);
        }
    },

    MULTIPLICACAO("x") {
        @Override
        public int aplicar(int a, int b) {
            return Math.multiplyExact(a, b);
        }
    },

    DIVISAO("/") {
        @Override
        public int aplicar(int a, int b) {
            // int / int já descarta a parte decimal; divisão por zero da erro
            return a / b;
        }
    };

    private final String simbolo;

    Operacao(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getSimbolo() {
        return simbolo;
    }

    // Cada constante implementa o seu próprio cálculo
    public abstract int aplicar(int a, int b);
}