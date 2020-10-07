/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectFour;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


/**
 * FXML Controller class
 *
 * @Qudrat E Rabbi
 * Professor John Baugh
 * CIS 296
 * Assignment Two
 */
public class FXMLController implements Initializable {
    
    private int num_of_turns = 0; // Max 42, then board is full        
    private int[][] connect_board = new int[6][7];// setting up length and width of the board    
    private int player = 1;                    // Game will start with player A
      
    @FXML
    private Button column_one_click;
    @FXML
    private Button column_two_click;
    @FXML
    private Button column_three_click;
    @FXML
    private Button column_four_click;
    @FXML
    private Button column_five_click;
    @FXML
    private Button column_six_click;
    @FXML
    private Button column_seven_click; 
    
    
    @FXML
    private Circle circle_one_one, circle_one_two, circle_one_three,circle_one_four, circle_one_five, circle_one_six, circle_one_seven;
            
    @FXML
    private Circle circle_two_one, circle_two_two, circle_two_three, circle_two_four, circle_two_five, circle_two_six, circle_two_seven;
    
    @FXML
    private Circle circle_three_one, circle_three_two, circle_three_three, circle_three_four, circle_three_five, circle_three_six, circle_three_seven;
    
    @FXML
    private Circle circle_four_one, circle_four_two, circle_four_three, circle_four_four, circle_four_five, circle_four_six, circle_four_seven;
    
    @FXML
    private Circle circle_five_one, circle_five_two, circle_five_three, circle_five_four, circle_five_five, circle_five_six, circle_five_seven;
    
    @FXML
    private Circle circle_six_one, circle_six_two, circle_six_three, circle_six_four, circle_six_five, circle_six_six, circle_six_seven;
                   
    private Circle[][] circle_array = new Circle[6][7] ;    // Decalring and initializing 2D circle array to hold circles 
                                                            // Correspnding values from connect_board will be used to set color to circles 
    
    @FXML
    private Label central_display, winner_label;// To set up turn display and one to display winner
    @FXML
    private ImageView background_pic; 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        // I have TRIED finding other ways, but this was the only way out
        // Any other way of initializing was met with failure 
        circle_array[0][0] = circle_six_one;
        circle_array[0][1] = circle_six_two;
        circle_array[0][2] = circle_six_three;
        circle_array[0][3] = circle_six_four;
        circle_array[0][4] = circle_six_five;
        circle_array[0][5] = circle_six_six;
        circle_array[0][6] = circle_six_seven;
        
        circle_array[1][0] = circle_five_one;
        circle_array[1][1] = circle_five_two;
        circle_array[1][2] = circle_five_three;
        circle_array[1][3] = circle_five_four;
        circle_array[1][4] = circle_five_five;
        circle_array[1][5] = circle_five_six;
        circle_array[1][6] = circle_five_seven;
        
        circle_array[2][0] = circle_four_one;
        circle_array[2][1] = circle_four_two;
        circle_array[2][2] = circle_four_three;
        circle_array[2][3] = circle_four_four;
        circle_array[2][4] =  circle_four_five;
        circle_array[2][5] = circle_four_six;
        circle_array[2][6] = circle_four_seven;
        
        circle_array[3][0] = circle_three_one;
        circle_array[3][1] = circle_three_two;
        circle_array[3][2] = circle_three_three;
        circle_array[3][3] = circle_three_four;
        circle_array[3][4] = circle_three_five;
        circle_array[3][5] = circle_three_six;
        circle_array[3][6] = circle_three_seven;
        
        circle_array[4][0] = circle_two_one;
        circle_array[4][1] = circle_two_two;
        circle_array[4][2] = circle_two_three;
        circle_array[4][3] = circle_two_four;
        circle_array[4][4] = circle_two_five;
        circle_array[4][5] = circle_two_six;
        circle_array[4][6] = circle_two_seven;
        
        circle_array[5][0] = circle_one_one;
        circle_array[5][1] = circle_one_two;
        circle_array[5][2] = circle_one_three;
        circle_array[5][3] = circle_one_four;
        circle_array[5][4] = circle_one_five;
        circle_array[5][5] = circle_one_six;
        circle_array[5][6] = circle_one_seven; 
        
        // Populating initial connect board with 0's 
        //  Will allow paiting usused circles white and 
        // allow to skip over empty ('0') values during win condition check
        for (int[] connect_board1 : connect_board) {
            java.util.Arrays.fill(connect_board1, 0, connect_board1.length, 0);
        }
        
        //board test was used as a reference to confirm functionalities of the program         
        
        board_test(connect_board);
        central_display.setText("Begin Player ONE(BLACK)");
               
    }
    
    

    @FXML
    private void initialize(MouseEvent event) {
        
    }
    
    // Each clicked button sets off a column method
    // number one to seven to symbolize 7 columns
    @FXML
    private void col_one_init(ActionEvent event) {
        num_of_turns++;                                      // Every action increments number of turns 
           if(legalMove(connect_board,0, player)){           // Insertion in column 0 
               if(checkWinCond(connect_board)){             // Win conditions checked
                                                                    
                   endGame();                                // We're in the End Game now ( Shuts everything down and displays winner
                    winner_label.setText("Player " + player +  " wins");                                
               }
               board_test(connect_board);                   //Reference purposes
               player = playerSwitch(player);               // Plyers switch turns 
               if ( player == 2 ){                          // Tried turning this into a method
                                                            // but lightItUp method wouldn't work that way
                   central_display.setText("Turn : PLAYER TWO(RED)");
               }
               else{ central_display.setText("Turn: PLAYER ONE(BLACK)");}
           }
           lightItUp(connect_board);                         // Paints the baord after each turn
    }

    @FXML
    private void col_two_init(ActionEvent event) {           // Same as column one with calculations dones at column '1'
        num_of_turns++;
           if(legalMove(connect_board,1, player)){
               if(checkWinCond(connect_board)){
                   endGame();
                   winner_label.setText("Player " + player +  " wins");  
                   
               }
               board_test(connect_board);
               player = playerSwitch(player);
               if ( player == 2 ){
                   central_display.setText("Turn : PLAYER TWO(RED)");
               }
               else{ central_display.setText("Turn: PLAYER ONE(BLACK)");}
           }
           lightItUp(connect_board);
           
    }

    @FXML
    private void col_three_init(ActionEvent event) {
        num_of_turns++;
           if(legalMove(connect_board,2, player)){
               if(checkWinCond(connect_board)){
                   endGame();
                  winner_label.setText("Player " + player +  " wins");            
                                      
               }
               board_test(connect_board);
               player = playerSwitch(player);
            if ( player == 2 ){
                   central_display.setText("Turn : PLAYER TWO(RED)");
               }
               else{ central_display.setText("Turn: PLAYER ONE(BLACK)");}
        }
           lightItUp(connect_board);
    }

    @FXML
    private void col_four_init(ActionEvent event) {
        num_of_turns++;
           if(legalMove(connect_board,3, player)){
               if(checkWinCond(connect_board)){
                   endGame();
                    winner_label.setText("Player " + player +  " wins");             
                                   
               }
               board_test(connect_board);
               player = playerSwitch(player);
               if ( player == 2 ){
                   central_display.setText("Turn : PLAYER TWO(RED)");
               }
               else{ central_display.setText("Turn: PLAYER ONE(BLACK)");}
           }
           lightItUp(connect_board);
    }

    @FXML
    private void col_five_init(ActionEvent event) {
       num_of_turns++;
           if(legalMove(connect_board,4, player)){
               if(checkWinCond(connect_board)){
                   endGame();
                    winner_label.setText("Player " + player +  " wins");              
                                     
               }
               board_test(connect_board);
               player = playerSwitch(player);
                if ( player == 2 ){
                   central_display.setText("Turn : PLAYER TWO(RED)");
               }
               else{ central_display.setText("Turn: PLAYER ONE(BLACK)");}
           }
           lightItUp(connect_board);
    }

    @FXML
    private void col_six_init(ActionEvent event) {
       num_of_turns++;
           if(legalMove(connect_board,5, player)){
               if(checkWinCond(connect_board)){
                   endGame();
                    winner_label.setText("Player " + player +  " wins"); 
                   
               }
               board_test(connect_board);
               player = playerSwitch(player);
                if ( player == 2 ){
                   central_display.setText("Turn : PLAYER TWO(RED)");
               }
               else{ central_display.setText("Turn: PLAYER ONE(BLACK)");}
               
           }
           lightItUp(connect_board);
    }

    @FXML
    private void col_seven_init(ActionEvent event) {
        num_of_turns++;
           if(legalMove(connect_board,6, player)){
               if(checkWinCond(connect_board)){
                   endGame();
                    winner_label.setText("Player " + player +  " wins");             
                                      
               }
               board_test(connect_board);
               player = playerSwitch(player);
                if ( player == 2 ){
                   central_display.setText("Turn : PLAYER TWO(RED)");
               }
               else{ central_display.setText("Turn: PLAYER ONE(BLACK)");}
           }
           lightItUp(connect_board);
    } 
    
    public boolean checkWinCond(int[][] board){
        
        if (num_of_turns == 42) // If number of turns reaches 42, the board is full 
                { 
                    endGame();
                    winner_label.setText("No Winner...");
                }
       
        // horizontal Win Check 
        for(int row = 0; row < board.length; row++){   // loops from 0 - 6      
            for(int col = 0; col < board[row].length -3; col++){ 
                int content = board[row][col];
                if(content != 0 && content == board[row][col+1] 
                        && content == board[row][col+2] && content == board[row][col+3])
                {
                    return true;
                }
            }
        }
        
        
        // Vertical Win Check 
       for(int col = 0; col < board[0].length; col++){
            for(int row = 0; row < board.length - 3 ; row++){
                if (board[row][col] != 0 && board[row][col] == board[row+1][col] &&
                        board[row][col] == board[row+2][col] && board[row][col] == board[row+3][col])
                    {
                        return true;
                    }
            }            
        } 
       
       // Diagonal Check (Top-left  /  )
       for (int row = 0; row < board.length - 3; row++){
            for (int col = 0; col < board[row].length - 3; col++){
                int content = board[row][col];
            if (content != 0 &&content == board[row+1][col+1] 
                    && content == board[row+2][col+2] && content == board[row+3][col+3])
                {
                    return true;
                }
            }
     
       }
       // Diagonal Check (Top-Right  \ )
       for (int row = 0; row < board.length - 3; row++){
            for (int col = 3; col < board[row].length; col++){
                int content = board[row][col];
                if (content != 0 && content == board[row+1][col-1] 
                        && content == board[row+2][col-2] && content == board[row+3][col-3])
                    {
                        return true;
                    }
            }
        }  
        
        return false;
    } 
    
    public int playerSwitch(int currPlayer){// switches players 
        if (currPlayer == 1)
        {  return 2; }
        else {
            return 1;
        }        
    }   
      
    
    public boolean legalMove(int[][] grid, int col, int player){
        boolean result = false;
        if(grid[0][col]!= 0){// If theres anything in 0 row, that column is full 
            central_display.setText("Column is full");
            return false;
        }// going down to find empty spots 
        for (int row = grid.length-1; row >= 0; row--){
            if(grid[row][col] == 0){
                grid[row][col] = player;
                return true;
            }
        }
        return result;       
    }
    // reference print to console to check if Gui is functioning correctly
    
    public void board_test(int[][] board){
        for (int row = 0; row < board.length; row++){
            System.out.print("|");
            for (int col = 0; col < board[row].length; col++){
                System.out.print(" " + board[row][col] + " |");
            }
            System.out.println();
        // TODO
    }
    }
    //Paints the baord 
    // 0's become white, 1's become black and 2's become red 
    public void lightItUp(int[][] board){
        for(int row = 0; row < board.length ; row++){
            for(int col = 0; col<= board.length; col++){
                if(board[row][col] == 1){
                    circle_array[row][col].setFill(Color.BLACK);
                }
                else if(board[row][col] == 2 ){
                    circle_array[row][col].setFill(Color.RED);
                }
                   
            }
        }
    }
    
    public void endGame(){//Sets visibility off to most elements 
        
        for(int row = 0; row < connect_board.length ; row++){
            for(int col = 0; col<= connect_board.length; col++){
                
                circle_array[row][col].setVisible(false);
                
            }
        }
        column_one_click.setVisible(false);
        column_two_click.setVisible(false);
        column_three_click.setVisible(false);
        column_four_click.setVisible(false);
        column_five_click.setVisible(false);
        column_six_click.setVisible(false);
        column_seven_click.setVisible(false); 
        background_pic.setVisible(false);
        central_display.setVisible(false);        
    }    
    }  