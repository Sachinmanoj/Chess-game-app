package ChessBoard;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import javax.swing.*;
public class Board extends JApplet implements ActionListener    {
    public static int DS,ngorsg; //double player or Single, new game or saved game
    Board b;
    ConstructBoard cb;
    MenuBarClass mbc;
    ClosingPrompt cp;
    FirstClickHighlight fch;
    ArtificialIntelligence ai;
    GameOver go;
    SecondClickReplace scr;
    public static int k,l,wk1=1,wk2=5,bk1=8,bk2=5,c1=0,c2=0,k1,k2,tr1,tr2,en1,en2,twiceChecked=0,pawnconv=1;
    static byte temp[]=null;
    byte name[],position[];
    JFrame mframe;
    JInternalFrame jif;
    JPanel board;
    JDesktopPane jdp;
    boolean escape=true;
    static public boolean white=true,checkmate=true,autoFlip,stale=false,playerchoice;
    static public boolean kdanger,passant,wrook1=true,wrook2=true,brook1=true,brook2=true;
    public JButton cell[][]=new JButton[9][9]; 
    Dimension d=new Dimension(750,750);
    Dimension d2=new Dimension(720,720);
   
    ImageIcon wkk=new ImageIcon(getPieceImageUrl("wkk"));
    ImageIcon wqq=new ImageIcon(getPieceImageUrl("wqq"));   
    ImageIcon wb1=new ImageIcon(getPieceImageUrl("wb1"));
    ImageIcon wb2=new ImageIcon(getPieceImageUrl("wb2"));
    ImageIcon wr1=new ImageIcon(getPieceImageUrl("wr1"));
    ImageIcon wr2=new ImageIcon(getPieceImageUrl("wr2"));
    ImageIcon wh1=new ImageIcon(getPieceImageUrl("wh1"));
    ImageIcon wh2=new ImageIcon(getPieceImageUrl("wh2"));
    ImageIcon wp1=new ImageIcon(getPieceImageUrl("wp1"));
    ImageIcon wp2=new ImageIcon(getPieceImageUrl("wp2"));
    ImageIcon wp3=new ImageIcon(getPieceImageUrl("wp3"));
    ImageIcon wp4=new ImageIcon(getPieceImageUrl("wp4"));
    ImageIcon wp5=new ImageIcon(getPieceImageUrl("wp5"));
    ImageIcon wp6=new ImageIcon(getPieceImageUrl("wp6"));
    ImageIcon wp7=new ImageIcon(getPieceImageUrl("wp7"));
    ImageIcon wp8=new ImageIcon(getPieceImageUrl("wp8"));
    ImageIcon bkk=new ImageIcon(getPieceImageUrl("bkk"));
    ImageIcon bqq=new ImageIcon(getPieceImageUrl("bqq"));
    ImageIcon bb1=new ImageIcon(getPieceImageUrl("bb1"));
    ImageIcon bb2=new ImageIcon(getPieceImageUrl("bb2"));
    ImageIcon br1=new ImageIcon(getPieceImageUrl("br1"));
    ImageIcon br2=new ImageIcon(getPieceImageUrl("br2"));
    ImageIcon bh1=new ImageIcon(getPieceImageUrl("bh1"));
    ImageIcon bh2=new ImageIcon(getPieceImageUrl("bh2"));
    ImageIcon bp1=new ImageIcon(getPieceImageUrl("bp1"));
    ImageIcon bp2=new ImageIcon(getPieceImageUrl("bp2"));
    ImageIcon bp3=new ImageIcon(getPieceImageUrl("bp3"));
    ImageIcon bp4=new ImageIcon(getPieceImageUrl("bp4"));
    ImageIcon bp5=new ImageIcon(getPieceImageUrl("bp5"));
    ImageIcon bp6=new ImageIcon(getPieceImageUrl("bp6"));
    ImageIcon bp7=new ImageIcon(getPieceImageUrl("bp7"));
    ImageIcon bp8=new ImageIcon(getPieceImageUrl("bp8"));
    
    public void launch(Board brd)    {
        
        b=brd;
        wk1=1;wk2=5;bk1=8;bk2=5;c1=0;c2=0;
        twiceChecked=0;pawnconv=1;
        temp=null;
        escape=true;
        white=true;checkmate=true;stale=false;
        wrook1=true;wrook2=true;brook1=true;brook2=true;
        
        fch=new FirstClickHighlight();
        scr=new SecondClickReplace();
        cp=new ClosingPrompt();
        cb=new ConstructBoard();
        mbc=new MenuBarClass();
        ai=new ArtificialIntelligence();
        mframe=new JFrame("Chess Reloaded v_1.0 Beta");
        board=new JPanel();
        jdp=new JDesktopPane();
        go=new GameOver();
        board.setLayout(new GridLayout(8,8));   
          
        
        if(b.ngorsg==0)
            try{cb.constructNewGameBoard(b);}catch(Exception e){}
        else
            try{cb.constructSavedGameBoard(b);}catch(Exception e){}
         
        if(b.DS==0)
        {
            System.out.println("Single player");
              playerchoice=false;
        }
        else
             System.out.println("Double player");      
       	 for(int i=1;i<=8;i++)
            for(int j=1;j<=8;j++)   {
               cell[i][j].addActionListener(this); 
            }
         
        refresh();      
        mframe.add(board);
        
        mframe.setVisible(true);
        mframe.setSize(740,740);
        mframe.setMinimumSize(d2);
        mframe.setMaximumSize(d);
        
        jdp.putClientProperty("JDesktopPane.dragMode", "outline");
        mbc.Menu(b);
       
        if(b.DS==0 && playerchoice==true)
            ai.evaluate(b);
        
        mframe.addWindowListener(new WindowAdapter()    {
            @Override
            public void windowClosing(WindowEvent we)   {                
                cp.closingPrompt(b);
            }
        });        
    }    
    
    public void refresh()   {
        for(int i=1;i<=8;i++)
            for(int j=1;j<=8;j++)   {
                if(i%2==1)
                    if(j%2==1)
                        cell[i][j].setBackground(Color.WHITE);
                    else
                        cell[i][j].setBackground(Color.GRAY);
             	else 
                    if(j%2==0)
                    	cell[i][j].setBackground(Color.WHITE);
                    else
                        cell[i][j].setBackground(Color.GRAY);
            }
    }
    
    @Override
    public void actionPerformed(ActionEvent e)  {
        try {
            scr.ac.stop();
        }   catch(Exception ec) {}
        
        int i,j;  
        name=null;
        position=e.getActionCommand().getBytes();
        i=(int)position[0]-48; 
        j=(int)position[1]-48;
        if(cell[i][j].getIcon()!=null)
            name=((ImageIcon)cell[i][j].getIcon()).getDescription().getBytes();   
        if(cell[i][j].getBackground()==Color.CYAN && (DS!=0 || escape)) {   
            refresh();
            scr.playSound("piecePlaced");
            scr.replace(b,temp,k,l,i,j,fch,passant,ai);
            
            if(scr.goneToPawnChoiceMenu!=true)
            {
                kdanger=makeKingOrange();   
                if(kdanger)
                {
                    System.out.println(" s "+twiceChecked);
                    checkForCheckmate();
                    setValues();
                    b.cell[k1][k2].setBackground(Color.orange);  
                    scr.playSound("kingMadeOrange");
                    scr.playSound("kingChecked");
                    if(checkmate)
                    {                    
                        b.cell[k1][k2].setBackground(Color.red);  
                        System.out.print("Checkmate"); 
                        if(DS!=0 && !white)
                            go.gameover(b,"Player White Won",true);
                        else if(DS!=0)
                            go.gameover(b,"Player Black Won",true);
                        else
                            go.gameover(b,"System Won",true);
                        return;
                    }                  
                    System.out.println(" s "+twiceChecked);
                }
            }
        }
        else if((cell[i][j].getBackground()==Color.RED || cell[i][j].getBackground()==Color.blue) && (DS!=0 || escape))
        {
            refresh();            
            scr.replace(b,temp,k,l,i,j,fch,passant,ai);
            
            scr.playSound("Nife");
            if(scr.goneToPawnChoiceMenu!=true)
            {
                kdanger=makeKingOrange();   
                
                if(kdanger)
                {
                    
                    checkForCheckmate();
                    setValues();
                    b.cell[k1][k2].setBackground(Color.orange);  
                    scr.playSound("kingMadeOrange");
                    scr.playSound("kingChecked");
                    if(checkmate)
                    {                    
                        b.cell[k1][k2].setBackground(Color.red);  
                        System.out.print("Checkmate");    
                        if(DS!=0 && !white)
                            go.gameover(b,"Player White Won",true);
                        else if(DS!=0)
                            go.gameover(b,"Player Black Won",true);
                        else
                            go.gameover(b,"System Won",true);
                        return;
                        //b.mframe.;
                    }
                    
                }
            }
        }
        else if(name!=null) {
            fch.resolve(b,name,i,j);
            escape=true;
            makeKingOrange();
            k=i;
            l=j;
            temp=name;                 
        }
        
        //cfd.draw1(b,i,j);
    }
    void checkForStaleMate(){
        byte[] name=null;
        String PieceColor;
        int i,j;
        stale=true;
       if(white==true){
           for(i=1;i<=8;i++)
               for(j=1;j<=8;j++)
               {     
                   
                   if(cell[i][j].getIcon()!=null) {
                        name=((ImageIcon)cell[i][j].getIcon()).getDescription().getBytes();                   
                        PieceColor=new String(name,0,1);
                        if(PieceColor.equals("w"))
                            fch.resolve(b,name,i,j);
                        if(!stale)
                            return;
                   }
               }
       }
       if(white==false){
           for(i=8;i>0;i--)
               for(j=8;j>0;j--)
               {
                   
                   if(cell[i][j].getIcon()!=null) {
                        name=((ImageIcon)cell[i][j].getIcon()).getDescription().getBytes();                   
                        PieceColor=new String(name,0,1);
                        if(PieceColor.equals("b"))
                            fch.resolve(b,name,i,j);
                        if(!stale)
                            return;
                   }
               }           
       }
    }
    
    
    boolean makeKingOrange()    {
        if(!b.white)    {
            
            if(!fch.setKingDanger(bk1,bk2,bk1,bk2,wk1,wk2,"w",-1))     {     
                b.cell[bk1][bk2].setBackground(Color.orange);  
                return true;
            }
        }
        else    {
            if(!fch.setKingDanger(wk1,wk2,wk1,wk2,bk1,bk2,"b",1))       {                                 
                b.cell[wk1][wk2].setBackground(Color.orange);
                return true;
            }
        }
        return false;
    }
    
    void setValues()    {
        // DIFFERENCE IS MUST NEED TO DIFF HORSE AND KNIGHT 
        
        int d1=k1-c1;
        int d2=k2-c2;
        if(d1==0) 
            if(d2<0)   {
                tr1=0;  tr2=-1;
            }
            else   {
                tr1=0;  tr2=1;
            }
        else if(d2==0)
            if(d1<0)   {
                tr1=-1;  tr2=0;
            }
            else   {
                tr1=1; tr2=0;
            }
        else if(d1==d2)             
            if(d1>0)    {           
                tr1=1; tr2=1;     
            }
            else    {
                tr1=-1; tr2=-1;
            }
        else if(d1==-d2)
            if(d1>0)   {
                tr1=1;  tr2=-1;
            }
            else   {
                tr1=-1;  tr2=1;
            }
        else 
            tr1=2;
    }
    URL getPieceImageUrl(String s1)    {
        URL  imageURL = getClass().getClassLoader().getResource("Pieces/"+s1+".png");        
        return imageURL;        
    }
    void checkForCheckmate()
    {
        int p=c1,q=c2;
        int twc=twiceChecked;
        if(!b.white)   {
            k1=bk1;
            k2=bk2;
            fch.kingHighlight(k1,k2,bk1,bk2,wk1,wk2,false,"w",-1);
       }
       else   {
            k1=wk1;
            k2=wk2;
            fch.kingHighlight(k1,k2,wk1,wk2,bk1,bk2,true,"b",1);
       }
        c1=p;
        c2=q;
        setValues();
       
       
       if(checkmate && twc<2)        
       {
           if(!b.white)  
                checkmate=fch.setKingDanger(c1,c2,0,0,0,0,"b",1);
            else
                checkmate=fch.setKingDanger(c1,c2,0,0,0,0,"w",-1);
        
           if(tr1!=2)
           {
               pawnconv=0;
              int x=p+tr1,y=q+tr2;             
               while((x!=k1 || y!=k2) && checkmate )   {
                   if(!b.white)  
                        checkmate=fch.setKingDanger(x,y,0,0,0,0,"b",1);
                   else
                        checkmate=fch.setKingDanger(x,y,0,0,0,0,"w",-1);
                    x=x+tr1;
                    y=y+tr2;
                    
                    
                }
               pawnconv=1;
               System.out.print("  "+checkmate);
            }
            
       }
       refresh();
       twiceChecked=twc;
       c1=p;
       c2=q;
    }
    
}