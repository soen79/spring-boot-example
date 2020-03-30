package springbootapp.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by User on 1/1/2020.
 *
 * Reads files in the resources directory
 */
public class FileReader {

    public static String getSource(String fileName)   {
        Path path = null;
        try {
            path = Paths.get(FileReader.class.getClassLoader().getResource(fileName).toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return path.toString();
    }

    public static BufferedReader readDataFile(String fileName) {
        BufferedReader inputReader = null;

        try {
            Path path = Paths.get(FileReader.class.getClassLoader().getResource(fileName).toURI());
            inputReader = new BufferedReader(new java.io.FileReader(path.toFile()));
        } catch (FileNotFoundException | URISyntaxException ex) {
            System.err.println("File not found: " + fileName);
        }

        return inputReader;
    }
}
