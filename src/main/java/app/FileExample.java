package src.main.java.app;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public abstract class FileExample {

    /* Context doesn't matter to it, if the line doesn't contain the regex, it skips it */

    public abstract boolean writeToFile(String fileName, ArrayList<String> credentials);

    public abstract ArrayList<Map<String, String>> readFromFile(String fileName);

    public boolean write(ArrayList<String> listToAdd, String fileName, Map<String, String> checkContainment, boolean appendEnable) {
        if (readFromFile(fileName).contains(checkContainment)) {
            return false;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, appendEnable))) {
            for (String item : listToAdd) {
                writer.write(item);
                if (listToAdd.indexOf(item) != listToAdd.size() - 1) {
                    writer.write(":");
                } else {
                    writer.newLine();
                }
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
