import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class Main2{
    private static Library library =new Library();
    private List<Book> borrowedBooks = new ArrayList<>();
    private CSVCoder<Book>booksCoder=null;
    public Main2(){
        booksCoder=new CSVCoder<>(';'){

            public Book decode(String[] data) {
                Book book = new Book();
                if (data.length == 5) {
                    book.setId(Integer.parseInt(data[0]));
                    book.setTitulo(data[1]);
                    book.setAutor(data[2]);
                    if (!data[3].isEmpty()) {
                        book.setAñoPublicacion(Integer.parseInt(data[3]));
                    }
                    if (!data[4].isEmpty()) {
                        book.setNumerodePaginas(Integer.parseInt(data[4]));
                    }
                }
                return book;
            
            }
            public String[] encode(Book book) {
                String[] data = new String[5];
                data[0] = String.valueOf(book.getId());
                data[1] = book.getTitulo();
                data[2] = book.getAutor();
                data[3] = String.valueOf(book.getAñoPublicacion());
                data[4] = String.valueOf(book.getNumerodePaginas());
                return data;
            }
        };
    }
  public void loadInfo(){
    List<Book>books= new ArrayList<>();
    try{
        booksCoder.readFromFile("data/Books.csv", books);
        library= new Library();
        for(Book book: books){
            library.addBook(book);
        }
        Console.writeLine();
    }catch(IOException e){
        Console.writeLine("Erro al intentar leer el archivo");
    }
  }
  public void loadInfo2() {
    List<Book> borrowedBooks = new ArrayList<>();
    try {
        booksCoder.readFromFile("data/prestamos.csv", borrowedBooks);
        for (Book book : borrowedBooks) {
            library.addBook(book);
        }
        Console.writeLine("Información de libros prestados cargada correctamente.");
    } catch (IOException e) {
        Console.writeLine("Error al intentar leer el archivo de préstamos.");
    }
}
  public void saveInfo(){
    try{
    booksCoder.writeToFile("data/Books.csv", library.getAllBooks());
  }catch(IOException e){
    Console.writeLine("Error al intenar guardar el archivo");
  }
}

public  void addBook() {
    loadInfo();
    Console.writeLine("Ingrese el Id:");
    int id = Integer.parseInt(Console.readLine());

    if (library.getBook(id) != null) {
        System.out.println("Error: este ID ya está siendo usado.");
        return; 
    }

    Console.writeLine("Ingrese el nombre del autor:");
    String autor = Console.readLine();

    Console.writeLine("Ingrese el nombre del libro:");
    String titulo = Console.readLine();

    Console.writeLine("Ingrese el año de la publicación del libro:");
    int añoPublicacion = Integer.parseInt(Console.readLine());

    Console.writeLine("Ingrese el número de páginas que contiene el libro:");
    int numerodePaginas = Integer.parseInt(Console.readLine());

    Book newBook = new Book();
    newBook.setId(id);
    newBook.setAutor(autor);
    newBook.setTitulo(titulo);
    newBook.setAñoPublicacion(añoPublicacion);
    newBook.setNumerodePaginas(numerodePaginas);
    

    library.addBook(newBook);

    try {
        saveInfo();
        System.out.println("Libro agregado exitosamente y guardado .");
    } catch (Exception e) {
        System.out.println("ERROR: No se pudo guardar el libro.");
    }
}
public void removeBook() {
    loadInfo();
    Console.writeLine("Ingrese la ID del libro que desea borrar:");
    int id = Integer.parseInt(Console.readLine());
    boolean found = false;
    for (Book book : library.getAllBooks()) {
        if (book.getId() == id) {
            library.removeBook(id);
            saveInfo(); 
            Console.writeLine("El libro ha sido borrado con éxito.");
            found = true;
            break;
        }
    }
    if (!found) {
        Console.writeLine("La ID ingresada no es correcta o no se encuentra el libro solicitado.");
    }
}
public void lendBook() {
    Main2 main2 = new Main2(); 
    main2.loadInfo(); 

    Console.writeLine("Ingrese la ID del libro que desea prestar:");
    int id = Integer.parseInt(Console.readLine());
    boolean found = false;
    for (Book book : main2.library.getAllBooks()) {
        if (book.getId() == id) {
            found = true;
            try {
                main2.booksCoder.writeToFile("data/prestamos.csv", main2.library.getAllBooks());
                main2.library.removeBook(id); 
                main2.saveInfo(); 
                Console.writeLine("El libro ha sido prestado con éxito.");
            } catch (IOException e) {
                Console.writeLine("Error al intentar prestar el libro.");
            }
            break;
        }
    }
    if (!found) {
        Console.writeLine("El libro con la ID proporcionada no se encontró en la biblioteca.");
    }
}
public void returnBook() {
    loadInfo();
    loadInfo2();
    Console.writeLine("Ingrese la ID del libro que desea devolver:");
    int id = Integer.parseInt(Console.readLine());
    boolean found = false;
    for (Book book : library.getAllBooks()) {
        if (book.getId() == id) {
            found = true;
            try {
                booksCoder.writeToFile("data/Books.csv", library.getAllBooks()); 
                saveInfo(); 
                borrowedBooks.remove(book);
                booksCoder.writeToFile("data/prestamos.csv", borrowedBooks); 
                Console.writeLine("El libro ha sido devuelto con éxito.");
            } catch (IOException e) {
                Console.writeLine("Error al intentar devolver el libro.");
            }
            break;
        }
    }
    if (!found) {
        Console.writeLine("El libro con la ID proporcionada no se encontró en la lista de préstamos.");
    }
}
public void showAllBooks() {
    loadInfo();
    loadInfo2();
    
    Console.writeLine("Libros disponibles:");
    for (Book book : library.getAllBooks()) {
        Console.writeLine(book.toString());
    }

    Console.writeLine("Libros prestados:");
    for (Book book : borrowedBooks) {
        Console.writeLine(book.toString());
    }
}
}


    


    
    


