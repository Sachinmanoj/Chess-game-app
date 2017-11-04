package ChessBoard;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.*;

public class NewOrSavedGame extends JApplet implements ActionListener{
    Board b;
    ImageIcon ii=new ImageIcon(getPieceImageUrl("background"));
    ImageIcon ng=new ImageIcon(getPieceImageUrl("newgame"));
    ImageIcon sg=new ImageIcon(getPieceImageUrl("savedgame"));
    JButton newGame,savedGame;
    JPanel panel;
    JLabel bkg=new JLabel();
    JLabel jl=new JLabel("Enter your choice:");//,display=new JLabel();
    JFrame frame;
    WelcomeScreen ws=new WelcomeScreen();
    public void newOrSavedGame(Board brd)
    {         
       this.b=brd;     
       bkg.setIcon(ii);
       frame=new JFrame("Chess Reloded v_1.0 Beta");   
       panel=new JPanel();        
       panel.setLayout(null);        
       
       newGame=new JButton();     
       savedGame=new JButton();    
       newGame.setIcon(ng);
       savedGame.setIcon(sg);
       newGame.setActionCommand("New Game");
       savedGame.setActionCommand("Saved Game");
       savedGame.addActionListener(this);
       newGame.addActionListener(this);
       jl.setBounds(0,0,400,40);
       savedGame.setBounds(100,50,250,40);
       newGame.setBounds(100,100,250,40);
          
       jl.setFont(new Font("Monotype Corsiva",Font.ITALIC,20));
       Font f=new Font("Monotype Corsiva",Font.ITALIC,40);       
       panel.add(jl,0,0);      
       panel.add(savedGame);
       panel.add(newGame);      
       
       bkg.setBounds(0,0,700,700);
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
        b.ngorsg=1;
    }
  
     public void actionPerformed(ActionEvent e)
    { 
        String  s=e.getActionCommand();
        System.out.println(s);
        if(s=="New Game")
            b.ngorsg=0;             
        else if(s=="Saved Game")        
            b.ngorsg=1;   
        
        if(b.ngorsg==0)
             ws.InitiateGame(b);
        else
            b.launch(b);
       frame.setVisible(false);        
    }
     URL getPieceImageUrl(String s1)    {
        URL  imageURL = getClass().getClassLoader().getResource("Pieces/"+s1+".png");        
        return imageURL;        
    }
}
