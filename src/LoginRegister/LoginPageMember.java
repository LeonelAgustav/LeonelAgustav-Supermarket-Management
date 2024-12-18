package LoginRegister;

import javax.swing.JOptionPane;
import MemberDashboard.MemberDashboard;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPageMember extends javax.swing.JFrame {
    public static int loggedInMemberId = -1;  // Variabel untuk menyimpan ID member yang login
    public static String loggedInMemberName = ""; // Variabel untuk menyimpan nama member yang login

    public LoginPageMember() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        back2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        username1 = new javax.swing.JTextField();
        password1 = new javax.swing.JPasswordField();
        Login = new javax.swing.JButton();
        register = new javax.swing.JLabel();
        BG1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(310, 390));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        back2.setBackground(new java.awt.Color(68, 108, 179));
        back2.setText("BACK");
        back2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        back2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                back2ActionPerformed(evt);
            }
        });
        getContentPane().add(back2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 50, 30));

        jLabel5.setBackground(new java.awt.Color(30, 130, 195));
        jLabel5.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("LOGIN");
        jLabel5.setAlignmentY(0.0F);
        jLabel5.setOpaque(true);
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 310, 70));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Password");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, 88, 20));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Username");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 89, 20));

        username1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        getContentPane().add(username1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 209, 31));

        password1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        getContentPane().add(password1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, 209, 35));

        Login.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Login.setText("LOGIN");
        Login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginActionPerformed(evt);
            }
        });
        getContentPane().add(Login, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 131, 35));

        register.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        register.setForeground(new java.awt.Color(255, 255, 255));
        register.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        register.setText("If Didn't Have Account Press Here");
        register.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                registerMouseClicked(evt);
            }
        });
        getContentPane().add(register, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 230, 20));

        BG1.setBackground(new java.awt.Color(68, 108, 179));
        BG1.setForeground(new java.awt.Color(255, 255, 255));
        BG1.setOpaque(true);
        getContentPane().add(BG1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 310, 410));

        setSize(new java.awt.Dimension(324, 393));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private int getMemberIdByUsername(String name) {
        String query = "SELECT member_id FROM members WHERE name = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "leonel", "")) {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("member_id"); // Get member_id
            } else {
                return -1; // If username not found
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    private void LoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginActionPerformed
        String name = username1.getText();
        String pass = new String(password1.getPassword());

        // Check if username and password are not empty
        if (name.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both username and password.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate login (from database)
        if (validateLogin(name, pass)) {
            // Retrieve member_id and member_name after successful login
            int memberId = getMemberIdByUsername(name);
            if (memberId != -1) {
                loggedInMemberId = memberId;  // Save member_id to static variable
                loggedInMemberName = name;   // Save member_name to static variable
                this.dispose();  // Close login page
            } else {
                JOptionPane.showMessageDialog(this, "Member ID not found");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password");
        }                                      
    }//GEN-LAST:event_LoginActionPerformed

    private boolean validateLogin(String name, String pass) {
    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "leonel", "")) {
        String query = "SELECT * FROM members WHERE name = ? AND password = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, name);
        ps.setString(2, pass); // Ensure passwords are handled securely (e.g., hashing in a real application)

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            // Login is successful, return true
            JOptionPane.showMessageDialog(this, "Login Successful!");
            dispose(); // Close login page
            new MemberDashboard().setVisible(true); // Open dashboard
            return true;  // Return true on successful login
        } else {
            // Invalid username or password, return false
            JOptionPane.showMessageDialog(this, "Invalid Username or Password.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Database connection error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        return false; // Return false if there's an error connecting to the database
    }
}
    
    private void registerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerMouseClicked
        evt.getSource();
        new RegisterPageMember().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_registerMouseClicked

    private void back2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_back2ActionPerformed
        new Main().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_back2ActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginPageMember().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BG1;
    private javax.swing.JButton Login;
    private javax.swing.JButton back2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPasswordField password1;
    private javax.swing.JLabel register;
    private javax.swing.JTextField username1;
    // End of variables declaration//GEN-END:variables
}
