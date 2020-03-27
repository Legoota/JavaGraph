package tests;

import graphe.grapheS.Sommet;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Classe de test de la classe Sommet
 */
public class SommetTest {

    private Sommet s1;
    private Sommet s2;
    private Sommet s3;
    private Sommet s4;
    private Sommet s5;

    @Before
    public void setUp() throws Exception {

        Sommet s1 = new Sommet(1);
        Sommet s2 = new Sommet(2);
        Sommet s3 = new Sommet(3);
        Sommet s4 = new Sommet(4);
        Sommet s5 = new Sommet(5);
    }

    @Test
    public void ajoutVoisinOk() {
        s1.addVoisin(s2);
        assertEquals(true, s1.isVoisin(s2));
    }

    @Test
    public void isVoisinOk() {
        s1.addVoisin(s2);
        assertEquals(true, s1.isVoisin(s2));
    }

    @Test
    public void isVoisinNotOk() {
        assertEquals(false, s1.isVoisin(s3));
    }
}