import java.io.*;
import java.util.Scanner;
public class Quadtree{


    private int size;
    private int max_lumi;

    private QuadtreeNode racine;

    //constructeur 
    //charge l'image PGM et construit le quadtree correspondant
    public Quadtree(String FilePath) throws FileNotFoundException{ 

        //lecture du fichier PGM et initialisation de la matrice 
        int[][] mat_Nodes = readPGMFile(FilePath);
        //construction de l'arbre
        racine = buildQuadtree(mat_Nodes, 0, this.size-1 , 0, this.size-1);
    }

    public QuadtreeNode buildQuadtree(int[][] matrix, int debLigne, int finLigne, int debCol, int finCol){
        if(debLigne == finLigne && debCol == finCol)
            return new QuadtreeNode(matrix[debLigne][debCol], true);
        if(debCol-debLigne == 2 && matrix[debLigne][debCol] == matrix[debLigne][debCol+1] && matrix[debLigne][debCol+1] == matrix[debLigne+1][debCol] && matrix[debLigne][debCol+1] == matrix[debLigne+1][debCol+1])
            return new QuadtreeNode(matrix[debLigne][debCol], true);
        
        int milieuLigne = (debLigne+finLigne)/2;
        int milieuCol = (debCol+finCol)/2;

        QuadtreeNode fils1 = buildQuadtree(matrix, debLigne, milieuLigne, debCol, milieuCol);
        QuadtreeNode fils2 = buildQuadtree(matrix, debLigne, milieuLigne, milieuCol+1, finCol);
        QuadtreeNode fils3 = buildQuadtree(matrix, milieuLigne+1, finLigne, milieuCol+1, finCol);
        QuadtreeNode fils4 = buildQuadtree(matrix, milieuLigne+1, finLigne, debCol, milieuCol);

        return new QuadtreeNode(0, false,fils1, fils2, fils3, fils4);
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

    public int getSize(){ return this.size; }
    public int getLumMax(){ return this.max_lumi; }


    //affichage d'un noeud de l'arbre
    public String toString(){
        if(racine == null)
            return"()";
        else 
            return racine.toString();
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