package tests;

import graphe.grapheS.Sommet;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Classe de test de Sommet
 */
public class SommetTest {

    private Sommet s1;
    private Sommet s2;
    private Sommet s3;
    private Sommet s4;
    private Sommet s5;

    /**
     * Initialisation de 5 sommets
     */
    @Before
    public void setUp() {

        s1 = new Sommet(1);
        s2 = new Sommet(2);
        s3 = new Sommet(3);
        s4 = new Sommet(4);
        s5 = new Sommet(5);
    }

    /**
     * Test ajout d'un voisin entre s1 et s2 (valide)
     */
    @Test
    public void ajoutVoisinOk() {
        s1.addVoisin(s2);
        assertEquals(true, s1.isVoisin(s2));
    }

    /**
     * Test de verification de la presence d'une arete entre s1 et s2 (valide)
     */
    @Test
    public void isVoisinOk() {
        s1.addVoisin(s2);
        assertEquals(true, s1.getVoisins().contains(s2));
    }

    /**
     * Test de verification de la non presence d'une arete entre s1 et s3
     */
    @Test
    public void isVoisinNotOk() {
        assertEquals(false, s1.getVoisins().contains(s3));
    }

    /**
     * Test du flag correctement initialise
     */
    @Test
    public void getFlagOk() {
        assertEquals(false, s4.getFlag());
    }

    /**
     * Test du setter de flag (valide)
     */
    @Test
    public void setFlagOk() {
        s5.setFlag(true);
        assertEquals(true, s5.getFlag());
    }

    /**
     * Test de l'impossibilite de creer un sommet avec id negatif (doit renvoyer une erreur)
     */
    @Test (expected = IllegalArgumentException.class)
    public void sommetConstructorNotOk() {
        new Sommet(-1);
        fail("N'aurait pas du creer un sommet d'id negatif");
    }
}