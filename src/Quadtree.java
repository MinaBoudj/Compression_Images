public class Quadtree{
    private Noeud racine;

    private static class Noeud{
        int luminosité;
        Noeud[] fils;

        Noeud(int luminosité){
            this.luminosité = luminosité;
            this.fils = new Noeud[4]; //4 fils pour chaque noeud
        }
    }
    //constructeur 
    public Quadtree(String FilePath){ //TODO
        //charge l'image PGM et construit le quadtree correspondant
        this.racine = null;
    }

    //la méthode qui donne la représentation textuelle du quadtree sous forme parenthésée (comme vu en TD2) où
    // chaque valeur de luminosite sera representee par sa valeur decimale
    public String toString(){ //TODO
        return " ";
    }

    //Methode qui génère à l'endroit path un fichier PGM qui correspond au Quadtree
    public void toPGM(String path){ //TODO

    }

    //Methode qui compresse le Quadtree selon la premiere technique 2.3.1
    public void compressLambda(){ //TODO

    }

    //Methode qui compresse le Quadtree selon la seconde technique 2.3.2
    public void compressRho(int percentage){ //TODO

    }





    
}