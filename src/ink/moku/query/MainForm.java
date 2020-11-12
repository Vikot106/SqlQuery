/*
 * Created by JFormDesigner on Fri Oct 30 13:56:39 GMT+08:00 2020
 */

package ink.moku.query;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author lhr
 */
public class MainForm extends JFrame {

    wLogin login = new wLogin("Login");
    Conn con = new Conn();
    Connection connection;
    private static String Table;
    static Statement stmt;
    static ResultSet rs;
    static ResultSetMetaData rsmd;

    public MainForm() throws IOException {
        initComponents();
    }

    public static void setTable(String table){
        MainForm.Table = table;
    }
    public static String getTable(){
        return Table;
    }

    private void initComponents() {
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(Toolkit.getDefaultToolkit().getImage(
                "Image\\icon_large.jpg"));// 设置窗体的图标
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        contentPanel = new JPanel();
        label1 = new JLabel();
        scrollPane1 = new JScrollPane();
        textArea = new JTextArea();
        label10 = new JLabel();
        contentPanel2 = new JPanel();
        add = new JButton();
        refresh = new JButton();
        modify = new JButton();
        delete = new JButton();
        statu = new JLabel();
        dialogPane = new JPanel();
        buttonBar2 = new JPanel();
        dbstatu = new JLabel();
        ip = new JLabel();
        buttonBar = new JPanel();
        connect = new JButton();
        exit = new JButton();

        //======== this ========
        setTitle("SQL Query");
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== contentPanel ========
        {
            contentPanel.setPreferredSize(new Dimension(21, 228));
            contentPanel.setLayout(new GridBagLayout());
            ((GridBagLayout)contentPanel.getLayout()).columnWidths = new int[] {0, 0};
            ((GridBagLayout)contentPanel.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            ((GridBagLayout)contentPanel.getLayout()).columnWeights = new double[] {1.0, 1.0E-4};
            ((GridBagLayout)contentPanel.getLayout()).rowWeights = new double[] {1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0E-4};

            //---- label1 ----
            label1.setText("Powered by HangruiLi  Version.201109");
            label1.setHorizontalAlignment(SwingConstants.LEFT);
            contentPanel.add(label1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));

            //======== scrollPane1 ========
            {

                //---- textArea ----
                textArea.setRows(11);
                textArea.setColumns(1);
                textArea.setEditable(false);
                scrollPane1.setViewportView(textArea);
            }
            contentPanel.add(scrollPane1, new GridBagConstraints(0, 1, 1, 12, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));

            //---- label10 ----
            label10.setText("  ");
            contentPanel.add(label10, new GridBagConstraints(0, 13, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
        }
        contentPane.add(contentPanel, BorderLayout.NORTH);

        //======== contentPanel2 ========
        {
            contentPanel2.setLayout(new GridBagLayout());
            ((GridBagLayout)contentPanel2.getLayout()).columnWidths = new int[] {0, 0, 0};
            ((GridBagLayout)contentPanel2.getLayout()).rowHeights = new int[] {0, 0, 0, 0};
            ((GridBagLayout)contentPanel2.getLayout()).columnWeights = new double[] {1.0, 1.0, 1.0E-4};
            ((GridBagLayout)contentPanel2.getLayout()).rowWeights = new double[] {1.0, 1.0, 1.0, 1.0E-4};

            //---- add ----
            add.setText("\u589e\u52a0");
            contentPanel2.add(add, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 10, 10), 0, 0));

            //---- refresh ----
            refresh.setText("\u5237\u65b0");
            contentPanel2.add(refresh, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 10, 0), 0, 0));

            //---- modify ----
            modify.setText("\u4fee\u6539");
            contentPanel2.add(modify, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 10, 10), 0, 0));

            //---- delete ----
            delete.setText("\u5220\u9664");
            contentPanel2.add(delete, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 10, 0), 0, 0));

            //---- statu ----
            statu.setText("\u8fd0\u884c\u72b6\u6001\uff1a\u8bf7\u5728\u8fde\u63a5\u6570\u636e\u5e93\u540e\u5237\u65b0\u9875\u9762");
            contentPanel2.add(statu, new GridBagConstraints(0, 2, 2, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
        }
        contentPane.add(contentPanel2, BorderLayout.CENTER);

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout());

            //======== buttonBar2 ========
            {
                buttonBar2.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar2.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar2.getLayout()).columnWidths = new int[] {0, 0, 0};
                ((GridBagLayout)buttonBar2.getLayout()).rowHeights = new int[] {0, 0};
                ((GridBagLayout)buttonBar2.getLayout()).columnWeights = new double[] {1.0, 1.0, 1.0E-4};
                ((GridBagLayout)buttonBar2.getLayout()).rowWeights = new double[] {1.0, 1.0E-4};

                //---- dbstatu ----
                dbstatu.setText("\u672a\u8fde\u63a5\u6570\u636e\u5e93");
                buttonBar2.add(dbstatu, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));

                //---- ip ----
                ip.setText("\u2014IP\u2014\u5e93\u540d\u2014\u8868\u540d");
                buttonBar2.add(ip, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(buttonBar2, BorderLayout.WEST);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

                //---- connect ----
                connect.setText("\u8fde\u63a5");
                buttonBar.add(connect, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- exit ----
                exit.setText("\u9000\u51fa");
                buttonBar.add(exit, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(buttonBar, BorderLayout.EAST);
        }
        contentPane.add(dialogPane, BorderLayout.SOUTH);
        setSize(540, 460);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(connection!=null){
                    boolean flag = true;
                    String data = JOptionPane.showInputDialog(null, "请输入要插入的数据 如“1,name” 多个数据以以半角逗号“,”隔开", "插入", JOptionPane.QUESTION_MESSAGE);
                    String[] strData = data.split("\\,");
                    String sql="insert into " + getTable() + "(";
                    try {
                        int count = rsmd.getColumnCount();
                        String[] name = new String[count];
                        for (int i = 0; i < count; i++) {
                            name[i] = rsmd.getColumnName(i + 1);
                            sql = sql + name[i] + ",";
                        }
                        sql = sql.substring(0,sql.length() - 1);
                        sql = sql + ") values(";
                        for (int i = 0; i < strData.length ; i++) {
                            sql = sql + strData[i] + ",";
                        }
                        sql = sql.substring(0,sql.length() - 1);
                        sql = sql + ")";
                        stmt.executeUpdate(sql);
                        JOptionPane.showMessageDialog(null, "添加成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                        flag = false;
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                        JOptionPane.showMessageDialog(null, "请检查输入内容", "提示", JOptionPane.ERROR_MESSAGE);
                    }
                    if(flag) {
                        JOptionPane.showMessageDialog(null,"添加失败","提示",JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    statu.setForeground(Color.red);
                }
            }
        });
        modify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(connection!=null){
                    boolean flag = true;
                    String key = JOptionPane.showInputDialog(null, "请指定要修改的行的数据条件 如“ID=1”", "修改", JOptionPane.QUESTION_MESSAGE);
                    String data = JOptionPane.showInputDialog(null, "请输入要修改的数据 如“name=lhr” 多个数据以半角逗号“,”隔开", "修改", JOptionPane.QUESTION_MESSAGE);
                    String[] strData = data.split("\\,");
                    String sql="update " + getTable() + " set ";
                    try {
                        int count = rsmd.getColumnCount();
                        for (int i = 0; i < strData.length; i++) {
                            String[] strTmp = strData[i].split("\\=");
                            sql = sql + strTmp[0] + "='" + strTmp[1] + "',";
                        }
                        sql = sql.substring(0,sql.length() - 1);
                        String[] strTmp = key.split("\\=");
                        sql = sql + " where " + strTmp[0] + "='" + strTmp[1] + "'";
                        //sql.replaceAll("([^=><])=([^=><])","='");
                        System.out.println(sql);
                        stmt.executeUpdate(sql);
                        JOptionPane.showMessageDialog(null, "修改成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                        flag = false;
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                        JOptionPane.showMessageDialog(null, "请检查输入内容", "提示", JOptionPane.ERROR_MESSAGE);
                    }catch (ArrayIndexOutOfBoundsException aioobe){
                        aioobe.printStackTrace();
                        JOptionPane.showMessageDialog(null, "请检查输入内容", "提示", JOptionPane.ERROR_MESSAGE);
                    }
                    if(flag) {
                        JOptionPane.showMessageDialog(null,"修改失败","提示",JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    statu.setForeground(Color.red);
                }
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(connection!=null) {
                    String id = JOptionPane.showInputDialog(null, "请输入要删除的内容所在的列名", "删除", JOptionPane.QUESTION_MESSAGE);
                    String rowNumber = JOptionPane.showInputDialog(null, "请输入关键字以删除行", "删除", JOptionPane.QUESTION_MESSAGE);
                    Boolean flag = true;
                    System.out.println(getTable());
                    try {
                        //while (rs.next()) {
                            //if (rs.getString(id).equals(id)) {
                                String sql = "delete from " + getTable() + " where " + id + "='" + rowNumber + "'";
                                System.out.println(sql);
                                stmt.executeUpdate(sql);
                                JOptionPane.showMessageDialog(null, "删除成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                                flag = false;
                                //break;
                            //}
                        //}
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                        JOptionPane.showMessageDialog(null, "请检查输入内容", "提示", JOptionPane.ERROR_MESSAGE);
                    }
                    if(flag) {
                        JOptionPane.showMessageDialog(null,"删除失败","提示",JOptionPane.ERROR_MESSAGE);
                    }
                }else {
                    statu.setForeground(Color.red);
                }
            }
        });
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println(save.getData());
                try {
                    statu.setForeground(Color.black);
                    connection = con.getConnection(login.getHost(),login.getPort(),login.getData(),login.getUser(),login.getPass());
                    MainForm.setTable(login.getTabl());
                    dbstatu.setText("已连接数据库");
                    ip.setText("—"+login.getHost()+"—"+login.getData()+"—"+login.getTabl());
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                    //System.out.println(wLogin.getData()+wLogin.getPass()+"a");
                    statu.setText("运行状态：无法连接数据库 请检查连接状况后刷新");
                }
                try {
                    String output = "";
                    //System.out.println(MainForm.getTable()+wLogin.getTabl());
                    MainForm.stmt = connection.createStatement();
                    MainForm.rs = stmt.executeQuery("select * from "+MainForm.getTable());
                    MainForm.rsmd = rs.getMetaData();
                    int count=rsmd.getColumnCount();
                    String[] name=new String[count];
                    for(int i=0;i<count;i++) {
                        name[i] = rsmd.getColumnName(i + 1);
                        output = output + name[i] + '\t';
                    }
                    output = output + '\n';
                    while(rs.next()) {
                        for(int i=0;i<count;i++) {
                            output = output + rs.getString(name[i]) + '\t';
                        }
                        output = output + '\n';
                    }
                    textArea.setText(output);
                    statu.setText("运行状态：正常");
                }catch(SQLException ee) {
                    ee.printStackTrace();
                    statu.setText("运行状态：无法查询数据 请检查数据表后刷新");
                }
            }
        });
        connect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login.setVisible();
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(connection!=null){
                    try {
                        connection.close();
                        rs.close();
                    } catch (SQLException throwable) {
                        throwable.printStackTrace();
                    }
                }
                System.exit(0);
            }
        });
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel contentPanel;
    private JLabel label1;
    private JScrollPane scrollPane1;
    private JTextArea textArea;
    private JLabel label10;
    private JPanel contentPanel2;
    private JButton add;
    private JButton refresh;
    private JButton modify;
    private JButton delete;
    private JLabel statu;
    private JPanel dialogPane;
    private JPanel buttonBar2;
    private JLabel dbstatu;
    private JLabel ip;
    private JPanel buttonBar;
    private JButton connect;
    private JButton exit;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

}
