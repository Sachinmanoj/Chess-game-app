
package ChessBoard;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;


public class MenuBarClass extends JApplet implements ActionListener {
    ImageIcon ii=new ImageIcon(getPieceImageUrl("background"));
    ImageIcon ef=new ImageIcon(getPieceImageUrl("exitflip"));
    ImageIcon creditsImage=new ImageIcon(getPieceImageUrl("credits"));
    ClosingPrompt cp=new ClosingPrompt();
    NewOrSavedGame nosg=new NewOrSavedGame();
    Board b;
    FlipBoard fb=new FlipBoard();
    JFrame frame=new JFrame("Flipped Preview");
    JFrame creditsFrame=new JFrame("Credits");
    JPanel panel=new JPanel();
    JPanel creditsPanel=new JPanel();
    JButton exitFlip=new JButton(ef);
    JLabel jl=new JLabel("Flipped preview..");
    JLabel bkg=new JLabel(ii); 
    JLabel creditsLabel=new JLabel(creditsImage);
    
    
     public void Menu(final Board b)
	{
                this.b=b;
		//File Menu
                JMenuBar mb=new JMenuBar();
                b.mframe.setJMenuBar(mb);
                
                exitFlip.setActionCommand("exitFlip");
                exitFlip.addActionListener(this);
                
		JMenu file = new JMenu("File");
		JMenuItem exit = new JMenuItem("Exit");
		exit.setActionCommand("exit");	
                exit.addActionListener(this);
		file.add(exit);
		//Game Menu
		JMenu game = new JMenu("Game");
		JMenuItem newGame = new JMenuItem("New Game");
		newGame.setActionCommand("newGame");
		newGame.addActionListener(this);
		game.add(newGame);
		//Options Menu
		JMenu options = new JMenu("Options");
		JMenuItem flip = new JMenuItem("Flip board");
		flip.setActionCommand("flip");
		flip.addActionListener(this);
		options.add(flip);		
		//Help Menu
		JMenu help = new JMenu("Help");
		JMenuItem rules = new JMenuItem("Rules!");
		rules.setActionCommand("rules");
		rules.addActionListener(this);
                JMenuItem credits=new JMenuItem("Credits");
                credits.setActionCommand("credits");
                credits.addActionListener(this);
                help.add(credits);
		help.add(rules);
                
		mb.add(file);
		mb.add(game);
		mb.add(options);
		mb.add(help);
                
                frame.addWindowListener(new WindowAdapter()    {
                public void windowClosing(WindowEvent we)   {                
                fb.flipBoard(b);
                b.mframe.enable();
                frame.setVisible(false);
                    }
                });
                creditsFrame.addWindowListener(new WindowAdapter()    {
                public void windowClosing(WindowEvent we)   {  
                b.mframe.enable();
                creditsFrame.setVisible(false);
                    }
                });
                
	}
     public void actionPerformed(ActionEvent ae)
     {
         String s=ae.getActionCommand();
         if(s.equals("exit"))
         {            
             b.mframe.setVisible(false);
             cp.closingPrompt(b);        
         }
         else if(s.equals("newGame"))
         {
             b.mframe.setVisible(false);
             nosg.newOrSavedGame(b);
         }  
         else if(s.equals("flip"))
         {
             panel.setLayout(null); 
             b.refresh();
             fb.flipBoard(b);   
             b.mframe.disable();
             jl.setBounds(0,0,200,40);
             jl.setFont(new Font("Monotype Corsiva",Font.ITALIC,20)); 
             panel.add(jl);
             exitFlip.setBounds(40,50,100,40);
             panel.add(exitFlip);
             bkg.setBounds(0,0,700,700);
             panel.add(bkg);
             frame.add(panel);
             frame.setSize(200,200);
             frame.setVisible(true);
         }else if(s.equals("exitFlip"))
         {
             fb.flipBoard(b);
             b.mframe.enable();
             frame.setVisible(false);
         }
         else if(s.equals("credits"))
         {
             creditsPanel.setLayout(null);
             b.mframe.disable();
             creditsLabel.setBounds(0,0,700,700);
             creditsPanel.add(creditsLabel);
             creditsFrame.add(creditsPanel);
             creditsFrame.setSize(700,700);
             creditsFrame.setVisible(true);
         }
     }   
    URL getPieceImageUrl(String s1)    {
        URL  imageURL = getClass().getClassLoader().getResource("Pieces/"+s1+".png");        
        return imageURL;        
    }
     
}
