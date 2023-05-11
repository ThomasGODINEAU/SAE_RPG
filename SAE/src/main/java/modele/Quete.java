package modele;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Quete {
    private int numero;            // Numéro de la quête
    private int [] position = new int [2];     // Coordonnées de la quête (position x,y)
    private int [] precondition = new int [4]; // Tableau des préconditions (4 éléments max)
    private int duree;             // Durée de la quête (en minutes)
    private int experience;        // Points d'expérience rapportés par la quête
    private String intitule;       // Titre de la quête

    /** Constructeur de la classe Quete. Il range les données lues dans les champs correspondants.
     *
     * @param ligne (String) : ligne du fichier lu a traiter et ordonner
     */
    public Quete(String ligne) {
        Scanner scanner = new Scanner(ligne).useDelimiter("\\|");
        while (scanner.hasNext()) {
            // Lecture des différents champs de la ligne de texte pour initialiser les attributs de la quête
            numero = scanner.nextInt();

            // Lecture et parsage de la position de la quête
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

            // Lecture et parsage des préconditions de la quête
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

    /** Méthode qui retourne true si la quête n'a pas de préconditions et false sinon
     *
     * @return (boolean)
     */
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


    public int compareTo(Quete quete) {
        if (this.numero < quete.numero) return -1;
        return 1;
    }

    // Ci-dessous se trouvent les méthodes "get"

    public int getNumero() {
        return numero;
    }

    public int[] getPosition() {
        return position;
    }

    public int[] getPrecondition() {
        return precondition;
    }

    public int getDuree() {
        return duree;
    }

    public int getExperience() {
        return experience;
    }
}
