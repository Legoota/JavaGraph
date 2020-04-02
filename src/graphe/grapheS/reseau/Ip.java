package graphe.grapheS.reseau;

/**
 * Classe qui gere des adresses IP
 */
public class Ip implements Comparable<Ip>{

    private int A, B, C, D;

    /**
     * Constructeur permettant de creer une adresse ip a partie de 4 int
     * @param A 1er octet
     * @param B 2eme octet
     * @param C 3eme octet
     * @param D 4eme octet
     */
    public Ip(int A, int B, int C, int D) {
        this.A=A;
        this.B=B;
        this.C=C;
        this.D=D;
    }

    /**
     * Getteur 1er octet
     * @return 1er octet
     */
    public int getA() {
        return A;
    }

    /**
     * Getteur 2eme octet
     * @return 2eme octet
     */
    public int getB() {
        return B;
    }

    /**
     * Getteur 3eme octet
     * @return 3eme octet
     */
    public int getC() {
        return C;
    }

    /**
     * Getteur 4eme octet
     * @return 4eme octet
     */
    public int getD() {
        return D;
    }

    /**
     * Constructeur d'adresse ip a partie d'un objet ip
     * @param IP
     */
    public Ip(Ip IP) {
        this.A=IP.getA();
        this.B=IP.getB();
        this.C=IP.getC();
        this.D=IP.getD();
    }

    /**
     * Methode qui implemente l'interface comparable
     * @param o Adresse ip
     * @return 0 si egale, 1 si superieur, -1 si inferieur
     */
    @Override
    public int compareTo(Ip o) {
        // TODO : pour implementation de l'interface comparable
        return 0;
    }

    /**
     * Methode pour incrementer une adresse ip
     */
    public void increment() throws IllegalArgumentException{
        if(this.A < 0 || this.B < 0|| this.C < 0 || this.D < 0)throw new IllegalArgumentException("Les octets de l'adresse ip ne peuvent pas etre negatifs !");
        if(this.D < 255)this.D++;
        else{
            this.D=0;
            if(this.C < 255)this.C++;
            else{
                this.C=0;
                if(this.B < 255)this.B++;
                else{
                    this.B=0;
                    if(this.A < 255)this.A++;
                    else{
                        this.A=0;
                    }
                }
            }
        }
    }

    /**
     * Méthode qui renvoit un string de l'adresse Ip
     * @return String de l'adresse Ip
     */
    public String toString() {
        return A+"."+B+"."+C+"."+D;
    }

    /**
     * Main temporaire de test
     * @param args arguments du main de test
     */
    public static void main(String[] args){
        Ip adresse = new Ip(255, 255, 255, -10);
        adresse.increment();
        System.out.println(adresse.toString());
    }
}
