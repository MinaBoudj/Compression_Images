import java.util.Scanner;
import java.io.*;

public class Principal{
    public static void main(String[] args) throws IOException{
        //mode interactif
        if(args.length == 0){
                try{
                        //initialisation d'un objet Quadtree à partir d'un fichier PGM
                        String image = "JDD2.pgm";
                        int percentage = 0;
                        Quadtree quadtree = new Quadtree("pgm_carres/JDD2.pgm");
                        Quadtree quadtreeCompressLambda = new Quadtree(quadtree.cloneTree(quadtree.getRacine()), quadtree.getSize(), quadtree.getLumMax());
                        Quadtree quadtreeCompressRho = new Quadtree(quadtree.cloneTree(quadtree.getRacine()), quadtree.getSize(), quadtree.getLumMax());
                        System.out.println(" teste de la fonction toString !! ");
                        // Écriture dans le fichier texte
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output/toString.txt"))) {
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
                                        case 1: System.out.println("Entrer le nom de l'image : ");
                                                String newImagePath = "pgm_carres/";
                                                newImagePath +=  scanner.next(); 
                                                quadtree = new Quadtree(newImagePath);
                                                break;

                                        case 2: 
                                                quadtreeCompressLambda.compressLambda();
                                                quadtreeCompressLambda.toPGM("pgm_carres/testCompressLambda.pgm");
                                                // Écriture dans le fichier texte
                                                try (BufferedWriter writer = new BufferedWriter(new FileWriter("output/toStringCompressLambda.txt"))) {
                                                        writer.write(quadtreeCompressLambda.toString());
                                                } catch (IOException e) {
                                                        e.printStackTrace();
                                                }
                                                //System.out.println(quadtree.toString());
                                                System.out.println("Le nombre de noeud après compression Lambda : "+quadtreeCompressLambda.countNodes());
                                                break;

                                        case 3: System.out.println("Entrer la valeur du pourcentage de compression Rho : ");
                                                percentage = scanner.nextInt();
                                                quadtreeCompressRho.compressRho(percentage);
                                                quadtreeCompressRho.toPGM("pgm_carres/testCompressRho.pgm");
                                                try (BufferedWriter writer = new BufferedWriter(new FileWriter("output/toStringCompressRho.txt"))) {
                                                        writer.write(quadtreeCompressRho.toString());
                                                } catch (IOException e) {
                                                        e.printStackTrace();
                                                }
                                                System.out.println("Le nombre de noeud aprés compression RHo : "+quadtreeCompressRho.countNodes());
                                                break;

                                        case 4: 
                                                quadtree.toPGM("pgm_carres/testToPGM.pgm");
                                                break;

                                        case 5: 
                                                String filename = "output/Statistique.txt";
                                                try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
                                                        writer.write("----- Staistiques sur l'image "+ image +" -----\n");
                                                        writer.write("Le nombre de noeud initiale : "+quadtree.countNodes()+"\n");
                                                        writer.write("Le nombre de noeud après compression Lambda : "+quadtreeCompressLambda.countNodes()+ "\n");
                                                        writer.write("pour rho = "+ percentage + " ,le nombre de noeud après compression Rho :"+quadtreeCompressRho.countNodes()+"\n");
                                                        writer.write("\n");
                                                } catch (IOException e) {
                                                        e.printStackTrace();
                                                }
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
                        Quadtree quadtreeCompressLambda = new Quadtree(quadtree.cloneTree(quadtree.getRacine()), quadtree.getSize(), quadtree.getLumMax());
                        Quadtree quadtreeCompressRho = new Quadtree(quadtree.cloneTree(quadtree.getRacine()), quadtree.getSize(), quadtree.getLumMax());
                        quadtreeCompressLambda.compressLambda();;
                        quadtreeCompressRho.compressRho(rho);
                        // Vos lignes de code
                        System.out.println("----- Statistiques sur l'image " + filePath + " -----");
                        System.out.println("Le nombre de noeud initiale : " + quadtree.countNodes());
                        System.out.println("Le nombre de noeud après compression Lambda : " + quadtreeCompressLambda.countNodes());
                        System.out.println("Le nombre de noeud après compression Rho : " + quadtreeCompressRho.countNodes());

                        
                }else{
                        System.out.println("Utilisation incorrecte. Veuillez spécifier le chemin du fichier PGM et la valeur de rho");
                }
        }

    }



}