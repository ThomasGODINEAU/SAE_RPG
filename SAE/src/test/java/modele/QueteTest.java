package modele;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class QueteTest {

    //Tests de la méthode sansPrecondition
    @Test
    public void testSansPreconditionToutesZero() {
        // Cas de test où toutes les préconditions sont égales à zéro
        Quete quete = new Quete("1|(4, 3)|()|2|100|explorer pic de Bhanborim");
        boolean result = quete.sansPrecondition();
        assertTrue(result);
    }

    @Test
    public void testSansPreconditionUneNonZero() {
        // Cas de test où une des préconditions est différente de zéro
        Quete quete = new Quete("2|(3, 1)|((1,),)|1|150|dialoguer avec Kaela la chaman des esprits");
        boolean result = quete.sansPrecondition();
        assertFalse(result);
    }

    //Tests de la méthode compareTo
    @Test
    public void testCompareToNumeroInferieur() {
        // Cas de test où le numéro de la quête appelante est inferieur au numéro de la quête donnée en paramètre
        Quete quete1 = new Quete("1|(4, 3)|()|2|100|explorer pic de Bhanborim");
        Quete quete2 = new Quete("2|(3, 1)|((1,),)|1|150|dialoguer avec Kaela la chaman des esprits");
        int result = quete1.compareTo(quete2);
        assertEquals(-1, result);
    }

    @Test
    public void testCompareToNumeroSuperieur() {
        // Cas de test où le numéro de la quête appelante est superieur au numéro de la quête donnée en paramètre
        Quete quete1 = new Quete("3|(0, 4)|((2,),)|3|200|explorer palais de Ahehona");
        Quete quete2 = new Quete("1|(4, 3)|()|2|100|explorer pic de Bhanborim");
        int result = quete1.compareTo(quete2);
        assertEquals(1, result);
    }

}
