package lessons;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScannerClass {
    public static void main(String[] args) {
        // print() non va a capo, quindi non legge il newline "\n"
        System.out.print("Scrivi qualcosa:");
        Scanner scanner = new Scanner(System.in);

        // Imposta il delimitatore per leggere fino a un newline
        scanner.useDelimiter("\n");
        System.out.println("Hai scritto: " + scanner.nextLine() + "\n");

        Scanner input = new Scanner(System.in);
        System.out.println("Inserisci un intero e un double separati da uno spazio:");
        int intValue = input.nextInt();
        double doubleValue = input.nextDouble();

        System.out.println("Intero: " + intValue);
        System.out.println("Double: " + doubleValue);
    }
}
