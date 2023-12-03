import java.io.*;
import java.util.Scanner;
public class Quadtree{


    private int size;
    private int max_lumi;

    private QuadtreeNode racine;

    //constructeur 
    public Quadtree(int size){
        this.racine = null;
        this.size = size ;
        this.max_lumi = 0;
    }

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
        //if(finLigne-debLigne == finCol-debCol && matrix[debLigne][debCol] == matrix[debLigne][debCol+1] && matrix[debLigne][debCol+1] == matrix[debLigne+1][debCol] && matrix[debLigne][debCol+1] == matrix[debLigne+1][debCol+1])
            //return new QuadtreeNode(matrix[debLigne][debCol], true);
        
        int milieuLigne = (debLigne+finLigne)/2;
        int milieuCol = (debCol+finCol)/2;

        QuadtreeNode fils1 = buildQuadtree(matrix, debLigne, milieuLigne, debCol, milieuCol);
        QuadtreeNode fils2 = buildQuadtree(matrix, debLigne, milieuLigne, milieuCol+1, finCol);
        QuadtreeNode fils3 = buildQuadtree(matrix, milieuLigne+1, finLigne, milieuCol+1, finCol);
        QuadtreeNode fils4 = buildQuadtree(matrix, milieuLigne+1, finLigne, debCol, milieuCol);

        if(fils1.isLeaf() && fils2.isLeaf() && fils3.isLeaf() && fils4.isLeaf() && fils1.getValue()== fils2.getValue() && 
            fils1.getValue()==fils3.getValue() &&  fils1.getValue()== fils4.getValue())
            return new QuadtreeNode(fils1.getValue(), true);

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
    public QuadtreeNode getRacine(){    return this.racine; }

    public void setRacine(QuadtreeNode newracine){ this.racine = newracine; }

    //affichage d'un noeud de l'arbre
    public String toString(){
        if(racine == null)
            return"()";
        else if(racine.isLeaf())
            return "(" + racine.getValue() + ")";
        else 
            return "(("+racine.getFils1().toString()+")" + "("+racine.getFils2().toString()+")" +"("+racine.getFils4().toString()+")"+"("+racine.getFils3().toString()+"))";
    }

    //Methode qui génère à l'endroit path un fichier PGM qui correspond au Quadtree
    public void toPGM(String path) throws IOException { 
        //on va d'abord stoquer les valeurs du quadtree dans une matrice
        //ne pas stoquer la valeur de la racine qui ne correspond à aucune luminosité
        int[][] matrix = new int[this.size][this.size];
        fillMatrixFromQuadtree(matrix, this.racine, 0, 0, this.size-1, this.size-1);

        //Ensuite ecrire la matrice dans le fichier pgm
        try(BufferedWriter ecrire = new BufferedWriter(new FileWriter(path))){
            ecrire.write("P2\n");
            ecrire.write("#generation du pgm\n");
            ecrire.write(this.size + " " + this.size + "\n");
            ecrire.write(this.max_lumi + "\n");

            for(int i=0; i<this.size; i++){
                for(int j=0; j<this.size; j++){
                    ecrire.write(matrix[i][j] + " ");
                }
                ecrire.write("\n");
            }
        }
        System.out.println("Fichier PGM généré avec succés !");
    }

    //mettre l'arbre dans une matrice 
    public void fillMatrixFromQuadtree(int[][] matrix, QuadtreeNode noeud, int debLigne, int debCol, int finLigne, int finCol){
        if(noeud != null){
            if(noeud.isLeaf()){ //si feuille
                for (int i = debLigne; i <= finLigne; i++) {
                    for (int j = debCol; j <= finCol; j++) {
                        matrix[i][j] = noeud.getValue();
                    }
                }
            }else{

                int milieuLigne = (debLigne+finLigne)/2;
                int milieuCol = (debCol+finCol)/2;

                fillMatrixFromQuadtree(matrix, noeud.getFils1(), debLigne, debCol, milieuLigne, milieuCol);
                fillMatrixFromQuadtree(matrix, noeud.getFils2(), debLigne, milieuCol+1, milieuLigne, finCol);
                fillMatrixFromQuadtree(matrix, noeud.getFils3(), milieuLigne+1, milieuCol+1, finLigne, finCol);
                fillMatrixFromQuadtree(matrix, noeud.getFils4(), milieuLigne+1, debCol, finLigne, milieuCol);
            }
        }
    }

    //Methode qui compresse le Quadtree selon la premiere technique 2.3.1
    public void compressLambda(){ 
        compressBrindille(this.racine);
    }

    //methode pour compresser une brindille
    private void compressBrindille(QuadtreeNode node){
        if(node != null && !node.isLeaf()){ 
            //vérifier si les fils sont des brindilles (feuilles ayant le même parent)
            if(node.getFils1().isLeaf() && node.getFils2().isLeaf() && node.getFils3().isLeaf() && node.getFils4().isLeaf()){
                //calculer la moyenne logarthmique de luminosite Λ
                double SumLambda = 0.0;
                for(int i=1; i<=4; i++){
                    double y_i = node.getFils(i).getValue();
                    SumLambda += Math.log(0.1 + y_i);
                }
                double Λ = Math.exp(0.25*SumLambda);
                //remplacer la brindille par une feuille de luminosité round(Λ)
                int compressedValue = (int)Math.round(Λ);
                node.setIsLeaf(true);
                node.setValue(compressedValue);
                node.setFils1(null);
                node.setFils2(null);
                node.setFils3(null);
                node.setFils4(null);
            }else{
                //appliquer la compression lambda sur les fils
                compressBrindille(node.getFils1());
                compressBrindille(node.getFils2());
                compressBrindille(node.getFils3());
                compressBrindille(node.getFils4());
            }
        }
    }

    public Quadtree effeuillageComplet(){
        //cloner l'arbre d'origine pour ne pas le modifier directement
        Quadtree compressedTree = new Quadtree(this.size);
        QuadtreeNode rootCopy = cloneTree(this.racine);

        //compresser la copie de la racine
        compressBrindille(rootCopy);

        //definir la copie de la racine comme racine de la copie de l'arbre
        compressedTree.setRacine(rootCopy);

        return compressedTree;
    }

    //methode pour cloner un arbre 
    private QuadtreeNode cloneTree(QuadtreeNode node){
        if(node == null)
            return null;
        else if(node.isLeaf())
            return new QuadtreeNode(node.getValue(), true);
        else 
            return new QuadtreeNode(0, false,
            cloneTree(node.getFils1()), cloneTree(node.getFils2()),
            cloneTree(node.getFils3()), cloneTree(node.getFils4()));
    }
    

    //Methode qui compresse le Quadtree selon la seconde technique 2.3.2
    public void compressRho(int percentage){ //TODO

    }





    
}