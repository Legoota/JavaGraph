package tests;

import graphe.grapheS.GraphS;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Classe de test de la classe GraphS
 */
public class GraphSTest {
    GraphS graphe;

    @Before
    public void setUp() {
        graphe = new GraphS(5);
        graphe.addArete(0,1);
        graphe.addArete(2,3);
        graphe.addArete(2,1);
        graphe.addArete(1,3);
        graphe.addArete(4,0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void isVoisinNegatif() {

    }

    @Test
    public void isVoisinOk() {

    }

    @Test
    public void isVoisinNotOk() {

    }

    @Test (expected = IllegalArgumentException.class)
    public void addAreteNegatif() {

    }

    @Test
    public void addAreteOk() {

    }

    @Test
    public void addAreteNotOk() {

    }

    @Test
    public void distance() {
    }
}