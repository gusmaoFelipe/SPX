
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
        if (problema.getQtdeRestricoes() > problema.getQtdeVariaveis() * FATOR_TOLERANCIA) {
            this.problema = problema.gerarDual();
        }

    }

    private void insertVariaveisArtificiais() {
        //Se houver restrição <= , precisa de M
        //Verificando se há restrições do tipo <=
        for (int i = 0; i < this.problema.getQtdeRestricoes(); i++) {
            
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
