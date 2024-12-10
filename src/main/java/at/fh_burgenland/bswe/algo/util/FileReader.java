package at.fh_burgenland.bswe.algo.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FileReader {

    public static ArrayList<Integer> readFile(Path p) {
        ArrayList<Integer> result = new ArrayList<>();
        if (Files.notExists(p)) {
            System.err.println("Pfad nicht gefunden " + p);
            return null;
        }

        try (BufferedReader br = Files.newBufferedReader(p)) {
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] split = line.split(";");
                Integer employeeID = Integer.parseInt(split[0]);
                for (int i = 0; i < split.length; i++) {
                    result.add(Integer.parseInt(split[i]));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println(e.getMessage());
        }
        return result;
    }

    private static ArrayList<Integer> getReservationsForShift(String[] split) {
        ArrayList<Integer> reservations = new ArrayList<>();
        reservations.add(Integer.parseInt(split[4]));
        reservations.add(Integer.parseInt(split[5]));
        reservations.add(Integer.parseInt(split[6]));

        return reservations;
    }


}
