package Utility;

import java.io.*;

public class FileManager {

    public static Object loadFile(File f) throws IOException {
        try (var in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(f)))) {
            try {
                return in.readObject();
            } catch (ClassNotFoundException e) {
                throw new IOException(e);
            }
        }
    }

    public static void save(String filename, Object object) {
        try (var out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(object);
        } catch (IOException e) {
            //TODO Debug-statement
            System.out.println("File could not be saved");
        }
    }


}
