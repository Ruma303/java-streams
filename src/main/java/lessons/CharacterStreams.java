package lessons;

import java.io.*;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CharacterStreams {

    // Esempio di percorso relativo
    private static final String FILE_PATH = "./src/main/resources/output.txt";

    // Esempio di percorso assoluto
    static Path relativePath = Paths.get("src/main/resources/test.txt");
    static Path ABSOLUTE_FILE_PATH = relativePath.toAbsolutePath();

    public static void main(String[] args) {

        // readerWriter();
        // leggiEScriviBuffered();
        inputOutputStream();
    }

    // Le classi FileReader e FileWriter sono sottoclassi di Reader
    // e Writer ma non permettono la codifica
    public static Reader readerWriter() {
        Writer writer = null;
        Reader reader = null;

        try {
            // Scrivere su un file
            writer = new FileWriter(FILE_PATH);
            writer.write("Ciao mondo!");
            System.out.println("Scritto nel file: " + FILE_PATH);
            writer.close();

            // Leggere da un file
            reader = new FileReader(FILE_PATH);
            int c;
            while ((c = reader.read()) != -1) {
                System.out.print((char) c);
            }
            return reader;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Chiudere lo stream
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    // BufferedReader e BufferedWriter sono sottoclassi di Reader e Writer
    // e permettono di leggere e scrivere in modo più efficiente
    public static BufferedReader leggiEScriviBuffered() {
        BufferedWriter bw = null;
        FileReader reader = null;
        BufferedReader br = null;

        try {
            // Il secondo argomento di FileWriter è true per appendere,
            // false o nulla per sovrascrivere il contenuto
            bw = new BufferedWriter(new FileWriter(ABSOLUTE_FILE_PATH.toString(), true));

            // Scrivere su un file con Scanner
            Scanner input = new Scanner(System.in);
            System.out.println("Inserisci una stringa. Premi 0 per terminare.");

            while (true) {
                String line = input.nextLine();
                if (line.equals("0")) {
                    break;
                }
                bw.write(line);
                bw.newLine(); // (Opzionale) Aggiunge una nuova riga
            }
            bw.flush(); // Flush per forzare la scrittura su disco

            reader = new FileReader(ABSOLUTE_FILE_PATH.toString());
            br = new BufferedReader(reader);
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return br;
    }

    // BufferedInputStream e BufferedOutputStream sono sottoclassi di InputStream e OutputStream
    // Per questo conviene usare InputStreamReader e OutputStreamWriter
    // con le relative classi Buffered

    public static void inputOutputStream() {
        // Il try-with-resources chiude automaticamente gli stream
        try (
                // Legge byte per byte da un file
                InputStream inputStream = new FileInputStream(ABSOLUTE_FILE_PATH.toString());
                // Scrive byte per byte su un file
                OutputStream outputStream = new FileOutputStream(FILE_PATH)
        ) {
            System.out.println("Lettura da: " + ABSOLUTE_FILE_PATH);
            byte[] buffer = new byte[1024];
            int bytesRead;
            System.out.println("Scrittura su: " + FILE_PATH);
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
