package Models;

public class Book {
    private String idBook;
    private String name;
    private String pages;
    private String author;

    public Book(String idBook, String name, String pages, String author) {
        this.idBook = idBook;
        this.name = name;
        this.pages = pages;
        this.author = author;
    }

    public String getIdBook() {
        return idBook;
    }

    public void setIdBook(String idBook) {
        this.idBook = idBook;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "idBook=" + idBook +
                ", name='" + name + '\'' +
                ", pages=" + pages +
                ", author='" + author + '\'' +
                '}';
    }
}
