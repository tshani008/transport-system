/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import transportsystem.FareManagment;
import transportsystem.Network;
import transportsystem.OtherServices;
import transportsystem.SetOfNetworks;
import transportsystem.SetOfFares;

/**
 *
 * @author 
 */
public class AddRouteUI extends javax.swing.JFrame {

    private Network network;
    private Vector data;
    private TableModel model;
    private Vector header_Routes;
    private int selectedRouteNo;
    private String slectvehicle;
    private Vector header_Vehicles;
    private Vector header_Fare;

    private SetOfNetworks theRoutes = new SetOfNetworks();
    private SetOfNetworks theVehicle = new SetOfNetworks();
    private SetOfFares theFare = new SetOfFares();
    //private SetOfNetworks holdings=new SetOfNetworks();

    private OtherServices service;

    private static final String FILE_NAME_ROUTES = "Routes.ser";
    private static final String FILE_NAME_Vehicles = "Vehicles.ser";
    private static final String FILE_NAME_Fare = "Fare.ser";
     private static final String FILE_NAME_Fare2 = "Fare2.ser";

    TableRowSorter<TableModel> sorter_Routes;
    TableRowSorter<TableModel> sorter_Vehicles;
    TableRowSorter<TableModel> sorter_Fare;

    List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(4);

    /**
     * Creates new form AddRouteUI
     * @param args
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    public static void main(String args[]) throws IOException, ClassNotFoundException {
        AddRouteUI theGUI = new AddRouteUI();
        theGUI.setVisible(true);
    }

    /**
     *
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public AddRouteUI() throws ClassNotFoundException, IOException {

        initComponents();
        service = new OtherServices();

        try {

            theRoutes.addAll(service.deserialize_routes(FILE_NAME_ROUTES));
          
        } catch (IOException e) {
            serialize_all();

        }
      ;
        try {
            theVehicle.addAll(service.deserialize_vehicle(FILE_NAME_Vehicles));
        } catch (Exception e) {

            serialize_Vehi();
        }

        try {
            theFare.addAll(service.deserialize_fare(FILE_NAME_Fare));
        } catch (Exception e) {

            serialize_Fare();
        }
//        
//          try {
//            theVehicle.addAll(service.deserialize_vehicle(FILE_NAME_Fare2));
//        } catch (Exception e) {
//
//            serialize_Vehi2();
//        }
        header_Routes = new Vector();
        header_Routes.add("Route Number");
        header_Routes.add("Route");
        header_Routes.add("Transport Type");
        loadRouteTable(theRoutes);
        displayDeserialize();
        displayDeserialize2();

        header_Vehicles = new Vector();
        header_Vehicles.add("Route Name");
        header_Vehicles.add("Transport Type");
        header_Vehicles.add("Vehicle Number");
        loadVehicleTable(theVehicle);

        header_Fare = new Vector();
        header_Fare.add("Route Name");
        header_Fare.add("Transport Type");
        header_Fare.add("Point 1");
        header_Fare.add("Point 2");
        header_Fare.add("Fare");
        loadFareTable(theFare);
        
        
       String name= (String) routenamecmb.getSelectedItem();
       
    }

    private void selectRouteRow() {

        //retrieving the selected row index
        int row = rotetable.getSelectedRow();

        //if a single row is selected from the table, get selectedBookNo
        if (rotetable.getRowSelectionAllowed()) {
            selectedRouteNo = Integer.parseInt(rotetable.getValueAt(row, 0).toString());
            String selectroutename = rotetable.getValueAt(row, 1).toString();
            String routeno = String.valueOf(selectedRouteNo);
            routeistxt.setText(routeno);
            routenametxt.setText(selectroutename);
        }
    }

    private void selectvehicleRow() {

        //retrieving the selected row index
        int row = vehicleNotbl.getSelectedRow();

        //if a single row is selected from the table, get vehicle number
        if (vehicleNotbl.getRowSelectionAllowed()) {
            // selectedRouteNo=Integer.parseInt(vehicleNotbl.getValueAt(row,0).toString());
            slectvehicle = vehicleNotbl.getValueAt(row, 2).toString();
                    //  routeistxt.setText(routeno);
            // routenametxt.setText(selectroutename);
            vehinotxt.setText(slectvehicle);
        }
    }

    private void loadRouteTable(SetOfNetworks net) throws IOException, ClassNotFoundException {
        data = new Vector<Vector<String>>();
        for (Network network : net) {
            Vector v = new Vector();
            v.add(network.getRoteId());
            v.add(network.getRouteName());
            v.add(network.getNetworkType());

            data.add(v);

        }
        model = new DefaultTableModel(data, header_Routes);

        rotetable.setModel(model);
        sorter_Routes = new TableRowSorter<TableModel>(model);
        rotetable.setRowSorter(sorter_Routes);
        jScrollPane1.setViewportView(rotetable);
        rotetable.setAutoscrolls(true);
        rotetable.getTableHeader().setReorderingAllowed(false);

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

        vehicleNotbl.setModel(model);
        sorter_Vehicles = new TableRowSorter<TableModel>(model);
        vehicleNotbl.setRowSorter(sorter_Vehicles);
        jScrollPane2.setViewportView(vehicleNotbl);
        vehicleNotbl.setAutoscrolls(true);
        vehicleNotbl.getTableHeader().setReorderingAllowed(false);

    }
    
        private void loadFareTable(SetOfFares fare) throws IOException, ClassNotFoundException {
        data = new Vector<Vector<String>>();
        for (FareManagment faremanage : fare) {
            Vector v = new Vector();
            v.add(faremanage.getRouteName());
            v.add(faremanage.getNetworkType());
            v.add(faremanage.getStartPoint());
             v.add(faremanage.getEndPoint());
            v.add(faremanage.getFare());

            data.add(v);

        }
        model = new DefaultTableModel(data, header_Fare);

        faretable.setModel(model);
       sorter_Fare = new TableRowSorter<TableModel>(model);
        faretable.setRowSorter(sorter_Fare);
        jScrollPane3.setViewportView(faretable);
        faretable.setAutoscrolls(true);
        faretable.getTableHeader().setReorderingAllowed(false);

    }
        
    /**
     *
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws ConcurrentModificationException
     */
    public final void displayDeserialize() throws IOException, ClassNotFoundException, ConcurrentModificationException {

        theRoutes = (SetOfNetworks) service.deserialize_routes(FILE_NAME_ROUTES);
        destRoutenamecmb.removeAllItems();
        for (Network holding : theRoutes) {
            destRoutenamecmb.addItem(holding.getRouteName());
        }
    }

    /**
     *
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws ConcurrentModificationException
     */
    public final void displayDeserialize2() throws IOException, ClassNotFoundException, ConcurrentModificationException {

  
        theRoutes = (SetOfNetworks) service.deserialize_routes(FILE_NAME_ROUTES);
        routenamecmb.removeAllItems();
        for (Network holding : theRoutes) {
            routenamecmb.addItem(holding.getRouteName());
        
        }
    }  

    
    /**
     *
     * @param type
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws ConcurrentModificationException
     * Display deserialized List of routes.
     */
    public final void displayDeserialization(String type) throws IOException, ClassNotFoundException, ConcurrentModificationException {
   

     Network net=new Network();
     net.getRouteName();
        
        theRoutes = (SetOfNetworks) service.deserialize_routes(FILE_NAME_ROUTES);
       
        
  
        routenamecmb.removeAllItems();
        for (Network holding : theRoutes) {
            routenamecmb.addItem(holding.getRouteName().matches(type));
        }
            
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        vehinotxt = new javax.swing.JTextField();
        routenamecmb = new javax.swing.JComboBox();
        vehinotranstypecmb = new javax.swing.JComboBox();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        vehicleNotbl = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        destRoutenamecmb = new javax.swing.JComboBox();
        faretypecmb = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        point1txt = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        point2txt = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        faretxt = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        faretable = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        routenametxt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        routeistxt = new javax.swing.JTextField();
        routetypecmb = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        removebutton = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        rotetable = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(0, 204, 153));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Route  :");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Vehical Number  :");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Transport Type:");

        routenamecmb.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        vehinotranstypecmb.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        vehinotranstypecmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Bus", "Train", "Other" }));

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton4.setText("Add");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton5.setText("Back");

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton6.setText("Remove");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        vehicleNotbl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        vehicleNotbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        vehicleNotbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vehicleNotblMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(vehicleNotbl);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/home.png"))); // NOI18N
        jLabel12.setText("Back");
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh.png"))); // NOI18N
        jLabel18.setText("Routes");
        jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel18MouseClicked(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dollar.png"))); // NOI18N
        jLabel19.setText("Fares");
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 704, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(127, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(routenamecmb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(vehinotxt)
                            .addComponent(vehinotranstypecmb, 0, 221, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(routenamecmb, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(vehinotxt, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(vehinotranstypecmb, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("     Add Vehicals    ", jPanel2);

        jPanel3.setBackground(new java.awt.Color(0, 204, 153));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Route  :");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Transport Type:");

        destRoutenamecmb.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        destRoutenamecmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        faretypecmb.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        faretypecmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Bus", "Train", "Other" }));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText(" Point    1:");

        point1txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                point1txtKeyTyped(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton7.setText("Add");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton8.setText("Remove");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton9.setText("Back");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Point     2 :");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Fare  :");

        faretxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                faretxtKeyTyped(evt);
            }
        });

        faretable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        faretable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(faretable);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/home.png"))); // NOI18N
        jLabel13.setText("Back");
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh.png"))); // NOI18N
        jLabel20.setText("Routes");
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/puzzle.png"))); // NOI18N
        jLabel21.setText("Vehicles");
        jLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel21MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)
                                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 698, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 118, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel10))
                                        .addGap(18, 18, 18))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addGap(64, 64, 64)))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(faretxt, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                                    .addComponent(destRoutenamecmb, javax.swing.GroupLayout.Alignment.LEADING, 0, 205, Short.MAX_VALUE)
                                    .addComponent(faretypecmb, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(point1txt, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(point2txt, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel21)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(destRoutenamecmb, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(faretypecmb, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(point1txt, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(point2txt, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(faretxt, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("           Add Fares To Relevant Destinations        ", jPanel3);

        jPanel1.setBackground(new java.awt.Color(0, 204, 153));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Route ID  :");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Route   :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Transport Type :");

        routeistxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                routeistxtActionPerformed(evt);
            }
        });
        routeistxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                routeistxtKeyTyped(evt);
            }
        });

        routetypecmb.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        routetypecmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Bus", "Train", "Other" }));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        removebutton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        removebutton.setText("Remove");
        removebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removebuttonActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setText("Back");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        rotetable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        rotetable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rotetableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(rotetable);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/home.png"))); // NOI18N
        jLabel14.setText("Back");
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dollar.png"))); // NOI18N
        jLabel16.setText("Fares");
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/puzzle.png"))); // NOI18N
        jLabel17.setText("Vehicles");
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addComponent(removebutton, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 679, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(140, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(routenametxt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(routeistxt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(routetypecmb, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(routeistxt, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(routenametxt, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(routetypecmb, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removebutton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        jTabbedPane1.addTab("     Add Routes     ", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 862, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 656, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 22, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (routenametxt.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(rootPane, "Please fill the fields !");
        } else {
            String id = routeistxt.getText();
            int id2 = Integer.valueOf(id);
            theRoutes.add(new Network(id2, routenametxt.getText(), routetypecmb.getSelectedItem().toString()));

            try {
                loadRouteTable(theRoutes);

            } catch (IOException ex) {
                Logger.getLogger(AddRouteUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AddRouteUI.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                serialize_all();
            } catch (Exception e) {
            }

            JOptionPane.showMessageDialog(rootPane, "Book Successfully added !");
            routeistxt.setText("");
            routenametxt.setText("");
            try {
                displayDeserialize();
            } catch (IOException ex) {
                Logger.getLogger(AddRouteUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AddRouteUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ConcurrentModificationException ex) {
                Logger.getLogger(AddRouteUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                displayDeserialize2();
            } catch (IOException ex) {
                Logger.getLogger(AddRouteUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AddRouteUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ConcurrentModificationException ex) {
                Logger.getLogger(AddRouteUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // Assign Vehicles To a route.

        if (vehinotxt.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(rootPane, "Please fill the fields !");
        } else {
            String route = routenamecmb.getSelectedItem().toString();
            String number = vehinotxt.getText();
            String vehicletype = vehinotranstypecmb.getSelectedItem().toString();
            theVehicle.add(new Network(route, number, vehicletype));

            try {
                serialize_Vehi();
            } catch (Exception e) {
            }
            try {
                serialize_Vehi2();
            } catch (IOException ex) {
                Logger.getLogger(AddRouteUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AddRouteUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(rootPane, "Vehicle Successfully added !");
            vehinotxt.setText("");
            try {
                loadVehicleTable(theVehicle);
            } catch (IOException ex) {
                Logger.getLogger(AddRouteUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AddRouteUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void routeistxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_routeistxtActionPerformed
        // TODO add your handling code here:
        routenametxt.grabFocus();

    }//GEN-LAST:event_routeistxtActionPerformed

    private void removebuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removebuttonActionPerformed
       
        /*Remove Routes From Routes.ser File*/
        int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this Route?", "Confirm",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.NO_OPTION) {
            removebutton.setVisible(false);
        } else if (response == JOptionPane.YES_OPTION) {

            theRoutes.removeNetwork(theRoutes.getRouteFromNumber(selectedRouteNo));
            try {

                loadRouteTable(theRoutes);
            } catch (IOException ex) {
                Logger.getLogger(AddRouteUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AddRouteUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            removebutton.setVisible(false);
            try {
                serialize_all();
            } catch (IOException ex) {
                Logger.getLogger(AddRouteUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AddRouteUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(rootPane, "Route is been deleted successfully !");
            routeistxt.setText("");
            routenametxt.setText("");
            try {
                displayDeserialize();
            } catch (IOException ex) {
                Logger.getLogger(AddRouteUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AddRouteUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ConcurrentModificationException ex) {
                Logger.getLogger(AddRouteUI.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                displayDeserialize2();
            } catch (IOException ex) {
                Logger.getLogger(AddRouteUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AddRouteUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ConcurrentModificationException ex) {
                Logger.getLogger(AddRouteUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

             // TODO add your handling code here:

    }//GEN-LAST:event_removebuttonActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:

        if (point1txt.getText().equalsIgnoreCase("") || point2txt.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(rootPane, "Please fill the fields !");
        } else {
            String point1 = point1txt.getText();
            String point2 = point2txt.getText();
            String route = destRoutenamecmb.getSelectedItem().toString();
            String type = faretypecmb.getSelectedItem().toString();

            String fare = faretxt.getText();
            theFare.add(new FareManagment(route, type, point1, point2, fare));

            try {
                serialize_Fare();
            } catch (Exception e) {
                e.printStackTrace();
            }

            JOptionPane.showMessageDialog(rootPane, "Book Successfully added !");
            
            try {
                loadFareTable(theFare);
            } catch (IOException ex) {
                Logger.getLogger(AddRouteUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AddRouteUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            point1txt.setText("");
            point2txt.setText("");

            faretxt.setText("");
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void rotetableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rotetableMouseClicked
        // TODO add your handling code here:
        selectRouteRow();
    }//GEN-LAST:event_rotetableMouseClicked

    private void vehicleNotblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vehicleNotblMouseClicked
        // TODO add your handling code here:
        selectvehicleRow();
    }//GEN-LAST:event_vehicleNotblMouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:

        int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this vehicle?", "Confirm",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.NO_OPTION) {
           // jButton6.setVisible(false);
        } else if (response == JOptionPane.YES_OPTION) {

            theVehicle.removeVehical(theVehicle.getVehicleFromNumber(vehinotxt.getText()));

               

            try {
                serialize_Vehi();
            } catch (IOException ex) {
                Logger.getLogger(AddRouteUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AddRouteUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                loadVehicleTable(theVehicle);
            } catch (IOException ex) {
                Logger.getLogger(AddRouteUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AddRouteUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                serialize_Vehi2();
            } catch (IOException ex) {
                Logger.getLogger(AddRouteUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AddRouteUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            
                  JOptionPane.showMessageDialog(rootPane, "Vehicle is been deleted successfully !");
            vehinotxt.setText("");
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void routeistxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_routeistxtKeyTyped
        // TODO add your handling code here:
        numbersOnlyValidation(evt);
    }//GEN-LAST:event_routeistxtKeyTyped

    private void point1txtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_point1txtKeyTyped
        // TODO add your handling code here:
        lettersOnlyValidation(evt);
    }//GEN-LAST:event_point1txtKeyTyped

    private void faretxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_faretxtKeyTyped
        // TODO add your handling code here:
        numbersOnlyValidation(evt);
    }//GEN-LAST:event_faretxtKeyTyped

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        // TODO add your handling code here:
        new AdminHomeUI().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jLabel12MouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        // TODO add your handling code here:
        new AdminHomeUI().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jLabel13MouseClicked

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        // TODO add your handling code here:
        new AdminHomeUI().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jLabel14MouseClicked

    private void jLabel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseClicked
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_jLabel21MouseClicked

    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked
        // TODO add your handling code here:
         jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_jLabel20MouseClicked

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
        // TODO add your handling code here:
         jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_jLabel17MouseClicked

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        // TODO add your handling code here:
         jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_jLabel16MouseClicked

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked
        // TODO add your handling code here:
         jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_jLabel19MouseClicked

    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseClicked
        // TODO add your handling code here:
         jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_jLabel18MouseClicked

    /**
     *
     * @throws IOException
     * @throws ClassNotFoundException
     * Serializing Routes
     */
    public void serialize_all() throws IOException, ClassNotFoundException {

        service.Serialize(theRoutes, FILE_NAME_ROUTES);

    }

    /**
     *
     * @throws IOException
     * @throws ClassNotFoundException
     * Serializing Vehicle Assigning details
     */
    public void serialize_Vehi() throws IOException, ClassNotFoundException {

        service.Serialize(theVehicle, FILE_NAME_Vehicles);

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
     * 
     * Numbers are only Accepted
     */
    private void numbersOnlyValidation(java.awt.event.KeyEvent evt) {

        try {
            char ch = evt.getKeyChar();
            if (!Character.isDigit(ch)) {

                evt.consume();
                Toolkit.getDefaultToolkit().beep();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     *
     * 
     * Letters are only Accepted
     */
    private void lettersOnlyValidation(java.awt.event.KeyEvent evt) {
        try {
            char ch = evt.getKeyChar();
            int no = evt.getKeyCode();

          
            if (Character.isLetter(ch)) {

            } else if (Character.isLetter(ch) || Character.isDigit(ch)) {
                evt.consume();
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(rootPane, "Characters Only !");

            }

        } catch (HeadlessException e) {
        }

    }
    /**
     *
     * @throws IOException
     * @throws ClassNotFoundException
     * Serializing Fares
     */
    public void serialize_Fare() throws IOException, ClassNotFoundException {

        service.Serialize(theFare, FILE_NAME_Fare);

    }

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
//            java.util.logging.Logger.getLogger(AddRouteUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(AddRouteUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(AddRouteUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(AddRouteUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//             //   new AddRouteUI().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox destRoutenamecmb;
    private javax.swing.JTable faretable;
    private javax.swing.JTextField faretxt;
    private javax.swing.JComboBox faretypecmb;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField point1txt;
    private javax.swing.JTextField point2txt;
    private javax.swing.JButton removebutton;
    private javax.swing.JTable rotetable;
    private javax.swing.JTextField routeistxt;
    private javax.swing.JComboBox routenamecmb;
    private javax.swing.JTextField routenametxt;
    private javax.swing.JComboBox routetypecmb;
    private javax.swing.JTable vehicleNotbl;
    private javax.swing.JComboBox vehinotranstypecmb;
    private javax.swing.JTextField vehinotxt;
    // End of variables declaration//GEN-END:variables
}
