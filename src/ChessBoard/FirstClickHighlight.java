package ChessBoard;
import java.awt.*;
import javax.swing.ImageIcon;
public class FirstClickHighlight    {
    Board b;
    int i,j;
    String PieceColor,PieceType;
    boolean z;
    public void resolve(Board b,byte name[],int i,int j)    {
        this.i=i;
        this.j=j;
        this.b=b;
        z=true;
        boolean staleflag=false;
        PieceColor=new String(name,0,1);
        PieceType=new String(name,1,1);
        if(!b.stale)
            b.refresh();    
        if(PieceColor.equals("w")&&b.white==true)   {
            if(!b.stale)
            b.cell[i][j].setBackground(Color.yellow);
            if(PieceType.equals("p") && safeMove(true)&&b.twiceChecked<2) {
                
                    if(b.passant) {
                        
                        if(!b.kdanger)  {
                            
                            if(i+1==b.en1&&(j+1==b.en2||j-1==b.en2))    {   
                                 if(b.stale==true)
                                 {
                                     b.stale=false;
                                     return;
                                 }
                                 b.cell[b.en1][b.en2].setBackground(Color.BLUE);
                            }
                                
                        }
                        else
                            if(i+1==b.en1&&(j+1==b.en2||j-1==b.en2)&&(helpKing(b.en1,b.en2,b.c1,b.c2) || helpKing(i,j-1,b.c1,b.c2) || helpKing(i,j+1,b.c1,b.c2)))
                            {
                                if(b.stale==true)
                                 {
                                     b.stale=false;
                                     return;
                                 }
                                 b.cell[b.en1][b.en2].setBackground(Color.blue);
                            }
                    }
                    if(b.cell[i+1][j].getIcon()==null)  {
                    if(b.kdanger)   {
                        if(helpKing(i+1,j,b.c1,b.c2))   {
                            if(b.stale==true)
                                 {
                                     b.stale=false;
                                     return;
                                 }
                            b.cell[i+1][j].setBackground(Color.cyan);
                            
                        }
                    }
                    else if(!b.kdanger)
                    {
                        if(b.stale==true)
                                 {
                                     b.stale=false;
                                     return;
                                 }
                        b.cell[i+1][j].setBackground(Color.cyan);
                    }
                }
                if(i==2&&b.cell[i+1][j].getIcon()==null&&b.cell[i+2][j].getIcon()==null)    {
                    if(b.kdanger)   {
                        if(helpKing(i+2,j,b.c1,b.c2))   {
                            if(b.stale==true)
                                 {
                                     b.stale=false;
                                     return;
                                 }
                            b.cell[i+2][j].setBackground(Color.cyan);
                           
                        }
                    }
                    else
                    {
                        if(b.stale==true)
                                 {
                                     b.stale=false;
                                     return;
                                 }
                        b.cell[i+2][j].setBackground(Color.cyan);
                    }
                }
                if(!b.kdanger && z)  {
                    makeRed(i+1,j+1,true);
                    makeRed(i+1,j-1,true);     
                }
                
                else if(i+1==b.c1 && j+1==b.c2 && b.kdanger) 
                {
                    if(b.stale==true)
                                 {
                                     b.stale=false;
                                     return;
                                 }
                    b.cell[b.c1][b.c2].setBackground(Color.red);
                }
                else if(i+1==b.c1 && j-1==b.c2 && b.kdanger)    
                {
                    if(b.stale==true)
                                 {
                                     b.stale=false;
                                     return;
                                 }
                    b.cell[b.c1][b.c2].setBackground(Color.red);
                }
            }
            else if(PieceType.equals("r") && safeMove(true) && b.twiceChecked<2)
                rookHighlight(true);        
            else if(PieceType.equals("h") && safeMove(true) && b.twiceChecked<2)   
                knightHighlight(true);
            else if(PieceType.equals("b") && safeMove(true) && b.twiceChecked<2)   
                bishopHighlight(true);          
 
            else if(PieceType.equals("q") && safeMove(true) && b.twiceChecked<2)    {
                if(b.stale)
                    staleflag=true;
                bishopHighlight(true);
                if(!b.stale && staleflag)                
                    return;
                rookHighlight(true);      
                
            }
            else if(PieceType.equals("k"))  
                kingHighlight(i,j,Board.wk1,Board.wk2,Board.bk1,Board.bk2,true,"b",1);
        }
        else if(PieceColor.equals("b")&&b.white==false)     {
            if(!b.stale)
            b.cell[i][j].setBackground(Color.yellow);
            if(PieceType.equals("p") && safeMove(false) && b.twiceChecked<2) {
                if(b.cell[i-1][j].getIcon()==null)  {
                    if(b.passant) {
                        if(!b.kdanger)  {
                            if(i-1==b.en1&&(j+1==b.en2||j-1==b.en2))
                            {
                                if(b.stale==true)
                                 {
                                     b.stale=false;
                                     return;
                                 }
                                b.cell[b.en1][b.en2].setBackground(Color.BLUE);
                            }
                        }
                        else
                            if(i-1==b.en1&&(j+1==b.en2||j-1==b.en2)&&(helpKing(b.en1,b.en2,b.c1,b.c2) || helpKing(i,j-1,b.c1,b.c2) || helpKing(i,j+1,b.c1,b.c2)))
                            {
                             if(b.stale==true)
                                 {
                                     b.stale=false;
                                     return;
                                 }
                                b.cell[b.en1][b.en2].setBackground(Color.blue);
                            }
                    }
                    if(b.kdanger&&!b.passant)   {
                        if(helpKing(i-1,j,b.c1,b.c2))   {
                            if(b.stale==true)
                                 {
                                     b.stale=false;
                                     return;
                                 }
                            b.cell[i-1][j].setBackground(Color.cyan);
                           
                            
                        }
                    }
                    else if(!b.kdanger)
                    {
                        if(b.stale==true)
                                 {
                                     b.stale=false;
                                     return;
                                 }
                       b.cell[i-1][j].setBackground(Color.cyan);
                    }
                }
                if(i==7&&b.cell[i-1][j].getIcon()==null&&b.cell[i-2][j].getIcon()==null)    {
                    if(b.kdanger)   {
                        if(helpKing(i-2,j,b.c1,b.c2))   {
                            if(b.stale==true)
                                 {
                                     b.stale=false;
                                     return;
                                 }
                            b.cell[i-2][j].setBackground(Color.cyan);
                            
                        }
                    }
                    else
                    {
                        if(b.stale==true)
                                 {
                                     b.stale=false;
                                     return;
                                 }
                        b.cell[i-2][j].setBackground(Color.cyan);
                    }
                }
                if(!b.kdanger && z)  {
                    makeRed(i-1,j+1,false);
                    makeRed(i-1,j-1,false);     
                }
                else if(i-1==b.c1 && j+1==b.c2 && b.kdanger)
                {
                    if(b.stale==true)
                                 {
                                     b.stale=false;
                                     return;
                                 }
                    b.cell[b.c1][b.c2].setBackground(Color.red);
                }
                else if(i-1==b.c1 && j-1==b.c2 && b.kdanger)
                {
                    if(b.stale==true)
                                 {
                                     b.stale=false;
                                     return;
                                 }
                    b.cell[b.c1][b.c2].setBackground(Color.red);
                }
            }
            else if(PieceType.equals("r") && safeMove(false) && b.twiceChecked<2)   
                rookHighlight(false);
            else if(PieceType.equals("h") && safeMove(false) && b.twiceChecked<2)   
                knightHighlight(false);
            else if(PieceType.equals("b") && safeMove(false) && b.twiceChecked<2)   
                bishopHighlight(false);
            else if(PieceType.equals("q") && safeMove(false) && b.twiceChecked<2)   {
                if(b.stale)
                    staleflag=true;
                bishopHighlight(false);
                if(!b.stale && staleflag)
                    return;
                rookHighlight(false);
            }
            else if(PieceType.equals("k"))  
                kingHighlight(i,j,Board.bk1,Board.bk2,Board.wk1,Board.wk2,false,"w",-1);
        }
        this.i=this.j=-1;
    }
    
    void knightHighlight(boolean boo)   {
        int m=1,n=2,temp;
        for(int t=1;t<=4;t++)   {
            try {
                if(b.cell[i-m][j-n].getIcon()==null)  
                    if(b.kdanger)   {
                        if(helpKing(i-m,j-n,b.c1,b.c2))   {
                            if(b.stale==true)
                                 {
                                     b.stale=false;
                                     return;
                                 }
                            b.cell[i-m][j-n].setBackground(Color.CYAN);      
                            
                        }
                    }
                    else
                    {
                        if(b.stale==true)
                                 {
                                     b.stale=false;
                                     return;
                                 }
                        b.cell[i-m][j-n].setBackground(Color.CYAN); 
                    }
                else    {
                    if(!b.kdanger)
                          makeRed(i-m,j-n,boo);                        
                    else  if(b.kdanger&&b.c1==i-m&&b.c2==j-n)
                    {
                        if(b.stale==true)
                                 {
                                     b.stale=false;
                                     return;
                                 }
                        b.cell[b.c1][b.c2].setBackground(Color.RED);
                    }
                }                        
            }catch(Exception e) {}
            try {
                if(b.cell[i-n][j-m].getIcon()==null)  
                    if(b.kdanger)   {
                        if(helpKing(i-n,j-m,b.c1,b.c2))   {
                            if(b.stale==true)
                                 {
                                     b.stale=false;
                                     return;
                                 }
                            b.cell[i-n][j-m].setBackground(Color.CYAN);      
                            
                        }
                    }
                    else
                    {
                        if(b.stale==true)
                                 {
                                     b.stale=false;
                                     return;
                                 }
                        b.cell[i-n][j-m].setBackground(Color.CYAN); 
                    }
                else    {
                    if(!b.kdanger)
                          makeRed(i-n,j-m,boo);                        
                    else  if(b.kdanger&&b.c1==i-n&&b.c2==j-m)
                    {
                        if(b.stale==true)
                                 {
                                     b.stale=false;
                                     return;
                                 }
                        b.cell[b.c1][b.c2].setBackground(Color.RED);
                    }
                } 
            }catch(Exception e) {}
            temp=m;
            m=-n;
            n=temp;
        }
    }
    
    void bishopHighlight(boolean boo)   {
        int a=1,t=1,temp; 
        int x,y; 
        for(int z=0;z<4;z++)    {
            try {
                x=i+a;
                y=j+t;
                while(b.cell[x][y].getIcon()==null) {
                    if(b.kdanger)   {
                        if(helpKing(x,y,b.c1,b.c2))    {
                            if(b.stale==true)
                                 {
                                     b.stale=false;
                                     return;
                                 }
                            b.cell[x][y].setBackground(Color.cyan);
                            
                        }
                    }
                    else
                    {
                        if(b.stale==true)
                                 {
                                     b.stale=false;
                                     return;
                                 }
                         b.cell[x][y].setBackground(Color.cyan);
                    }
                    x+=a;  
                    y+=t;
                }
                if(!b.kdanger)
                    makeRed(x,y,boo);
                else if(b.c1==x&&b.c2==y)
                {
                    if(b.stale==true)
                                 {
                                     b.stale=false;
                                     return;
                                 }
                    b.cell[x][y].setBackground(Color.RED);
                }
            }catch(Exception e) {}
            temp=a;
            a=-t;
            t=temp;         
        }
    }
	
    void kingHighlight(int i,int j,int k1,int k2,int ok1,int ok2,boolean boo,String pc,int r)   {
        int m=1,n=1,l=0,t=1,temp;
        b.checkmate=true;
        for(int z=0;z<4;z++)    {
            try {
                
                if(setKingDanger(i+m,j+n,k1,k2,ok1,ok2,pc,r))    {
                    if(b.cell[i+m][j+n].getIcon()==null)    {
                        if(b.stale==true)
                                 {
                                     b.stale=false;
                                     return;
                                 }
                        b.cell[i+m][j+n].setBackground(Color.cyan);
                         b.checkmate=false;
                    }
                    else
                       makeRed(i+m,j+n,boo);
                        
                  
               }
                
            } catch(Exception e) {}
            try {
                
                if(setKingDanger(i+l,j+t,k1,k2,ok1,ok2,pc,r))    {
                    if(b.cell[i+l][j+t].getIcon()==null)    {
                        if(b.stale==true)
                                 {
                                     b.stale=false;
                                     return;
                                 }
                        b.cell[i+l][j+t].setBackground(Color.cyan);
                        b.checkmate=false;
                    }
                    else
                        makeRed(i+l,j+t,boo);
                     
                }
               
            } catch(Exception e) {}
            temp=m;                    					
            m=-n;
            n=temp;
            temp=l;                    					
            l=-t;
            t=temp;
         }
        if(boo && !b.kdanger)     {
            if(b.wrook1)
                caslingCode(-1,k1,k2,ok1,ok2,pc,r);
            if(b.wrook2)
                caslingCode(1,k1,k2,ok1,ok2,pc,r);
        }
        if(!boo && !b.kdanger)    {
            if(b.brook1)
                caslingCode(-1,k1,k2,ok1,ok2,pc,r);
            if(b.brook2)
                caslingCode(1,k1,k2,ok1,ok2,pc,r);
        }
    }
 	
    void rookHighlight(boolean boo) {
        int m=1,n=0,temp,x,y;  
        for(int z=0;z<4;z++)    {
            x=i+m;
            y=j+n;
            try {
                while(b.cell[x][y].getIcon()==null) {
                    if(b.kdanger)   {
                        if(helpKing(x,y,b.c1,b.c2))   {
                            if(b.stale==true)
                                 {
                                     b.stale=false;
                                     return;
                                 }
                               b.cell[x][y].setBackground(Color.cyan);
                        
                        }
                    }
                    else
                    {
                        if(b.stale==true)
                                 {
                                     b.stale=false;
                                     return;
                                 }
                         b.cell[x][y].setBackground(Color.cyan);
                    }
                    x+=m;  
                    y+=n;
                }
                if(!b.kdanger)
                    makeRed(x,y,boo);
                else if(b.c1==x&&b.c2==y)
                {
                    if(b.stale==true)
                                 {
                                     b.stale=false;
                                     return;
                                 }
                    b.cell[x][y].setBackground(Color.RED);
                }
            }catch(Exception e) {}
            temp=m;
            m=-n;			
            n=temp;
        }
    }
    
    String colorId(int i,int j) {
        byte name[];
        name=((ImageIcon)(b.cell[i][j].getIcon())).getDescription().getBytes();
        return new String(name,0,1);
    }

    String nameId(int i,int j)  {
        byte name[];
        name=((ImageIcon)(b.cell[i][j].getIcon())).getDescription().getBytes();
       	return new String(name,1,1);
    }

    void makeRed(int i,int j,boolean boo)   {
        try {
            String piececolor=colorId(i,j);
            if(piececolor.equals("b")&&boo) {
                if(b.stale==true)
                                 {
                                     b.stale=false;
                                     return;
                                 }
                b.cell[i][j].setBackground(Color.red);
                b.checkmate=false;
            }
            else if((piececolor.equals("w"))&&(!boo))   {
                if(b.stale==true)
                                 {
                                     b.stale=false;
                                     return;
                                 }
            	b.cell[i][j].setBackground(Color.red);
                b.checkmate=false;
            }
        }catch(Exception e){}
    }
    
    /* The arguments for the setKingDanger must be 
     * the cell to be checked (i,j), 
     * the current king position (k,l),
     * the current opponent king postion (m,n) and
     * the color of the opponent (col) */
    
    boolean setKingDanger(int i,int j,int k,int l,int m,int n,String col,int r)  {
        boolean a=false,b=false,c=false,d=false,e=false;
        Board.twiceChecked=0;
        if(Board.pawnconv==1)            
            a=pawnCheck(r,i,j,col);
        else
            a=stpawn(r,i,j,col);
        b=knightCheck(i,j,col);
        c=bishopCheck(i,j,k,l,m,n,col,"b");
        if(Board.twiceChecked<2)
            d=rookCheck(i,j,k,l,m,n,col,"r");
        if(Board.twiceChecked<2)
            e=queenCheck(i,j,k,l,m,n,col,"q");
        
        if(a||b||c||d||e)
            return false;
        return true;
    }
    
    boolean pawnCheck(int a,int x,int y,String col)   {
        boolean check=false;
        try {
                if(nameId(x+a,y-1).equals("p")&&colorId(x+a,y-1).equals(col))   
                    check=setCheckPosition(x+a,y-1);
        }catch(Exception e){}
        try {
                if(nameId(x+a,y+1).equals("p")&&colorId(x+a,y+1).equals(col))  
                    check=setCheckPosition(x+a,y+1);
        }
        catch(Exception e){}
        return check;
    }
    
    boolean knightCheck(int i, int j,String col)   {
        int m=1,n=2,temp;
        boolean check=false;
        for(int t=1;t<=4&&!check;t++)   {
            try {
                if(b.cell[i-m][j-n].getIcon()!=null)  
                    if(nameId(i-m,j-n).equals("h")&&colorId(i-m,j-n).equals(col))
                        check=setCheckPosition(i-m,j-n);                        
            }catch(Exception e) {}
            try {
                if(b.cell[i-n][j-m].getIcon()!=null)  
                    if(nameId(i-n,j-m).equals("h")&&colorId(i-n,j-m).equals(col))
                        check=setCheckPosition(i-n,j-m);
            }catch(Exception e) {}
            temp=m;
            m=-n;
            n=temp;
        }
        return check;
    }
    
    boolean bishopCheck(int i,int j,int k,int l,int m,int n,String col,String piece) {
        int a=1,t=1,temp; 
        int x,y; 
        boolean check=false;
        for(int z=0;z<4&&!check;z++)    {
            try {
                x=i+a;
                y=j+t;
                if(x==k&&y==l)  {
                    x+=a;
                    y+=t;
                }
                if(x==m&&y==n) 
                    check=true;
                if(!check)  {
                    while(b.cell[x][y].getIcon()==null) {
                        x+=a;  
                        y+=t;
                    }
                    if(nameId(x,y).equals(piece)&&colorId(x,y).equals(col))
                        check=setCheckPosition(x,y);
                }
            }catch(Exception e) {}
            temp=a;
            a=-t;
            t=temp;         
        }
        return check;
    }
    
    boolean rookCheck(int i,int j,int k,int l,int m,int n,String col,String piece)   {
        int a=1,t=0,temp,x,y;  
        boolean check=false;
        for(int z=0;z<4&&!check;z++)    {
            x=i+a;
            y=j+t;
            try {
                if(x==k&&y==l)  {
                    x+=a;
                    y+=t;
                }
                if(x==m&&y==n) 
                    check=true;
                if(!check)  {
                    while(b.cell[x][y].getIcon()==null) {
                        x+=a;  
                        y+=t;
                    }
                    if(nameId(x,y).equals(piece)&&colorId(x,y).equals(col))
                        check=setCheckPosition(x,y);
                }
            }catch(Exception e) {}
            temp=a;
            a=-t;			
            t=temp;
        }
        return check;
    }
    
    boolean queenCheck(int i,int j,int k,int l,int m,int n,String col,String piece) {
        boolean check=false,a,b;
        b=rookCheck(i,j,k,l,m,n,col,piece);
        a=bishopCheck(i,j,k,l,m,n,col,piece);
        check=a||b;
        return check;
    }
    
    
    boolean stpawn(int a,int x,int y,String col)
    {
        try {
                if(nameId(x+a,y).equals("p")&&colorId(x+a,y).equals(col))   
                    return true;
            }
        catch(Exception e){}
        try {
            if((x+a+a==2 || x+a+a==7) && nameId(x+a+a,y).equals("p") && colorId(x+a+a,y).equals(col))  
                  return true;
            }
        catch(Exception e){}
        return false;
    }
    
    boolean setCheckPosition (int x,int y) {
        Board.twiceChecked++;
        Board.c1=x;
        Board.c2=y;
        
        return true;
    }
    

    boolean isQueen_Bishop(int a,int t,boolean boo) {
        try {
            int x=i+a,y=j+t;
            boolean z=true;         
            while(b.cell[x][y].getIcon()==null) {
                z=false;
                x+=a;  
                y+=t;
            }
            if( nameId(x,y).equals("b") || nameId(x,y).equals("q")) {
                if((colorId(x,y).equals("b") && boo) || ( colorId(x,y).equals("w") && !boo))  {
                    if(!b.kdanger)
                    {
                        if(PieceType.equals("b") || PieceType.equals("q"))  {
                            for(x=i-a,y=j-t; b.cell[x][y].getIcon()==null; x-=a,y-=t)
                                b.cell[x][y].setBackground(Color.cyan);
                            for(x=i+a,y=j+t; b.cell[x][y].getIcon()==null; x+=a,y+=t)
                                b.cell[x][y].setBackground(Color.cyan);
                            b.cell[x][y].setBackground(Color.red);
                        }
                        else if((PieceType.equals("p") && z && x==i+a && y==j+t))   {
                            if(boo && a>0)
                                b.cell[x][y].setBackground(Color.red);
                            else if(!boo && a<0)
                                b.cell[x][y].setBackground(Color.red);
                        }
                    }
                    return false;
                }
            }
        }catch(Exception e){}
        return true;
    }
    
    boolean isQueen_Rook(int p,int q,boolean boo)   {
        try {
            int x=i+p;
            int y=j+q;
            while(b.cell[x][y].getIcon()==null) {
                x+=p;  
                y+=q;
            }
            if(nameId(x,y).equals("r") || nameId(x,y).equals("q"))  {               
                if(( colorId(x,y).equals("b") && boo) || ( colorId(x,y).equals("w") && !boo))  {
                    if(!b.kdanger)
                    {
                        if(PieceType.equals("r") || PieceType.equals("q"))  {
                            for(x=i-p,y=j-q;b.cell[x][y].getIcon()==null;x-=p,y-=q)
                                b.cell[x][y].setBackground(Color.cyan);
                            for(x=i+p,y=j+q;b.cell[x][y].getIcon()==null;x+=p,y+=q)
                                b.cell[x][y].setBackground(Color.cyan);
                            b.cell[x][y].setBackground(Color.red);
                        }
                        else if(PieceType.equals("p") && q==0)  {
                            z=false;
                            return true;
                        }
                    }
                    return false;
                }
            }
        }catch(Exception e){}
        return true;	
    }
	
    boolean safeMove(boolean boo)   {
        int temp,t=1,a=1,z,x,y,p=1,q=0,m,n;
        for(z=1;z<=4;z++)   {
            try {
                x=i+a;
                y=j+t;
                while(b.cell[x][y].getIcon()==null) {
                    x+=a;  
                    y+=t;
                }
                if(nameId(x,y).equals("k"))
                    if((colorId(x,y).equals("b") && !boo) || ( colorId(x,y).equals("w") && boo))  
                    	return isQueen_Bishop(-a,-t,boo);						
            }catch(Exception e) {}
            try {
                x=i+p;
                y=j+q;
                while(b.cell[x][y].getIcon()==null) {
                    x+=p;  
                    y+=q;
                }
                if(nameId(x,y).equals("k"))
                    if((colorId(x,y).equals("b") && !boo) || ( colorId(x,y).equals("w") && boo))  
                    	return isQueen_Rook(-p,-q,boo);		
            }catch(Exception e) {}
            temp=a;
            a=-t;
            t=temp; 
            temp=p; 
            p=-q;			
            q=temp;
        }
        return true;           
    }
    
    private boolean helpKing(int i, int j,int x,int y) {
        if(b.tr1!=2)    {
            while(x!=b.k1 || y!=b.k2)   {
                if(x==i&&y==j)
                    return true;
                x=x+b.tr1;
                y=y+b.tr2;
            }
        }
        return false;
    }
    
    void caslingCode(int direct,int k1,int k2,int ok1,int ok2,String pc,int r) {
        boolean IsAll=true;
        int temp=direct;
        try {
        while(b.cell[i][j+temp].getIcon()==null)    {
            if(!setKingDanger(i,j+temp,k1,k2,ok1,ok2,pc,r))
                IsAll=false;
            temp=temp+direct;
        }
        if(nameId(i,j+temp).equals("r") && IsAll)
            b.cell[i][j+2*direct].setBackground(Color.blue);
        }
        catch(Exception e){}
    }
    void handleStaleMate()
    {
        System.out.print("Stale Mate");
    }
   
}