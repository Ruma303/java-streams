package lessons;

import java.io.FileInputStream;
import java.io.IOException;

public class ByteStreams {

  private static final String FILE_PATH = "./src/main/resources/img/code.png";
  private static final String FILE_PATH_WRITE = "./src/main/resources/data/output.dat";

  public static void main(String[] args) {

    //getFileInputStream(FILE_PATH);
    //optimizedFileInputStream(FILE_PATH);

  }

  // Esempio non ottimale
  public static FileInputStream getFileInputStream(String fileName) {
    FileInputStream fis = null;
    try {
      fis = new FileInputStream(FILE_PATH);
      int data;
      while ((data = fis.read()) != -1) {
        System.out.print((char) data);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (fis != null) {
        try {
          fis.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return fis;
  }

  public static FileInputStream optimizedFileInputStream(String fileName) {
    try (FileInputStream fis = new FileInputStream(FILE_PATH)) {
      int data;
      while ((data = fis.read()) != -1) {
        System.out.print((char) data); // Stampa i byte letti come caratteri
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }


}
