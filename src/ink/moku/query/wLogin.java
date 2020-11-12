package ink.moku.query;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class wLogin extends JFrame {
        JPanel Title = new JPanel(new GridLayout(3, 1,10,10));
        JPanel Fun = new JPanel(new GridLayout(7, 1, 10, 10));
        JPanel Button = new JPanel(new GridLayout(1, 2, 100, 10));

        JLabel TitleText = new JLabel();
        JLabel SubtitleA = new JLabel();
        JLabel SubtitleB = new JLabel();
        JLabel tHost = new JLabel();
        JTextField Host = new JTextField();
        JLabel tPort = new JLabel();
        JTextField Port = new JTextField();
        JLabel tData = new JLabel();
        JTextField Data = new JTextField();
        JLabel tTabl = new JLabel();
        JTextField Tabl = new JTextField();
        JLabel tUser = new JLabel();
        JTextField User = new JTextField();
        JLabel tPass = new JLabel();
        JPasswordField Pass = new JPasswordField();
        JButton Confirm = new JButton();
        JButton Exit = new JButton();

        Conn con = new Conn();
        Connection connection;
        //SaveInfo save = new SaveInfo();

        private static String host;
        private static String port;
        private static String data;
        private static String tabl;
        private static String user;
        private static String pass;

        wLogin(String s) throws IOException {
                super(s);
                setSize(420, 435);
                setResizable(false);
                setLocationRelativeTo(getOwner());
                setIconImage(Toolkit.getDefaultToolkit().getImage(
                        "Image\\icon_large.jpg"));// 设置窗体的图标
                setLayout(new FlowLayout(1, 10, 10));

                TitleText.setFont(new java.awt.Font("微软雅黑", Font.BOLD, 18));
                TitleText.setForeground(new java.awt.Color(255, 100, 0));
                TitleText.setText("MySQL连接窗口 Ver.201023");
                TitleText.setHorizontalAlignment(SwingConstants.CENTER);
                SubtitleA.setFont(new java.awt.Font("微软雅黑", Font.PLAIN, 16));
                SubtitleA.setForeground(new java.awt.Color(0, 70, 255));
                SubtitleA.setText("驱动等待连接...");
                SubtitleA.setHorizontalAlignment(SwingConstants.CENTER);
                SubtitleB.setFont(new java.awt.Font("微软雅黑", Font.PLAIN, 16));
                SubtitleB.setForeground(new java.awt.Color(0, 70, 255));
                SubtitleB.setText("数据库等待连接...");
                SubtitleB.setHorizontalAlignment(SwingConstants.CENTER);

                tHost.setFont(new java.awt.Font("微软雅黑", 0, 18));
                tHost.setText("主机：");
                Host.setText("127.0.0.1");
                Host.setColumns(8);
                tPort.setFont(new java.awt.Font("微软雅黑", 0, 18));
                tPort.setText("端口：");
                Port.setText("3306");
                Port.setColumns(8);
                tData.setFont(new java.awt.Font("微软雅黑", 0, 18));
                tData.setText("库名：");
                Data.setText("student");
                Data.setColumns(8);
                tTabl.setFont(new java.awt.Font("微软雅黑", 0, 18));
                tTabl.setText("表名：");
                Tabl.setText("class_1");
                Tabl.setColumns(8);
                tUser.setFont(new java.awt.Font("微软雅黑", 0, 18));
                tUser.setText("用户：");
                User.setText("root");
                User.setColumns(8);
                tPass.setFont(new java.awt.Font("微软雅黑", 0, 18));
                tPass.setText("密码：");
                Pass.setColumns(8);
                Confirm.setFont(new java.awt.Font("微软雅黑", 0, 19));
                Confirm.setText("连接");
                Confirm.addActionListener(new ConfirmAction());
                Exit.setFont(new java.awt.Font("微软雅黑", 0, 19));
                Exit.setText("返回");
                Exit.addActionListener(new ExitAction());

                Title.add(TitleText);
                Title.add(SubtitleA);
                Title.add(SubtitleB);
                Fun.add(tHost);
                Fun.add(Host);
                Fun.add(tPort);
                Fun.add(Port);
                Fun.add(tData);
                Fun.add(Data);
                Fun.add(tTabl);
                Fun.add(Tabl);
                Fun.add(tUser);
                Fun.add(User);
                Fun.add(tPass);
                Fun.add(Pass);
                Button.add(Confirm);
                Button.add(Exit);
                add(Title);
                add(Fun);
                add(Button);

                setVisible(false);
        }
        public static void setInfo(String host, String port, String data, String tabl, String user, String pass){
                wLogin.host = host;
                wLogin.port = port;
                wLogin.data = data;
                wLogin.tabl = tabl;
                wLogin.user = user;
                wLogin.pass = pass;
        }
        public static String getHost(){
                return host;
        }
        public static String getPort(){
                return port;
        }
        public static String getData(){
                return data;
        }
        public static String getTabl(){
                return tabl;
        }
        public static String getUser(){
                return user;
        }
        public static String getPass(){
                return pass;
        }

        public void setVisible(){
                setVisible(true);
        }

        public class ConfirmAction implements ActionListener {
                public void actionPerformed(ActionEvent e) {
                        String sHost = Host.getText().toString();
                        String sPort = Port.getText().toString();
                        String sData = Data.getText().toString();
                        String sTabl = Tabl.getText().toString();
                        String sUser = User.getText().toString();
                        String sPass = String.valueOf(Pass.getPassword());// 获得文本框的内容
                        try {
                                con.getDriver();
                                SubtitleA.setText("驱动连接成功");
                        } catch (ClassNotFoundException classNotFoundException) {
                                classNotFoundException.printStackTrace();
                                SubtitleA.setText("驱动连接失败");
                        }
                        try {
                                connection = con.getConnection(sHost, sPort, sData, sUser, sPass);
                                //save.SaveInfo(sHost, sPort, sData, sUser, sPass);
                                DatabaseMetaData meta = connection.getMetaData();
                                ResultSet rsTables = meta.getTables(null , null, sTabl, null);
                                if(rsTables.next()){
                                        SubtitleB.setText("登录成功");
                                }else{
                                        SubtitleB.setText("登录失败，请检查表名");
                                }
                                wLogin.setInfo(sHost, sPort, sData, sTabl, sUser, sPass);
                        } catch (SQLException throwable) {
                                throwable.printStackTrace();
                                SubtitleB.setText("登录失败，请检查信息");
                        }
                }
        }
        public class ExitAction implements ActionListener {
                public void actionPerformed(ActionEvent e) {
                        setVisible(false);
                }
        }
}
