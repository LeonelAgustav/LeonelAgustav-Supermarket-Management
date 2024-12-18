package LoginRegister;

import AdminDashboard.MainAdminDashboard;
import StaffDashboard.MainStaffDashboard;
import javax.swing.*;
import java.sql.*;

public class LoginPageStaffAdmin extends javax.swing.JFrame {


    public LoginPageStaffAdmin() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        back3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        username = new javax.swing.JTextField();
        password = new javax.swing.JPasswordField();
        Login = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        admin = new javax.swing.JRadioButton();
        staff = new javax.swing.JRadioButton();
        BG = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        back3.setBackground(new java.awt.Color(68, 108, 179));
        back3.setForeground(new java.awt.Color(255, 255, 255));
        back3.setText("BACK");
        back3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        back3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                back3ActionPerformed(evt);
            }
        });
        getContentPane().add(back3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 50, 30));

        jLabel1.setBackground(new java.awt.Color(30, 130, 195));
        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("LOGIN");
        jLabel1.setAlignmentY(0.0F);
        jLabel1.setOpaque(true);
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 310, 70));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Password");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, 88, 20));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Username");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 89, 20));

        username.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        getContentPane().add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 209, 31));

        password.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        getContentPane().add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, 209, 35));

        Login.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Login.setText("LOGIN");
        Login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginActionPerformed(evt);
            }
        });
        getContentPane().add(Login, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 290, 131, 35));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("LOGIN AS");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, -1, -1));

        admin.setBackground(new java.awt.Color(68, 108, 179));
        buttonGroup1.add(admin);
        admin.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        admin.setForeground(new java.awt.Color(255, 255, 255));
        admin.setText("Admin");
        getContentPane().add(admin, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, -1, 20));

        staff.setBackground(new java.awt.Color(68, 108, 179));
        buttonGroup1.add(staff);
        staff.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        staff.setForeground(new java.awt.Color(255, 255, 255));
        staff.setText("Staff");
        getContentPane().add(staff, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 240, -1, 20));

        BG.setBackground(new java.awt.Color(68, 108, 179));
        BG.setOpaque(true);
        getContentPane().add(BG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 310, 360));

        setSize(new java.awt.Dimension(323, 395));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void LoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginActionPerformed
        String name = username.getText();
        String pass = new String(password.getPassword());
        String role = null;
        
        if (admin.isSelected()) {
            role = "ADMIN";
        } else if (staff.isSelected()) {
            role = "STAFF";
        }

        // Validate role selection
        if (role == null) {
            JOptionPane.showMessageDialog(this, "Please select a role.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (name.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both username and password.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String query;
        if ("ADMIN".equals(role)) {
            query = "SELECT * FROM admin WHERE NAME = ? AND PASSWORD = ?";
        } else if ("STAFF".equals(role)) {
            query = "SELECT * FROM staff WHERE name = ? AND password = ?";
        } else {
            JOptionPane.showMessageDialog(this, "Invalid role selected");
            return;
        }

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "leonel", "");
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, name);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Login Successful");
                dispose();
                if ("ADMIN".equals(role)) {
                    new MainAdminDashboard().setVisible(true); // Replace with your admin dashboard
                } else {
                    new MainStaffDashboard().setVisible(true); // Replace with your staff dashboard
                }
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
        }
    }//GEN-LAST:event_LoginActionPerformed

    private void back3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_back3ActionPerformed
        new Main().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_back3ActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new LoginPageStaffAdmin().setVisible(true));
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BG;
    private javax.swing.JButton Login;
    private javax.swing.JRadioButton admin;
    private javax.swing.JButton back3;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField password;
    private javax.swing.JRadioButton staff;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
