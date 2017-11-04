package ChessBoard;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.*;
public class SecondClickReplace  {
    int i,j,draw50=0,wx1=0,wx2=0,wy1=0,wy2=0,bx1=0,bx2=0,by1=0,by2=0,x=2;
    Board b;
    PawnChoice pc=new PawnChoice();
    FlipBoard fb=new FlipBoard();
    ArtificialIntelligence ai;
    AudioClip ac;
    URL acpath;
    static boolean goneToPawnChoiceMenu=false;
    public void replace(Board b,byte oldname[],int k,int l,int i,int j,FirstClickHighlight fc,boolean en,ArtificialIntelligence ai) {     
        this.ai=ai;
        this.b=b;        
        draw50++;
        goneToPawnChoiceMenu=false;
        b.passant=false;
        this.b=b;
        this.i=i;
        this.j=j;
        if(new String(oldname,0,1).equals("w"))
            b.white=false;
       	else
            b.white=true;
        String s1=new String(oldname,0,3);
        URL  imageURL = getClass().getClassLoader().getResource("Pieces/"+s1+".png");  
        ImageIcon ii=new ImageIcon(imageURL);
        ii.setDescription(s1);
        if(b.cell[i][j].getIcon()!=null )
            x=2;
        else if(b.DS!=0)
        {
            if(new String(oldname,0,1).equals("w"))
            {
                if(wx1==i && wy1==j && wx2==k && wy2==l)
                    x++;
                else
                    x=2;
                wx2=i;wx1=k;wy2=j;wy1=l;
            } 
            else
            {
                if(bx1==i && by1==j && bx2==k && by2==l)
                    x++;
                else
                    x=2;
                bx2=i;bx1=k;by2=j;by1=l;
            }
        }
        
        if(en)
        {
            if(!b.white)
                b.cell[i-1][j].setIcon(null);
            else
                b.cell[i+1][j].setIcon(null);
        }
        else
            if(b.cell[i][j].getIcon()!=null || new String(oldname,1,1).equals("p"))
                draw50=0;
        b.cell[k][l].setIcon(null);
        b.cell[i][j].setIcon(ii);
                
        if(new String(oldname,1,1).equals("k")) {
            if(new String(oldname,0,1).equals("w")) {       
                Board.wk1=i;     
		Board.wk2=j; 
                b.wrook1=false;
                b.wrook2=false;
            }	
            else if(new String(oldname,0,1).equals("b"))    {	
		Board.bk1=i;     
		Board.bk2=j;     
                b.brook1=false;
                b.brook2=false;
            }
            if(j-l==2)  {
                ii=(ImageIcon)b.cell[i][8].getIcon();
                b.cell[i][8].setIcon(null);
                b.cell[i][j-1].setIcon(ii);
            }
            if(j-l==-2)  {
                ii=(ImageIcon)b.cell[i][1].getIcon();
                b.cell[i][1].setIcon(null);
                b.cell[i][j+1].setIcon(ii);
            }                    
        }
        else if(new String(oldname,1,1).equals("r")) {
            if(new String(oldname,0,1).equals("w")) {
                if(new String(oldname,2,1).equals("1"))
                        b.wrook1=false;
                else
                       b.wrook2=false;
            }
            if(new String(oldname,0,1).equals("b")) {
                if(new String(oldname,2,1).equals("1"))
                        b.brook1=false;
                else
                       b.brook2=false;
            }
        }
        else if(new String(oldname,1,1).equals("p")) {
            if(!b.white&&i-k==2) {
                try {
                    
                    if((fc.nameId(i,j+1).equals("p")&&fc.colorId(i,j+1).equals("b")))    
                        setEnPassantValues(-1,i,j);                    
                }   catch(Exception e)  {}
                try {
                    if(fc.nameId(i,j-1).equals("p")&&fc.colorId(i,j-1).equals("b")) 
                        setEnPassantValues(-1,i,j);
                }   catch(Exception e)  {}
            }
            else if(b.white&&k-i==2) {
                try {
                    
                    if((fc.nameId(i,j+1).equals("p")&&fc.colorId(i,j+1).equals("w")))    {
                        setEnPassantValues(1,i,j);    
                    
                }
                }   catch(Exception e)  {}
                try {
                    if(fc.nameId(i,j-1).equals("p")&&fc.colorId(i,j-1).equals("w")) 
                        setEnPassantValues(1,i,j);
                }   catch(Exception e)  {}
            }
            
            if(i==8){
                   
                    pc.pawnChoice(b,k,l,i,j,this);
                    
            }
            else if(i==1){
                
                pc.pawnChoice(b,k,l,i,j,this);
            }            
        }
        b.kdanger=false;
        if(draw50==100 || x==9)
        {
            System.out.print("Game draw");  
            b.go.gameover(b,"Game Drawn",false);
            return;
        }
        System.out.print(""+b.white);
        b.checkForStaleMate();
        if(b.stale)
        {
            System.out.println("Stalemate Draw");
            b.go.gameover(b,"Stalemate Draw",false);
            return;
        }
        b.stale=false;
        b.refresh();
        if(b.DS==0&&goneToPawnChoiceMenu==false)
        {
            
            b.kdanger=b.makeKingOrange();
            System.out.println("balu"+b.kdanger);
            if(b.kdanger) {
                b.checkForCheckmate();
                b.setValues();
                 System.out.println("ben  "+b.checkmate);
                    b.cell[b.k1][b.k2].setBackground(Color.orange);  
                    if(b.checkmate)
                    {
                        b.cell[b.k1][b.k2].setBackground(Color.red);  
                        System.out.print("Checkmate");
                            b.go.gameover(b,"Player Won",true);
                            b.white=!b.white;
                            return;
                                           
                    }                 
            }           
            ai.evaluate(b);
            b.white=!b.white;
            b.checkForStaleMate();
            System.out.println("\nThala pola varuma\n"+b.white);
            b.refresh();
            b.cell[ai.i][ai.j].setBackground(Color.yellow);
            b.cell[ai.k][ai.l].setBackground(Color.cyan);
            if(b.stale)
            {
                System.out.println("Stalemate Draw");
                b.go.gameover(b,"Stalemate Draw",false);
            return;
            }
            b.stale=false;
            b.escape=false;
        }
        
    }
    public void playSound(String s){
        acpath=getClass().getClassLoader().getResource("Sound/"+s+".wav");
        ac=Applet.newAudioClip(acpath);
        ac.play();
    }
    public void setEnPassantValues(int a,int i,int j)   {
        b.en1=i+a;
        b.en2=j;
        
        b.passant=true;
    }
}
