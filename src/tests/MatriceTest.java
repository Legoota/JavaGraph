package tests;

import exceptions.BadSizeGrapheException;
import matrice.Matrice;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.*;

/**
 * Classe de test de la classe Matrice
 */
public class MatriceTest {
    private Matrice m;

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

    @Test (expected = BadSizeGrapheException.class)
    public void creationMatriceImpossible() {
        Matrice m2 = new Matrice(-5);
        fail("Aurait du lancer BadSizeGrapheException");
    }

    @Test
    public void creationMatriceAleatoire() {
        Matrice m3 = new Matrice(5);
        assertThat(m3, instanceOf(Matrice.class));
    }

    @Test (expected = IllegalArgumentException.class)
    public void getImpossible() {
        m.get(100, 100);
        fail("Aurait du lancer IndexOutOfBoundsException");
    }

    @Test
    public void getValeur() {
        assertEquals(1, m.get(0, 1));
    }

    @Test (expected = IllegalArgumentException.class)
    public void setImpossible() {
        m.set(100, 100, 100);
        fail("Aurait du lancer IndexOutOfBoundsException");
    }

    @Test
    public void setValeur() {
        m.set(0,0,10);
        assertEquals(10,m.get(0,0));
    }
}