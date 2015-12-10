/**
 *
 * @author elder
 */
public class SimplexNormal {
 
    double[][] Matriz = {{-696, -399, 100, 0, 0, 0, -900},
                          {1,    2,    0,  1, 0, 0,   4},
                          {3,    1,    0,  0, 1, 0,   3},
                          {4,    3,   -1,  0, 0, 1,   6}};

    
    int linhas = 4; // numero de linhas da matriz
    int colunas = 7; // numero de colunas da matriz

    private int entraBase() {
        double menorValor = 0;
        int posicao = 0;
        for (int i = 0; i < (colunas-1); i++) {
            if (Matriz[0][i] < menorValor) {
                menorValor = Matriz[0][i];
                posicao = i;
            }
  
        }

        if(menorValor >= 0)
           posicao = -1;
        
        return posicao;
    }

    private int saiBase(int entraBase) {
        double valorMenor = -1, valor = 0;
        int posicao = -1;
        for (int i = 1; i < linhas; i++) {
            if (Matriz[i][entraBase] > 0 && Matriz[i][colunas - 1] > 0) {
                valor = Matriz[i][colunas - 1] / Matriz[i][entraBase];
                if (valorMenor == -1 || valor < valorMenor) {
                    valorMenor = valor;
                    posicao = i;
                }
            }
        }
        return posicao;
    }

    private void calcularMatriz(int entraBase, int saiBase) {
    
        double pivo = Matriz[saiBase][entraBase];

        // dividir linha da Matriz do pivo pelo pivo
        if (pivo != 1) {
            for (int i = 0; i < colunas; i++) {
                Matriz[saiBase][i] = Matriz[saiBase][i] / pivo;
            }
        }

        // Aplicar Gauss-Jordan
        for (int i = 0; i < linhas; i++) {
          double   valor = Matriz[i][entraBase] * (-1);
            for (int j = 0; j < colunas; j++) {
                if (saiBase != i) {
                    Matriz[i][j] = Matriz[i][j] + (Matriz[saiBase][j] * valor);
                }
            }    
        }

    }

    public void printarMatriz() {
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                System.out.printf("%.2f ",Matriz[i][j]);
            }
            System.out.println("");
        }

    }

    public static void main(String args[]) {
        SimplexNormal simplex = new SimplexNormal();
        double iteracoes = 0;
        int entraBase = simplex.entraBase(); // Coluna de quem entra na base
      
        while(entraBase != -1){
        int saiBase = simplex.saiBase(entraBase); // Linha de quem sai da base
        
            System.out.println(entraBase + "  " + saiBase);
        if(saiBase == -1){
            System.out.println("Não há solucão otima");
            break;
        }
        simplex.calcularMatriz(entraBase, saiBase); //Calcula "Matriz Gauss Jordan"
        simplex.printarMatriz();
        entraBase = simplex.entraBase();
        iteracoes++; // n + 2 * m
        }
    }
}
