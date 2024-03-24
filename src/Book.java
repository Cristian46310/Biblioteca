public class Book {
    private int id=0;
    private String Titulo="";
    private String Autor="";
    private int AñoPublicacion=0;
    private int numerodePaginas=0;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitulo() {
        return Titulo;
    }
    public void setTitulo(String titulo) {
        Titulo = titulo;
    }
    public String getAutor() {
        return Autor;
    }
    public void setAutor(String autor) {
        Autor = autor;
    }
    public int getAñoPublicacion() {
        return AñoPublicacion;
    }
    public void setAñoPublicacion(int añoPublicacion) {
        AñoPublicacion = añoPublicacion;
    }
    public int getNumerodePaginas() {
        return numerodePaginas;
    }
    public void setNumerodePaginas(int numerodePaginas) {
        this.numerodePaginas = numerodePaginas;
    }
    public String toString() {
        return "ID: " + id + ", Título: " + Titulo + ", Autor: " + Autor + ", Año de publicación: " + AñoPublicacion + ", Número de páginas: " + numerodePaginas;
    
}
}