import Models.Book;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LibraryMainGUI extends JFrame {
    private JPanel mainPanel;
    private JPanel panelTop;
    private JPanel panelLeft;
    private JPanel panelRight;
    private JList<String> bookList;
    private JTextField textIdBook;
    private JTextField textName;
    private JTextField textPages;
    private JTextField textAuthor;
    private JButton pushNewBookButton;
    private JButton showBooksListButton;
    private JButton updateBookButton;
    private JButton deleteBookButton;
    private JTextField textDeleteBook;

    private ArrayList<Book> books;
    private DefaultListModel<String> booksListModel;

    public LibraryMainGUI(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(this.mainPanel);
        this.pack();
        books = new ArrayList<>();
        booksListModel = new DefaultListModel<>();
        bookList.setModel(booksListModel);
        updateBookButton.setEnabled(false);
        pushNewBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Book b = new Book(
                        textIdBook.getText(),
                        textName.getText(),
                        textPages.getText(),
                        textAuthor.getText()
                        );
                BookService bs = new BookService();
                bs.pushBook(b);
                refreshBookList();
            }
        });

        updateBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int bookNumber = bookList.getSelectedIndex();
                if (bookNumber >= 0) {
                    Book b = books.get(bookNumber);
                    b.setIdBook(textIdBook.getText());
                    b.setName(textName.getText());
                    b.setAuthor(textAuthor.getText());
                    b.setPages(textPages.getText());
                    refreshBookList();
                }
            }
        });
        bookList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int bookNumber = bookList.getSelectedIndex();
                if (bookNumber >= 0) {
                    Book b = books.get(bookNumber);
                    textIdBook.setText(b.getIdBook());
                    textName.setText(b.getName());
                    textPages.setText(b.getPages());
                    textAuthor.setText(b.getAuthor());
                    updateBookButton.setEnabled(true);
                } else {
                    updateBookButton.setEnabled(false);
                }
            }
        });
        showBooksListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                books.clear();
                ArrayList<Book> arrayList;
                BookService bookService = new BookService();
                arrayList = bookService.getBookList();
                refreshBookList(arrayList);
            }
        });

        deleteBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BookService bookService = new BookService();
                bookService.deleteBook(textDeleteBook.getText());
                System.out.println("Book with id " + textDeleteBook.getText() + " has been deleted..");
                refreshBookList();
            }
        });
    }

    public void refreshBookList(ArrayList<Book> arrayList) {
        if (arrayList != null) {
            books.addAll(arrayList);
            System.out.println("Removing all people from list");
            booksListModel.removeAllElements();
            for (Book b : arrayList) {
                System.out.println("Adding book to list: " + b.getName());
                booksListModel.addElement(b.getIdBook() + ". " + b.getName());
            }
        }
    }

    public void refreshBookList() {
        if (books != null) {
            booksListModel.removeAllElements();
            System.out.println("Removing all people from list");
            BookService bookService = new BookService();
            bookService.getBookList().forEach(value -> {
                booksListModel.addElement(value.getIdBook() + ". " +value.getName());
            });

        }
    }

    public static void main(String[] args) {
        LibraryMainGUI frame = new LibraryMainGUI("My Library APP");
        frame.setVisible(true);

    }
}