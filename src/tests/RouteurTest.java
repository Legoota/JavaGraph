package tests;

import exceptions.BadIpException;
import graphe.grapheS.reseau.Ip;
import graphe.grapheS.reseau.Machine;
import graphe.grapheS.reseau.Routeur;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Classe de test de Routeur
 */
public class RouteurTest {

    Routeur r;

    /**
     * Initialisation d'un routeur pour les tests sur Reseau
     */
    @Before
    public void setUp() {
        r = new Routeur(1,5,new Ip(1,1,1,1), new Ip(192,168,1,0),24);
    }

    /**
     * Test de l'attribution d'IP aux machines avec le DHCP
     */
    @Test
    public void DHCP() {
        r.DHCP();
        boolean res = true;
        for(int i = 0; i < r.getSommets().size(); i++) if(r.getMachineById(i).getIp() == null) res = false;
        assertEquals(true,res);
    }

    /**
     * Test d'obtention de la machine a partir de son id
     */
    @Test
    public void getMachineById() {
        Machine m = new Machine(1, Machine.Type.PC);
        assertEquals(m.getId(),r.getMachineById(1).getId());
    }

    /**
     * Test de lancement d'erreur si id inexistant
     */
    @Test (expected = IllegalArgumentException.class)
    public void getMachineByIdError() {
        r.getMachineById(50);
        fail("Aurait du lancer IllegalArgumentException");
    }

    /**
     * Test d'obtention de la machine a partir de son adresse Ip
     */
    @Test
    public void getMachineByIp() {
        Machine m = new Machine(0, Machine.Type.PC);
        m.setIp(new Ip(192,168,1,1));
        r.DHCP(); // mise en place des adresses Ip
        assertEquals(m.getId(),r.getMachineByIp(new Ip(192,168,1,1)).getId());
    }

    /**
     * Test du lancement d'erreur si Ip inexistant
     */
    @Test (expected = BadIpException.class)
    public void getMachineByIpError() {
        r.DHCP(); // mise en place des adresses Ip
        r.getMachineByIp(new Ip(192,168,1,200));
        fail("Aurait du lancer BadIpException");
    }

    /**
     * Test d'ajout d'une machine au routeur
     */
    @Test
    public void addMachine() {
        Machine m = new Machine(100, Machine.Type.PC);
        r.addMachine(m);
        assertEquals(6,r.getTaille());
    }

    /**
     * Test de lancement d'exception
     */
    @Test (expected = IllegalArgumentException.class)
    public void addMachineError() {
        Machine m = new Machine(1, Machine.Type.PC);
        r.addMachine(m);
        fail("Aurait du lancer IllegalArgumentException");
    }

    /**
     * Test de suppression d'une machine a partir de son id
     */
    @Test
    public void deleteMachineFromId() { assertEquals(true,r.deleteMachine(1)); }

    /**
     * Test de lancement d'erreur lors de la suppression d'une machine d'id inexistant
     */
    @Test (expected = IllegalArgumentException.class)
    public void deleteMachineFromIdError() {
        r.deleteMachine(100);
        fail("Aurait du lancer IllegalArgumentException");
    }

    /**
     * Test de suppression d'une machine a partir d'elle meme
     */
    @Test
    public void deleteMachineFromMachine() { assertEquals(true,r.deleteMachine(r.getMachineById(1))); }

    /**
     * Test de la methode ping entre deux machines du routeur
     */
    @Test
    public void ping() {
        r.DHCP();
        assertEquals(true,r.ping(r.getMachineById(1),r.getMachineById(2)));
    }

    /**
     * Test de la methode message entre deux machines
     */
    @Test
    public void message() {
        r.DHCP();
        assertEquals(true,r.message("test",new Ip(192,168,1,1), new Ip(192,168,1,2)));
    }
}