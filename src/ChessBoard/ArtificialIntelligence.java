package ChessBoard;
import java.awt.Color;
import java.net.URL;
import java.util.Random;
import javax.swing.ImageIcon;
public class ArtificialIntelligence {
    Board b;
    AiFirstClickHighlight afch=new AiFirstClickHighlight();;
    static int i,j,k,l;   
    String pieceColor,pieceName;    
    public void evaluate(Board b)
    {
        
        this.b=b;  
        
        k=-1; 
        randomMove();
        pieceColor=afch.colorId(i, j);
        pieceName=afch.nameId(i, j);
        b.scr.draw50++;
        if(b.cell[i][j].getIcon()!=null || pieceName.equals("p"))
                b.scr.draw50=0;
        if(b.passant && k==b.en1 && l==b.en2)
        {
            if(b.white)
                b.cell[k-1][l].setIcon(null);
            else
                b.cell[k+1][l].setIcon(null);
        }
        b.passant=false;
        if(pieceName.equals("p") && pieceColor.equals("b") && k==1)   {
            ImageIcon ii=new ImageIcon(getPieceImageUrl("bqq"));
            ii.setDescription("bqq");
            b.cell[k][l].setIcon(ii);
            b.cell[i][j].setIcon(null); 
            
        }
        else if(pieceName.equals("p") && pieceColor.equals("w") && k==8)  {
            ImageIcon ii=new ImageIcon(getPieceImageUrl("wqq"));
            ii.setDescription("wqq");
            b.cell[k][l].setIcon(ii);
            b.cell[i][j].setIcon(null); 
        }
        else    {
          if(pieceName.equals("p")) {
            if(b.white&&k-i==2) {
                try {
                    if((b.fch.nameId(k,l+1).equals("p")&&b.fch.colorId(k,l+1).equals("b")))    
                        b.scr.setEnPassantValues(-1,k,l);                    
                }   catch(Exception e)  {}
                try {
                    if(b.fch.nameId(k,l-1).equals("p")&&b.fch.colorId(k,l-1).equals("b")) 
                        b.scr.setEnPassantValues(-1,k,l);
                }   catch(Exception e)  {}
            }
            else if(!b.white&&i-k==2) {
                try {
                    if((b.fch.nameId(k,l+1).equals("p")&&b.fch.colorId(k,l+1).equals("w")))    
                        b.scr.setEnPassantValues(1,k,l);                    
                }   catch(Exception e)  {}
                try {
                    
                    if(b.fch.nameId(k,l-1).equals("p")&&b.fch.colorId(k,l-1).equals("w")) 
                        b.scr.setEnPassantValues(1,k,l);
                }   catch(Exception e)  {}
            }
        }
        
            if(pieceName.equals("k") && pieceColor.equals("b"))
            {
                Board.bk1=k;     
		Board.bk2=l;   
                b.brook1=false;
                b.brook2=false;
                if(j-l==-2)  {
               
                b.cell[i][j-1].setIcon(b.cell[i][8].getIcon());
                b.cell[i][8].setIcon(null);
                
                }
                if(j-l==2)  {

                     b.cell[i][j+1].setIcon(b.cell[i][1].getIcon());
                    b.cell[i][1].setIcon(null);
                    
                }                    
                
            }
            b.cell[k][l].setIcon(b.cell[i][j].getIcon());
            b.cell[i][j].setIcon(null);             
        } 
          
        if(b.scr.draw50==100)
        {
            System.out.print("Game Draw");
             b.go.gameover(b,"Game Drawn",false);
            return;
        }
    }
    void randomMove()
    {        
        byte [] name=null;
        String PieceColor=null;
        boolean val=false;       
        Random r=new Random();
        while(k==-1) {
            val=false;
            i=r.nextInt(8)+1;
            for(;!val;)  {
                for(j=1;j<=8&&!val;j++)   {
                    if(b.cell[i][j].getIcon()!=null)   {
                        name=((ImageIcon)b.cell[i][j].getIcon()).getDescription().getBytes();
                        PieceColor=new String(name,0,1);
                        if(PieceColor.equals("b"))  
                            val=true;
                    }
                }
                if(!val)
                    i=i%8+1;
            }
            j=r.nextInt(8)+1;
            val=false;
            for(int q=0;q<8 && val==false;q++) {
                System.out.print("egianl ");
                if(b.cell[i][j].getIcon()!=null)    {
                    name=((ImageIcon)b.cell[i][j].getIcon()).getDescription().getBytes();
                    PieceColor=new String(name,0,1);
                    System.out.println(" "+name);
                    if(PieceColor.equals("b"))  
                        val=true;
                }
                if(!val)
                    j=j%8+1;
            }
            System.out.println("i " + +i +"j " + +j);
            
            int m1=b.c1,m2=b.c2,tvc=b.twiceChecked;
            afch.resolve(b, name, i, j, this);
            b.c1=m1;
            b.c2=m2;
            b.twiceChecked=tvc;
            System.out.println("Looping here : "+k);
        }
    }
    URL getPieceImageUrl(String s1)    {
        URL  imageURL = getClass().getClassLoader().getResource("Pieces/"+s1+".png");        
        return imageURL;        
    }
    
}
