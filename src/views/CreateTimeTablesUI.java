/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.io.IOException;
import java.util.ConcurrentModificationException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import transportsystem.FareManagment;
import transportsystem.Network;
import transportsystem.Planning;
import transportsystem.OtherServices;
import transportsystem.SetOfFares;
import transportsystem.SetOfNetworks;
import transportsystem.SetOfTimetables;

/**
 *
 * @author theja
 */
public class CreateTimeTablesUI extends javax.swing.JFrame {

    private Vector data;
    private TableModel model;
    private Vector header_Vehicles;
      private Vector header_Table;
    TableRowSorter<TableModel> sorter_Vehicles;
       TableRowSorter<TableModel>  sorter_Tables2;
    private static final String FILE_NAME_ROUTES = "Routes.ser";
    private static final String FILE_NAME_Vehicles = "Vehicles.ser";
    private static final String FILE_NAME_Fare = "Fare.ser";
    private static final String FILE_NAME_Fare2 = "Fare2.ser";
    private static final String FILE_NAME_Timetable = "TimeTable.ser";
    private OtherServices service;
    private SetOfNetworks theRoutes = new SetOfNetworks();
    private SetOfNetworks theVehicle = new SetOfNetworks();
    private SetOfFares theFare = new SetOfFares();
    private SetOfTimetables theTimetables = new SetOfTimetables();
    private Planning timPlanning;

    /**
     * Creates new form CreateTimeTablesUI
     * @param args
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    public static void main(String args[]) throws IOException, ClassNotFoundException {
        CreateTimeTablesUI theGUI = new CreateTimeTablesUI();
        theGUI.setVisible(true);
    }

    /**
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public CreateTimeTablesUI() throws IOException, ClassNotFoundException {
        initComponents();
        service = new OtherServices();
      vehinumtxt.setVisible(false);

        try {
            theVehicle.addAll(service.deserialize_vehicle(FILE_NAME_Fare2));
        } catch (IOException e) {

            serialize_Vehi2();
        } catch (ClassNotFoundException e) {
            serialize_Vehi2();
        }
        try {
            theTimetables.addAll(service.deserialize_timetable(FILE_NAME_Timetable));
        } catch (Exception e) {

            serialize_timetable();
        }
                try {
            theRoutes.addAll(service.deserialize_routes(FILE_NAME_ROUTES));
        } catch (Exception e) {

            serialize_all();
        }
      

        header_Vehicles = new Vector();
        header_Vehicles.add("Route Name");
        header_Vehicles.add("Vehicle Number");
        header_Vehicles.add("Transport Type");

        loadVehicleTable(theVehicle);
        displayDeserialization();
        displayDeserialization2();
        
          header_Table = new Vector();
        header_Table.add("Route Name");
        header_Table.add("Vehicle Number");
         header_Table.add("Transport Type");
        header_Table.add("Depature Time");
        header_Table.add("Arrival Time");
        header_Table.add("Available");
        loadTimeTable(theTimetables);
    }

    private void loadVehicleTable(SetOfNetworks net) throws IOException, ClassNotFoundException {
        data = new Vector<Vector<String>>();
        for (Network network : net) {
            Vector v = new Vector();
            v.add(network.getRouteName());
             v.add(network.getVehicleNo());
            v.add(network.getNetworkType());
           

            data.add(v);

        }
        model = new DefaultTableModel(data, header_Vehicles);

        timetablefaretable.setModel(model);
        sorter_Vehicles = new TableRowSorter<TableModel>(model);
        timetablefaretable.setRowSorter(sorter_Vehicles);
        jScrollPane1.setViewportView(timetablefaretable);
        timetablefaretable.setAutoscrolls(true);
        timetablefaretable.getTableHeader().setReorderingAllowed(false);

    }
    
        private void loadTimeTable(SetOfTimetables tbs) throws IOException, ClassNotFoundException {
        data = new Vector<Vector<String>>();
        for (Planning table : tbs) {
            Vector v = new Vector();
            v.add(table.getRoute());
            v.add(table.getType());
            v.add(table.getVehicleno());
            v.add(table.getStarttime());
            v.add(table.getEndtime());
            v.add(table.getStatus());

            data.add(v);

        }
        model = new DefaultTableModel(data, header_Table);

        routetimetable.setModel(model);
        sorter_Tables2 = new TableRowSorter<TableModel>(model);
        routetimetable.setRowSorter(sorter_Tables2);
        jScrollPane2.setViewportView(routetimetable);
        routetimetable.setAutoscrolls(true);
        routetimetable.getTableHeader().setReorderingAllowed(false);

    }

    /**
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void serialize_Vehi2() throws IOException, ClassNotFoundException {

        service.Serialize(theVehicle, FILE_NAME_Fare2);

    }

    /**
     *
     * @throws IOException
     * @throws ClassNotFoundException
     * Serialize Timetable Details
     */
    public void serialize_timetable() throws IOException, ClassNotFoundException {

        service.Serialize(theTimetables, FILE_NAME_Timetable);

    }

    private void selectRouteRow() {

        //retrieving the selected row index
        int row = timetablefaretable.getSelectedRow();

        //if a single row is selected from the table, get selectedBookNo
        if (timetablefaretable.getRowSelectionAllowed()) {
            // selectedRouteNo = Integer.parseInt(timetablefaretable.getValueAt(row, 0).toString());
            String selectroutename = timetablefaretable.getValueAt(row, 0).toString();
            String type = timetablefaretable.getValueAt(row, 1).toString();
            String vehino = timetablefaretable.getValueAt(row, 2).toString();

            routetxt.setText(selectroutename);
            typetxt.setText(type);
            vehinotxt.setText(vehino);

        }
    }
   private void selectTimetableRow() {

        //retrieving the selected row index
        int row = routetimetable.getSelectedRow();

        //if a single row is selected from the table, get selectedBookNo
        if (routetimetable.getRowSelectionAllowed()) {
      
            String vehino = routetimetable.getValueAt(row, 2).toString();

       vehinumtxt.setText(vehino);

        }
    }

    /**
     *
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws ConcurrentModificationException
     * Deserialzing Routes To display in Combo box
     */
    public final void displayDeserialization() throws IOException, ClassNotFoundException, ConcurrentModificationException {

        Network net = new Network();
        net.getRouteName();

        theRoutes = (SetOfNetworks) service.deserialize_routes(FILE_NAME_ROUTES);

        routenamecmb.removeAllItems();
        for (Network holding : theRoutes) {
            routenamecmb.addItem(holding.getRouteName());
        }

    }

    /**
     *
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws ConcurrentModificationException
     */
    public final void displayDeserialization2() throws IOException, ClassNotFoundException, ConcurrentModificationException {

        Network net = new Network();
        net.getRouteName();

        theRoutes = (SetOfNetworks) service.deserialize_routes(FILE_NAME_ROUTES);

        routecmb.removeAllItems();
        for (Network holding : theRoutes) {
            routecmb.addItem(holding.getRouteName());
        }

    }

    /**
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void serialize_all() throws IOException, ClassNotFoundException {

        service.Serialize(theRoutes, FILE_NAME_ROUTES);

    }

    /**
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void serialize_Timetable() throws IOException, ClassNotFoundException {

        service.Serialize(theTimetables, FILE_NAME_Timetable);

    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        routetxt = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        Availablecmb = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        timetablefaretable = new javax.swing.JTable();
        typetxt = new javax.swing.JTextField();
        vehinotxt = new javax.swing.JTextField();
        endtxt = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        startxt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        routenamecmb = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        routetimetable = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        routecmb = new javax.swing.JComboBox();
        removebtn = new javax.swing.JButton();
        vehinumtxt = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(0, 204, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Route  :");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 64, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Type :");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 121, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Vehicle No  :");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 182, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Start Time :");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 252, -1, -1));
        jPanel1.add(routetxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 190, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Availability  :");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 388, -1, -1));

        Availablecmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Daily", "Weekend" }));
        Availablecmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AvailablecmbActionPerformed(evt);
            }
        });
        jPanel1.add(Availablecmb, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 380, 190, 33));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("Create");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 470, 100, 32));

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setText("View TimeTables");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 470, 160, 32));

        timetablefaretable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        timetablefaretable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                timetablefaretableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(timetablefaretable);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 60, 410, 440));
        jPanel1.add(typetxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 190, 30));

        vehinotxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vehinotxtActionPerformed(evt);
            }
        });
        jPanel1.add(vehinotxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 170, 190, 30));
        jPanel1.add(endtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 310, 190, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("End Time :");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 317, -1, -1));

        startxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startxtActionPerformed(evt);
            }
        });
        jPanel1.add(startxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 240, 190, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Choose Route :");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, -1, -1));

        routenamecmb.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        routenamecmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                routenamecmbActionPerformed(evt);
            }
        });
        jPanel1.add(routenamecmb, new org.netbeans.lib.awtextra.AbsoluteConstraints(466, 11, 190, 33));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/home.png"))); // NOI18N
        jLabel11.setText("Back");
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 0, -1, 40));

        jTabbedPane2.addTab("          Create TimeTables             ", jPanel1);

        jPanel2.setBackground(new java.awt.Color(0, 204, 153));

        routetimetable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        routetimetable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        routetimetable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                routetimetableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(routetimetable);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Choose Route  :");

        routecmb.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        routecmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        routecmb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                routecmbItemStateChanged(evt);
            }
        });
        routecmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                routecmbActionPerformed(evt);
            }
        });

        removebtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        removebtn.setText("Remove");
        removebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removebtnActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/home.png"))); // NOI18N
        jLabel10.setText("Back");
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(removebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 726, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 38, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(36, 36, 36)
                        .addComponent(routecmb, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(94, 94, 94)
                        .addComponent(vehinumtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(routecmb, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(vehinumtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(removebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("         Edit TimeTable        ", jPanel2);

        getContentPane().add(jTabbedPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-5, -5, 790, 570));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        if (startxt.getText().equalsIgnoreCase("") || routetxt.getText().equalsIgnoreCase("") || typetxt.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(rootPane, "Please fill the fields !");
        } else {
            String route = routetxt.getText();
            String type = typetxt.getText();
            String num = vehinotxt.getText();
            String strat = startxt.getText();
            String end = endtxt.getText();
            String status = Availablecmb.getSelectedItem().toString();

            theTimetables.add(new Planning(route, type, num, strat, end, status));
            try {
                serialize_timetable();
            } catch (Exception e) {
            }

            JOptionPane.showMessageDialog(rootPane, "Add To TimeTable Successfully !");
            routetxt.setText("");
            typetxt.setText("");
            vehinotxt.setText("");
            startxt.setText("");
            endtxt.setText("");
            try {
                loadTimeTable(theTimetables);
            } catch (IOException ex) {
                Logger.getLogger(CreateTimeTablesUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CreateTimeTablesUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        jTabbedPane2.setSelectedIndex(1);

    }//GEN-LAST:event_jButton2ActionPerformed

    private void AvailablecmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AvailablecmbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AvailablecmbActionPerformed

    private void startxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_startxtActionPerformed

    private void vehinotxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vehinotxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_vehinotxtActionPerformed

    private void routenamecmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_routenamecmbActionPerformed
        // TODO add your handling code here:
        RowFilter<TableModel, Object> rf = null;
        try {
            rf = RowFilter.regexFilter(routenamecmb.getSelectedItem().toString(), 0);
        } catch (Exception e) {
        }
        sorter_Vehicles.setRowFilter(rf);
    }//GEN-LAST:event_routenamecmbActionPerformed

    private void timetablefaretableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_timetablefaretableMouseClicked
        // TODO add your handling code here:
        selectRouteRow();
    }//GEN-LAST:event_timetablefaretableMouseClicked

    private void routecmbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_routecmbItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_routecmbItemStateChanged

    private void routecmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_routecmbActionPerformed
        // TODO add your handling code here:
                RowFilter<TableModel, Object> rf = null;
        try {
            rf = RowFilter.regexFilter(routecmb.getSelectedItem().toString(),0);
        } catch (Exception e) {
           
        }
        try {
              sorter_Tables2.setRowFilter(rf);
        } catch (Exception e) {
        }
      
 
    }//GEN-LAST:event_routecmbActionPerformed

    private void routetimetableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_routetimetableMouseClicked
        // TODO add your handling code here:
        selectTimetableRow();
    }//GEN-LAST:event_routetimetableMouseClicked

    private void removebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removebtnActionPerformed
        // TODO add your handling code here:
                int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this Recode?", "Confirm",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.NO_OPTION) {
            removebtn.setVisible(false);
        } else if (response == JOptionPane.YES_OPTION) {

           // thet.removeNetwork(theRoutes.getRouteFromNumber(selectedRouteNo));
            theTimetables.removeTimeTable(theTimetables.getRouteFromNumber(vehinumtxt.getText()));
            try {

                loadTimeTable(theTimetables);
            } catch (IOException ex) {
                Logger.getLogger(AddRouteUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AddRouteUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            removebtn.setVisible(false);
            try {
                serialize_timetable();
            } catch (IOException ex) {
                Logger.getLogger(AddRouteUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AddRouteUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(rootPane, "Recode is been deleted successfully !");
            vehinumtxt.setText("");
         

        }

    }//GEN-LAST:event_removebtnActionPerformed

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        // TODO add your handling code here:
        new AdminHomeUI().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jLabel10MouseClicked

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel11MouseClicked

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(CreateTimeTablesUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(CreateTimeTablesUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(CreateTimeTablesUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(CreateTimeTablesUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new CreateTimeTablesUI().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox Availablecmb;
    private javax.swing.JTextField endtxt;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JButton removebtn;
    private javax.swing.JComboBox routecmb;
    private javax.swing.JComboBox routenamecmb;
    private javax.swing.JTable routetimetable;
    private javax.swing.JTextField routetxt;
    private javax.swing.JTextField startxt;
    private javax.swing.JTable timetablefaretable;
    private javax.swing.JTextField typetxt;
    private javax.swing.JTextField vehinotxt;
    private javax.swing.JTextField vehinumtxt;
    // End of variables declaration//GEN-END:variables
}
