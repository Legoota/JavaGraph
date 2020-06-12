package graphe.grapheS.reseau;

/**
 * Classe qui gere des adresses IP (ordre big-endian ou gros-boutisme)
 */
public class Ip implements Comparable<Ip>{

    private int A, B, C, D;

    /**
     * Constructeur permettant de creer une adresse ip a partie de 4 int
     * @param A 1er octet
     * @param B 2eme octet
     * @param C 3eme octet
     * @param D 4eme octet
     * @throws IllegalArgumentException Les octets sont négatifs
     */
    public Ip(int A, int B, int C, int D) throws IllegalArgumentException {
        if(A < 0 || B < 0 || C < 0 || D < 0 || A > 255 || B > 255 || C > 255 || D > 255) throw new IllegalArgumentException("Les octets de l'adresse ip ne peuvent pas etre negatifs ni superieurs a 255 !");
        this.A=A;
        this.B=B;
        this.C=C;
        this.D=D;
    }

    /**
     * Constructeur d'adresse Ip a partie d'un objet Ip
     * @param IP L'objet IP a dupliquer
     */
    public Ip(Ip IP) {
        this.A=IP.getA();
        this.B=IP.getB();
        this.C=IP.getC();
        this.D=IP.getD();
    }

    /**
     * Setter d'une adresse IP
     * @param ip L'adresse IP
     */
    public void setIp(Ip ip){
        this.A = ip.getA();
        this.B = ip.getB();
        this.C = ip.getC();
        this.D = ip.getD();
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
     * Methode hashCode
     * @return Code de hash de l'adresse IP
     */
    @Override
    public int hashCode() {
        String hash = ""+this.A + this.B + this.C + this.D;
        return hash.hashCode();
    }

    /**
     * Methode equals
     * @param o Objet a comparer avec l'instance courante
     * @return <b>True</b> si les deux objets ont la meme ip, <b>False</b> sinon
     */
    @Override
    public boolean equals(Object o){
        if(o != null)
        {
            Ip o2 = new Ip((Ip)o);
            if(o2.A == this.A && o2.B == this.B && o2.C == this.C && o2.D == this.D) { return true; }
        }
        return false;
    }

    /**
     * Methode qui implemente l'interface comparable
     * @param o Adresse ip
     * @return <b>0</b> si egale, <b>1</b> si superieur, <b>-1</b> si inferieur
     */
    @Override
    public int compareTo(Ip o) {
        if(o != null)
        {
            if(o.A == this.A && o.B == this.B && o.C == this.C && o.D == this.D) { return 0; }
            else{
                String thisAdd = ""+this.A + this.B + this.C + this.D;
                String oAdd = ""+o.A + o.B + o.C + o.D;
                return Long.parseLong(thisAdd) > (Long.parseLong(oAdd)) ? 1 : -1;
            }
        }
        else
            return -10;
    }

    /**
     * Methode pour incrementer une adresse ip
     */
    public void increment() {
        if(this.D < 255) this.D++;
        else{
            this.D=0;
            if(this.C < 255) this.C++;
            else{
                this.C=0;
                if(this.B < 255) this.B++;
                else{
                    this.B=0;
                    if(this.A < 255) this.A++;
                    else this.A=0;
                }
            }
        }
    }

    /**
     * Methode qui renvoit un string de l'adresse Ip
     * @return String de l'adresse Ip
     */
    public String toString() {
        return A+"."+B+"."+C+"."+D;
    }
}
