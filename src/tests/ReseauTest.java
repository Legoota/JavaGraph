package tests;

import graphe.grapheS.reseau.Ip;
import graphe.grapheS.reseau.Reseau;
import graphe.grapheS.reseau.Routeur;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Classe de test de Reseau
 */
public class ReseauTest {

    private Reseau reseau;

    /**
     * Initialisation du reseau pour les tests sur Reseau
     */
    @Before
    public void setUp() {
        reseau = new Reseau(10,5);
    }

    /**
     * Test d'ajout d'un routeur au reseau
     */
    @Test
    public void addRouteurOk() {
        Routeur r = new Routeur(100,2,new Ip(1,1,1,100), new Ip(192,168,1,100),24);
        reseau.addRouteur(r);
        assertEquals(11, reseau.getTaille());
    }

    /**
     * Test de non ajout d'un routeur au reseau (car deja present)
     */
    @Test (expected = IllegalArgumentException.class)
    public void addRouteurNOk() {
        Routeur r = new Routeur(100,2,new Ip(1,1,1,100), new Ip(192,168,1,100),24);
        reseau.addRouteur(r);
        reseau.addRouteur(r); // ajout duplique
        fail("Aurait du lancer IllegalArgumentException");
    }

    /**
     * Test de suppression d'un routeur du reseau
     */
    @Test
    public void deleteRouteurOk() {
        Routeur r = new Routeur(100,2,new Ip(1,1,1,100), new Ip(192,168,1,100),24);
        reseau.addRouteur(r);
        assertEquals(true, reseau.deleteRouteur(new Ip(1,1,1,100)));
    }

    /**
     * Test de la non suppression d'un routeur qui n'est pas dans le graphe
     */
    @Test
    public void deleteRouteurNOk() {
        assertEquals(false, reseau.deleteRouteur(new Ip(100,1,1,1)));
    }

    /**
     * Test d'obtention d'un routeur a partir de son adresse IP ext
     */
    @Test
    public void getRouteurFromIp() {
        assertEquals(1, reseau.getRouteurFromIp(new Ip(1,1,1,1)).getId());
    }

    /**
     * Test d'erreur sur l'obtention d'un routeur a partir d'une adresse IP inexistante
     */
    @Test (expected = IllegalArgumentException.class)
    public void getRouteurFromIpFalse() {
        reseau.getRouteurFromIp(new Ip(10,1,1,1)); // IP non presente dans le reseau
        fail("Aurait du lancer IllegalArgumentException");
    }

    /**
     * Test d'obtention d'un routeur a partir de son ID
     */
    @Test
    public void getRouteurFromId() {
        assertEquals(new Ip(1,1,1,1),reseau.getRouteurFromId(1).getExt());
    }

    /**
     * Test d'erreur sur l'obtention d'un routeur a partir d'un ID inexistant
     */
    @Test (expected = IllegalArgumentException.class)
    public void getRouteurFromIdFalse() {
        reseau.getRouteurFromId(100);
        fail("Aurait du lancer IllegalArgumentException");
    }
}