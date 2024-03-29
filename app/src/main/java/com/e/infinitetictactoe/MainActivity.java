package com.e.infinitetictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    private Button[][] buttons=new Button[3][3];
    private Boolean player1Turn=true;
    private int roundCount;
    private int player1Points;
    private int player2Points;
    private TextView textViewPlayer1;
    private TextView textViewPlayer2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewPlayer1=findViewById(R.id.textviewp1);
        textViewPlayer2=findViewById(R.id.textviewp2);
        for(int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                String buttonId="button_"+i+j;
                int resId=getResources().getIdentifier(buttonId,"id",getPackageName());
                buttons[i][j]=findViewById(resId);
                buttons[i][j].setOnClickListener(this);
            }
        }
        Button buttonReset=findViewById(R.id.buttonReset);
        buttonReset.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")){
            return;
        }
        if (player1Turn){
            ((Button) v).setText("X");
        }
        else{
            ((Button) v).setText("O");
        }
        roundCount++;
        if (checkForWin()){
            if (player1Turn){
                player1Win();
            }
            else{
                player2Win();
            }
        }
        else if (roundCount==9){
            draw();
        }
        else{
            player1Turn=!player1Turn;
        }
    }
    private Boolean checkForWin(){
        String[][] field=new String[3][3];
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                field[i][j]=buttons[i][j].getText().toString();
            }
        }
        for (int i=0;i<3;i++){
            if (field[i][0].equals(field[i][1])&&field[i][0].equals(field[i][2])&& !field[i][0].equals("")){
                return true;
            }
        }
        for (int i=0;i<3;i++){
            if (field[0][i].equals(field[1][i])&&field[0][i].equals(field[2][i])&& !field[0][i].equals("")){
                return true;
            }
        }
        if (field[0][0].equals(field[1][1])&&field[0][0].equals(field[2][2])&& !field[0][0].equals("")){
            return true;
        }
        if (field[0][2].equals(field[1][1])&&field[0][2].equals(field[2][0])&& !field[0][2].equals("")){
            return true;
        }
        return false;
    }
    private void player1Win(){
        player1Points++;
        Toast.makeText(this,"Player 1 Wins!",Toast.LENGTH_LONG).show();
        updatePointsText();
        resetBoard();
    }
    private void player2Win(){
        player2Points++;
        Toast.makeText(this,"Player 2 Wins!",Toast.LENGTH_LONG).show();
        updatePointsText();
        resetBoard();
    }
    private void draw(){
        Toast.makeText(this,"Draw!",Toast.LENGTH_LONG).show();
        resetBoard();
    }
    private void updatePointsText(){
        textViewPlayer1.setText("Player 1 : "+player1Points);
        textViewPlayer2.setText("Player 2 : "+player2Points);
    }
    private void resetBoard(){
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                buttons[i][j].setText("");
            }
        }
        roundCount=0;
        player1Turn=true;
    }
    private void resetGame(){
        player1Points=0;
        player2Points=0;
        updatePointsText();
        resetBoard();
    }
}
