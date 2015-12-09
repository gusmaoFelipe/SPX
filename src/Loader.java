
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

/**
 *
 * @author gusmao
 */
public class Loader {

    private File arquivo;
    private String path;
    private FileInputStream fileInputStream;
    private InputStreamReader inputStreamReader;
    private BufferedReader bufferedReader;

    public Loader(String path) {
        this.path = path;
        this.arquivo = new File(path);
        try {
            this.fileInputStream = new FileInputStream(arquivo);
            this.inputStreamReader = new InputStreamReader(fileInputStream);
            this.bufferedReader = new BufferedReader(inputStreamReader);
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo não encontrado: " + ex.getMessage());
        }
    }

    /**
     * @return the arquivo
     */
    public File getArquivo() {
        return arquivo;
    }

    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @return the fileInputStream
     */
    public FileInputStream getFileInputStream() {
        return fileInputStream;
    }

    /**
     * @return the inputStreamReader
     */
    public InputStreamReader getInputStreamReader() {
        return inputStreamReader;
    }

    /**
     * @return the bufferedReader
     */
    public BufferedReader getBufferedReader() {
        return bufferedReader;
    }

}
