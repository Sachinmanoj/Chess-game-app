package ChessBoard;
import javax.swing.*;
import java.net.URL;
import java.awt.*;
import java.awt.event.*;


public class PawnChoice extends JApplet implements ActionListener{
    Board b;
    JRadioButton queen,rook,bishop,knight;
    JButton ok;
    ButtonGroup bg;
    JPanel pawnPanel;
    JLabel jl,display;
    String PieceType,PieceColor;
    int k,l,i,j;
    ImageIcon ii;
    JFrame pframe;
    SecondClickReplace scr;
    public void pawnChoice(Board b,int k,int l,int i,int j,SecondClickReplace scr)
    {
        scr.goneToPawnChoiceMenu=true;
        this.i=i; this.k=k;
        this.j=j; this.l=l;
        this.b=b;
        this.scr=scr;
        jl=new JLabel("Enter your choice:");
        display=new JLabel();
        pframe=new JFrame("Enter your choice...");
        ok=new JButton("Okay");
        queen=new JRadioButton("Queen");
        rook=new JRadioButton("Rook");
        bishop=new JRadioButton("Bishop");
        knight=new JRadioButton("Knight");
        bg=new ButtonGroup();
        pawnPanel=new JPanel();
        pawnPanel.setBackground(Color.WHITE);
        Font f=new Font("Monotype Corsiva",Font.ITALIC,40);
        
        pawnPanel.setLayout(null);   
        queen.addActionListener(this);
        rook.addActionListener(this);
        bishop.addActionListener(this);
        knight.addActionListener(this);  
        ok.addActionListener(this);
        
        queen.setFont(f);
        rook.setFont(f);
        bishop.setFont(f);
        knight.setFont(f);
        jl.setFont(new Font("Monotype Corsiva",Font.ITALIC,20));
        
        
        bg.add(queen);
        bg.add(rook);
        bg.add(bishop);
        bg.add(knight); 
        
        
        jl.setBounds(0,0,400,40);
        queen.setBounds(0,50,200,40);
        rook.setBounds(0,100,200,40);
        bishop.setBounds(201,50,200,40);
        knight.setBounds(201,100,200,40); 
        ok.setBounds(201,200,100,30);
        display.setBounds(30,160,90,90);
        
        pawnPanel.add(jl); 
        pawnPanel.add(queen);
        pawnPanel.add(rook);
        pawnPanel.add(bishop);
        pawnPanel.add(knight); 
        pawnPanel.add(ok);
        pawnPanel.add(display);
   
        if(!b.white)
            PieceColor="w";
        else
            PieceColor="b";
        PieceType=PieceColor+"qq";
        
        pframe.add(pawnPanel);
        pframe.setSize(420,300);
        b.mframe.disable();
        pframe.setVisible(true);
        
        displayPiece("qq");
        
        pframe.addWindowListener(new WindowAdapter()    {
            public void windowClosing(WindowEvent we)   {
                defaultChoice();
            }
        });
    }
  
    public void actionPerformed(ActionEvent e)
    { 
        String s1=e.getActionCommand();
        if(s1.equals("Okay"))
        {
            System.out.println("Hello ben");
            defaultChoice();            
        }
        else if(s1.equals("Queen"))
        {
            PieceType=PieceColor+"qq";
            displayPiece("qq");
        }
        else if(s1.equals("Rook"))
        {
            PieceType=PieceColor+"r1";
            displayPiece("r1");
        }
        else if(s1.equals("Bishop"))
        {
            PieceType=PieceColor+"b1";
            displayPiece("b1");
        }
        else
        {
            PieceType=PieceColor+"h1";
            displayPiece("h1");
        }    
      
    }
    
    public void defaultChoice() {
        URL  imageURL = getClass().getClassLoader().getResource("Pieces/"+PieceType+".png");  
        ii=new ImageIcon(imageURL);
        ii.setDescription(PieceType);
        b.cell[k][l].setIcon(null);
        b.cell[i][j].setIcon(ii);   
        pframe.setVisible(false);
        b.mframe.enable();
        b.mframe.setVisible(true);
        scr.playSound("evilLaugh");
        b.kdanger=false;
        System.out.println("Hello bond1");
        b.kdanger=b.makeKingOrange();
        System.out.println("Hello bond2");
        if(b.kdanger) {
            b.checkForCheckmate();
            b.setValues();
                b.cell[b.k1][b.k2].setBackground(Color.orange);  
                if(b.checkmate)
                {
                    b.cell[b.k1][b.k2].setBackground(Color.red);  
                    System.out.print("Checkmate");
                    if(b.DS!=0 && !b.white)
                            b.go.gameover(b,"Player White Won",true);
                        else if(b.DS!=0)
                            b.go.gameover(b,"Player Black Won",true);
                        else
                        {
                            b.go.gameover(b,"Player Won",true);
                            b.white=!b.white;
                        }
                    return;
                   // b.mframe.disable();
                }                 
         }
          
          if(scr.b.DS==0)    {
             
             scr.ai.evaluate(b);
             scr.b.white=!scr.b.white;
             b.checkForStaleMate();
            System.out.println("\nThala pola varuma\n"+b.white);
            b.refresh();
            b.cell[scr.ai.i][scr.ai.j].setBackground(Color.yellow);
            b.cell[scr.ai.k][scr.ai.l].setBackground(Color.cyan);
            if(b.stale)
            {
                System.out.println("Stalemate Draw");
                 b.go.gameover(b,"Stalemate Draw",false);
            return;
            }
            b.stale=false;
             b.escape=false;
             
             b.kdanger=b.makeKingOrange();   
                if(b.kdanger)
                {
                    System.out.println(" s "+b.twiceChecked);
                    b.checkForCheckmate();
                    b.setValues();
                    b.cell[b.k1][b.k2].setBackground(Color.orange);  
                    scr.playSound("kingMadeOrange");
                    scr.playSound("kingChecked");
                    if(b.checkmate)
                    {                    
                        b.cell[b.k1][b.k2].setBackground(Color.red);  
                        System.out.print("Checkmate");  
                        b.go.gameover(b,"System Won",true);
                        return;
                    }                  
                    System.out.println(" s "+b.twiceChecked);
                }
            }
          
         
    }
    public void displayPiece(String s1)
    {
        URL  imageURL = getClass().getClassLoader().getResource("Pieces/"+PieceColor+s1+".png");  
        ii=new ImageIcon(imageURL);        
        display.setIcon(ii);
    }  
}
