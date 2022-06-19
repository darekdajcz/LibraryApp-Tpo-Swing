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
}
