package AdminDashboard;

import java.awt.Color;
import LoginRegister.LoginPageStaffAdmin;
import Add.AddBooks;
import Update.UpdateBooks;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;


public class AdminBooksDashboard extends javax.swing.JFrame {

    private Timer searchTimer;
    
    private String selectedBookID;
    
    public AdminBooksDashboard() {
        initComponents();
        loadBooksData();
    }

    private void loadBooksData() {
            DefaultTableModel model = (DefaultTableModel) bookdata.getModel();
            model.setRowCount(0); // Menghapus data lama dari tabel jika ada

            String url = "jdbc:mysql://localhost:3306/library";
            String user = "leonel";
            String password = "";
            String query = "SELECT book_id, title, author, quantity FROM books";

            try (Connection conn = DriverManager.getConnection(url, user, password);
                 PreparedStatement stmt = conn.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {

                // Iterasi hasil query dan tambahkan ke tabel
                while (rs.next()) {
                    Object[] row = {
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("quantity")
                    };
                    model.addRow(row);
                }

            } catch (Exception e) {
                e.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(this,
                    "Error loading books data: " + e.getMessage(),
                    "Database Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    
    private void loadSearchBooksData(String searchText) {
        DefaultTableModel model = (DefaultTableModel) bookdata.getModel();
        model.setRowCount(0);

        String url = "jdbc:mysql://localhost:3306/library";
        String user = "leonel";
        String password = "";
        String query = "SELECT book_id, title, author, quantity FROM books";

        if (searchText != null && !searchText.trim().isEmpty()) {
            query += " WHERE title LIKE ? OR author LIKE ?"; // Tambahkan kondisi pencarian
        }

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            if (searchText != null && !searchText.trim().isEmpty()) {
                String searchPattern = "%" + searchText + "%";
                stmt.setString(1, searchPattern);
                stmt.setString(2, searchPattern);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Object[] row = {
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("quantity")
                    };
                    model.addRow(row);
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
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        returns1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        delete = new javax.swing.JButton();
        add = new javax.swing.JButton();
        update = new javax.swing.JButton();
        dashboardadmin = new javax.swing.JButton();
        books = new javax.swing.JButton();
        staff = new javax.swing.JButton();
        borrow = new javax.swing.JButton();
        member = new javax.swing.JButton();
        logout = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        bookdata = new javax.swing.JTable();
        search = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(30, 130, 195));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));

        returns1.setBackground(new java.awt.Color(30, 130, 195));
        returns1.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        returns1.setForeground(new java.awt.Color(255, 255, 255));
        returns1.setText("Return");
        returns1.setBorder(null);
        returns1.setHideActionText(true);
        returns1.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        returns1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returns1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Library");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("List Books");

        delete.setText("Delete");
        delete.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        delete.setBorderPainted(false);
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        add.setText("ADD");
        add.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        add.setBorderPainted(false);
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        update.setText("Update");
        update.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        update.setBorderPainted(false);
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        dashboardadmin.setBackground(new java.awt.Color(30, 130, 195));
        dashboardadmin.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        dashboardadmin.setForeground(new java.awt.Color(255, 255, 255));
        dashboardadmin.setText("Overview");
        dashboardadmin.setBorder(null);
        dashboardadmin.setHideActionText(true);
        dashboardadmin.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        dashboardadmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dashboardadminMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dashboardadminMouseExited(evt);
            }
        });
        dashboardadmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboardadminActionPerformed(evt);
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

        staff.setBackground(new java.awt.Color(30, 130, 195));
        staff.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        staff.setForeground(new java.awt.Color(255, 255, 255));
        staff.setText("Staff");
        staff.setBorder(null);
        staff.setHideActionText(true);
        staff.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        staff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                staffMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                staffMouseExited(evt);
            }
        });
        staff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                staffActionPerformed(evt);
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

        member.setBackground(new java.awt.Color(30, 130, 195));
        member.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        member.setForeground(new java.awt.Color(255, 255, 255));
        member.setText("Member");
        member.setBorder(null);
        member.setHideActionText(true);
        member.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        member.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                memberMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                memberMouseExited(evt);
            }
        });
        member.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                memberActionPerformed(evt);
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

        bookdata.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        bookdata.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"", null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Books ID", "Books Name", "Author", "Quantity"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        bookdata.setShowGrid(true);
        bookdata.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bookdataMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(bookdata);
        if (bookdata.getColumnModel().getColumnCount() > 0) {
            bookdata.getColumnModel().getColumn(0).setMaxWidth(55);
            bookdata.getColumnModel().getColumn(3).setMaxWidth(55);
        }

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addGap(39, 39, 39)
                .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(logout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(member, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(staff, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(borrow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(books, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dashboardadmin, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(returns1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 637, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(dashboardadmin, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(books, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(borrow, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(returns1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(staff, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(member, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(logout, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 430));

        setSize(new java.awt.Dimension(794, 441));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        AddBooks addBooksDialog = new AddBooks();
        addBooksDialog.setVisible(true);

        // Tambahkan WindowListener untuk mendeteksi kapan dialog ditutup
        addBooksDialog.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosed(java.awt.event.WindowEvent e) {
            loadBooksData(); // Panggil metode ini setelah dialog ditutup
        }
    });
    }//GEN-LAST:event_addActionPerformed

    private void dashboardadminMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboardadminMouseEntered
        evt.getSource();
        dashboardadmin.setBackground(new Color(58,83,155));
    }//GEN-LAST:event_dashboardadminMouseEntered

    private void dashboardadminMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboardadminMouseExited
        evt.getSource();
        dashboardadmin.setBackground(new Color(68,108,179));
    }//GEN-LAST:event_dashboardadminMouseExited

    private void dashboardadminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboardadminActionPerformed
        evt.getSource();
        new MainAdminDashboard().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_dashboardadminActionPerformed

    private void booksMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_booksMouseEntered
        evt.getSource();
        staff.setBackground(new Color(58,83,155));
    }//GEN-LAST:event_booksMouseEntered

    private void booksMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_booksMouseExited
        evt.getSource();
        books.setBackground(new Color(68,108,179));
    }//GEN-LAST:event_booksMouseExited

    private void booksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_booksActionPerformed
        evt.getSource();
        new AdminBooksDashboard().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_booksActionPerformed

    private void staffMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_staffMouseEntered
        evt.getSource();
        staff.setBackground(new Color(58,83,155));
    }//GEN-LAST:event_staffMouseEntered

    private void staffMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_staffMouseExited
        evt.getSource();
        staff.setBackground(new Color(68,108,179));
    }//GEN-LAST:event_staffMouseExited

    private void staffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_staffActionPerformed
        evt.getSource();
        new AdminStaffDashboard().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_staffActionPerformed

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
        new AdminBorrowDashboard().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_borrowActionPerformed

    private void memberMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_memberMouseEntered
        evt.getSource();
        member.setBackground(new Color(58,83,155));
    }//GEN-LAST:event_memberMouseEntered

    private void memberMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_memberMouseExited
        evt.getSource();
        member.setBackground(new Color(68,108,179));
    }//GEN-LAST:event_memberMouseExited

    private void memberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_memberActionPerformed
        evt.getSource();
        new AdminMemberDashboard().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_memberActionPerformed

    private void logoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseEntered
        evt.getSource();
        logout.setBackground(new Color(58,83,155));
    }//GEN-LAST:event_logoutMouseEntered

    private void logoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseExited
        evt.getSource();
        logout.setBackground(new Color(68,108,179));
    }//GEN-LAST:event_logoutMouseExited

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed
        new LoginPageStaffAdmin().setVisible(true);
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

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
    if (this.selectedBookID == null || this.selectedBookID.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please select a book first!");
        return;
    }

    // Konfirmasi penghapusan
    int confirm = JOptionPane.showConfirmDialog(this, 
        "Are you sure you want to delete the book with ID: " + this.selectedBookID + "?", 
        "Delete Confirmation", 
        JOptionPane.YES_NO_OPTION);

    if (confirm == JOptionPane.YES_OPTION) {
        String deleteBorrowQuery = "DELETE FROM borrow WHERE book_id = ?";
        String deleteBookQuery = "DELETE FROM books WHERE book_id = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "leonel", "")) {
            // Hapus data dari tabel 'borrow'
            try (PreparedStatement stmtBorrow = conn.prepareStatement(deleteBorrowQuery)) {
                stmtBorrow.setInt(1, Integer.parseInt(this.selectedBookID)); // Konversi book_id menjadi int
                stmtBorrow.executeUpdate();
            }

            // Hapus data dari tabel 'books'
            try (PreparedStatement stmtBook = conn.prepareStatement(deleteBookQuery)) {
                stmtBook.setInt(1, Integer.parseInt(this.selectedBookID)); // Konversi book_id menjadi int
                int rowsAffected = stmtBook.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Book deleted successfully!");
                    loadBooksData(); // Refresh data di tabel
                    this.selectedBookID = null; // Reset ID setelah penghapusan
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete the book. Please try again.");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage());
        }
    }
    }//GEN-LAST:event_deleteActionPerformed

    private void returns1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returns1ActionPerformed
        new AdminReturnDashboard().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_returns1ActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        UpdateBooks UpdateBooksDialog = new UpdateBooks();
        UpdateBooksDialog.setVisible(true);

        // Tambahkan WindowListener untuk mendeteksi kapan dialog ditutup
        UpdateBooksDialog.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosed(java.awt.event.WindowEvent e) {
            loadBooksData(); // Panggil metode ini setelah dialog ditutup
        }
        });
    }//GEN-LAST:event_updateActionPerformed

    private void bookdataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookdataMouseClicked
    int selectedRow = bookdata.getSelectedRow();
    if (selectedRow >= 0) {
        DefaultTableModel model = (DefaultTableModel) bookdata.getModel();
        
        // Simpan data yang di-*click* ke variabel
        String selectedBookID = model.getValueAt(selectedRow, 0).toString(); // Kolom 0 adalah book_id

        // Simpan data ke variabel global jika diperlukan
        this.selectedBookID = selectedBookID;
    }
    }//GEN-LAST:event_bookdataMouseClicked

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminBooksDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    private javax.swing.JTable bookdata;
    private javax.swing.JButton books;
    private javax.swing.JButton borrow;
    private javax.swing.JButton dashboardadmin;
    private javax.swing.JButton delete;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton logout;
    private javax.swing.JButton member;
    private javax.swing.JButton returns1;
    private javax.swing.JTextField search;
    private javax.swing.JButton staff;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
}
