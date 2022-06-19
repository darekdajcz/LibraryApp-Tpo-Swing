import Models.Book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class BookService {

    public ArrayList<Book> getBookList() {
        var bookList = new ArrayList<Book>();
        try {
            Connection connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3308/library", "root", "Uzi2115");

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from book");

            while (resultSet.next()) {

                String idBook = resultSet.getString("idBook");
                String name = resultSet.getString("name");
                String pages = resultSet.getString("pages");
                String author = resultSet.getString("author");
                var book = new Book(idBook, name, pages, author);
                bookList.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookList;
    }

    public void pushBook(Book book) {
        var bookList = new ArrayList<Book>();
        try {
            Connection connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3308/library", "root", "Uzi2115");

            System.out.println("insert into book (`idBook`, `name`, `pages`, `author`) values("+
                    book.getIdBook() + ",'" +
                    book.getName() + "'," +
                    book.getPages() + "," +
                    "'" +book.getAuthor() + "'"+")");

            Statement statement = connection.createStatement();
            statement.executeUpdate("insert into book (`idBook`, `name`, `pages`, `author`) values(%s,'%s',%s,'%s');"
                    .formatted(book.getIdBook(), book.getName(), book.getPages(), book.getAuthor()));




        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteBook(String idBook) {
        var bookList = new ArrayList<Book>();
        try {
            Connection connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3308/library", "root", "Uzi2115");

            Statement statement = connection.createStatement();
            statement.executeUpdate("delete from book where idBook = %s".formatted(idBook));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateBook(Book book) {
        var bookList = new ArrayList<Book>();
        try {
            Connection connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3308/library", "root", "Uzi2115");

            Statement statement = connection.createStatement();
            statement.executeUpdate("update book" +
                    "set idBook = 2" +
                    "set name = '2'" +
                    "set pages = 2" +
                    "set author = '2'" +
                    "where idBook = 2;");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
