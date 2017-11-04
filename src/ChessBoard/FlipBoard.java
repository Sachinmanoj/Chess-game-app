package ChessBoard;
import java.net.URL;
import javax.swing.*;
import java.io.*;

public class FlipBoard {
    Board b;
    public void flipBoard(Board b)
    {
        this.b=b;
        int i,j,k;    
        ImageIcon ii1,ii2;      
        for(k=1;k<=8;k++)
         for(i=1,j=8;i<=4&&j>=5;i++,j--)            
            {                
                ii1=(ImageIcon)b.cell[i][k].getIcon();
                ii2=(ImageIcon)b.cell[j][k].getIcon();
                b.cell[i][k].setIcon(ii2);
                b.cell[j][k].setIcon(ii1);
                
            }
        for(k=1;k<=8;k++)
            for(i=1,j=8;i<5&&j>4;i++,j--)
            {
                ii1=(ImageIcon)b.cell[k][i].getIcon();
                ii2=(ImageIcon)b.cell[k][j].getIcon();
                b.cell[k][i].setIcon(ii2);
                b.cell[k][j].setIcon(ii1);                      
            }           
       
    }    
}
