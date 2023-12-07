import java.util.*;
public class EcartBrindille {
    private double ecart;
    private QuadtreeNode noeud;
    private QuadtreeNode pere;

    //constructeur
    public EcartBrindille(double ecart, QuadtreeNode noeud, QuadtreeNode pere){
        this.ecart = ecart;
        this.noeud = noeud;
        this.pere = pere;
    }

    //getters et setters
    public void setEcart(int ecart){ this.ecart = ecart; }
    public void setNoeud(QuadtreeNode node){ this.noeud = node; }
    public void setPere(QuadtreeNode pere){ this.pere = pere; }

    public double getEcart(){ return this.ecart; }
    public QuadtreeNode getNode(){ return this.noeud; }
    public QuadtreeNode getPere(){ return this.pere; }

}