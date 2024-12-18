package LoginRegister;

import GuestDashboard.GuestDashboard;

public class Main extends javax.swing.JFrame {

    public Main() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        adminstaff = new javax.swing.JLabel();
        guest = new javax.swing.JLabel();
        member = new javax.swing.JLabel();
        BG1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setBackground(new java.awt.Color(30, 130, 195));
        jLabel5.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("LOGIN AS");
        jLabel5.setAlignmentY(0.0F);
        jLabel5.setOpaque(true);
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 310, 70));

        adminstaff.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        adminstaff.setForeground(new java.awt.Color(255, 255, 255));
        adminstaff.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        adminstaff.setText("ADMIN/STAFF");
        adminstaff.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        adminstaff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                adminstaffMouseClicked(evt);
            }
        });
        getContentPane().add(adminstaff, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, 150, 60));

        guest.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        guest.setForeground(new java.awt.Color(255, 255, 255));
        guest.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        guest.setText("GUEST");
        guest.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        guest.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                guestMouseClicked(evt);
            }
        });
        getContentPane().add(guest, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, 150, 60));

        member.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        member.setForeground(new java.awt.Color(255, 255, 255));
        member.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        member.setText("MEMBER");
        member.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        member.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                memberMouseClicked(evt);
            }
        });
        getContentPane().add(member, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 170, 150, 60));

        BG1.setBackground(new java.awt.Color(68, 108, 179));
        BG1.setOpaque(true);
        getContentPane().add(BG1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 310, 290));

        setSize(new java.awt.Dimension(323, 363));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void adminstaffMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adminstaffMouseClicked
        new LoginPageStaffAdmin().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_adminstaffMouseClicked

    private void guestMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guestMouseClicked
        new GuestDashboard().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_guestMouseClicked

    private void memberMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_memberMouseClicked
        new LoginPageMember().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_memberMouseClicked

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BG1;
    private javax.swing.JLabel adminstaff;
    private javax.swing.JLabel guest;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel member;
    // End of variables declaration//GEN-END:variables
}
