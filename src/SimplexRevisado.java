
import Jama.Matrix;

/**
 *
 * @author gusmao
 */
public class SimplexRevisado {

    private final double FATOR_TOLERANCIA = 1.2;        //20% maior
    public static int nIteracoes;
    private int qtdeVarArificiais;

    private PPL problema;                   //instancia de um problema de programação linear
    private PPL simplex;                    //tabelaSimplex
    private int[] base;                   //variáveis da base
    private Matrix matriz;
    private double[] funcaoObjetivo;        //funcaoObjetivo atual
    private double[] colunaPivot;           //coluna do pivot

    public SimplexRevisado(PPL problema) {
        qtdeVarArificiais = 0;
        this.problema = problema;
        nIteracoes = 0;
        if (problema.getQtdeRestricoes() > problema.getQtdeVariaveis() * FATOR_TOLERANCIA) {
            //this.problema = problema.gerarDual();
        }
        this.simplex = this.problema;
        //Etapa 01: construção da solução inicial viável
        this.insertVariaveisArtificiais();
        this.insereElementosDaMatriz();
        this.insereBase();
    }

    //Etapa 01: construção da solução inicial viável
    private void insertVariaveisArtificiais() {
        //Se houver restrição <= , precisa de M
        //Verificando se há restrições do tipo <=
        for (int i = 0; i < this.simplex.getQtdeRestricoes(); i++) {
            //Garantia que o lado direito é positivo
            if (this.simplex.getRestricoes()[i][this.simplex.getQtdeVariaveis()] < 0.0) {
                for (int j = 0; j <= this.simplex.getQtdeVariaveis(); j++) {
                    this.simplex.getRestricoes()[i][j] *= -1; // Troca o sinal das restrições
                }
                this.simplex.getSinalRestricoes()[i] *= -1; // Troca a direção do sinal
            }
            switch (this.simplex.getSinalRestricoes()[i]) {
                case PPL.MAIOR_IGUAL:
                    this.simplex.addVariavelArtificial(-1.0, i);
                    this.qtdeVarArificiais++;
                    break;
                case PPL.MENOR_IGUAL:
                    this.simplex.addVariavelArtificial(1.0, i);
                    this.qtdeVarArificiais++;
                    break;
                case PPL.IGUAL:
                    break;
            }
        }
    }

    private void insereElementosDaMatriz() {
        double[][] matrizDouble = new double[qtdeVarArificiais][qtdeVarArificiais];
        int begin = simplex.getQtdeVariaveis() - qtdeVarArificiais;
        for (int linha = begin; linha < simplex.getQtdeVariaveis(); linha++) {
            for (int coluna = 0; coluna < qtdeVarArificiais; coluna++) {
                matrizDouble[linha - begin][coluna] = simplex.getRestricoes()[coluna][linha];
            }
        }
        matriz = new Matrix(matrizDouble);
    }

    private void insereBase() {
        //guardando os índices das variáveis da base inicial
        for (int i = 0; i < simplex.getQtdeVariaveis() - qtdeVarArificiais; i++) {
            base[i] = qtdeVarArificiais + i - 1;
        }
    }

    private void insereCoeficientesDaFuncaoObjetivo() {
        for (int i = 0; i < simplex.getQtdeVariaveis(); i++) {
            
        }
    }

    //Etapa 02: calcular a inversa de B utilizando decomposição
    private void calculaInversa() {
    }

    private void insertFolga(double coeficiente, int i) {
        int novaQtdRestricoes = simplex.getQtdeRestricoes();
        int novaQtdVariaveis = simplex.getQtdeVariaveis() + 1;
        double[][] novasRestricoes = new double[novaQtdRestricoes][novaQtdVariaveis + 1];
        for (int k = 0; k < novaQtdRestricoes; k++) {
            for (int l = 0; l < novaQtdVariaveis - 1; l++) {
                //Mantém as restrições
                //System.out.println("["+k+"] ["+l+"] "+simplex.getRestricoes()[k][l]);
                novasRestricoes[k][l] = simplex.getRestricoes()[k][l];
            }
            //Seta o valor da variável de folga
            novasRestricoes[i][novaQtdVariaveis - 1] = coeficiente;
            //Move o lado direito para a direita
            novasRestricoes[k][novaQtdVariaveis] = simplex.getRestricoes()[k][simplex.getQtdeVariaveis()];
        }
        simplex.getSinalRestricoes()[i] = PPL.IGUAL;
        //Copia a função objetivo + nova variável de foga com coeficiente 0
        double[] novaFuncaoObjetivo = new double[novaQtdVariaveis];
        for (int k = 0; k < novaQtdVariaveis - 1; k++) {
            novaFuncaoObjetivo[k] = simplex.getFuncaoObjetivo()[k];
        }
        novaFuncaoObjetivo[novaQtdVariaveis - 1] = 0.0;
        //Copia a restrição de não negatividade
        int[] novaNaoNegatividadeMenorIgual = new int[novaQtdVariaveis];
        for (int k = 0; k < novaQtdVariaveis - 1; k++) {
            novaNaoNegatividadeMenorIgual[k] = simplex.getNaoNegatividadeMenor()[k];
        }
        novaNaoNegatividadeMenorIgual[novaQtdVariaveis - 1] = 0;

        int[] novaNaoNegatividadeMaiorIgual = new int[novaQtdVariaveis];
        for (int k = 0; k < novaQtdVariaveis - 1; k++) {
            novaNaoNegatividadeMaiorIgual[k] = simplex.getNaoNegatividadeMaior()[k];
        }
        novaNaoNegatividadeMaiorIgual[novaQtdVariaveis - 1] = 1;

        int[] novaNaoNegatividadeReais = new int[novaQtdVariaveis];
        for (int k = 0; k < novaQtdVariaveis - 1; k++) {
            novaNaoNegatividadeReais[k] = simplex.getNaoNegatividadeIgual()[k];
        }
        novaNaoNegatividadeReais[novaQtdVariaveis - 1] = 0;
        simplex.setRestricoes(novasRestricoes);
        simplex.setFuncaoObjetivo(novaFuncaoObjetivo);
        simplex.setNaoNegatividadeMaior(novaNaoNegatividadeMaiorIgual);
        simplex.setNaoNegatividadeIgual(novaNaoNegatividadeReais);
        simplex.setNaoNegatividadeMenor(novaNaoNegatividadeMenorIgual);
        simplex.setQtdeRestricoes(novaQtdRestricoes);
        simplex.setQtdeVariaveis(novaQtdVariaveis);
    }

    /**
     * @return the problema
     */
    public PPL getProblemaOriginal() {
        return problema;
    }

    /**
     * @param problema the problema to set
     */
    public void setProblemaOriginal(PPL problema) {
        this.problema = problema;
    }

    public PPL getSimplex() {
        return simplex;
    }

    public void setSimplex(PPL simplex) {
        this.simplex = simplex;
    }

    /**
     * @return the base
     */
    public int[] getBase() {
        return base;
    }

    /**
     * @param base the base to set
     */
    public void setBase(int[] base) {
        this.base = base;
    }

}
