package MemberDashboard;

import LoginRegister.LoginPageMember;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.Timer;


public class MemberReturnDashboard extends javax.swing.JFrame {


    public MemberReturnDashboard() {
        initComponents();
        loadBooksBorrowData();
    }
    
    private Timer searchTimer;

    private void loadBooksBorrowData() {
        DefaultListModel<String> model = new DefaultListModel<>();
        borrowlist.setModel(model);

        int memberId = LoginPageMember.loggedInMemberId;  // Get the logged-in member ID

        String url = "jdbc:mysql://localhost:3306/library";
        String user = "leonel";
        String password = "";

        // SQL query to retrieve borrow records
        String query = "SELECT book_id, books_name, borrow_date, status FROM borrow WHERE member_id = ?";  // Assuming you want to load for the logged-in member

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, memberId);  // Set the member ID for the query

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int bookId = rs.getInt("book_id");
                    String booksName = rs.getString("books_name");
                    Date borrowDate = rs.getDate("borrow_date");
                    String status = rs.getString("status");

                    // Format the borrow date using SimpleDateFormat
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String formattedBorrowDate = sdf.format(borrowDate);

                    // Format the item string to display
                    String item = String.format("ID: %d - %s Borrowed on: %s (Status: %s)", bookId, booksName, formattedBorrowDate, status);
                    model.addElement(item);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error loading books data: " + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void loadSearchBooksData(String searchText) {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        borrowlist.setModel(listModel);

        String url = "jdbc:mysql://localhost:3306/library";
        String user = "leonel";
        String password = "";
        String query = "SELECT borrow_id, member_id, member_name, book_id, books_name, borrow_date FROM borrow";

        // Add search conditions if searchText is provided
        if (searchText != null && !searchText.trim().isEmpty()) {
            query += " WHERE books_name LIKE ? OR book_id LIKE ? OR borrow_date LIKE ?";
        }

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            if (searchText != null && !searchText.trim().isEmpty()) {
                String searchPattern = "%" + searchText + "%";
                stmt.setString(1, searchPattern);
                stmt.setString(2, searchPattern);
                stmt.setString(3, searchPattern);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Format the string with relevant column data
                    String item = String.format("Borrow ID: %d, Book ID: %d, Book Name: %s, Borrowed by: %s, Borrowed on: %s",
                            rs.getInt("borrow_id"), // Borrow ID
                            rs.getInt("book_id"),    // Book ID
                            rs.getString("books_name"), // Book Name
                            rs.getString("member_name"), // Member's name
                            rs.getDate("borrow_date").toString()); // Borrow Date
                    listModel.addElement(item);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Error loading books data: " + e.getMessage(),
                    "Database Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    private void returnBook() {
        String selectedBook = borrowlist.getSelectedValue();
        if (selectedBook == null) {
            JOptionPane.showMessageDialog(this, "Please select a book to return.");
            return;
        }

        int bookId = Integer.parseInt(selectedBook.split(" ")[1]);

        // Retrieve the logged-in member ID from LoginPageMember
        int memberId = LoginPageMember.loggedInMemberId;
        String memberName = LoginPageMember.loggedInMemberName;
        if (memberId == -1 || memberName == null) {
            JOptionPane.showMessageDialog(this, "No member is logged in.");
            return;
        }

        String url = "jdbc:mysql://localhost:3306/library";
        String user = "leonel";
        String password = "";

        String bookNameQuery = "SELECT title FROM books WHERE book_id = ?";
        String insertQuery = "INSERT INTO returns (member_id, member_name, book_id, book_name, return_date, status) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement bookStmt = conn.prepareStatement(bookNameQuery);
             PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {

            // Retrieve book name
            String bookName = "Unknown Book";
            bookStmt.setInt(1, bookId);
            try (ResultSet rs = bookStmt.executeQuery()) {
                if (rs.next()) {
                    bookName = rs.getString("title");
                }
            }

            // Get current date using java.sql.Date
            java.sql.Date returnDate = new java.sql.Date(System.currentTimeMillis());

            // Insert return record into the returns table
            insertStmt.setInt(1, memberId);
            insertStmt.setString(2, memberName);
            insertStmt.setInt(3, bookId);
            insertStmt.setString(4, bookName);
            insertStmt.setDate(5, returnDate);  // Pass java.sql.Date directly
            insertStmt.setString(6, "returned");

            int rowsAffected = insertStmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Book returned successfully!");

                // Now delete the borrow record from the borrow table
                String deleteBorrowQuery = "DELETE FROM borrow WHERE member_id = ? AND book_id = ?";
                try (PreparedStatement deleteStmt = conn.prepareStatement(deleteBorrowQuery)) {
                    deleteStmt.setInt(1, memberId);
                    deleteStmt.setInt(2, bookId);
                    deleteStmt.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this,
                            "Error deleting borrow record: " + e.getMessage(),
                            "Database Error",
                            JOptionPane.ERROR_MESSAGE);
                    return; // If delete fails, return without updating inventory or reloading data
                }
                updateBookQuantity(bookId);
                loadBooksBorrowData();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to return the book.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error returning book: " + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateBookQuantity(int bookId) {
        String url = "jdbc:mysql://localhost:3306/library";
        String user = "leonel";
        String password = "";
        String query = "UPDATE books SET quantity = quantity + 1 WHERE book_id = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, bookId);
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error updating book quantity: " + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private int getLoggedInMemberId() {
        return 1; // Assume member ID 1 is logged in.
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        borrowlist = new javax.swing.JList<>();
        retruns = new javax.swing.JButton();
        dashboardmember = new javax.swing.JButton();
        books = new javax.swing.JButton();
        borrow = new javax.swing.JButton();
        logout = new javax.swing.JButton();
        search = new javax.swing.JTextField();
        returns = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(30, 130, 195));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Library");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("List Borrow");

        borrowlist.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(borrowlist);

        retruns.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        retruns.setText("RETURN");
        retruns.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                retrunsActionPerformed(evt);
            }
        });

        dashboardmember.setBackground(new java.awt.Color(30, 130, 195));
        dashboardmember.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        dashboardmember.setForeground(new java.awt.Color(255, 255, 255));
        dashboardmember.setText("Overview");
        dashboardmember.setBorder(null);
        dashboardmember.setHideActionText(true);
        dashboardmember.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        dashboardmember.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dashboardmemberMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dashboardmemberMouseExited(evt);
            }
        });
        dashboardmember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboardmemberActionPerformed(evt);
            }
        });

        books.setBackground(new java.awt.Color(30, 130, 195));
        books.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        books.setForeground(new java.awt.Color(255, 255, 255));
        books.setText("Books");
        books.setBorder(null);
        books.setHideActionText(true);
        books.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        books.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                booksMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                booksMouseExited(evt);
            }
        });
        books.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                booksActionPerformed(evt);
            }
        });

        borrow.setBackground(new java.awt.Color(30, 130, 195));
        borrow.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        borrow.setForeground(new java.awt.Color(255, 255, 255));
        borrow.setText("Borrow");
        borrow.setBorder(null);
        borrow.setHideActionText(true);
        borrow.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        borrow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                borrowMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                borrowMouseExited(evt);
            }
        });
        borrow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrowActionPerformed(evt);
            }
        });

        logout.setBackground(new java.awt.Color(30, 130, 195));
        logout.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        logout.setForeground(new java.awt.Color(255, 255, 255));
        logout.setText("LogOut");
        logout.setBorder(null);
        logout.setHideActionText(true);
        logout.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logoutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                logoutMouseExited(evt);
            }
        });
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });

        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });
        search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchKeyTyped(evt);
            }
        });

        returns.setBackground(new java.awt.Color(30, 130, 195));
        returns.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        returns.setForeground(new java.awt.Color(255, 255, 255));
        returns.setText("Return");
        returns.setBorder(null);
        returns.setHideActionText(true);
        returns.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        returns.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                returnsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                returnsMouseExited(evt);
            }
        });
        returns.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addGap(51, 51, 51)
                .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(logout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(borrow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(books, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dashboardmember, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(returns, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(179, 179, 179)
                        .addComponent(retruns)
                        .addGap(200, 200, 200))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(dashboardmember, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(books, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(borrow, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(returns, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(logout, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(retruns)))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(644, 410));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void retrunsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_retrunsActionPerformed
        returnBook();
    }//GEN-LAST:event_retrunsActionPerformed

    private void dashboardmemberMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboardmemberMouseEntered
        evt.getSource();
        dashboardmember.setBackground(new Color(58,83,155));
    }//GEN-LAST:event_dashboardmemberMouseEntered

    private void dashboardmemberMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboardmemberMouseExited
        evt.getSource();
        dashboardmember.setBackground(new Color(68,108,179));
    }//GEN-LAST:event_dashboardmemberMouseExited

    private void dashboardmemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboardmemberActionPerformed
        evt.getSource();
        new MemberDashboard().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_dashboardmemberActionPerformed

    private void booksMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_booksMouseEntered
        evt.getSource();
        books.setBackground(new Color(58,83,155));
    }//GEN-LAST:event_booksMouseEntered

    private void booksMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_booksMouseExited
        evt.getSource();
        books.setBackground(new Color(68,108,179));
    }//GEN-LAST:event_booksMouseExited

    private void booksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_booksActionPerformed
        evt.getSource();
        new MemberBooksDashboard().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_booksActionPerformed

    private void borrowMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_borrowMouseEntered
        evt.getSource();
        borrow.setBackground(new Color(58,83,155));
    }//GEN-LAST:event_borrowMouseEntered

    private void borrowMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_borrowMouseExited
        evt.getSource();
        borrow.setBackground(new Color(68,108,179));
    }//GEN-LAST:event_borrowMouseExited

    private void borrowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrowActionPerformed
        evt.getSource();
        new MemberBorrowDashboard().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_borrowActionPerformed

    private void logoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseEntered
        evt.getSource();
        logout.setBackground(new Color(58,83,155));
    }//GEN-LAST:event_logoutMouseEntered

    private void logoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseExited
        evt.getSource();
        logout.setBackground(new Color(68,108,179));
    }//GEN-LAST:event_logoutMouseExited

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed
        new LoginPageMember().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_logoutActionPerformed

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        String searchText = search.getText(); // Ambil teks dari JTextField
        loadSearchBooksData(searchText); // Panggil fungsi loadMemberData dengan teks pencarian
    }//GEN-LAST:event_searchActionPerformed

    private void searchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyTyped
        // Hentikan task pencarian sebelumnya jika ada
        if (searchTimer != null && searchTimer.isRunning()) {
            searchTimer.stop(); // Gunakan stop() untuk menghentikan timer sebelumnya
        }

        // Mulai timer baru untuk delay sebelum pencarian
        searchTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = search.getText(); // Ambil teks dari JTextField
                loadSearchBooksData(searchText); // Panggil fungsi pencarian
            }
        });

        searchTimer.setRepeats(false); // Pastikan timer hanya dijalankan sekali setelah 500ms
        searchTimer.start(); // Mulai timer
    }//GEN-LAST:event_searchKeyTyped

    private void returnsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_returnsMouseEntered
        evt.getSource();
        returns.setBackground(new Color(58,83,155));
    }//GEN-LAST:event_returnsMouseEntered

    private void returnsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_returnsMouseExited
        evt.getSource();
        returns.setBackground(new Color(68,108,179));
    }//GEN-LAST:event_returnsMouseExited

    private void returnsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnsActionPerformed
        new MemberReturnDashboard().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_returnsActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MemberReturnDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MemberReturnDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MemberReturnDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MemberReturnDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MemberReturnDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton books;
    private javax.swing.JButton borrow;
    private javax.swing.JList<String> borrowlist;
    private javax.swing.JButton dashboardmember;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton logout;
    private javax.swing.JButton retruns;
    private javax.swing.JButton returns;
    private javax.swing.JTextField search;
    // End of variables declaration//GEN-END:variables
}
