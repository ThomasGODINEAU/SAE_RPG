package modele;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Quete {
    int numero;
    int [] position = new int [2];
    int [] precondition = new int [4];
    int duree;
    int experience;
    String intitule;

    public Quete(String ligne) {
        Scanner scanner = new Scanner(ligne).useDelimiter("\\|");
        while (scanner.hasNext()) {
            numero = scanner.nextInt();

            String pos = scanner.next();
            pos = pos.replace("(", "");
            pos = pos.replace(")", "");
            pos = pos.replace(" ", "");
            Scanner scanPos = new Scanner(pos).useDelimiter(",");
            int i = 0;
            while (scanPos.hasNext()) {
                position[i] = Integer.parseInt(scanPos.next());
                i++;
            }

            String preconditions = scanner.next();
            preconditions = preconditions.replace("(", "");
            preconditions = preconditions.replace(")", "");
            preconditions = preconditions.replace(" ", "");
            Scanner scanPreconditions = new Scanner(preconditions).useDelimiter(",");
            int j = 0;
            while (scanPreconditions.hasNext()) {
                String extrait = scanPreconditions.next();
                if (! extrait.equals(""))
                    precondition[j] = Integer.parseInt(extrait);
                j++;
            }

            duree = scanner.nextInt();
            experience = scanner.nextInt();
            intitule = scanner.next();
        }
    }

    public boolean sansPrecondition(){
        for(int i=0; i<4; i++){
            if(this.precondition[i]!=0){
                return false;
            }
        }
        return true;
    }

    public String toString() {
        return (numero + " " + Arrays.toString(position) + " " + Arrays.toString(precondition) + " " + duree + " " + experience + " " + intitule + "\n");
    }
}
