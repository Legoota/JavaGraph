package tests;
import graphe.grapheS.reseau.Ip;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Classe de test de Ip
 */
public class IpTest {

    Ip ipTest1;

    /**
     * Initialisation d'une adresse IP de test
     * @throws Exception IP non valide
     */
    @Before
    public void setUp() throws Exception {
        ipTest1 = new Ip(192,168,0,254);
    }

    /**
     * Test de creation d'une adresse IP avec une valeur superieure a 255
     */
    @Test (expected = IllegalArgumentException.class)
    public void createIpErrorHigher(){
        Ip ipError1 = new Ip(1,1,256,1);
        fail("Aurait du lancer IllegalArgumentException");
    }

    /**
     * Test de creation d'une adresse IP avec une valeur inferieure a 0
     */
    @Test (expected = IllegalArgumentException.class)
    public void createIpErrorLower(){
        Ip ipError2 = new Ip(1,1,-12,1);
        fail("Aurait du lancer IllegalArgumentException");
    }

    /**
     * Test de l'egalite de deux adresses Ip : cas OK
     */
    @Test
    public void testEqualsOk() {
        Ip ipTest2 = new Ip(192,168,0,254);
        assertEquals(true,ipTest1.equals(ipTest2));
    }

    /**
     * Test de l'egalite de deux adresses Ip : cas NO OK
     */
    @Test
    public void testEqualsNoOk() {
        Ip ipTest2 = new Ip(192,168,0,253);
        assertEquals(false,ipTest1.equals(ipTest2));
    }

    /**
     * Test de la compraaison de deux adresses ip : cas egal
     */
    @Test
    public void testCompareToEquals() {
        Ip ipTest2 = new Ip(192,168,0,254);
        assertEquals(0,ipTest1.compareTo(ipTest2));
    }

    /**
     * Test de la compraaison de deux adresses ip : cas inferieur
     */
    @Test
    public void testCompareToLower() {
        Ip ipTest2 = new Ip(192,168,0,255);
        assertEquals(-1,ipTest1.compareTo(ipTest2));
    }

    /**
     * Test de la compraaison de deux adresses ip : cas superieur
     */
    @Test
    public void testCompareToHigher() {
        Ip ipTest2 = new Ip(192,168,0,253);
        assertEquals(1,ipTest1.compareTo(ipTest2));
    }

    /**
     * Test de la methode d'incrementation d'un adresse ip
     */
    @Test
    public void testIncrement() {
        Ip ipTest3 = new Ip(255,255,255,255);
        ipTest3.increment();
        Ip ipTest2 = new Ip(0,0,0,0);
        assertEquals(ipTest2,ipTest3);
    }
}