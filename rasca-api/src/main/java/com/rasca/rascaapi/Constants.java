package com.rasca.rascaapi;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class Constants {
   // public static String API_SECRET_KEY = lecturaLlave();

    public static final long TOKEN_VALIDITY = 2*60*60*1000;

    public static String API_SECRET_KEY() {
        String data = "";
        try {
            File archivoLlave = new File("llaveSecreta.key");
            Scanner scanner = new Scanner(archivoLlave);
            while (scanner.hasNextLine()) {
                data = scanner.nextLine();
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }
}
