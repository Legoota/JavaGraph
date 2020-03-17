package matrice;

/**
 * Classe Matrice (tableau 2D)
 */
public class Matrice {
    public final int ordre;
    int[][] A;

    //TODO: Verif si tout marche bien
    /**
     * Initialize la matrice avec des valeurs aleatoires entre 0 et 9.
     * @param ordre Dimension de la matrice.
     */
    public Matrice(int ordre){
        this.ordre = ordre;
        this.A = new int[ordre][ordre];
        for (int i = 0;i < this.ordre;i++){
            for (int j = 0;j < this.ordre;j++){
                A[i][j] = (int)(Math.random()*10);
            }
        }
    }

    /**
     * Initialise la matrice en prenant directemnt un tableau d'entiers.
     * @param M Le tableau de valeurs.
     */
    public Matrice(int[][] M){
        this.ordre = M.length;
        this.A = M;
    }

    /**
     * Permet d'obtenir un element de la matrice.
     * @param l Position par rapport au premier parametre.
     * @param h Position par rapport au second parametre.
     * @return Valeur souhaitee.
     */
    public int get(int l, int h) {
        return this.A[l][h];
    }

    /**
     * Change la valeur d'un elment de la matrice.
     * @param l Position par rapport au premier paramï¿½tre.
     * @param h Position par rapport au second paramï¿½tre.
     * @param v Valeur positionnelle en (l,h).
     */
    public void set(int l,int h, int v) {
        this.A[l][h] = v;
    }

    /**
     * @return Une chaine de caractere representant la matrice.
     */
    public String toString(){
        String str = "";
        for (int i = 0;i < this.ordre;i++){
            for (int j = 0;j < this.ordre;j++){
                str += this.A[i][j]+" ";
            }
            str += "\n";
        }
        return str;
    }

    /**
     * Permet d'obtenir le tableau a deux dimensions correspondant a la matrice
     * @return Tableau en deux dimensions de la matrice
     */
    public int[][] getArray() {
        return this.A;
    }

    /**
     * Verifie l'egalite d'une matrice avec la matrice de l'instance invoquee.
     * @param M La matrice a comparer.
     * @return <b>True</b> si les deux matrices sont identiques, <b>False</b> sinon.
     */
    public boolean equals(Matrice M) {
        boolean b = true;
        if (this.ordre != M.ordre) b = false;
        else {
            for (int i = 0;i < this.ordre;i++){
                for (int j = 0;j < this.ordre;j++){
                    if(this.A[i][j] != M.get(i,j)) b=false;
                }
            }
        }
        return b;
    }

    /**
     * Ajoute deux matrices.
     * @param M La matrice a ajouter a la matrice de l'instance courrante.
     * @return Le resultat de la somme.
     */
    public Matrice add(Matrice M) {
        Matrice Mret=new Matrice(this.ordre);
        for (int i=0;i<this.ordre;i++) {
            for (int j=0;j<this.ordre;j++) {
                Mret.set(i,j,A[i][j]+M.get(i,j));
            }
        }
        return Mret;
    }

    /**
     * Multiplie deux matrices.
     * @param M La matrice a multiplier avec la matrice de l'instance courrante.
     * @return Le resultat du produit.
     */
    public Matrice mult(Matrice M) {
        Matrice Mret=new Matrice(this.ordre);
        for (int i=0;i<this.ordre;i++) {
            for (int j=0;j<M.ordre;j++) {
                int v=0;
                for (int k=0;k<this.ordre;k++)v+=A[i][k]*M.get(k,j);
                Mret.set(i,j,v);
            }
        }
        return Mret;
    }

    /**
     * Calculle puissance de la matrice.
     * @param k La matrice a mettre a la puissance k.
     * @return Le resultat du produit.
     */
    public Matrice puissance(int k) {
        Matrice Mret = new Matrice(A);
        if (k==1) return Mret;
        Mret = puissance(k/2);
        Mret = Mret.mult(Mret);
        if (k%2 == 1) Mret = Mret.mult(this);
        return Mret;
    }
}