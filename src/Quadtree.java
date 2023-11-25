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
        //construction de l'arbre
        racine = buildQuadtree(mat_Nodes, 0, 0, size);

    }

    //
    public int getSize(){ return this.size; }

    public QuadtreeNode buildQuadtree(int[][] matrix, int x, int y, int width){
        if(width == 1){
            return new QuadtreeNode(matrix[x][y]);
        }
        //teste pour matrice = height=2 tester si les valeurs sont egaux créer un seul noeud

        int halfWidth = width/2;
        

        QuadtreeNode fils1 = buildQuadtree(matrix, x, y, halfWidth);
        QuadtreeNode fils2 = buildQuadtree(matrix, x+halfWidth, y, halfWidth);
        QuadtreeNode fils3 = buildQuadtree(matrix, x + halfWidth, y + halfWidth, halfWidth);
        QuadtreeNode fils4 = buildQuadtree(matrix, x, y+halfWidth, halfWidth);
       

        return new QuadtreeNode(
            (fils1.getValue() + fils2.getValue() + fils3.getValue() + fils4.getValue())/4,
            fils1, fils2, fils3, fils4
            );
        
    }


    //lecture du fichier PGM et retourne la matrice correspondante
    public int[][] readPGMFile(String filename) throws FileNotFoundException{
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
        return "(" + toString(this.racine.getFils1()) + " " + toString(this.racine.getFils2()) + " " + toString(this.racine.getFils4()) + " " + toString(this.racine.getFils3()) + ")" ;
    }

    //affichage d'un noeud de l'arbre
    public String toString(QuadtreeNode node){
        if(node == null)
            return "()";
        
        return "(" + node.getValue() + " " + toString(node.getFils1()) + " " + toString(node.getFils2()) + " " + toString(node.getFils4()) + " " + toString(node.getFils3()) + ")";
    }

    //Methode qui génère à l'endroit path un fichier PGM qui correspond au Quadtree
    public void toPGM(String path) throws FileNotFoundException { //TODO
        //on va d'abord stoquer les valeurs du quadtree dans une matrice
        //ne pas stoquer la valeur de la racine qui ne correspond à aucune luminosité
        int[][] matrix = new int[this.size][this.size];
        int i = 0;
        int j = 0;
        //matrix = buildMatrix(this, matrix, i, j);






        /*for(int i = 0; i < this.size; i++){
            for(int j = 0; j < this.size; j++){
                matrix[i][j] = this.racine.getValue();    
            }
        }

        //on va ensuite creer le fichier PGM
        PrintWriter file = new PrintWriter(new File(path + ".pgm"));
        file.println("P2");
        file.println("#commentaire");
        file.println(size + " " + size);
        file.println(max_lumi);
        for(int i = 0; i < size; i++){
            for(int j= 0; j<size; j++){
                file.print(matrix[i][j] + " ");
            }
            file.println();
        }

        file.close();*/

    }

    public int[][] buildMatrix(QuadtreeNode quadtree, int[][] matrix, int i, int j){
       /* if(quadtree == null )
            return matrix;
        else if(quadtree.getFils1()!= null){
            return buildMatrix(quadtree.getFils1(), matrix, i, j);
        }else if(quadtree.getFils2() != null){
            return buildMatrix(quadtree.getFils2(), matrix, i, j);
        }else if(quadtree.getFils3() != null){
            return buildMatrix(quadtree.getFils3(), matrix, i, j);
        }else if(quadtree.getFils4() != null){
            return buildMatrix(quadtree.getFils4(), matrix, i, j);
        }else{
            matrix[i][j] = quadtree.getValue();
                j += 1;
            else{
                i +=1;
                j = 0;
            }
            return matrix;
        }*/
        return matrix;
    }

    //Methode qui compresse le Quadtree selon la premiere technique 2.3.1
    public void compressLambda(){ //TODO

    }

    //Methode qui compresse le Quadtree selon la seconde technique 2.3.2
    public void compressRho(int percentage){ //TODO

    }





    
}