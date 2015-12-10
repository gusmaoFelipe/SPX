
/**
 * Problema de Programação Linear
 *
 * @author gusmao
 */
public class PPL {

    private final double COEFICIENTE_INVALIDO = Double.MAX_VALUE;
    private final int NUMERO_MAXIMO_ITERACOES;

    public static final int MAIOR = 2;
    public static final int MAIOR_IGUAL = 1;
    public static final int IGUAL = 0;
    public static final int MENOR_IGUAL = -1;
    public static final int MENOR = -2;

    private boolean maximizacao;                //é um problema de maximização?
    private int qtdeRestricoes;                 //quantidade de restrições deste problema
    private int qtdeVariaveis;                  //quantidade de variáveis deste problema
    private Double[] funcaoObjetivo;            //função objetivo (somente coeficientes)
    private Double[][] restricoes;              //restricoes (somente coeficientes) + lado direito
    private int[] sinalRestricoes;              //indica o sinal de cada restrição
    // -2 -1 0 1 2 para < <= = >= >, respectivamente
    private int[] naoNegatividadeMenorIgual;    //indica os coeficientes de não negatividade <=
    private int[] naoNegatividadeMaiorIgual;    //indica os coeficientes de não negatividade >= 
    private int[] naoNegatividadeReais;         //indica os coeficientes de não negatividade =
    // 1: presença -- 0: ausência

    /**
     * Construtor padrão. A entrada vem pronta
     *
     * @param maximizacao
     * @param qtdeRestricoes
     * @param qtdeVariaveis
     * @param funcaoObjetivo
     * @param restricoes
     * @param sinalRestricoes
     * @param naoNegatividadeMenorIgual
     * @param naoNegatividadeMaiorIgual
     * @param naoNegatividadeReais
     */
<<<<<<< HEAD
    public PPL(boolean maximizacao, int qtdeRestricoes, int qtdeVariaveis, Double[] funcaoObjetivo, Double[][] restricoes, int[] sinalRestricoes, int[] naoNegatividadeMenorIgual, int[] naoNegatividadeMaiorIgual, int[] naoNegatividadeReais) {
=======
    public PPL(boolean maximizacao, int qtdeRestricoes,
            int qtdeVariaveis, double[] funcaoObjetivo,
            double[][] restricoes, int[] sinalRestricoes,
            int[] naoNegatividadeMenorIgual, int[] naoNegatividadeMaiorIgual,
            int[] naoNegatividadeReais) {
>>>>>>> cf44c4742eed2574ce012ec78951ab7d806c4985
        this.maximizacao = maximizacao;
        this.qtdeRestricoes = qtdeRestricoes;
        this.qtdeVariaveis = qtdeVariaveis;
        this.funcaoObjetivo = funcaoObjetivo;
        this.restricoes = restricoes;
        this.sinalRestricoes = sinalRestricoes;
        this.naoNegatividadeMenorIgual = naoNegatividadeMenorIgual;
        this.naoNegatividadeMaiorIgual = naoNegatividadeMaiorIgual;
        this.naoNegatividadeReais = naoNegatividadeReais;
        this.NUMERO_MAXIMO_ITERACOES = 3 * this.qtdeRestricoes / 2;
    }

    /**
     * Construtor não-padrão. Necessita de conversão de Strings, a entrada não
     * está pronta
     *
     * @param tipo: maximizacao ou minimizacao (Max ou Min)
     * @param qtdeRestricoes
     * @param qtdeVariaveis
     * @param funcaoObjetivo
     * @param restricoes
     * @param naoNegatividade
     */
    public PPL(String tipo, int qtdeRestricoes, int qtdeVariaveis, 
            String funcaoObjetivo, String[] restricoes, String[] naoNegatividade) {
        if (tipo.equalsIgnoreCase("max")) {     //Se for Max, set True.. se não, continue false
            this.maximizacao = true;
        }
        this.qtdeRestricoes = qtdeRestricoes;
        this.qtdeVariaveis = qtdeVariaveis;
        this.insertFuncaoObjetivo(funcaoObjetivo);
        this.insertRestricoes(restricoes);
        this.insertNaoNegatividade(naoNegatividade);
        this.NUMERO_MAXIMO_ITERACOES = 3 * this.qtdeRestricoes / 2;
    }

    public void print() {
        int contador = 0;
        System.out.println("==============================================================================");
        if (this.maximizacao) {
            System.out.print("Maximizar: ");
        } else {
            System.out.print("Minimizar: ");
        }
        System.out.print(funcaoObjetivo[0] + "X1");
        for (int i = 1; i < qtdeVariaveis; i++) {
            if (funcaoObjetivo[i] >= 0) {
                System.out.print(" + " + funcaoObjetivo[i] + "X" + (i + 1));
            } else {
                System.out.print("  " + funcaoObjetivo[i] + "X" + (i + 1));
            }
        }
        System.out.println("\nSujeito a:");
        int j;
        for (int i = 0; i < qtdeRestricoes; i++) {
            System.out.print(restricoes[i][0] + "X1");
            for (j = 1; j < qtdeVariaveis; j++) {
                if (restricoes[i][j] >= 0) {
                    System.out.print(" + " + restricoes[i][j] + "X" + (j + 1));
                } else {
                    System.out.print("  " + restricoes[i][j] + "X" + (j + 1));
                }
            }
            switch (getSinalRestricoes()[i]) {
<<<<<<< HEAD
                case -1:
=======
                case PPL.MENOR_IGUAL:
>>>>>>> cf44c4742eed2574ce012ec78951ab7d806c4985
                    System.out.print(" <= " + restricoes[i][j]);
                    break;
                case PPL.MAIOR_IGUAL:
                    System.out.print(" >= " + restricoes[i][j]);
                    break;
                case PPL.IGUAL:
                    System.out.print(" = " + restricoes[i][j]);
                    break;
            }
            System.out.println("");
        }
        System.out.println("Restrito a:");
        for (int i = 0; i < qtdeVariaveis; i++) {
            if (naoNegatividadeMenorIgual[i] == 1) {
                contador++;
                System.out.print("X" + (i + 1) + ",");
            }
        }
        if (contador > 0) {
            System.out.print("\b  MENORES ou iguais a 0\n");
        }
        contador = 0;
        for (int i = 0; i < qtdeVariaveis; i++) {
            if (naoNegatividadeMaiorIgual[i] == 1) {
                contador++;
                System.out.print("X" + (i + 1) + ",");
            }
        }
        if (contador > 0) {
            System.out.print("\b  MAIORES ou iguais a 0\n");
        }
        contador = 0;
        for (int i = 0; i < qtdeVariaveis; i++) {
            if (naoNegatividadeReais[i] == 1) {
                contador++;
                System.out.print("X" + (i + 1) + ",");
            }
        }
        if (contador > 0) {
            System.out.print("\b  IGUAIS a 0\n");
        }

        System.out.println("\n==============================================================================");
    }

    private void insertRestricoes(String[] restricoes) {
<<<<<<< HEAD
        this.restricoes = new Double[qtdeRestricoes][qtdeVariaveis + 1];
=======
        this.restricoes = new double[qtdeRestricoes][qtdeVariaveis + 1];
>>>>>>> cf44c4742eed2574ce012ec78951ab7d806c4985
        this.setSinalRestricoes(new int[qtdeRestricoes]);
        String atual;
        String numero;
        char c;
        int indiceString;
        double coeficiente = COEFICIENTE_INVALIDO;

        try {
            for (int i = 0; i < qtdeRestricoes; i++) {                  //por cada restrição
                atual = restricoes[i];
                indiceString = 0;
                for (int j = 0; j <= qtdeVariaveis; j++) {               //por cada variável
                    numero = "";
                    c = atual.charAt(indiceString++);
                    while (c != ' ') {                                  //enquanto nao achei espaço
                        if (indiceString == atual.length()) {
                            break;
                        }
                        numero += c + "";
                        c = atual.charAt(indiceString++);
                    }
                    if (numero.charAt(0) == 'x') {                      //se houver um x...   
                        j = Integer.parseInt(numero.charAt(1) + "") - 1;//vefica qual variavel e modifica o contador j
                        if (coeficiente == COEFICIENTE_INVALIDO) {      //não há coeficiente explicito
                            this.restricoes[i][j] = 1.0;
                        } else {                                        //há coeficiente explicito
                            this.restricoes[i][j] = coeficiente;
                        }
                        coeficiente = COEFICIENTE_INVALIDO;             //zerou coeficiente
                    } else if (numero.charAt(0) == '<' || numero.charAt(0) == '>' || numero.charAt(0) == '=') {
                        switch (numero.charAt(0)) {
                            case '<':                                   //<=
<<<<<<< HEAD
                                this.getSinalRestricoes()[i] = this.MENOR_IGUAL;
                                break;
                            case '>':
                                this.getSinalRestricoes()[i] = this.MAIOR_IGUAL;           //>=
                                break;
                            case '=':
                                this.getSinalRestricoes()[i] = this.IGUAL;            //=
=======
                                this.getSinalRestricoes()[i] = this.getMENOR_IGUAL();
                                break;
                            case '>':
                                this.getSinalRestricoes()[i] = this.getMAIOR_IGUAL();           //>=
                                break;
                            case '=':
                                this.getSinalRestricoes()[i] = this.getIGUAL();            //=
>>>>>>> cf44c4742eed2574ce012ec78951ab7d806c4985
                                break;
                        }
                        numero = "";
                        j = qtdeVariaveis;
                        c = atual.charAt(indiceString++);
                        while (c != ' ') {                                  //enquanto nao achei espaço
                            numero += c + "";
                            if (indiceString == atual.length()) {
                                break;
                            }
                            c = atual.charAt(indiceString++);
                        }
                        this.restricoes[i][j] = Double.parseDouble(numero);
                    } else if (numero.charAt(0) == '-' || numero.charAt(0) == '+') {
                        if (numero.charAt(1) == 'x') {                      //entrada do tipo -xi
                            j = Integer.parseInt(numero.charAt(2) + "") - 1;
                            this.restricoes[i][j] = -1.0;
                        } else {                                             //é um número negativo
                            coeficiente = Double.parseDouble(numero);
                        }
                    } else {                                                //é um número positivo
                        coeficiente = Double.parseDouble(numero);
                    }
                }
            }
        } catch (NullPointerException | StringIndexOutOfBoundsException ex) {
            System.out.println("Entrada fora do padrão: " + ex.getMessage());
            System.exit(0);
        }
    }

    private void insertNaoNegatividade(String[] naoNegatividade) {
        this.naoNegatividadeMenorIgual = new int[qtdeVariaveis];
        this.naoNegatividadeMaiorIgual = new int[qtdeVariaveis];
        this.naoNegatividadeReais = new int[qtdeVariaveis];

        descobrirNaoNegatividade(naoNegatividade[0], this.naoNegatividadeMenorIgual);   //<=
        descobrirNaoNegatividade(naoNegatividade[1], this.naoNegatividadeMaiorIgual);   //>=    
        descobrirNaoNegatividade(naoNegatividade[2], this.naoNegatividadeReais);        //=
    }

    private void descobrirNaoNegatividade(String linha, int[] valores) {
        String numero;
        char c;

        if (linha != null && linha.length() > 1) {
            int i = 0;
            while (i < linha.length()) {
                numero = "";
                c = linha.charAt(i++);
                while (c != ' ') {
                    numero += c + "";
                    if (i == linha.length()) {
                        break;
                    }
                    c = linha.charAt(i++);
                }
                valores[Integer.parseInt(numero) - 1] = 1;
            }
        }
    }

    private void insertFuncaoObjetivo(String funcaoObjetivo) {
        this.funcaoObjetivo = new Double[qtdeVariaveis];
        String numero;
        char c;
        int indiceString = 0;
        int indiceVetor = 0;

        try {
            for (int i = 0; i < qtdeVariaveis; i++) {
                numero = "";
                c = funcaoObjetivo.charAt(indiceString++);
                while (c != ' ') {
                    numero += c + "";
                    if (indiceString == funcaoObjetivo.length()) {
                        break;
                    }
                    c = funcaoObjetivo.charAt(indiceString++);
                }
                this.funcaoObjetivo[indiceVetor++] = Double.parseDouble(numero);
            }
        } catch (StringIndexOutOfBoundsException ex) {
            System.out.println("Entrada fora do padrão!");
            System.exit(0);
        }
    }

    public PPL gerarDual() {
        //Variáveis necessárias para contruir um novo objetivo
        int qtdeRestricoesDual = this.qtdeVariaveis;
        int qtdeVariaveisDual = this.qtdeRestricoes;
        Double[] funcaoObjetivoDual = new Double[this.qtdeRestricoes];
        Double[][] restricoesDual = new Double[this.qtdeVariaveis][this.qtdeRestricoes + 1];
        int[] sinalRestricoesDual = new int[this.qtdeVariaveis];
        int[] naoNegatividadeMenorIgualDual = new int[this.qtdeRestricoes];
        int[] naoNegatividadeMaiorIgualDual = new int[this.qtdeRestricoes];
        int[] naoNegatividadeReaisDual = new int[this.qtdeRestricoes];

        this.changeMaximizacao();           //muda o estado de maximizacao ou minização
        //add os coeficientes da funcao objetivo
        for (int i = 0; i < this.qtdeRestricoes; i++) {
            funcaoObjetivoDual[i] = this.restricoes[i][this.qtdeVariaveis]; //ultimo elemento da linha
        }
        //add as restricoes
        for (int i = 0; i < this.qtdeRestricoes; i++) {
            for (int j = 0; j < this.qtdeVariaveis; j++) {
                restricoesDual[j][i] = this.restricoes[i][j];
            }
        }
        //add o resultado das restricoes
        for (int i = 0; i < this.qtdeVariaveis; i++) {
            restricoesDual[i][this.qtdeRestricoes] = this.funcaoObjetivo[i];
        }
        //add o sinal das restricoes
        for (int i = 0; i < this.qtdeVariaveis; i++) {
            if (!isMaximizacao()) {                  //PRIMAL DE MAXIMIZACAO
                if (this.naoNegatividadeMenorIgual[i] == 1) {
                    sinalRestricoesDual[i] = this.getMAIOR_IGUAL();
                }
                if (this.naoNegatividadeMaiorIgual[i] == 1) {
                    sinalRestricoesDual[i] = this.getMENOR_IGUAL();
                }
                if (this.naoNegatividadeReais[i] == 1) {
                    sinalRestricoesDual[i] = this.getIGUAL();
                }
            } else {                                  //PRIMAL DE MINIMIZACAO
                if (this.naoNegatividadeMenorIgual[i] == 1) {
                    sinalRestricoesDual[i] = this.getMENOR_IGUAL();
                }
                if (this.naoNegatividadeMaiorIgual[i] == 1) {
                    sinalRestricoesDual[i] = this.getMAIOR_IGUAL();
                }
                if (this.naoNegatividadeReais[i] == 1) {
                    sinalRestricoesDual[i] = this.getIGUAL();
                }
            }
        }
        //add nao negatividade
        for (int i = 0; i < this.qtdeRestricoes; i++) {
            if (!isMaximizacao()) {                  //PRIMAL DE MAXIMIZACAO
                switch (this.getSinalRestricoes()[i]) {
<<<<<<< HEAD
                    case MENOR_IGUAL:
=======
                    case PPL.MENOR_IGUAL:
>>>>>>> cf44c4742eed2574ce012ec78951ab7d806c4985
                        naoNegatividadeMenorIgualDual[i] = 1;
                        break;
                    case PPL.MAIOR_IGUAL:
                        naoNegatividadeMaiorIgualDual[i] = 1;
                        break;
                    case PPL.IGUAL:
                        naoNegatividadeReaisDual[i] = 1;
                        break;
                }
            } else {                                  //PRIMAL DE MINIMIZACAO
                switch (this.getSinalRestricoes()[i]) {
                    case MENOR_IGUAL:
                        naoNegatividadeMaiorIgualDual[i] = 1;
                        break;
                    case MAIOR_IGUAL:
                        naoNegatividadeMenorIgualDual[i] = 1;
                        break;
                    case IGUAL:
                        naoNegatividadeReaisDual[i] = 1;
                        break;
                }
            }
        }

        return new PPL(this.maximizacao, qtdeRestricoesDual, qtdeVariaveisDual,
                funcaoObjetivoDual, restricoesDual, sinalRestricoesDual,
                naoNegatividadeMenorIgualDual, naoNegatividadeMaiorIgualDual,
                naoNegatividadeReaisDual);
    }

    /**
     * @return the maximizacao
     */
    public boolean isMaximizacao() {
        return maximizacao;
    }

    /**
     * @param maximizacao the maximizacao to set
     */
    public void setMaximizacao(boolean maximizacao) {
        this.maximizacao = maximizacao;
    }

    /**
     * change the state of maximizacao
     */
    public void changeMaximizacao() {
        this.maximizacao = !isMaximizacao();
    }

    /**
     * @return the qtdeRestricoes
     */
    public int getQtdeRestricoes() {
        return qtdeRestricoes;
    }

    /**
     * @param qtdeRestricoes the qtdeRestricoes to set
     */
    public void setQtdeRestricoes(int qtdeRestricoes) {
        this.qtdeRestricoes = qtdeRestricoes;
    }

    /**
     * @return the qtdeVariaveis
     */
    public int getQtdeVariaveis() {
        return qtdeVariaveis;
    }

    /**
     * @param qtdeVariaveis the qtdeVariaveis to set
     */
    public void setQtdeVariaveis(int qtdeVariaveis) {
        this.qtdeVariaveis = qtdeVariaveis;
    }

    /**
     * @return the funcaoObjetivo
     */
    public Double[] getFuncaoObjetivo() {
        return funcaoObjetivo;
    }

    /**
     * @param funcaoObjetivo the funcaoObjetivo to set
     */
    public void setFuncaoObjetivo(Double[] funcaoObjetivo) {
        this.funcaoObjetivo = funcaoObjetivo;
    }

    /**
     * @return the restricoes
     */
    public Double[][] getRestricoes() {
        return restricoes;
    }

    /**
     * @param restricoes the restricoes to set
     */
    public void setRestricoes(Double[][] restricoes) {
        this.restricoes = restricoes;
    }

    /**
     * @return the naoNegatividadeMenor
     */
    public int[] getNaoNegatividadeMenor() {
        return naoNegatividadeMenorIgual;
    }

    /**
     * @param naoNegatividadeMenor the naoNegatividadeMenor to set
     */
    public void setNaoNegatividadeMenor(int[] naoNegatividadeMenor) {
        this.naoNegatividadeMenorIgual = naoNegatividadeMenor;
    }

    /**
     * @return the naoNegatividadeMaior
     */
    public int[] getNaoNegatividadeMaior() {
        return naoNegatividadeMaiorIgual;
    }

    /**
     * @param naoNegatividadeMaior the naoNegatividadeMaior to set
     */
    public void setNaoNegatividadeMaior(int[] naoNegatividadeMaior) {
        this.naoNegatividadeMaiorIgual = naoNegatividadeMaior;
    }

    /**
     * @return the naoNegatividadeIgual
     */
    public int[] getNaoNegatividadeIgual() {
        return naoNegatividadeReais;
    }

    /**
     * @param naoNegatividadeIgual the naoNegatividadeIgual to set
     */
    public void setNaoNegatividadeIgual(int[] naoNegatividadeIgual) {
        this.naoNegatividadeReais = naoNegatividadeIgual;
    }

    /**
     * @return the NUMERO_MAXIMO_ITERACOES
     */
    public int getNUMERO_MAXIMO_ITERACOES() {
        return NUMERO_MAXIMO_ITERACOES;
    }

<<<<<<< HEAD
    /**
     * @return the sinalRestricoes
     */
=======
>>>>>>> cf44c4742eed2574ce012ec78951ab7d806c4985
    public int[] getSinalRestricoes() {
        return sinalRestricoes;
    }

<<<<<<< HEAD
    /**
     * @param sinalRestricoes the sinalRestricoes to set
     */
=======
>>>>>>> cf44c4742eed2574ce012ec78951ab7d806c4985
    public void setSinalRestricoes(int[] sinalRestricoes) {
        this.sinalRestricoes = sinalRestricoes;
    }

<<<<<<< HEAD
    public void addVariavelArtificial(double var, int restricao) {
        Double[] auxFuncaoObjetivo = this.funcaoObjetivo;
        Double[][] auxRestricoes = this.restricoes;
        //Adicionando na funcao Objetivo
        this.funcaoObjetivo = new Double[++qtdeVariaveis];
        for (int i = 0; i < qtdeVariaveis; i++) {
            this.funcaoObjetivo[i] = auxFuncaoObjetivo[i];
        }
        this.funcaoObjetivo[qtdeVariaveis - 1] = var;
        //Adicionando nas restricoes
        this.restricoes = new Double[qtdeRestricoes][qtdeVariaveis];
        for (int linha = 0; linha < qtdeRestricoes; linha++) {
            for (int coluna = 0; coluna < qtdeVariaveis; coluna++) {
                this.restricoes[linha][coluna] = auxRestricoes[linha][coluna];
            }
        }
        for (int i = 0; i < qtdeRestricoes; i++) {
            this.restricoes[i][qtdeVariaveis - 1] = 0.0;
        }

=======
    public int getMAIOR() {
        return MAIOR;
    }

    public int getMAIOR_IGUAL() {
        return MAIOR_IGUAL;
    }

    public int getIGUAL() {
        return IGUAL;
    }

    public int getMENOR_IGUAL() {
        return MENOR_IGUAL;
    }

    public int getMENOR() {
        return MENOR;
>>>>>>> cf44c4742eed2574ce012ec78951ab7d806c4985
    }

}
