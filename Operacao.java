public enum Operacao {

    SOMA("+") {
        @Override
        public int aplicar(int a, int b) {
            // dá erro se estourar o int
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
            // já descarta a parte decimal
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

    public abstract int aplicar(int a, int b);
}