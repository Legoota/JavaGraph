package exceptions;

import graphe.grapheS.reseau.Ip;

/**
 * Gestion des exceptions sur les erreurs d'ip
 */
public class BadIpException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    Ip myip;

    /**
     * Constructeur d'une classe exception
     * @param myip l'adresse ip qui lance l'exception
     */
    public BadIpException(Ip myip) {
        this.myip = myip;
    }

    /**
     * Methode d'affichage de l'erreur
     * @return Message d'erreur
     */
    public String toString() {
        return "BadIpException : " + myip + " n'est pas une adresse ip correcte !";
    }
}
