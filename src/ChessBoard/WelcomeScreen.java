package ChessBoard;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.*;

public class WelcomeScreen extends JApplet implements ActionListener{
    Board b;
    ImageIcon ii=new ImageIcon(getPieceImageUrl("background"));
    ImageIcon sp=new ImageIcon(getPieceImageUrl("singleplayer"));
    ImageIcon dp=new ImageIcon(getPieceImageUrl("doubleplayer"));
    JLabel bkg=new JLabel();
    JButton doublePlayer,singlePlayer;
    JPanel panel;
    JLabel jl=new JLabel("Enter your choice:");//,display=new JLabel();
    JFrame frame;
    public void InitiateGame(Board brd)
    {         
       this.b=brd;
       bkg.setIcon(ii);
       frame=new JFrame("Chess Reloded v_1.0 Beta");   
       panel=new JPanel();
       panel.setLayout(null);        
       doublePlayer=new JButton(dp);
       singlePlayer=new JButton(sp); 
       singlePlayer.setActionCommand("Single Player");
       doublePlayer.setActionCommand("Double Player");
       singlePlayer.addActionListener(this);
       doublePlayer.addActionListener(this);     
       jl.setBounds(0,0,400,40);
       singlePlayer.setBounds(100,50,250,40);
       doublePlayer.setBounds(100,100,250,40);     
       jl.setFont(new Font("Monotype Corsiva",Font.ITALIC,20)); 
       panel.add(jl);
       panel.add(singlePlayer);
       panel.add(doublePlayer); 
       bkg.setBounds(0,0,750,750);
       panel.add(bkg);  
       frame.add(panel);
       frame.setSize(700,700);
       frame.setVisible(true);
       b.DS=1;        
        frame.addWindowListener(new WindowAdapter()    {
            public void windowClosing(WindowEvent we)   {
               System.exit(0);
            }
        });
    }   
     public void actionPerformed(ActionEvent e)
    { 
        String s1=e.getActionCommand();
        if(s1.equals("Single Player"))        
            b.DS=0;        
        else if(s1.equals("Double Player"))        
            b.DS=1;              
        b.launch(b);
        frame.setVisible(false);        
    }
    URL getPieceImageUrl(String s1)    {
        URL  imageURL = getClass().getClassLoader().getResource("Pieces/"+s1+".png");        
        return imageURL;        
    }
}

    

