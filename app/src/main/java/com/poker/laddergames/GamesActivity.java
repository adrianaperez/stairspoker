package com.poker.laddergames;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;


public class GamesActivity extends Activity {
    // Variables for view components
    ImageButton[] dashboard = new ImageButton[16];

    Button playBtn;
    Button closeBtn;
    Button cleanBtn;

    TextView txtHand;
    
    int[] pictures;

    // Game variables
    HashMap<Integer, Integer> cardsMap = new HashMap<>();
    ArrayList<Integer> cardsInDashboard = new ArrayList<>();
    String HAND = "Hand: ";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.juego);
        init();
    }

    private void init(){
        loadDashboard();
        loadButtons();
        loadText();
        loadPictures();

        //Add picture to dashboard
        for(int i = 0; i < dashboard.length; i++){
            dashboard[i].setScaleType(ImageView.ScaleType.CENTER_CROP);
            dashboard[i].setImageResource(pictures[i]);
        }

        //HashMap of cards 
        cardsMap = new HashMap<>();
        for(int i = 0; i < 13; i++)
            cardsMap.put(i, i + 2);

        //Click in grid of dashboard
        for(int i = 0; i< dashboard.length; i++) {
            final int j = i;
            dashboard[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addCards(j);
                    int[] hand = convertIntegers(cardsInDashboard);
                    txtHand.setText(HAND + buildStringHand(hand));
                }
            });
        }
    }

    private void loadDashboard(){
        dashboard[0] = findViewById(R.id.boton00);
        dashboard[1] = findViewById(R.id.boton01);
        dashboard[2] = findViewById(R.id.boton02);
        dashboard[3] = findViewById(R.id.boton03);
        dashboard[4] = findViewById(R.id.boton04);
        dashboard[5] = findViewById(R.id.boton05);
        dashboard[6] = findViewById(R.id.boton06);
        dashboard[7] = findViewById(R.id.boton07);
        dashboard[8] = findViewById(R.id.boton08);
        dashboard[9] = findViewById(R.id.boton09);
        dashboard[10] = findViewById(R.id.boton10);
        dashboard[11] = findViewById(R.id.boton11);
        dashboard[12] = findViewById(R.id.boton12);
        dashboard[13] = findViewById(R.id.boton13);
        dashboard[14] = findViewById(R.id.boton14);
        dashboard[15] = findViewById(R.id.boton15);
    }

    private void loadButtons(){
        playBtn = findViewById(R.id.btnInit);
        cleanBtn = findViewById(R.id.btnClean);
        closeBtn = findViewById(R.id.btnQuit);

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play();
            }
        });

        cleanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clean();
            }
        });

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    // Play Game
    private void play(){
        int[] cards = convertIntegers(cardsInDashboard);

        boolean result = isStairs(cards);
        showResult(cards, result);
    }

    // Clean data
    private void clean() {
        txtHand.setText(HAND + getString(R.string.message_select_cards));
        cardsInDashboard.clear();
        playBtn.setEnabled(true);
    }
    
    // Print the hand and whether or not it is a ladder on the console
    private void showResult(int[] hand, boolean result) {
        txtHand.setText(HAND + buildStringHand(hand));
        String message;
        
        if(result)
            message = getString(R.string.message_winner);
        else
            message = getString(R.string.message_loser);

        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
        toast.show();

        playBtn.setEnabled(false);
    }

    // Build string of hand string
    private static String buildStringHand(int[] hand){
        String cards = "[";
        for (int i = 0; i < hand.length; i++) {
            if(i == hand.length - 1)
                cards += hand[i] + "]";
            else
                cards += hand[i] + ",";
        }
        return cards;
    }

    // Determines whether a card game in a list represents a poker straight
    private static boolean isStairs(int[] hand) {
        int[] cards = removeDuplicates(hand);

        if (cards.length < 5)
            return false;

        Arrays.sort(cards);

        for (int i = 0; i < cards.length; i++) {
            int counter = 1;

            if(cards[i] == 2 && cards[cards.length - 1] == 14)
                counter++;

            for (int j = i + 1; j < cards.length; j++) {
                if(cards[j] != cards[j-1] + 1)
                    break;

                counter++;
            }

            if (counter >= 5)
                return true;
        }
        return false;
    }

    // Remove duplicate elements from an array
    private static int[] removeDuplicates(int[] arr) {
        return Arrays.stream(arr)
                .distinct()
                .toArray();
    }

    // Convert Integer List to int Array
    public static int[] convertIntegers(List<Integer> integers)
    {
        int[] ret = new int[integers.size()];
        for (int i=0; i < ret.length; i++)
            ret[i] = integers.get(i).intValue();

        return ret;
    }

    private void loadText(){
        txtHand = findViewById(R.id.tvHand);
        txtHand.setText(HAND + getString(R.string.message_select_cards));
    }

    private void loadPictures(){
        pictures = new int[]{
            R.drawable.icon2,
            R.drawable.icon3,
            R.drawable.icon4,
            R.drawable.icon5,
            R.drawable.icon6,
            R.drawable.icon7,
            R.drawable.icon8,
            R.drawable.icon9,
            R.drawable.icon10,
            R.drawable.icon11,
            R.drawable.icon12,
            R.drawable.icon13,
            R.drawable.icon14,
            R.drawable.iconwall,
            R.drawable.iconwall,
            R.drawable.iconwall,
        };
    }

    private void addCards(int index){
        if(cardsInDashboard.size() > 7){
            Toast toast = Toast.makeText(getApplicationContext(), R.string.message_max_cards, Toast.LENGTH_LONG);
            toast.show();
            return;
        }

        for (Integer i : cardsMap.keySet()) {
            if(index == i) {
                cardsInDashboard.add(cardsMap.get(i));
            }
        }
    }
}
