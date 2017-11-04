package ChessBoard;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.URL;
public class ClosingPrompt extends JApplet implements ActionListener{
    Board b;
    JFrame frame;
    JPanel panel;  
    JButton Save_and_Quit,Quit,Cancel;
    JLabel jl1;
    JLabel bkg=new JLabel();
    boolean etemp;
    ImageIcon ii=new ImageIcon(getPieceImageUrl("background"));
    ImageIcon saveandquit=new ImageIcon(getPieceImageUrl("saveandquit"));
    ImageIcon quit=new ImageIcon(getPieceImageUrl("quit"));
    ImageIcon cancel=new ImageIcon(getPieceImageUrl("cancel"));
    public void closingPrompt(Board b)
    {
        this.b=b;
        etemp=false;
        bkg.setIcon(ii);
        try{saveGame();}catch(Exception e){}
        frame=new JFrame("Chess Reloaded : Closing Prompt");
        panel=new JPanel();       
        jl1=new JLabel("Enter your choice: ");   
        panel.setBackground(Color.WHITE);     
        panel.setLayout(null);
        Save_and_Quit=new JButton(saveandquit);
        Quit=new JButton(quit);      
        Cancel=new JButton(cancel); 
        Save_and_Quit.setActionCommand("Save and Quit");
        Quit.setActionCommand("Quit");
        Cancel.setActionCommand("Cancel");
        Save_and_Quit.setBounds(10,50,120,50);
        Quit.setBounds(140,50,120,50);
        Cancel.setBounds(270,50,120,50);
        jl1.setFont(new Font("Monotype Corsiva",Font.ITALIC,20));
        jl1.setBounds(0,0,200,20);    
        Save_and_Quit.addActionListener(this);
        Quit.addActionListener(this);
        Cancel.addActionListener(this);        
        panel.add(Save_and_Quit);
        panel.add(Quit);      
        panel.add(Cancel);
        panel.add(jl1); 
        bkg.setBounds(0,0,750,750);
        panel.add(bkg);  
        frame.add(panel);       
        frame.setSize(420,300);
        frame.setVisible(true);        
        frame.addWindowListener(new WindowAdapter()    {
            public void windowClosing(WindowEvent we)   {
                etemp=true;
                
                try{ saveGame(); }catch(Exception e){}                
            }
        });             
    }
    public void actionPerformed (ActionEvent ae)
    {
        String s=ae.getActionCommand();
        
        if(s.equals("Save and Quit"))                     
        {
            etemp=true;
            
            try{ saveGame(); }catch(Exception e){}            
        }
        else if(s.equals("Quit"))
        {
            
             System.exit(0);
        }
        else if(s.equals("Cancel"))
        {
            frame.setVisible(false);
              b.mframe.setVisible(true);
        }
    }
    public void saveGame()throws IOException
    {
     if(etemp==true)  
     {
        byte []temp;
        String s,s1;
        FileWriter fw=new FileWriter("SavedGame.chr");
        for(int i=1;i<=8;i++)
            for(int j=1;j<=8;j++)
            {
                if(b.cell[i][j].getIcon()!=null)
                {
                    temp=((ImageIcon)b.cell[i][j].getIcon()).getDescription().getBytes();
                    s=new String(temp);
                    fw.write(s+"\n");
                }
                else
                    fw.write("null\n");
            }        
        fw.write(""+b.k+"\n");
        fw.write(""+b.l+"\n");
        fw.write(""+b.wk1+"\n");
        fw.write(""+b.wk2+"\n");
        fw.write(""+b.bk1+"\n");
        fw.write(""+b.bk2+"\n");
        fw.write(""+b.c1+"\n");
        fw.write(""+b.c2+"\n");
        fw.write(""+b.k1+"\n");
        fw.write(""+b.k2+"\n");
        fw.write(""+b.tr1+"\n");
        fw.write(""+b.tr2+"\n");
        fw.write(""+b.en1+"\n");
        fw.write(""+b.en2+"\n");
        fw.write(""+b.twiceChecked+"\n");
        fw.write(""+b.pawnconv+"\n"); 
        
        fw.write(""+b.white+"\n");
        fw.write(""+b.checkmate+"\n");
        fw.write(""+b.kdanger+"\n");
        fw.write(""+b.passant+"\n");
        fw.write(""+b.wrook1+"\n");
        fw.write(""+b.wrook2+"\n");
        fw.write(""+b.brook1+"\n");
        fw.write(""+b.brook2+"\n");   
        fw.write(""+b.DS+"\n");
        fw.close();        
        FileReader fr=new FileReader("SavedGame.chr");
        BufferedReader br=new BufferedReader(fr);
        /*while((s1=br.readLine())!=null)
        {
            System.out.println(s1);
        }*/
        fr.close();
        
             System.exit(0);      
     }
    }
     URL getPieceImageUrl(String s1)    {
        URL  imageURL = getClass().getClassLoader().getResource("Pieces/"+s1+".png");        
        return imageURL;        
    }
}
  

