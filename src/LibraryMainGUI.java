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
    private JButton pushNewBookButton;
    private JTextField textIdBook;
    private JTextField textName;
    private JTextField textPages;
    private JTextField textAuthor;
    private JButton showBooksListButton;

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


        pushNewBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("XXX");
            }
        });


        bookList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int bookNumber = bookList.getSelectedIndex();
                if(bookNumber >= 0) {
                    Book b = books.get(bookNumber);
                    textIdBook.setText(b.getIdBook());
                    textName.setText(b.getName());
                    textPages.setText(b.getPages());
                    textAuthor.setText(b.getAuthor());
                }
            }
        });
        showBooksListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Book> arrayList;
                BookService bookService = new BookService();
                arrayList = bookService.getBookList();
                refreshBookList(arrayList);
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
                booksListModel.addElement(b.getName());
            }
        }
    }

    public static void main(String[] args) {
        LibraryMainGUI frame = new LibraryMainGUI("My Library APP");
        frame.setVisible(true);

    }
}