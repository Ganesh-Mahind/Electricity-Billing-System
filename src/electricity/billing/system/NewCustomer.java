
package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class NewCustomer extends JFrame implements ActionListener {
 
    JTextField tfname,tfaddress,tfstate,tfcity,tfemail,tfphone;
    JButton next, cancel;
    JLabel lblmeter;
    NewCustomer() {
   setSize(700,500);
   setLocation(400,200);

   JPanel p =new JPanel();
   p.setLayout(null);
   p.setBackground(new Color(173,216,230));
   add(p);
   
   JLabel heading=new JLabel("New Customer");
   heading.setBounds(180,10,200,25);
   heading.setFont(new Font("Tahoma",Font.PLAIN,24));
   p.add(heading);
   
   JLabel lblname=new JLabel("Customer Name");
   lblname.setBounds(100,80,100,20);
   p.add(lblname);
   
   tfname=new JTextField();
   tfname.setBounds(240,80,200,20);
   p.add(tfname);
   
   JLabel lblmeterno=new JLabel("Meter Number");
   lblmeterno.setBounds(100,120,100,20);
   p.add(lblmeterno);
   
    lblmeter=new JLabel("");
   lblmeter.setBounds(240,120,100,20);
   p.add(lblmeter);
   
   Random ran = new Random();
   long number=ran.nextLong() % 1000000;
   lblmeter.setText("" + Math.abs (number));
   
   JLabel lbladdress=new JLabel("Address");
   lbladdress.setBounds(100,160,100,20);
   p.add(lbladdress);
   
   
   tfaddress=new JTextField();
   tfaddress.setBounds(240,160,200,20);
   p.add(tfaddress);
   
   JLabel lblcity=new JLabel("City");
   lblcity.setBounds(100,200,100,20);
   p.add(lblcity);
   
   
   tfcity=new JTextField();
   tfcity.setBounds(240,200,200,20);
   p.add(tfcity);
   
   JLabel lblstate=new JLabel("State");
   lblstate.setBounds(100,240,100,20);
   p.add(lblstate);
   
   
   tfstate=new JTextField();
   tfstate.setBounds(240,240,200,20);
   p.add(tfstate);
   
   JLabel lblemail=new JLabel("Email");
   lblemail.setBounds(100,280,100,20);
   p.add(lblemail);
   
   
   tfemail=new JTextField();
   tfemail.setBounds(240,280,200,20);
   p.add(tfemail);
   
   JLabel lblphone=new JLabel("Phone Number");
   lblphone.setBounds(100,320,100,20);
   p.add(lblphone);
   
   
   tfphone=new JTextField();
   tfphone.setBounds(240,320,200,20);
   tfphone.addKeyListener(new KeyAdapter() {
         public void keyTyped(KeyEvent e) {
             char c = e.getKeyChar();
             if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                  e.consume();  // if it's not a number, ignore the event
             }
         }
    });
   
   p.add(tfphone);
   
   
   next = new JButton("Next");
   next.setBounds(120,390,100,25);
   next.setBackground(Color.BLACK);
   next.setForeground(Color.WHITE);
   next.addActionListener(this);
   p.add(next);
   
    cancel = new JButton("Cancel");
    cancel.setBounds(300,390,100,25);
    cancel.setBackground(Color.BLACK);
    cancel.setForeground(Color.WHITE);
    cancel.addActionListener(this);
    
   p.add(cancel);
   
   setLayout(new BorderLayout());
   
   add(p,"Center");
   
   ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/hicon1.jpg"));
   Image i2 = i1.getImage().getScaledInstance(150, 300, Image.SCALE_DEFAULT);
   ImageIcon i3 = new ImageIcon(i2);
   JLabel image = new JLabel(i3);
   add(image,"West");
   
   getContentPane().setBackground(Color.WHITE);
   
   setVisible(true);
  }
    
    public void actionPerformed(ActionEvent ae){
        String name =tfname.getText();
          String meter = lblmeter.getText();
          String address = tfaddress.getText();
          String city = tfcity.getText();
          String state = tfstate.getText();
          String email = tfemail.getText();
          String phone= tfphone.getText();
      if(ae.getSource()== next){
//          name =tfname.getText();
//          meter = lblmeter.getText();
//          address = tfaddress.getText();
//          city = tfcity.getText();
//          state = tfstate.getText();
//          email = tfemail.getText();
          if(name.isEmpty()){
              JOptionPane.showMessageDialog(NewCustomer.this,"Enter the Name.");
          }
          else if(meter.isEmpty()){
              JOptionPane.showMessageDialog(NewCustomer.this,"Enter the meter");
          }
          else if(address.isEmpty()){
              JOptionPane.showMessageDialog(NewCustomer.this,"Enter the address");
          }
          else if(city.isEmpty()){
              JOptionPane.showMessageDialog(NewCustomer.this,"Ente the City");
          }
          else if(state.isEmpty()){
              JOptionPane.showMessageDialog(NewCustomer.this,"Enter the state.");
          }
          else if(email.isEmpty()){
              JOptionPane.showMessageDialog(NewCustomer.this,"Enter the email.");
          }
          else if(tfphone.getText().length()<10||tfphone.getText().length()>10 ||tfphone.getText().equals(""))//||!mobileno.contains("[0ie-9]") 
          {
              JOptionPane.showMessageDialog(NewCustomer.this,"Moblie number is mandotory.");
          }
          else{
              String query1 = "insert into customer values('"+name+"','"+meter+"','"+address+"','"+city+"','"+state+"','"+email+"', '"+phone+"')";  
          
          String query2 = "insert into login values('"+meter+"','','"+name+"','' ,'')";
          
          try {
              Conn c= new  Conn();
              c.s.executeUpdate(query1);
              c.s.executeUpdate(query2);
              
              JOptionPane.showMessageDialog(null, "Customer Details Added Successfully");
              setVisible(false);
              
              //new frame
              new MeterInfo(meter);
                  
          } catch (Exception e){
              e.printStackTrace();
          }
          }
        } 
        else{
          
          setVisible(false);
        } 
    }
  public static void main (String[]args){
      new NewCustomer();
  }
}
