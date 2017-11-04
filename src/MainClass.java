import ChessBoard.*;
public class MainClass  {
    public static void main(String[] args)  {
        Board b=new Board();         
        NewOrSavedGame o=new NewOrSavedGame();       
        o.newOrSavedGame(b);      
    }
}