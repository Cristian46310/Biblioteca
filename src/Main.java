
public class Main {
    public static void main(String[] args) {
        Main2 main2 = new Main2();
        int opcion;
        do {
            System.out.println("Menu principal");
            System.out.println();
            System.out.println("1) Agregar libro");
            System.out.println("2) Eliminar libro");
            System.out.println("3) Prestar un libro");
            System.out.println("4) Devolver un libro");
            System.out.println("5) Mostrar todos los libros");
            System.out.println("6)Salir del programa");
            System.out.println("Ingrese una opcion:");
                opcion = Integer.parseInt(Console.readLine());

                switch (opcion) {
                    case 1:
                        main2.addBook();
                        break;
                    case 2:
                    main2.removeBook();
                    break;
                    case 3:
                    main2.lendBook();
                    break;
                    case 4:
                    main2.returnBook();
                    break;
                    case 5:
                    main2.showAllBooks();
                    break;
                }
        }while(opcion!=6);
    }
}

