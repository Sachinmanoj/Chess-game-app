package ChessBoard;
import java.util.Random;
import javax.swing.ImageIcon;
public class AiFirstClickHighlight    {
    Board b;
    int i,j,vm=0;
    int xpos[]=new int[32];
    int ypos[]=new int[32];//  No.of valid moves of randomly selected piece -> vm
    String PieceColor,PieceType;
    boolean z;
    static int hm=-1;   
    ArtificialIntelligence ai;
    public void resolve(Board b,byte name[],int i,int j,ArtificialIntelligence ai)    {
         System.out.println("c1="+b.c1+" c2="+b.c2);
        this.i=i;
        this.j=j;
        this.b=b;
        z=true;
        this.ai=ai;
        PieceColor=new String(name,0,1);
        PieceType=new String(name,1,1);
        hm=-1;
       vm=0;
         if(PieceColor.equals("b")&&Board.white==false)     {             
            if(PieceType.equals("p") && safeMove() && Board.twiceChecked<2) {
                
                
                    if(b.passant) {
                        if(!b.kdanger)  {
                            if(i-1==b.en1&&(j+1==b.en2||j-1==b.en2))
                            {
                                xpos[vm]=b.en1;
                                ypos[vm++]=b.en2;
                                //b.cell[b.en1][b.en2].setBackground(Color.BLUE);
                            }
                        }
                        else
                            if(i-1==b.en1&&(j+1==b.en2||j-1==b.en2)&&(helpKing(b.en1,b.en2,b.c1,b.c2) || helpKing(i,j-1,b.c1,b.c2) || helpKing(i,j+1,b.c1,b.c2)))
                            {
                                xpos[vm]=b.en1;
                                ypos[vm++]=b.en2;
                            }
                    }
                   if(b.cell[i-1][j].getIcon()==null)  {
                    if(b.kdanger)   {
                        if(helpKing(i-1,j,b.c1,b.c2))   {
                            xpos[vm]=i-1;
                            ypos[vm++]=j;
                            //b.cell[i-1][j].setBackground(Color.cyan);                          
                            
                        }
                    }
                    else if(!b.kdanger)
                    {
                         xpos[vm]=i-1;
                         ypos[vm++]=j;
                                               
                        //b.cell[i-1][j].setBackground(Color.cyan);
                    }
                }
                if(i==7&&b.cell[i-1][j].getIcon()==null&&b.cell[i-2][j].getIcon()==null)    {
                    if(b.kdanger)   {
                        if(helpKing(i-2,j,b.c1,b.c2))   {
                            xpos[vm]=i-2;
                            ypos[vm++]=j;
                           // b.cell[i-2][j].setBackground(Color.cyan);
                            
                        }
                    }
                    else
                    {
                        xpos[vm]=i-2;
                        ypos[vm++]=j;
                       // b.cell[i-2][j].setBackground(Color.cyan);
                    }
                }
                if(!b.kdanger && z)  {
                    makeRed(i-1,j+1,false);
                    makeRed(i-1,j-1,false);     
                }
                else if(i-1==b.c1 && j+1==b.c2 && b.kdanger)
                {
                    xpos[vm]=b.c1;
                    ypos[vm++]=b.c2;
                      
                   // b.cell[b.c1][b.c2].setBackground(Color.red);
                }
                else if(i-1==b.c1 && j-1==b.c2 && b.kdanger)
                {
                    xpos[vm]=b.c1;
                    ypos[vm++]=b.c2;
                    //    b.cell[b.c1][b.c2].setBackground(Color.red);
                }
                
            }
            else if(PieceType.equals("r") && safeMove() && b.twiceChecked<2)   
                rookHighlight(false);
            else if(PieceType.equals("h") && safeMove() && b.twiceChecked<2)   
                knightHighlight(false);
            else if(PieceType.equals("b") && safeMove() && b.twiceChecked<2)   
                bishopHighlight(false);
            else if(PieceType.equals("q") && safeMove() && b.twiceChecked<2)   {
                bishopHighlight(false);
                rookHighlight(false);
            }
            else if(PieceType.equals("k"))  
                kingHighlight(i,j,Board.bk1,Board.bk2,Board.wk1,Board.wk2,false,"w",-1);
        }
        this.i=this.j=-1;
        if(vm==0)
        {
            ai.k=-1;
            return;
        }
        if(hm==-1)
        {
            Random rr=new Random();
            hm=rr.nextInt(vm);
            
            ai.k=xpos[hm];
            ai.l=ypos[hm];
        }
        System.out.println("vm = "+vm+" Kdanger : "+b.kdanger+" Twc  : "+b.twiceChecked);
    }
    
    void knightHighlight(boolean boo)   {
        int m=1,n=2,temp;
        for(int t=1;t<=4;t++)   {
            try {
                if(b.cell[i-m][j-n].getIcon()==null)  
                    if(b.kdanger)   {
                        if(helpKing(i-m,j-n,b.c1,b.c2))   {                            
                            xpos[vm]=i-m;
                            ypos[vm++]=j-n;      // CYAN                   
                        }
                    }
                    else
                    {
                        xpos[vm]=i-m;
                        ypos[vm++]=j-n;   // CYAN             
                    }
                else    {
                    if(!b.kdanger)
                          makeRed(i-m,j-n,boo);                        
                    else  if(b.kdanger&&b.c1==i-m&&b.c2==j-n)
                    {
                        xpos[vm]=i-m;
                        ypos[vm++]=j-n;  // b.cell[b.c1][b.c2].setBackground(Color.RED);           
                    }
                }                        
            }catch(Exception e) {}
            try {
                if(b.cell[i-n][j-m].getIcon()==null)  
                    if(b.kdanger)   {
                        if(helpKing(i-n,j-m,b.c1,b.c2))   {                 
                            xpos[vm]=i-n;
                            ypos[vm++]=j-m;//   b.cell[i-n][j-m].setBackground(Color.CYAN);     
                        }
                    }
                    else
                    {
                       xpos[vm]=i-n;
                       ypos[vm++]=j-m; //  b.cell[i-n][j-m].setBackground(Color.CYAN);                    
                    }
                else    {
                    if(!b.kdanger)
                          makeRed(i-n,j-m,boo);                        
                    else  if(b.kdanger&&b.c1==i-n&&b.c2==j-m)
                    {
                        xpos[vm]=i-n;
                        ypos[vm++]=j-m;  // b.cell[b.c1][b.c2].setBackground(Color.RED);           
                    
                    }
                } 
            }catch(Exception e) {}
            temp=m;
            m=-n;
            n=temp;
            System.out.println("");
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
                            xpos[vm]=x;
                            ypos[vm++]=y;
                        }
                    }
                    else
                    {
                        xpos[vm]=x;
                        ypos[vm++]=y;
                    }
                    x+=a;  
                    y+=t;
                }
                if(!b.kdanger)
                    makeRed(x,y,boo);
                else if(b.c1==x&&b.c2==y)
                {
                    xpos[vm]=x;
                    ypos[vm++]=y;
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
        vm=0;
        hm=-1;
        for(int z=0;z<4;z++)    {
            try {
                
                if(setKingDanger(i+m,j+n,k1,k2,ok1,ok2,pc,r))    {
                    if(b.cell[i+m][j+n].getIcon()==null)    {
                        xpos[vm]=i+m;
                        ypos[vm++]=j+n;
                        b.checkmate=false;
                    }
                    else
                       makeRed(i+m,j+n,boo);
                }
            } catch(Exception e) {}
            try {
                if(setKingDanger(i+l,j+t,k1,k2,ok1,ok2,pc,r))    {
                    if(b.cell[i+l][j+t].getIcon()==null)    {
                        xpos[vm]=i+l;
                        ypos[vm++]=j+t;
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
                            xpos[vm]=x;
                            ypos[vm++]=y;
                            //b.cell[x][y].setBackground(Color.cyan);
                        
                        }
                    }
                    else    {
                        xpos[vm]=x;
                        ypos[vm++]=y;
                    }
                    x+=m;  
                    y+=n;
                }
                if(!b.kdanger)
                    makeRed(x,y,boo);
                else if(b.c1==x&&b.c2==y)   {
                    xpos[vm]=x;
                    ypos[vm++]=y;
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
                xpos[vm]=i;
                ypos[vm++]=j; //b.cell[i][j].setBackground(Color.red);
                b.checkmate=false;
            }
            else if((piececolor.equals("w"))&&(!boo))   {
                if(b.stale==true)
                                 {
                                     b.stale=false;
                                     return;
                                 }
            	xpos[vm]=i;
                ypos[vm++]=j;//b.cell[i][j].setBackground(Color.red);
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
    

    boolean isQueen_Bishop(int a,int t) {
        try {
            int x=i+a,y=j+t;
            boolean z=true;         
            while(b.cell[x][y].getIcon()==null) {
                z=false;
                x+=a;  
                y+=t;
            }
            if( nameId(x,y).equals("b") || nameId(x,y).equals("q")) {
              if(!b.kdanger)
              {
                if(PieceType.equals("b") || PieceType.equals("q"))  {
                        for(x=i-a,y=j-t; b.cell[x][y].getIcon()==null; x-=a,y-=t)
                        {
                            xpos[vm]=x;
                            ypos[vm++]=y;
                        }
                        for(x=i+a,y=j+t; b.cell[x][y].getIcon()==null; x+=a,y+=t)
                        {
                            xpos[vm]=x;
                            ypos[vm++]=y;
                        }
                        xpos[vm]=x;
                        ypos[vm++]=y;
                    }
                    else if((PieceType.equals("p") && z && x==i+a && y==j+t))   {
                        if(a<0)
                        {
                            xpos[vm]=x;
                            ypos[vm++]=y;
                        }
                    }
                 }
                 return false;
                }
            
        }catch(Exception e){}
        return true;
    }
    
    boolean isQueen_Rook(int p,int q)   {
        try {
            int x=i+p;
            int y=j+q;
            while(b.cell[x][y].getIcon()==null) {
                x+=p;  
                y+=q;
            }
            if(nameId(x,y).equals("r") || nameId(x,y).equals("q"))  {               
                if(!b.kdanger)
                {
                    if(PieceType.equals("r") || PieceType.equals("q"))  {
                    
                        for(x=i-p,y=j-q;b.cell[x][y].getIcon()==null;x-=p,y-=q)
                        {
                            xpos[vm]=x;
                            ypos[vm++]=y;
                        }
                        for(x=i+p,y=j+q;b.cell[x][y].getIcon()==null;x+=p,y+=q)
                        {
                            xpos[vm]=x;
                            ypos[vm++]=y;
                        }
                        xpos[vm]=x;
                        ypos[vm++]=y;
                    }
                    else if(PieceType.equals("p") && q==0)  {
                        z=false;
                        return true;
                    }
                }
                    return false;
                }
            
        }catch(Exception e){}
        return true;	
    }
	
    boolean safeMove()   {
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
                    return isQueen_Bishop(-a,-t);						
            }catch(Exception e) {}
            try {
                x=i+p;
                y=j+q;
                while(b.cell[x][y].getIcon()==null) {
                    x+=p;  
                    y+=q;
                }
                if(nameId(x,y).equals("k"))
                    return isQueen_Rook(-p,-q);		
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
        {
            xpos[vm]=i;
            ypos[vm++]=j+2*direct;
        
        }
            //b.cell[i][j+2*direct].setBackground(Color.blue);
        }
        catch(Exception e){}
    }
  
}