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

    public String toString(){
        if(est_feuille == true)
            return " " + this.valeur;
        else 
            return "("+ fils1.toString()+ ")("+ fils2.toString()+ ")("+fils3.toString()+")("+fils4.toString()+")";
    }


}
