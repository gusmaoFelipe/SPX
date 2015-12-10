
/**
 *
 * @author gusmao
 */
public class SimplexSolver {

    private final double FATOR_TOLERANCIA = 1.2;        //20% maior

    private PPL problema;                   //instancia de um problema de programação linear
    private PPL simplex;                    //tabelaSimplex
    private int[][] base;                   //variáveis da base

    public SimplexSolver(PPL problema) {
        this.problema = problema;
        /*if (problema.getQtdeRestricoes() > problema.getQtdeVariaveis() * FATOR_TOLERANCIA) {
         this.problema = problema.gerarDual();
         }*/

    }

    public void insertVariaveisArtificiais() {
        //Se houver restrição <= , precisa de M
        //Verificando se há restrições do tipo <=
        for (int i = 0; i < this.problema.getQtdeRestricoes(); i++) {
            //Garantia que o lado direito é positivo
            if (this.problema.getRestricoes()[i][this.problema.getQtdeVariaveis()] < 0.0) {
                for (int j = 0; j <= this.problema.getQtdeVariaveis(); j++) {
                    this.problema.getRestricoes()[i][j] *= -1; // Troca osinal das restrições
                }
                this.problema.getSinalRestricoes()[i] *= -1; //Troca a direção do sinal
            }
            switch (this.problema.getSinalRestricoes()[i]) {
                case PPL.MAIOR_IGUAL:
                    break;
                case PPL.MENOR_IGUAL:
                    int novaQtdRestricoes = problema.getQtdeRestricoes();
                    int novaQtdVariaveis = problema.getQtdeVariaveis() + 1;
                    double[][] novasRestricoes = new double[novaQtdRestricoes][novaQtdVariaveis + 1];
                    for (int k = 0; k < novaQtdRestricoes; k++) {
                        for (int l = 0; l < novaQtdVariaveis - 1; l++) {
                            //Mantém as restrições
                            //System.out.println("["+k+"] ["+l+"] "+problema.getRestricoes()[k][l]);
                            novasRestricoes[k][l] = problema.getRestricoes()[k][l];
                        }
                        //Seta o valor da variável de folga
                        novasRestricoes[i][novaQtdVariaveis-1]=1;
                        //Move o lado direito para a direita
                        novasRestricoes[k][novaQtdVariaveis] = problema.getRestricoes()[k][problema.getQtdeVariaveis()];
                    }
                    problema.getSinalRestricoes()[i] = PPL.IGUAL;
                    //Copia a função objetivo + nova variável de foga com coeficiente 0
                    double[] novaFuncaoObjetivo = new double[novaQtdVariaveis];
                    for (int k = 0; k < novaQtdVariaveis - 1; k++) {
                        novaFuncaoObjetivo[k] = problema.getFuncaoObjetivo()[k];
                    }
                    novaFuncaoObjetivo[novaQtdVariaveis - 1] = 0;
                    //Copia a restrição de não negatividade
                    int[] novaNaoNegatividadeMenorIgual = new int[novaQtdVariaveis];
                    for (int k = 0; k < novaQtdVariaveis - 1; k++) {
                        novaNaoNegatividadeMenorIgual[k] = problema.getNaoNegatividadeMenor()[k];
                    }
                    novaNaoNegatividadeMenorIgual[novaQtdVariaveis - 1] = 0;

                    int[] novaNaoNegatividadeMaiorIgual = new int[novaQtdVariaveis];
                    for (int k = 0; k < novaQtdVariaveis - 1; k++) {
                        novaNaoNegatividadeMaiorIgual[k] = problema.getNaoNegatividadeMaior()[k];
                    }
                    novaNaoNegatividadeMaiorIgual[novaQtdVariaveis - 1] = 1;

                    int[] novaNaoNegatividadeReais = new int[novaQtdVariaveis];
                    for (int k = 0; k < novaQtdVariaveis - 1; k++) {
                        novaNaoNegatividadeReais[k] = problema.getNaoNegatividadeIgual()[k];
                    }
                    novaNaoNegatividadeReais[novaQtdVariaveis - 1] = 0;
                    problema.setRestricoes(novasRestricoes);
                    problema.setFuncaoObjetivo(novaFuncaoObjetivo);
                    problema.setNaoNegatividadeMaior(novaNaoNegatividadeMaiorIgual);
                    problema.setNaoNegatividadeIgual(novaNaoNegatividadeReais);
                    problema.setNaoNegatividadeMenor(novaNaoNegatividadeMenorIgual);
                    problema.setQtdeRestricoes(novaQtdRestricoes);
                    problema.setQtdeVariaveis(novaQtdVariaveis);
                    break;
                case PPL.IGUAL:
                    break;
            }
        }
    }

    /**
     * @return the problema
     */
    public PPL getProblema() {
        return problema;
    }

    /**
     * @param problema the problema to set
     */
    public void setProblema(PPL problema) {
        this.problema = problema;
    }

    /**
     * @return the base
     */
    public int[][] getBase() {
        return base;
    }

    /**
     * @param base the base to set
     */
    public void setBase(int[][] base) {
        this.base = base;
    }

}
