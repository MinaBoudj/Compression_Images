import java.util.Scanner;
import java.io.*;

public class Principal{
    public static void main(String[] args) throws IOException{
        //mode interactif
        if(args.length == 0){
                try{
                        //initialisation d'un objet Quadtree à partir d'un fichier PGM
                        Quadtree quadtree = new Quadtree("pgm_carres/test.pgm");
                        System.out.println(" teste de la fonction toString : ");
                        // Écriture dans le fichier texte
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
                                writer.write(quadtree.toString());
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                        System.out.println("Le nombre de noeud initiale : "+quadtree.countNodes());
                        //affichage du menu interactif
                        Scanner scanner = new Scanner(System.in);
                        int choice;

                        do{
                                System.out.println("1. Choisir une autre image PGM");
                                System.out.println("2. Appliquer la compression Lambda");
                                System.out.println("3. Appliquer la compression Rho");
                                System.out.println("4. Générer le fichier PGM dans le répertoire courant");
                                System.out.println("5. Afficher les statistiques de compression ");
                                System.out.println("6. QUITTER");

                                System.out.println("Choix : ");
                                choice = scanner.nextInt();

                                switch (choice){
                                        case 1: System.out.println("Entrer le chemin vers une autre image : ");
                                                String newImagePath = scanner.next();
                                                quadtree = new Quadtree(newImagePath);
                                                break;

                                        case 2: //quadtree.compressLambda();
                                                Quadtree quadtreeCompress = quadtree.compressLambda();
                                                quadtreeCompress.toPGM("pgm_carres/testCompressLambda.pgm");
                                                System.out.println(quadtreeCompress.toString());
                                                System.out.println("Le nombre de noeud après compression Lambda : "+quadtreeCompress.countNodes());
                                                break;

                                        case 3: System.out.println("Entrer la valeur du pourcentage de compression Rho : ");
                                                int percentage = scanner.nextInt();
                                                Quadtree compressQuad = quadtree.compressRho(percentage);
                                                compressQuad.toPGM("pgm_carres/testCompressRho.pgm");
                                                System.out.println("Le nombre de noeud aprés compression RHo : "+compressQuad.countNodes());
                                                break;

                                        case 4: //System.out.println("Entrer le chemin de sortie du fichier PGM : ");
                                                //String outputFilePath = scanner.next();
                                                quadtree.toPGM("pgm_carres/testToPGM.pgm");
                                                break;

                                        case 5: //le nombre de noeuds du quadtree initial
                                                System.out.println("Le nombre de noeud initiale : "+quadtree.countNodes());
                                                //TODO
                                                //le taux de compression réalisé
                                                break;
                                        default : System.out.println("Vous avez QUITTER !");
                                }

                        }while(choice !=6);

                        scanner.close();
                }catch(FileNotFoundException e){
                        System.out.println("Erreur de lecture du fichier "+ e);
                }
        }else{
                //mode non interactif
                if(args.length == 2){
                        String filePath = args[0];
                        int rho = Integer.parseInt(args[1]);

                        Quadtree quadtree = new Quadtree(filePath);
                        quadtree.compressLambda();;
                        quadtree.compressRho(rho);

                        //Générer les fichiers correpondants et afficher les statistiques 
                        //..
                }else{
                        System.out.println("Utilisation incorrecte. Veuillez spécifier le chemin du fichier PGM et la valeur de rho");
                }
        }

    }



}