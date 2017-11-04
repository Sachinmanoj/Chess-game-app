package ChessBoard;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
public class ConstructBoard extends JApplet {
    Board b;
    public void constructNewGameBoard(Board b)
    {
        this.b=b;
        b.cell[1][1]=new JButton(b.wr1);b.wr1.setDescription("wr1");
       	b.cell[1][2]=new JButton(b.wh1);b.wh1.setDescription("wh1");
       	b.cell[1][3]=new JButton(b.wb1);b.wb1.setDescription("wb1");
       	b.cell[1][4]=new JButton(b.wqq);b.wqq.setDescription("wqq");
       	b.cell[1][5]=new JButton(b.wkk);b.wkk.setDescription("wkk");
       	b.cell[1][6]=new JButton(b.wb2);b.wb2.setDescription("wb2");
       	b.cell[1][7]=new JButton(b.wh2);b.wh2.setDescription("wh2");
       	b.cell[1][8]=new JButton(b.wr2);b.wr2.setDescription("wr2");
       	b.cell[2][1]=new JButton(b.wp1);b.wp1.setDescription("wp1");
       	b.cell[2][2]=new JButton(b.wp2);b.wp2.setDescription("wp2");
       	b.cell[2][3]=new JButton(b.wp3);b.wp3.setDescription("wp3");
       	b.cell[2][4]=new JButton(b.wp4);b.wp4.setDescription("wp4");
       	b.cell[2][5]=new JButton(b.wp5);b.wp5.setDescription("wp5");
       	b.cell[2][6]=new JButton(b.wp6);b.wp6.setDescription("wp6");
       	b.cell[2][7]=new JButton(b.wp7);b.wp7.setDescription("wp7");
       	b.cell[2][8]=new JButton(b.wp8);b.wp8.setDescription("wp8");
        b.cell[8][1]=new JButton(b.br1);b.br1.setDescription("br1");
        b.cell[8][2]=new JButton(b.bh1);b.bh1.setDescription("bh1");
        b.cell[8][3]=new JButton(b.bb1);b.bb1.setDescription("bb1");
        b.cell[8][4]=new JButton(b.bqq);b.bqq.setDescription("bqq");
        b.cell[8][5]=new JButton(b.bkk);b.bkk.setDescription("bkk");
        b.cell[8][6]=new JButton(b.bb2);b.bb2.setDescription("bb2");
        b.cell[8][7]=new JButton(b.bh2);b.bh2.setDescription("bh2");
        b.cell[8][8]=new JButton(b.br2);b.br2.setDescription("br2");
        b.cell[7][1]=new JButton(b.bp1);b.bp1.setDescription("bp1");
        b.cell[7][2]=new JButton(b.bp2);b.bp2.setDescription("bp2");
        b.cell[7][3]=new JButton(b.bp3);b.bp3.setDescription("bp3");
        b.cell[7][4]=new JButton(b.bp4);b.bp4.setDescription("bp4");
        b.cell[7][5]=new JButton(b.bp5);b.bp5.setDescription("bp5");
        b.cell[7][6]=new JButton(b.bp6);b.bp6.setDescription("bp6");
        b.cell[7][7]=new JButton(b.bp7);b.bp7.setDescription("bp7");
        b.cell[7][8]=new JButton(b.bp8);b.bp8.setDescription("bp8");
        b.wk1=1;
        b.wk2=5;
        b.bk1=8;
        b.bk2=5;   
        for(int i=1;i<=8;i++)
            for(int j=1;j<=8;j++)   {
                if(i!=1&&i!=2&&i!=7&&i!=8)  {
                    b.cell[i][j]=new JButton();            
               	}
                b.cell[i][j].setActionCommand(""+i+j);                         
               	b.board.add(b.cell[i][j]);              
            }
    }
    
    public void constructSavedGameBoard(Board b)throws IOException
    {
        this.b=b;
        String s;
        FileReader fr=new FileReader("SavedGame.chr");
        BufferedReader br=new BufferedReader(fr);        
        for(int i=1;i<=8;i++)
            for(int j=1;j<=8;j++)
            {
                s=br.readLine(); 
                if(s.equals("null"))                 
                    b.cell[i][j]=new JButton();                
                else    
                {   
                    if(s.equals("wr1"))
                       { b.cell[i][j]=new JButton(b.wr1);b.wr1.setDescription("wr1"); }                  
                    else if(s.equals("wh1"))
                       { b.cell[i][j]=new JButton(b.wh1);b.wh1.setDescription("wh1");}
                    else if(s.equals("wb1"))
                       { b.cell[i][j]=new JButton(b.wb1);b.wb1.setDescription("wb1"); }
                    else if(s.equals("wqq"))
                       { b.cell[i][j]=new JButton(b.wqq);b.wqq.setDescription("wqq");}
                    else if(s.equals("wkk"))
                       { b.cell[i][j]=new JButton(b.wkk);b.wkk.setDescription("wkk");}
                    else if(s.equals("wb2"))
                       { b.cell[i][j]=new JButton(b.wb2);b.wb2.setDescription("wb2");}
                    else if(s.equals("wh2"))
                       { b.cell[i][j]=new JButton(b.wh2);b.wh2.setDescription("wh2");}
                    else if(s.equals("wr2"))
                       { b.cell[i][j]=new JButton(b.wr2);b.wr2.setDescription("wr2");}
                    else if(s.equals("wp1"))
                       { b.cell[i][j]=new JButton(b.wp1);b.wp1.setDescription("wp1");}
                    else if(s.equals("wp2"))
                       { b.cell[i][j]=new JButton(b.wp2);b.wp2.setDescription("wp2");}
                    else if(s.equals("wp3"))
                       { b.cell[i][j]=new JButton(b.wp3);b.wp3.setDescription("wp3");}
                    else if(s.equals("wp4"))
                       { b.cell[i][j]=new JButton(b.wp4);b.wp4.setDescription("wp4");}
                    else if(s.equals("wp5"))
                       { b.cell[i][j]=new JButton(b.wp5);b.wp5.setDescription("wp5");}
                    else if(s.equals("wp6"))
                       { b.cell[i][j]=new JButton(b.wp6);b.wp6.setDescription("wp6");}
                    else if(s.equals("wp7"))
                       { b.cell[i][j]=new JButton(b.wp7);b.wp7.setDescription("wp7");}
                    else if(s.equals("wp8"))
                       { b.cell[i][j]=new JButton(b.wp8);b.wp8.setDescription("wp8");}
                    else if(s.equals("br1"))
                       { b.cell[i][j]=new JButton(b.br1);b.br1.setDescription("br1");}
                    else if(s.equals("bh1"))
                       { b.cell[i][j]=new JButton(b.bh1);b.bh1.setDescription("bh1");}
                    else if(s.equals("bb1"))
                       { b.cell[i][j]=new JButton(b.bb1);b.bb1.setDescription("bb1");}
                    else if(s.equals("bqq"))
                       { b.cell[i][j]=new JButton(b.bqq);b.bqq.setDescription("bqq");}
                    else if(s.equals("bkk"))
                       { b.cell[i][j]=new JButton(b.bkk);b.bkk.setDescription("bkk");}
                    else if(s.equals("bb2"))
                       { b.cell[i][j]=new JButton(b.bb2);b.bb2.setDescription("bb2");}
                    else if(s.equals("bh2"))
                       { b.cell[i][j]=new JButton(b.bh2);b.bh2.setDescription("bh2");}
                    else if(s.equals("br2"))
                       { b.cell[i][j]=new JButton(b.br2);b.br2.setDescription("br2");}
                    else if(s.equals("bp1"))
                       { b.cell[i][j]=new JButton(b.bp1);b.bp1.setDescription("bp1");}
                    else if(s.equals("bp2"))
                       { b.cell[i][j]=new JButton(b.bp2);b.bp2.setDescription("bp2");}
                    else if(s.equals("bp3"))
                       { b.cell[i][j]=new JButton(b.bp3);b.bp3.setDescription("bp3");}
                    else if(s.equals("bp4"))
                       { b.cell[i][j]=new JButton(b.bp4);b.bp4.setDescription("bp4");}
                    else if(s.equals("bp5"))
                       { b.cell[i][j]=new JButton(b.bp5);b.bp5.setDescription("bp5");}
                    else if(s.equals("bp6"))
                       { b.cell[i][j]=new JButton(b.bp6);b.bp6.setDescription("bp6");}
                    else if(s.equals("bp7"))
                       { b.cell[i][j]=new JButton(b.bp7);b.bp7.setDescription("bp7");}
                    else if(s.equals("bp8"))
                       { b.cell[i][j]=new JButton(b.bp8);b.bp8.setDescription("bp8");}
                }
                b.cell[i][j].setActionCommand(""+i+j);
                b.board.add(b.cell[i][j]);            
            }          
        b.k=Integer.parseInt(br.readLine());
        b.l=Integer.parseInt(br.readLine());
        b.wk1=Integer.parseInt(br.readLine());
        b.wk2=Integer.parseInt(br.readLine());
        b.bk1=Integer.parseInt(br.readLine());
        b.bk2=Integer.parseInt(br.readLine());
        b.c1=Integer.parseInt(br.readLine());
        b.c2=Integer.parseInt(br.readLine());
        b.k1=Integer.parseInt(br.readLine());
        b.k2=Integer.parseInt(br.readLine());
        b.tr1=Integer.parseInt(br.readLine());
        b.tr2=Integer.parseInt(br.readLine());
        b.en1=Integer.parseInt(br.readLine());
        b.en2=Integer.parseInt(br.readLine());        
        b.twiceChecked=Integer.parseInt(br.readLine());
        b.pawnconv=Integer.parseInt(br.readLine());    
        
        b.white=resolveBoolean(br.readLine());
        b.checkmate=resolveBoolean(br.readLine());
        b.kdanger=resolveBoolean(br.readLine());
        b.passant=resolveBoolean(br.readLine());
        b.wrook1=resolveBoolean(br.readLine());
        b.wrook2=resolveBoolean(br.readLine());
        b.brook1=resolveBoolean(br.readLine());;
        b.brook2=resolveBoolean(br.readLine());;     
        b.DS=Integer.parseInt(br.readLine());
        fr.close();    
    }
    public boolean resolveBoolean(String s)
    {
        if(s.equals("true"))
            return true;
        else
            return false;
    }
}
