
/**
 *
 * @author gusmao
 */
public class SimplexSolver {

    private final double FATOR_TOLERANCIA = 1.2;        //20% maior
    public static int nIteracoes;

    private PPL problema;                   //instancia de um problema de programação linear
    private PPL simplex;                    //tabelaSimplex
    private int[][] base;                   //variáveis da base

    public SimplexSolver(PPL problema) {
        this.problema = problema;
        nIteracoes = 0;
        if (problema.getQtdeRestricoes() > problema.getQtdeVariaveis() * FATOR_TOLERANCIA) {
            this.problema = problema.gerarDual();
        }
        this.simplex = this.problema;

    }

    private void insertVariaveisArtificiais() {

        //Se houver restrição <= , precisa de M
        //Verificando se há restrições do tipo <=
        boolean hasRestricaoMenorIgual = false;
        for (int restricao = 0; restricao < this.problema.getQtdeRestricoes(); restricao++) {
            if (problema.getSinalRestricoes()[restricao] == -1) {
                hasRestricaoMenorIgual = true;
                break;
            }
        }
        if (hasRestricaoMenorIgual) {
            for (int restricao = 0; restricao < problema.getQtdeRestricoes(); restricao++) {
                switch (problema.getSinalRestricoes()[restricao]) {
                    case -1:                                                //<=
                        problema.addVariavelArtificial(-1, restricao);
                        break;
                    case 1:                                                 //>=
                        problema.addVariavelArtificial(1, restricao);
                        break;
                    case 0:                                                 //=
                        break;
                }
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
