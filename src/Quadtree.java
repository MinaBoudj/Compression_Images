import java.io.*;
import java.util.Scanner;
public class Quadtree{


    private int size;
    private int max_lumi;

    private QuadtreeNode racine;

    public class QuadtreeNode{
        private int value;
        private QuadtreeNode fils1;
        private QuadtreeNode fils2;
        private QuadtreeNode fils3;
        private QuadtreeNode fils4;

        public QuadtreeNode(int value){
            this.value = value;
            this.fils1 = null;
            this.fils2 = null;
            this.fils3 = null;
            this.fils4 = null;
        }

        public QuadtreeNode(int value, QuadtreeNode fils1, QuadtreeNode fils2, QuadtreeNode fils3, QuadtreeNode fils4){
            this.value = value;
            this.fils1 = fils1;
            this.fils2 = fils2;
            this.fils3 = fils3;
            this.fils4 = fils4;
        }
        private int getValue(){
            return this.value;
        }

        private void setValue(int newVal){
            this.value = newVal;
        }

        private QuadtreeNode getFils1(){ return this.fils1; }
        private QuadtreeNode getFils2() { return this.fils2; }
        private QuadtreeNode getFils3() { return this.fils3; }
        private QuadtreeNode getFils4() { return this.fils4; }
    }

    //constructeur 
    //charge l'image PGM et construit le quadtree correspondant
    public Quadtree(String FilePath) throws FileNotFoundException{ 

        //lecture du fichier PGM et initialisation de la matrice 
        int[][] mat_Nodes = readPGMFile(FilePath);
        System.out.println(" size : "+size+ "\n");
        //construction de l'arbre
        racine = buildQuadtree(mat_Nodes, 0, 0, size, size);

    }

    private QuadtreeNode buildQuadtree(int[][] matrix, int x, int y, int width, int height){
        if(width == 1 && height == 1){
            return new QuadtreeNode(matrix[x][y]);
        }

        int halfWidth = width/2;
        int halfHeight  = height/2;

        QuadtreeNode fils1 = buildQuadtree(matrix, x, y, halfWidth, halfHeight);
        QuadtreeNode fils2 = buildQuadtree(matrix, x+halfWidth, y, halfWidth, halfHeight);
        QuadtreeNode fils3 = buildQuadtree(matrix, x, y+halfHeight, halfWidth, halfHeight);
        QuadtreeNode fils4 = buildQuadtree(matrix, x + halfWidth, y + halfHeight, halfWidth, halfHeight);

        return new QuadtreeNode(
            (fils1.getValue() + fils2.getValue() + fils3.getValue() + fils4.getValue())/4,
            fils1, fils2, fils3, fils4
            );
        
    }

    //lecture du fichier PGM et retourne la matrice correspondante
    private int[][] readPGMFile(String filename) throws FileNotFoundException{
            Scanner scanner = new Scanner(new File(filename));
            scanner.nextLine(); // 'P2'
            scanner.nextLine(); // commentaire    
            String[] tsize = scanner.nextLine().split((" "));
            this.size = Integer.parseInt(tsize[0]);
            this.max_lumi = Integer.parseInt(scanner.nextLine()); //liminosite max
            int[][] matrix = new int[size][size];
            for(int i = 0; i < size; i++){
                //String[] line = scanner.nextLine().split((" "));
                for(int j=0; j < size; j++){
                    matrix[i][j] = Integer.parseInt(scanner.next());
                }
            }
            scanner.close();
            return matrix;

    }

    //la méthode qui donne la représentation textuelle du quadtree sous forme parenthésée (comme vu en TD2) où
    // chaque valeur de luminosite sera representee par sa valeur decimale
    public String toString(){ 
        return "(" + this.racine.getValue() + " " + toString(this.racine.getFils1()) + " " + toString(this.racine.getFils2()) + " " + toString(this.racine.getFils3()) + " " + toString(this.racine.getFils4()) + ")" ;
    }

    //affichage d'un noeud de l'arbre
    public String toString(QuadtreeNode node){
        if(node == null)
            return "()";
        
        return "(" + node.getValue() + " " + toString(node.getFils1()) + " " + toString(node.getFils2()) + " " + toString(node.getFils3()) + " " + toString(node.getFils4()) + ")";
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