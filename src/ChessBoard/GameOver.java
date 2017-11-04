
package ChessBoard;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.*;

public class GameOver extends JApplet implements ActionListener {
    Board b;
    ImageIcon ii=new ImageIcon(getPieceImageUrl("Draw"));
    ImageIcon ii1=new ImageIcon(getPieceImageUrl("checkmate"));
    ImageIcon ii2=new ImageIcon(getPieceImageUrl("continue"));
    ImageIcon ii3=new ImageIcon(getPieceImageUrl("exit"));
    JButton continu,exit;
    JPanel panel;
    JLabel bkg=new JLabel();
    JLabel jl;//,display=new JLabel();
    JFrame frame;
    NewOrSavedGame ns=new NewOrSavedGame();
    public void gameover(Board b,String x,boolean n)
    {
        this.b=b;
        b.mframe.disable();
        
        if(!n)
            bkg.setIcon(ii);
        else
            bkg.setIcon(ii1);
       frame=new JFrame("Chess Reloded v_1.0 Beta");   
       panel=new JPanel();        
       panel.setLayout(null);        
       continu=new JButton();     
       exit=new JButton();    
       jl=new JLabel(x);
       continu.setActionCommand("continue");
       exit.setActionCommand("Exit");
       exit.addActionListener(this);
       continu.addActionListener(this);
       jl.setBounds(5,5,600,100);
       exit.setIcon(ii3);
       
       exit.setBounds(100,560,250,40);
       continu.setIcon(ii2);
       continu.setBounds(100,500,250,40);
       jl.setForeground(Color.red);   
       jl.setFont(new Font("Monotype Corsiva",Font.ITALIC,40));
       Font f=new Font("Monotype Corsiva",Font.ITALIC,40);       
       panel.add(jl,0,0);      
       panel.add(exit);
       panel.add(continu);      
       
       bkg.setBounds(0,0,700,700);
       panel.add(bkg); 
       frame.add(panel);
       frame.setSize(700,700);
       frame.setVisible(true);
       
        frame.addWindowListener(new WindowAdapter()    {
            public void windowClosing(WindowEvent we)   {
                System.exit(0);
            }
        });
    }
      public void actionPerformed(ActionEvent e)
    { 
        String  s=e.getActionCommand();
        System.out.println(s);
        if(s=="continue")
            ns.newOrSavedGame(b);             
        else if(s=="Exit")        
            System.exit(0);
        b.mframe.enable();
        b.mframe.setVisible(false);
           frame.setVisible(false);        
    }
    
    URL getPieceImageUrl(String s1)    {
        URL  imageURL = getClass().getClassLoader().getResource("Pieces/"+s1+".png");        
        return imageURL;        
    }
}
