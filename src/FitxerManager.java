import model.Llibre;
import model.Usuari;

import java.io.*;
import java.util.ArrayList;

public class FitxerManager {

    public static ArrayList<Llibre> carregarLlibres(String file) {
        ArrayList<Llibre> llibres = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                llibres.add(Llibre.fromCSV(line));
            }
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
        return llibres;
    }

    public static ArrayList<Usuari> carregarUsuaris(String file) {
        return new ArrayList<>();
    }

    public static void guardarLlibres(ArrayList<Llibre> llibres,String file) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for(Llibre l : llibres){
                bw.write(l.toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing file.");
        }
    }




}


