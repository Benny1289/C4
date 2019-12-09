import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class C4
{
    JLabel[][] grid;
    JPanel panel;
    public  JButton[] bArr;
    JLabel currPlayer;
    Popup p;
    public boolean turn = true;
    public C4(){
        prepareGui();

    }

    public static void main (String args[]){
        C4 c1 = new C4();
    }

    public void prepareGui () {

        JFrame main = new JFrame("C4");
        grid = new JLabel[6][7];
        JPanel panel = new JPanel();

        JLabel l = new JLabel("Yellow Has Won, Board Has reset"); 

        PopupFactory pf = new PopupFactory(); 
        JPanel p2 = new JPanel();
        JPanel p1 = new JPanel(); 
        p1.setBackground(Color.red);
        p2.add(l); 
        
        p = pf.getPopup(main, p2, 180, 100); 

        
        //Creates grid 7x6 + 1 row for buttons to drop tile
        panel.setLayout(new GridLayout(7,7));

        //Create buttons to drop gamepiece
        JButton[] bArr = new JButton[7];
        click c1 = new click();
        for(int i = 0; i < 7; i++) {
            bArr[i] = new JButton();
            panel.add(bArr[i]);
            bArr[i].addActionListener(c1);
            bArr[i].setActionCommand(i+"");
        }
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 7; y++) {
                 grid[x][y] = new JLabel(" ");
                grid[x][y].setOpaque(true);
                grid[x][y].setBackground(Color.WHITE);
                grid[x][y].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                //grid[x][y].setFontSize(30);
                panel.add(grid[x][y]);
            }
        }
        main.add(panel);
        main.setSize(700,700);
        main.setVisible(true);
        main.setLocationRelativeTo(null);
        //main.setDefaultOperation(JFrame.EXIT_ON_CLOSE);

    }
    public class click implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            int p = 0;
            String col = ae.getActionCommand();
            boolean lol = true;
            p = Integer.parseInt(col);
            int targetRow = 5;
            int countTurns = 0;
            for(int i = 5; i > -1; i--) {
                //System.out.println("i value = "+i);
                if (grid[i][p].getText().equals(" ")) {

                    targetRow = i;
                    i=-11;        

                }
            }
            if (!grid[0][p].getText().equals(" ")) { 
                turn=!turn; 
                lol = false;
            }
            if (turn && lol) {
                
                grid[targetRow][p].setText("red");
                grid[targetRow][p].setBackground(Color.RED);
                grid[targetRow][p].setForeground(Color.RED);
                System.out.println(checkWinnerUpDownLeftRight(targetRow, p, "red"));
                System.out.println(CheckDiagonal(targetRow, p, "red"));
                countTurns++;
            }
            else if (!turn && lol) {
                grid[targetRow][p].setText("yellow");
                grid[targetRow][p].setBackground(Color.YELLOW);
                grid[targetRow][p].setForeground(Color.YELLOW);
                System.out.println(checkWinnerUpDownLeftRight(targetRow, p, "yellow"));
                System.out.println(CheckDiagonal(targetRow, p, "yellow"));
                countTurns++;
            }

            {
                turn=!turn;
            }
            
            
        }

        public boolean checkWinnerUpDownLeftRight(int row, int col, String player) {
            int counter = 1;
            for (int x = row+1; x<=row+3; x++) {
                if (x>=0 && x<=5)
                    if (grid[x][col].getText().equals(player)) {
                        counter++;

                        //System.out.println("counter1"+ counter);
                    }
                    else 
                        x=30;

            }
            for (int x = row-1; x>= row-3; x--) {
                if (x<=5 && x>=0)
                    if (grid[x][col].getText().equals(player)) {
                        counter++;
                        //System.out.println("counter2"+ counter);
                    }
                    else
                        x=-10;
            }
            if (counter>=4) {
                System.out.println("Vertical"+player); 
                   for (int ThatGuy4348 = 0; ThatGuy4348 < 6; ThatGuy4348++) {
                    for (int y = 0; y < 7; y++) {
                    grid[ThatGuy4348][y].setOpaque(true);
                    if(player=="yellow"){
                        grid[ThatGuy4348][y].setBackground(Color.YELLOW);
                        grid[ThatGuy4348][y].setText(" ");
                        p.show();
                    }else{
                        grid[ThatGuy4348][y].setBackground(Color.RED);
                        grid[ThatGuy4348][y].setText(" ");
                        p.show();
                    }
                    grid[ThatGuy4348][y].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                }
              }
                return true;
            }
            counter = 1;
            for (int x = col+1; x<=col+3; x++) {
                if (x>=0 && x<=6)
                    if (grid[row][x].getText().equals(player)) {
                        counter++;
                        //System.out.println("counter1"+ counter);
                    }
                    else
                        x=10;
            }
            for (int x = col-1; x>= col-3; x--) {
                if (x<=6 && x>=0)
                    if (grid[row][x].getText().equals(player)) {
                        counter++;
                        //System.out.println("counter2"+ counter);
                    }
            }
            if (counter>=4) {
                System.out.println("Across"+player); 
                   for (int ThatGuy4348 = 0; ThatGuy4348 < 6; ThatGuy4348++) {
                    for (int y = 0; y < 7; y++) {
                    grid[ThatGuy4348][y].setOpaque(true);
                    if(player=="yellow"){
                        grid[ThatGuy4348][y].setBackground(Color.YELLOW);
                        grid[ThatGuy4348][y].setText(" ");
                        p.show();
                    }else{
                        grid[ThatGuy4348][y].setBackground(Color.RED);
                        grid[ThatGuy4348][y].setText(" ");
                        p.show();
                    }
                    grid[ThatGuy4348][y].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                }
              }
                return true;
            }
            
            return false;
        }
         public boolean CheckDiagonal(int row, int col, String player) {
            int counter = 0;
            int r = row+3;
            int c = col+3;
            int x  = 0;
            while (x<7) {
                if (r >= 0 && r <=5 && c >= 0 && c <=6) {
                    if (grid[r][c].getText().equals(player)) {
                        counter++;
                    }
                    else if (x<4){
                        counter = 0;
                    }
                }
                r--;
                c--;
                x++;
            }
            if (counter>=4){ 
              System.out.println("Diagonal"+player); 
                   for (int ThatGuy4348 = 0; ThatGuy4348 < 6; ThatGuy4348++) {
                    for (int y = 0; y < 7; y++) {
                    grid[ThatGuy4348][y].setOpaque(true);
                    if(player=="yellow"){
                        grid[ThatGuy4348][y].setBackground(Color.YELLOW);
                        grid[ThatGuy4348][y].setText(" ");
                        p.show();
                    }else{
                        grid[ThatGuy4348][y].setBackground(Color.RED);
                        grid[ThatGuy4348][y].setText(" ");
                        p.show();
                    }
                    grid[ThatGuy4348][y].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                }
              }
                return true;
            }
            r = row-3;
            c = col+3;
           x=0;
            while (x<7) {
                if (r >= 0 && r <=5 && c >= 0 && c <=6) {
                    if (grid[r][c].getText().equals(player)) {
                        counter++;
                    }
                    else if(x<4) {
                        counter = 0;
                    }
                }
                r++;
                c--;
                x++;
            }
            if (counter>=4){
              System.out.println("Diagonal"+player); 
                 for (int ThatGuy4348 = 0; ThatGuy4348 < 6; ThatGuy4348++) {
                    for (int y = 0; y < 7; y++) {
                    grid[ThatGuy4348][y].setOpaque(true);
                    if(player=="yellow"){
                        grid[ThatGuy4348][y].setBackground(Color.YELLOW);
                        grid[ThatGuy4348][y].setText(" ");
                        p.show();
                    }else{
                        grid[ThatGuy4348][y].setBackground(Color.RED);
                        grid[ThatGuy4348][y].setText(" ");
                        p.show();
                    }
                    grid[ThatGuy4348][y].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                }
              }
                return true;                
           }
                return false;
        }
    }
}