public class QuadtreeNode {
    private int valeur;
    private boolean est_feuille;
    private QuadtreeNode fils1;
    private QuadtreeNode fils2;
    private QuadtreeNode fils3;
    private QuadtreeNode fils4;
    
    public QuadtreeNode(int valeur, boolean est_feuille){
        this.valeur = valeur;
        this.est_feuille = est_feuille;
        this.fils1 = null;
        this.fils2 = null;
        this.fils3 = null;
        this.fils4 = null;
    }

    public QuadtreeNode(int valeur, boolean est_feuille, QuadtreeNode fils1, QuadtreeNode fils2, QuadtreeNode fils3, QuadtreeNode fils4){
        this.valeur = valeur;
        this.est_feuille = est_feuille;
        this.fils1 = fils1;
        this.fils2 = fils2;
        this.fils3 = fils3;
        this.fils4 = fils4;
    }

    public int getValue() { return this.valeur; }
    public boolean isLeaf() { return this.est_feuille; }
    public QuadtreeNode getFils1(){ return this.fils1; }
    public QuadtreeNode getFils2(){ return this.fils2; }
    public QuadtreeNode getFils3(){ return this.fils3; }
    public QuadtreeNode getFils4(){ return this.fils4; }

    public void setValue(int val){ this.valeur = val; }
    public void setIsLeaf(boolean newVal) { this.est_feuille = newVal; }
    public void setFils1(QuadtreeNode fils){ this.fils1 = fils; }
    public void setFils2(QuadtreeNode fils){ this.fils2  = fils; }
    public void setFils3(QuadtreeNode fils){ this.fils3 = fils; }
    public void setFils4(QuadtreeNode fils){ this.fils4 = fils; }

    //récupere le fils i 
    public QuadtreeNode getFils(int i){
        if(i==1)
            return this.fils1;
        else if(i==2)
            return this.fils2;
            else if(i==3)
                return this.fils3;
                else if(i==4)
                    return this.fils4;
                    else 
                        return null;
    }

    //methode qui teste si le noeud courant est une brindille
    //une brindille : si le noeud n'est pas une feuille mais que ces 4 fils sont des feuilles
    public boolean isBrindille(){
        if(!this.est_feuille && this.fils1.isLeaf() && this.fils2.isLeaf() && 
            this.fils3.isLeaf() && this.fils4.isLeaf())
            return true;
        else 
            return false;
    }

    //methode qui vérifie si les 4 fils sont des feuilles et sont egaux
    public boolean allCompressedTreeEqual(){
        if(this.fils1.isLeaf() && this.fils2.isLeaf() && this.fils3.isLeaf() &&
        this.fils4.isLeaf() && this.fils1.getValue() == this.fils2.getValue() &&
        this.fils1.getValue() == this.fils3.getValue() &&
        this.fils1.getValue() == this.fils4.getValue())
            return true;
        else
            return false;
    }

    //compter le nombre de noeud 
    public int countNodes(){
        if(this == null)
            return 0;
        else if(this.est_feuille)//une feuille
            return 1;
        else{
            int cptNode = 1;
            cptNode += this.fils1.countNodes(); 
            cptNode += this.fils2.countNodes();
            cptNode += this.fils3.countNodes();
            cptNode += this.fils4.countNodes();
            return cptNode;
        }
    }

    //methode d'affichage des valeurs du noeud
    public String toString(){
        if(est_feuille == true)
            return " " + this.valeur;
        else{ 
            return fils1.toString() + fils2.toString() + fils3.toString() + fils4.toString();
        }
    }


}
