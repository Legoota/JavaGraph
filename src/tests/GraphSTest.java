package tests;

import graphe.grapheS.GraphS;
import graphe.grapheS.Sommet;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Classe de test de GraphS
 */
public class GraphSTest {
    GraphS graphe;

    /**
     * Initialisation d'un graphe de test
     */
    @Before
    public void setUp() {
        graphe = new GraphS(6);
        graphe.addArete(0,1);
        graphe.addArete(2,3);
        graphe.addArete(2,1);
        graphe.addArete(1,3);
        graphe.addArete(4,0);
    }

    /**
     * Test d'ajout d'une valeur negative (doit renvoyer une erreur)
     */
    @Test (expected = IllegalArgumentException.class)
    public void isVoisinNegatif() {
        graphe.isVoisin(-1, 1);
        fail("Aurait du lancer IllegalArgumentException");
    }

    /**
     * Test de presence d'une arete entre deux sommets
     */
    @Test
    public void isVoisinOk() {
        assertEquals(true, graphe.isVoisin(0,1));
    }

    /**
     * Test de non presence d'une arete entre deux sommets
     */
    @Test
    public void isVoisinNotOk() {
        assertEquals(false, graphe.isVoisin(0,3));
    }

    /**
     * Test d'ajout d'une arete avec un sommet negatif
     */
    @Test (expected = IllegalArgumentException.class)
    public void addAreteNegatif() {
        graphe.addArete(-1,1);
    }

    /**
     * Test d'ajout d'une arete entre deux sommets (valide)
     */
    @Test
    public void addAreteOk() {
        assertEquals(true,(graphe.isVoisin(0,1)));
    }

    /**
     * Test d'ajout d'une arete entre deux sommets (non valide)
     */
    @Test
    public void addAreteNotOk() {
        assertEquals(false, graphe.isVoisin(0,3));
    }

    /**
     * Test de distance entre deux sommets (valide)
     */
    @Test
    public void distanceOk() {
        assertEquals(3,graphe.distance(3,4));
    }

    /**
     * Test de distance entre deux sommets qui n'ont pas de chemin (non valide)
     */
    @Test
    public void distanceNotOk() {
        assertEquals(-1,graphe.distance(5,0));
    }

    /**
     * Test de distance avec un sommet inexistant (doit rendre une erreur)
     */
    @Test (expected = IllegalArgumentException.class)
    public void distanceError() {
        graphe.distance(-1,1);
        fail("Aurait du lancer IllegalArgumentException");
    }

    /**
     * Test de suppression d'un sommet (a partir de l'objet sommet)
     */
    @Test
    public void testDeleteSommetTrueFromSommet() { assertEquals(true,graphe.deleteSommet(graphe.getSommetbyId(5))); }

    /**
     * Test de suppression d'un sommet (a partir de l'ID du sommet)
     */
    @Test
    public void testDeleteSommetTrueFromId() { assertEquals(true,graphe.deleteSommet(5)); }

    /**
     * Test de suppresion d'un sommet (a partir de l'objet sommet, doit renvoyer false)
     */
    @Test
    public void testDeleteSommetFalseFromSommet() {
        Sommet s = new Sommet(10);
        assertEquals(false,graphe.deleteSommet(s));
    }

    /**
     * Test de suppression d'un sommet (a partir de l'ID du sommet, erreur attendue)
     */
    @Test (expected = IllegalArgumentException.class)
    public void testDeleteSommetFalseFromId() {
        Sommet s = new Sommet(10);
        graphe.deleteSommet(10);
        fail("Aurait du lancer IllegalArgumentException");
    }

    /**
     * Test de suppression d'une arete (entre 2 sommets)
     */
    @Test
    public void testDeleteAreteTrueFromSommets() { assertEquals(true,graphe.deleteArete(graphe.getSommetbyId(4),graphe.getSommetbyId(0))); }

    /**
     * Test de suppression d'une arete (entre 2 IDs de sommets)
     */
    @Test
    public void testDeleteAreteTrueFromIds() { assertEquals(true,graphe.deleteArete(4,0)); }

    /**
     * Test de suppression d'une arete (entre 2 sommets)
     */
    @Test
    public void testDeleteAreteFalseFromSommets() { assertEquals(false,graphe.deleteArete(graphe.getSommetbyId(4),graphe.getSommetbyId(1))); }

    /**
     * Test de suppression d'une arete (entre 2 IDs de sommets)
     */
    @Test
    public void testDeleteAreteFalseFromIds() { assertEquals(false,graphe.deleteArete(4,1)); }


    /**
     * Test obtention sommet avec l'id : cas OK
     */
    @Test
    public void testGetSommetByIdOk() {
        assertEquals(new Sommet(3).getId(),graphe.getSommetbyId(3).getId());
    }

    /**
     * Test obtention sommet avec l'id incorrecte : cas NO OK
     */
    @Test (expected = IllegalArgumentException.class)
    public void testGetSommetByIdNOk() {
        graphe.getSommetbyId(10);
        fail("Aurait du lancer IllegalArgumentException");
    }

    /**
     * Test lancement d'erreur pour chemin inexistant
     */
    @Test (expected = IllegalArgumentException.class)
    public void testBFSPathNoPath() {
        graphe.BFSPath(graphe.getSommetbyId(0),graphe.getSommetbyId(5));
        fail("Aurait du lancer IllegalArgumentException");
    }

    /**
     * Test lancement d'erreur pour chemin avec sommet qui n'est pas dans le graphe
     */
    @Test (expected = IllegalArgumentException.class)
    public void testBFSPathNoSommet() {
        Sommet temp = new Sommet(10);
        graphe.BFSPath(graphe.getSommetbyId(0),temp);
        fail("Aurait du lancer IllegalArgumentException");
    }
}