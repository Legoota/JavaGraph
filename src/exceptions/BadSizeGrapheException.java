package exceptions;

/**
 * Classe exception quand un graphe n'a pas les bonnes dimensions.
 */
public class BadSizeGrapheException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    int size;

    /**
     * Constructeur de la classe exception.
     * @param size La taille qui lance l'exception.
     */
    public BadSizeGrapheException(int size) { this.size = size; }

    /**
     * Methode d'affichage de l'erreur
     * @return Message d'erreur
     */
    public String toString() {
        return this.size < 0 ? "BadSizeGrapheException: " + this.size + " est trop petit."
                : "BadSizeGrapheException: " + this.size + " est trop grand.";
    }
}
