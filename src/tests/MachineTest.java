package tests;

import graphe.grapheS.reseau.Ip;
import graphe.grapheS.reseau.Machine;
import graphe.grapheS.reseau.Routeur;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MachineTest {

    Machine m1;

    @Before
    public void setUp(){
        m1 = new Machine(0,Machine.Type.PC);
    }

    /**
     * Test de l'ajout d'une adresse ip a une machine
     */
    @Test
    public void testSetIp() {
        m1.setIp(new Ip(1,1,1,1));
        assertEquals(new Ip(1,1,1,1),m1.getIp());
    }

    /**
     * Test de l'obtention du reseau de la machine
     */
    @Test
    public void testGetReseau() {
        m1.setIp(new Ip(192,168,1,213));
        assertEquals(new Ip(192,168,1,0),m1.getReseau(new Ip(255,255,255,0)));
    }

    /**
     * Test de reattribution d'une adresse ip
     */
    @Test
    public void testDHCPM() {
        Routeur R = new Routeur(1,10,new Ip(1,1,1,1),new Ip(192,168,1,1),24);
        Machine m1 = new Machine(15,Machine.Type.PC);
        Machine m2 = new Machine(20,Machine.Type.PC);
        R.addMachine(m1);
        R.addMachine(m2);
        assertEquals(new Ip(192, 168,1,11),m1.getIp());
        m1.DHCPM(R);
        assertEquals(new Ip(192, 168,1,13),m1.getIp());
        m2.DHCPM(R);
        assertEquals(new Ip(192, 168,1,11),m2.getIp());
    }
}