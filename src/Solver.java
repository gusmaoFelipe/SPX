
import java.io.IOException;

/**
 *
 * @author gusmao
 */
public class Solver {

    public static void main(String[] args) {
        Loader carregamento = null;
        PPL instancia;

        if (args.length != 0) {
            carregamento = new Loader(args[0]);
        } else {
            carregamento = new Loader("file/reddyMikks.txt");
        }

        String linha = "";
        String numero = "";
        char c;
        int indiceString = 0;

        String tipo = null;
        int qtdeRestricoes = 0;
        int qtdeVariaveis = 0;
        String funcaoObjetivo = "";
        String[] restricoes = null;
        String[] naoNegatividade = null;

        try {
            //Lendo tipo
            linha = carregamento.getBufferedReader().readLine();
            tipo = linha;
            carregamento.getFileInputStream();
            //Lendo quantidades
            linha = carregamento.getBufferedReader().readLine();
            //Lendo quantidade de variaveis
            c = linha.charAt(indiceString++);
            while (c != ' ') {
                numero += c + "";
                c = linha.charAt(indiceString++);
            }
            qtdeVariaveis = Integer.parseInt(numero);
            numero = "";                            //zerou numero
            //Lendo quantidade de restricoes
            c = linha.charAt(indiceString++);
            while (true) {
                numero += c + "";
                if (indiceString == linha.length()) {
                    break;
                }
                c = linha.charAt(indiceString++);
            }
            qtdeRestricoes = Integer.parseInt(numero);
            //Lendo funcao objetivo
            funcaoObjetivo = carregamento.getBufferedReader().readLine();
            //Lendo Restricoes
            restricoes = new String[qtdeRestricoes];
            for (int i = 0; i < qtdeRestricoes; i++) {
                restricoes[i] = carregamento.getBufferedReader().readLine();
            }
            //Lendo nao Negatividade
            naoNegatividade = new String[3];
            naoNegatividade[0] = carregamento.getBufferedReader().readLine();
            naoNegatividade[1] = carregamento.getBufferedReader().readLine();
            naoNegatividade[2] = carregamento.getBufferedReader().readLine();
        } catch (IOException ex) {
            System.out.println("Erro de leitura: " + ex.getMessage());
        }
        

        instancia = new PPL(tipo, qtdeRestricoes, qtdeVariaveis, funcaoObjetivo, restricoes, naoNegatividade);
        SimplexSolver simplexSolver = new SimplexSolver(instancia);
        simplexSolver.getProblema().print();
        
//        System.out.println("\nRealizando DUAL\n");
//        instancia = instancia.gerarDual();
//        instancia.print();
//        System.out.println("\nRealizando DUAL\n");
//        instancia = instancia.gerarDual();
//        instancia.print();

    }
}
