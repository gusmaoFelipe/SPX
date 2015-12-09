
import java.util.Arrays;

/**
 * Generics for Matrix
 *
 * @author gusmao
 */
public class Matriz {

    private int qtdeLinhas;
    private int qtdeColunas;
    private Double[][] matriz;

    /**
     * Construtor padrão
     *
     * @param qtdeLinhas
     * @param qtdeColunas
     */
    public Matriz(int qtdeLinhas, int qtdeColunas, boolean identidade) {
        this.qtdeLinhas = qtdeLinhas;
        this.qtdeColunas = qtdeColunas;
        this.matriz = new Double[qtdeLinhas][qtdeColunas];
        if (identidade) {
            if (qtdeLinhas != qtdeColunas) {
                qtdeLinhas = qtdeColunas;
                this.matriz = new Double[qtdeLinhas][qtdeColunas];
            }
            this.gerarMatrizIdentidade();
        }
    }

    /**
     * Construtor com matriz double
     *
     * @param qtdeLinhas
     * @param qtdeColunas
     * @param matriz
     */
    public Matriz(int qtdeLinhas, int qtdeColunas, Double[][] matriz) {
        this.qtdeLinhas = qtdeLinhas;
        this.qtdeColunas = qtdeColunas;
        this.matriz = matriz;
    }

    public Matriz multiplicacao(Matriz m) {
        if (m != null || m.qtdeColunas + m.qtdeLinhas < 1) {    //matriz valida
            return null;
        }
        if (m.getMatriz().length == 0) {
            return null;
        }

        Matriz novaMatriz = new Matriz(this.qtdeLinhas, m.qtdeColunas, new Double[this.qtdeLinhas][m.getQtdeColunas()]);

        if (this.qtdeColunas == this.qtdeLinhas) {
            for (int linha = 0; linha < m.getMatriz().length; linha++) {
                for (int coluna = 0; coluna < this.getMatriz()[0].length; coluna++) {
                    int somaProduto = 0;
                    for (int i = 0; i < this.qtdeColunas; i++) {
                        somaProduto += this.getElemento(linha, i) * m.getElemento(i, coluna);
                        novaMatriz.setElemento(somaProduto, linha, coluna);
                    }
                }
            }
        }
        return null;                    //Nao pode multiplicar
    }

    public void print() {
        System.out.println("====================================================");
        for (int linha = 0; linha < this.qtdeLinhas; linha++) {
            System.out.println(Arrays.deepToString(this.getMatriz()[linha]));
        }
        System.out.println("====================================================");
    }

    private void gerarMatrizIdentidade() {
        for (int linha = 0; linha < this.qtdeLinhas; linha++) {
            for (int coluna = 0; coluna < this.qtdeColunas; coluna++) {
                if (linha == coluna) {
                    this.setElemento(1.0, linha, coluna);
                } else {
                    this.setElemento(0.0, linha, coluna);
                }
            }
        }
    }

    public static void main(String[] args) {
        Double[][] d1 = {{1.0, 2.0, 3.0}, {1.0, 2.0, 3.0}, {3.0, 2.0, 2.0}};
        Double[][] d2 = {{2.0, 2.0, 2.0}, {2.0, 2.0, 2.0}, {2.0, 2.0, 2.0}};
        Double[][] d3 = {{1.0}, {1.0}, {3.0}};
        Double[][] d4 = {{1.0, 2.0, 3.0}};
        Matriz m1 = new Matriz(3, 3, d1);
        Matriz m2 = new Matriz(3, 3, d2);
        Matriz m3Identidade = new Matriz(3, 3, true);
        Matriz m4 = new Matriz(3, 1, d3);
        Matriz m5 = new Matriz(1, 3, d4);
        System.out.println("Matriz 1");
        m1.print();
        m2 = m1.multiplicacao(m2);
        m2.print();
        m4 = m4.multiplicacao(m5);
        m4.print();
    }
    
    public double getElemento(int i, int j) {
        return this.matriz[i][j];
    }

    public void setElemento(double elemento, int i, int j) {
        this.matriz[i][j] = elemento;
    }

    /**
     * @return the qtdeLinhas
     */
    public int getQtdeLinhas() {
        return qtdeLinhas;
    }

    /**
     * @param qtdeLinhas the qtdeLinhas to set
     */
    public void setQtdeLinhas(int qtdeLinhas) {
        this.qtdeLinhas = qtdeLinhas;
    }

    /**
     * @return the qtdeColunas
     */
    public int getQtdeColunas() {
        return qtdeColunas;
    }

    /**
     * @param qtdeColunas the qtdeColunas to set
     */
    public void setQtdeColunas(int qtdeColunas) {
        this.qtdeColunas = qtdeColunas;
    }

    /**
     * @return the matriz
     */
    public Double[][] getMatriz() {
        return matriz;
    }

    /**
     * @param matriz the matriz to set
     */
    public void setMatriz(Double[][] matriz) {
        this.matriz = matriz;
    }

}
