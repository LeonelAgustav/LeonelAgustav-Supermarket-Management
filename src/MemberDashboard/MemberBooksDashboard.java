package MemberDashboard;

import java.awt.Color;
import LoginRegister.LoginPageStaffAdmin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;


public class MemberBooksDashboard extends javax.swing.JFrame {

    private Timer searchTimer;
    
    public MemberBooksDashboard() {
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
                    "Error loading staff data: " + e.getMessage(),
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
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        dashboardmember = new javax.swing.JButton();
        books = new javax.swing.JButton();
        borrow = new javax.swing.JButton();
        logout = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        bookdata = new javax.swing.JTable();
        search = new javax.swing.JTextField();
        returns = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(30, 130, 195));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Library");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("List Books");

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

        bookdata.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        bookdata.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
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
        bookdata.setEnabled(false);
        bookdata.setShowGrid(true);
        jScrollPane1.setViewportView(bookdata);

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
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addGap(39, 39, 39)
                .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(logout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(borrow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(books, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dashboardmember, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(returns, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 637, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 380));

        setSize(new java.awt.Dimension(786, 390));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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

    private void returnsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_returnsMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_returnsMouseEntered

    private void returnsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_returnsMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_returnsMouseExited

    private void returnsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnsActionPerformed
        new MemberReturnDashboard().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_returnsActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MemberBooksDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable bookdata;
    private javax.swing.JButton books;
    private javax.swing.JButton borrow;
    private javax.swing.JButton dashboardmember;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton logout;
    private javax.swing.JButton returns;
    private javax.swing.JTextField search;
    // End of variables declaration//GEN-END:variables
}
