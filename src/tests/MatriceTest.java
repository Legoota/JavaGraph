package tests;

import exceptions.BadSizeGrapheException;
import matrice.Matrice;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.*;

/**
 * Classe de test de Matrice
 */
public class MatriceTest {
    private Matrice m;

    /**
     * Initialisation de la matrice d'adjacence pour le tests
     */
    @Before
    public void setUp() {
        int[][] mat =  {{0,1,1,0,1,1,0,0,0},
                        {1,0,0,1,0,0,0,0,0},
                        {1,0,0,0,0,0,0,0,0},
                        {0,1,0,0,0,1,1,0,0},
                        {1,0,0,0,0,0,0,0,0},
                        {1,0,0,1,0,0,1,0,0},
                        {0,0,0,1,0,1,0,1,0},
                        {0,0,0,0,0,0,1,0,1},
                        {0,0,0,0,0,0,0,1,0}};
        m = new Matrice(mat);
    }

    /**
     * Test de creation d'une matrice dont l'ordre est negatif (doit renvoyer une erreur)
     */
    @Test (expected = BadSizeGrapheException.class)
    public void creationMatriceImpossible() {
        Matrice m2 = new Matrice(-5);
        fail("Aurait du lancer BadSizeGrapheException");
    }

    /**
     * Test si la nouvelle matrice aleatoire est bien une instance de la classe Matrice (sinon, doit renvoyer une erreur)
     */
    @Test
    public void creationMatriceAleatoire() {
        Matrice m3 = new Matrice(5);
        assertThat(m3, instanceOf(Matrice.class));
    }

    /**
     * Test de recuperation d'un element qui n'est pas dans la matrice (doit renvoyer une erreur)
     */
    @Test (expected = IllegalArgumentException.class)
    public void getImpossible() {
        m.get(100, 100);
        fail("Aurait du lancer IndexOutOfBoundsException");
    }

    /**
     * Test de recuperation d'une valeur dans la matrice
     */
    @Test
    public void getValeur() {
        assertEquals(1, m.get(0, 1));
    }

    /**
     * Test d'ajout d'une valeur impossible dans la matrice (doit renvoyer une erreur)
     */
    @Test (expected = IllegalArgumentException.class)
    public void setImpossible() {
        m.set(100, 100, 100);
        fail("Aurait du lancer IndexOutOfBoundsException");
    }

    /**
     * Test d'ajout d'une valeur correcte dans la matrice
     */
    @Test
    public void setValeur() {
        m.set(0,0,10);
        assertEquals(10,m.get(0,0));
    }
}