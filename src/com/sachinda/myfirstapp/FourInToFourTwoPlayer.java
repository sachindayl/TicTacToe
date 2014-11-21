package com.sachinda.myfirstapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.ImageView;

public class FourInToFourTwoPlayer extends Activity implements View.OnClickListener {

    //buttons initialized
    Button one, two, three, four, five, six, seven, eight, nine, ten, eleven, twelve, thirteen, fourteen, fifteen, sixteen;

    boolean oneChk = false;
    boolean twoChk = false;
    boolean threeChk = false;
    boolean fourChk = false;
    boolean fiveChk = false;
    boolean sixChk = false;
    boolean sevenChk = false;
    boolean eightChk = false;
    boolean nineChk = false;

    //counter to recognize which opponent
    int counter;

    String cross = "Cross";
    String circle = "Circle";
    AlertDialog.Builder alert;
    AlertDialog dialog;
    Intent intent, end;
    Thread th;

    ImageView startGame;
    ImageView p1;
    ImageView p2;
    ImageView p1Wins;
    ImageView p2Wins;
    ImageView draw;

    AlphaAnimation startTheGame;
    AlphaAnimation p1turn;
    AlphaAnimation p2turn;
    AlphaAnimation p1winner;
    AlphaAnimation p2winner;
    AlphaAnimation itsdraw;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        int style = ((GlobalVariable) this.getApplication()).getStyle();
        if (style == 1) {
            setContentView(R.layout.game_start);
        } else if (style == 2) {
            setContentView(R.layout.activity_one_player_activity3);
        }

        intent = getIntent();
        end = new Intent(FourInToFourTwoPlayer.this, StartScreen.class);

        one = (Button) findViewById(R.id.empty_button);
        two = (Button) findViewById(R.id.empty_button2);
        three = (Button) findViewById(R.id.empty_button3);
        four = (Button) findViewById(R.id.empty_button4);
        five = (Button) findViewById(R.id.empty_button5);
        six = (Button) findViewById(R.id.empty_button6);
        seven = (Button) findViewById(R.id.empty_button7);
        eight = (Button) findViewById(R.id.empty_button8);
        nine = (Button) findViewById(R.id.empty_button9);
        ten = (Button) findViewById(R.id.empty_button10);
        eleven = (Button) findViewById(R.id.empty_button11);
        twelve = (Button) findViewById(R.id.empty_button12);
        thirteen = (Button) findViewById(R.id.empty_button13);
        fourteen = (Button) findViewById(R.id.empty_button14);
        fifteen = (Button) findViewById(R.id.empty_button15);
        sixteen = (Button) findViewById(R.id.empty_button16);

        startGame = (ImageView) findViewById(R.id.start_the_game);
        p1 = (ImageView) findViewById(R.id.player_one);
        p2 = (ImageView) findViewById(R.id.player_two);
        p1Wins = (ImageView) findViewById(R.id.player_one_wins);
        p2Wins = (ImageView) findViewById(R.id.player_two_wins);
        draw = (ImageView) findViewById(R.id.its_draw);

        startTheGame = new AlphaAnimation(1.0f, 0.0f);
        p1turn = new AlphaAnimation(1.0f, 0.0f);
        p2turn = new AlphaAnimation(1.0f, 0.0f);
        p1winner = new AlphaAnimation(1.0f, 0.0f);
        p2winner = new AlphaAnimation(1.0f, 0.0f);
        itsdraw = new AlphaAnimation(1.0f, 0.0f);

        counter = 0;

        one.setOnClickListener(this);
        one.setEnabled(true);
        two.setOnClickListener(this);
        two.setEnabled(true);
        three.setOnClickListener(this);
        three.setEnabled(true);
        four.setOnClickListener(this);
        four.setEnabled(true);
        five.setOnClickListener(this);
        five.setEnabled(true);
        six.setOnClickListener(this);
        six.setEnabled(true);
        seven.setOnClickListener(this);
        seven.setEnabled(true);
        eight.setOnClickListener(this);
        eight.setEnabled(true);
        nine.setOnClickListener(this);
        nine.setEnabled(true);
        ten.setOnClickListener(this);
        ten.setEnabled(true);
        eleven.setOnClickListener(this);
        eleven.setEnabled(true);
        twelve.setOnClickListener(this);
        twelve.setEnabled(true);
        thirteen.setOnClickListener(this);
        thirteen.setEnabled(true);
        fourteen.setOnClickListener(this);
        fourteen.setEnabled(true);
        fifteen.setOnClickListener(this);
        fifteen.setEnabled(true);
        sixteen.setOnClickListener(this);
        sixteen.setEnabled(true);


        animationSetup();
    }

    //delays the replay or quit dialog box
    public void delayDialog() {
        th = new Thread() {
            public void run() {
                try {
                    Thread.sleep(2500);
                    FourInToFourTwoPlayer.this.runOnUiThread(new Runnable() {
                        public void run() {
                            alert = new AlertDialog.Builder(FourInToFourTwoPlayer.this);
                            alert.setMessage("Do you want to start a new game?")
                                    .setCancelable(false)
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            finish();
                                            startActivity(intent);

                                        }
                                    })
                                    .setNegativeButton("No", new DialogInterface.OnClickListener() {

                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                            finish();
                                            startActivity(end);

                                        }
                                    });

                            dialog = alert.show();
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        th.start();
    }

    //shows what happens after button click
    @Override
    public void onClick(View v) {
        //using style to recognize which theme is being used
        int style = ((GlobalVariable) this.getApplication()).getStyle();
        switch (v.getId()) {
            case R.id.empty_button:

                if (one.isEnabled()) {
                    oneChk = true;
                    if (counter == 0) {
                        if (style == 1) {
                            v.setBackgroundResource(R.drawable.redsquarecircle);
                        } else if (style == 2) {
                            v.setBackgroundResource(R.drawable.bsbluesquarecircle);
                        }
                        counter++;
                        v.setTag(circle);

                        //checks for 1,2,3,4 pattern
                        if (v.getTag().equals(two.getTag())
                                && v.getTag().equals(three.getTag()) && v.getTag().equals(four.getTag())) {
                            if (style == 1) {
                                one.setBackgroundResource(R.drawable.redcircle);
                                two.setBackgroundResource(R.drawable.redcircle);
                                three.setBackgroundResource(R.drawable.redcircle);
                                four.setBackgroundResource(R.drawable.redcircle);
                            } else if (style == 2) {
                                one.setBackgroundResource(R.drawable.bscirclewon);
                                two.setBackgroundResource(R.drawable.bscirclewon);
                                three.setBackgroundResource(R.drawable.bscirclewon);
                                four.setBackgroundResource(R.drawable.bscirclewon);
                            }
                            //locks all buttons after there is a winner
                            disableAllButtons();
                            p1Wins.startAnimation(p1winner);
                            delayDialog();

                        } else if (v.getTag().equals(five.getTag())
                                && v.getTag().equals(nine.getTag()) && v.getTag().equals(thirteen.getTag())) {
                            if (style == 1) {
                                one.setBackgroundResource(R.drawable.redcircle);
                                five.setBackgroundResource(R.drawable.redcircle);
                                nine.setBackgroundResource(R.drawable.redcircle);
                                thirteen.setBackgroundResource(R.drawable.redcircle);
                            } else if (style == 2) {
                                one.setBackgroundResource(R.drawable.bscirclewon);
                                five.setBackgroundResource(R.drawable.bscirclewon);
                                nine.setBackgroundResource(R.drawable.bscirclewon);
                                thirteen.setBackgroundResource(R.drawable.bscirclewon);
                            }

                            disableAllButtons();

                            p1Wins.startAnimation(p1winner);
                            delayDialog();
                        } else if (v.getTag().equals(five.getTag())
                                && v.getTag().equals(nine.getTag())) {
                            if (style == 1) {
                                one.setBackgroundResource(R.drawable.redcircle);
                                five.setBackgroundResource(R.drawable.redcircle);
                                nine.setBackgroundResource(R.drawable.redcircle);
                            } else if (style == 2) {
                                one.setBackgroundResource(R.drawable.bscirclewon);
                                five.setBackgroundResource(R.drawable.bscirclewon);
                                nine.setBackgroundResource(R.drawable.bscirclewon);
                            }

                            disableAllButtons();

                            p1Wins.startAnimation(p1winner);
                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk) {
                                p2.setAnimation(p2turn);
                            } else {
                                disableAllButtons();

                                draw.startAnimation(itsdraw);
                                delayDialog();
                            }

                        }

                    } else {
                        if (style == 1) {
                            v.setBackgroundResource(R.drawable.redsquarecross);
                        } else if (style == 2) {
                            v.setBackgroundResource(R.drawable.bsbluesquarecross);
                        }
                        counter--;
                        v.setTag(cross);
                        if (v.getTag().equals(two.getTag())
                                && v.getTag().equals(three.getTag())) {
                            if (style == 1) {
                                one.setBackgroundResource(R.drawable.redcross);
                                two.setBackgroundResource(R.drawable.redcross);
                                three.setBackgroundResource(R.drawable.redcross);
                            } else if (style == 2) {
                                one.setBackgroundResource(R.drawable.bscrosswon);
                                two.setBackgroundResource(R.drawable.bscrosswon);
                                three.setBackgroundResource(R.drawable.bscrosswon);
                            }

                            disableAllButtons();

                            p2Wins.startAnimation(p2winner);
                            delayDialog();
                        } else if (v.getTag().equals(four.getTag())
                                && v.getTag().equals(seven.getTag())) {
                            if (style == 1) {
                                one.setBackgroundResource(R.drawable.redcross);
                                four.setBackgroundResource(R.drawable.redcross);
                                seven.setBackgroundResource(R.drawable.redcross);
                            } else if (style == 2) {
                                one.setBackgroundResource(R.drawable.bscrosswon);
                                four.setBackgroundResource(R.drawable.bscrosswon);
                                seven.setBackgroundResource(R.drawable.bscrosswon);
                            }

                            disableAllButtons();

                            p2Wins.startAnimation(p2winner);
                            delayDialog();
                        } else if (v.getTag().equals(five.getTag())
                                && v.getTag().equals(nine.getTag())) {
                            if (style == 1) {
                                one.setBackgroundResource(R.drawable.redcross);
                                five.setBackgroundResource(R.drawable.redcross);
                                nine.setBackgroundResource(R.drawable.redcross);
                            } else if (style == 2) {
                                one.setBackgroundResource(R.drawable.bscrosswon);
                                five.setBackgroundResource(R.drawable.bscrosswon);
                                nine.setBackgroundResource(R.drawable.bscrosswon);
                            }

                            disableAllButtons();

                            p2Wins.startAnimation(p2winner);
                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk) {
                                p1.startAnimation(p1turn);
                            } else {
                                disableAllButtons();

                                draw.startAnimation(itsdraw);
                                delayDialog();
                            }

                        }

                    }

                    one.setEnabled(false);
                }
                break;

            case R.id.empty_button2:

                if (two.isEnabled()) {
                    twoChk = true;
                    if (counter == 0) {
                        if (style == 1) {
                            v.setBackgroundResource(R.drawable.blacksquarecircle);
                        } else if (style == 2) {
                            v.setBackgroundResource(R.drawable.bswhitesquarecircle);
                        }
                        counter++;
                        v.setTag(circle);
                        if (v.getTag().equals(one.getTag())
                                && v.getTag().equals(three.getTag())) {
                            if (style == 1) {
                                one.setBackgroundResource(R.drawable.redcircle);
                                two.setBackgroundResource(R.drawable.redcircle);
                                three.setBackgroundResource(R.drawable.redcircle);
                            } else if (style == 2) {
                                one.setBackgroundResource(R.drawable.bscirclewon);
                                two.setBackgroundResource(R.drawable.bscirclewon);
                                three.setBackgroundResource(R.drawable.bscirclewon);
                            }

                            disableAllButtons();

                            p1Wins.startAnimation(p1winner);
                            delayDialog();
                        } else if (v.getTag().equals(five.getTag())
                                && v.getTag().equals(eight.getTag())) {
                            if (style == 1) {
                                two.setBackgroundResource(R.drawable.redcircle);
                                five.setBackgroundResource(R.drawable.redcircle);
                                eight.setBackgroundResource(R.drawable.redcircle);
                            } else if (style == 2) {
                                two.setBackgroundResource(R.drawable.bscirclewon);
                                five.setBackgroundResource(R.drawable.bscirclewon);
                                eight.setBackgroundResource(R.drawable.bscirclewon);
                            }
                            disableAllButtons();

                            p1Wins.startAnimation(p1winner);
                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk) {
                                p2.startAnimation(p2turn);
                            } else {
                                disableAllButtons();

                                draw.startAnimation(itsdraw);
                                delayDialog();
                            }

                        }

                    } else {
                        if (style == 1) {
                            v.setBackgroundResource(R.drawable.blacksquarecross);
                        } else if (style == 2) {
                            v.setBackgroundResource(R.drawable.bswhitesquarecross);
                        }
                        counter--;
                        v.setTag(cross);
                        if (v.getTag().equals(one.getTag())
                                && v.getTag().equals(three.getTag())) {
                            if (style == 1) {
                                one.setBackgroundResource(R.drawable.redcross);
                                two.setBackgroundResource(R.drawable.redcross);
                                three.setBackgroundResource(R.drawable.redcross);
                            } else if (style == 2) {
                                one.setBackgroundResource(R.drawable.bscrosswon);
                                two.setBackgroundResource(R.drawable.bscrosswon);
                                three.setBackgroundResource(R.drawable.bscrosswon);
                            }

                            disableAllButtons();

                            p2Wins.startAnimation(p2winner);
                            delayDialog();
                        } else if (v.getTag().equals(five.getTag())
                                && v.getTag().equals(eight.getTag())) {
                            if (style == 1) {
                                two.setBackgroundResource(R.drawable.redcross);
                                five.setBackgroundResource(R.drawable.redcross);
                                eight.setBackgroundResource(R.drawable.redcross);
                            } else if (style == 2) {
                                two.setBackgroundResource(R.drawable.bscrosswon);
                                five.setBackgroundResource(R.drawable.bscrosswon);
                                eight.setBackgroundResource(R.drawable.bscrosswon);
                            }

                            disableAllButtons();

                            p2Wins.startAnimation(p2winner);
                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk) {
                                p1.startAnimation(p1turn);
                            } else {
                                disableAllButtons();

                                draw.startAnimation(itsdraw);
                                delayDialog();
                            }

                        }
                    }

                    two.setEnabled(false);

                }
                break;

            case R.id.empty_button3:

                if (three.isEnabled()) {
                    threeChk = true;
                    if (counter == 0) {
                        if (style == 1) {
                            v.setBackgroundResource(R.drawable.redsquarecircle);
                        } else if (style == 2) {
                            v.setBackgroundResource(R.drawable.bsbluesquarecircle);
                        }
                        counter++;
                        v.setTag(circle);

                        if (v.getTag().equals(one.getTag())
                                && v.getTag().equals(two.getTag())) {
                            if (style == 1) {
                                one.setBackgroundResource(R.drawable.redcircle);
                                two.setBackgroundResource(R.drawable.redcircle);
                                three.setBackgroundResource(R.drawable.redcircle);
                            } else if (style == 2) {
                                one.setBackgroundResource(R.drawable.bscirclewon);
                                two.setBackgroundResource(R.drawable.bscirclewon);
                                three.setBackgroundResource(R.drawable.bscirclewon);
                            }
                            disableAllButtons();

                            p1Wins.startAnimation(p1winner);
                            delayDialog();
                        } else if (v.getTag().equals(five.getTag())
                                && v.getTag().equals(seven.getTag())) {
                            if (style == 1) {
                                three.setBackgroundResource(R.drawable.redcircle);
                                five.setBackgroundResource(R.drawable.redcircle);
                                seven.setBackgroundResource(R.drawable.redcircle);
                            } else if (style == 2) {
                                three.setBackgroundResource(R.drawable.bscirclewon);
                                five.setBackgroundResource(R.drawable.bscirclewon);
                                seven.setBackgroundResource(R.drawable.bscirclewon);
                            }
                            disableAllButtons();

                            p1Wins.startAnimation(p1winner);
                            delayDialog();
                        } else if (v.getTag().equals(six.getTag())
                                && v.getTag().equals(nine.getTag())) {
                            if (style == 1) {
                                three.setBackgroundResource(R.drawable.redcircle);
                                six.setBackgroundResource(R.drawable.redcircle);
                                nine.setBackgroundResource(R.drawable.redcircle);
                            } else if (style == 2) {
                                three.setBackgroundResource(R.drawable.bscirclewon);
                                six.setBackgroundResource(R.drawable.bscirclewon);
                                nine.setBackgroundResource(R.drawable.bscirclewon);
                            }
                            disableAllButtons();

                            p1Wins.startAnimation(p1winner);
                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk) {
                                p2.startAnimation(p2turn);
                            } else {
                                disableAllButtons();

                                draw.startAnimation(itsdraw);
                                delayDialog();
                            }

                        }
                    } else {
                        if (style == 1) {
                            v.setBackgroundResource(R.drawable.redsquarecross);
                        } else if (style == 2) {
                            v.setBackgroundResource(R.drawable.bsbluesquarecross);
                        }
                        counter--;
                        v.setTag(cross);
                        if (v.getTag().equals(one.getTag())
                                && v.getTag().equals(two.getTag())) {
                            if (style == 1) {
                                one.setBackgroundResource(R.drawable.redcross);
                                two.setBackgroundResource(R.drawable.redcross);
                                three.setBackgroundResource(R.drawable.redcross);
                            } else if (style == 2) {
                                one.setBackgroundResource(R.drawable.bscrosswon);
                                two.setBackgroundResource(R.drawable.bscrosswon);
                                three.setBackgroundResource(R.drawable.bscrosswon);
                            }
                            disableAllButtons();

                            p2Wins.startAnimation(p2winner);
                            delayDialog();
                        } else if (v.getTag().equals(five.getTag())
                                && v.getTag().equals(seven.getTag())) {
                            if (style == 1) {
                                three.setBackgroundResource(R.drawable.redcross);
                                five.setBackgroundResource(R.drawable.redcross);
                                seven.setBackgroundResource(R.drawable.redcross);
                            } else if (style == 2) {
                                three.setBackgroundResource(R.drawable.bscrosswon);
                                five.setBackgroundResource(R.drawable.bscrosswon);
                                seven.setBackgroundResource(R.drawable.bscrosswon);
                            }
                            disableAllButtons();

                            p2Wins.startAnimation(p2winner);
                            delayDialog();
                        } else if (v.getTag().equals(six.getTag())
                                && v.getTag().equals(nine.getTag())) {
                            if (style == 1) {
                                three.setBackgroundResource(R.drawable.redcross);
                                six.setBackgroundResource(R.drawable.redcross);
                                nine.setBackgroundResource(R.drawable.redcross);
                            } else if (style == 2) {
                                three.setBackgroundResource(R.drawable.bscrosswon);
                                six.setBackgroundResource(R.drawable.bscrosswon);
                                nine.setBackgroundResource(R.drawable.bscrosswon);
                            }
                            disableAllButtons();

                            p2Wins.startAnimation(p2winner);
                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk) {
                                p1.startAnimation(p1turn);
                            } else {
                                disableAllButtons();

                                draw.startAnimation(itsdraw);
                                delayDialog();
                            }

                        }

                    }

                    three.setEnabled(false);

                }
                break;

            case R.id.empty_button4:
                if (four.isEnabled()) {
                    fourChk = true;
                    if (counter == 0) {
                        if (style == 1) {
                            v.setBackgroundResource(R.drawable.blacksquarecircle);
                        } else if (style == 2) {
                            v.setBackgroundResource(R.drawable.bswhitesquarecircle);
                        }
                        counter++;
                        v.setTag(circle);
                        if (v.getTag().equals(one.getTag())
                                && v.getTag().equals(seven.getTag())) {
                            if (style == 1) {
                                one.setBackgroundResource(R.drawable.redcircle);
                                four.setBackgroundResource(R.drawable.redcircle);
                                seven.setBackgroundResource(R.drawable.redcircle);
                            } else if (style == 2) {
                                one.setBackgroundResource(R.drawable.bscirclewon);
                                four.setBackgroundResource(R.drawable.bscirclewon);
                                seven.setBackgroundResource(R.drawable.bscirclewon);
                            }

                            disableAllButtons();

                            p1Wins.startAnimation(p1winner);
                            delayDialog();
                        } else if (v.getTag().equals(five.getTag())
                                && v.getTag().equals(six.getTag())) {
                            if (style == 1) {
                                four.setBackgroundResource(R.drawable.redcircle);
                                five.setBackgroundResource(R.drawable.redcircle);
                                six.setBackgroundResource(R.drawable.redcircle);
                            } else if (style == 2) {
                                four.setBackgroundResource(R.drawable.bscirclewon);
                                five.setBackgroundResource(R.drawable.bscirclewon);
                                six.setBackgroundResource(R.drawable.bscirclewon);
                            }
                            disableAllButtons();

                            p1Wins.startAnimation(p1winner);
                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk) {
                                p2.startAnimation(p2turn);
                            } else {
                                disableAllButtons();

                                draw.startAnimation(itsdraw);
                                delayDialog();
                            }

                        }
                    } else {
                        if (style == 1) {
                            v.setBackgroundResource(R.drawable.blacksquarecross);
                        } else if (style == 2) {
                            v.setBackgroundResource(R.drawable.bswhitesquarecross);
                        }
                        counter--;
                        v.setTag(cross);
                        if (v.getTag().equals(one.getTag())
                                && v.getTag().equals(seven.getTag())) {
                            if (style == 1) {
                                one.setBackgroundResource(R.drawable.redcross);
                                four.setBackgroundResource(R.drawable.redcross);
                                seven.setBackgroundResource(R.drawable.redcross);
                            } else if (style == 2) {
                                one.setBackgroundResource(R.drawable.bscrosswon);
                                four.setBackgroundResource(R.drawable.bscrosswon);
                                seven.setBackgroundResource(R.drawable.bscrosswon);
                            }

                            disableAllButtons();

                            p2Wins.startAnimation(p2winner);
                            delayDialog();
                        } else if (v.getTag().equals(five.getTag())
                                && v.getTag().equals(six.getTag())) {
                            if (style == 1) {
                                four.setBackgroundResource(R.drawable.redcross);
                                five.setBackgroundResource(R.drawable.redcross);
                                six.setBackgroundResource(R.drawable.redcross);
                            } else if (style == 2) {
                                four.setBackgroundResource(R.drawable.bscrosswon);
                                five.setBackgroundResource(R.drawable.bscrosswon);
                                six.setBackgroundResource(R.drawable.bscrosswon);
                            }
                            disableAllButtons();

                            p2Wins.startAnimation(p2winner);
                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk) {
                                p1.startAnimation(p1turn);
                            } else {
                                disableAllButtons();

                                draw.startAnimation(itsdraw);
                                delayDialog();
                            }

                        }
                    }
                    four.setEnabled(false);
                }
                break;

            case R.id.empty_button5:
                if (five.isEnabled()) {
                    fiveChk = true;
                    if (counter == 0) {
                        if (style == 1) {
                            v.setBackgroundResource(R.drawable.redsquarecircle);
                        } else if (style == 2) {
                            v.setBackgroundResource(R.drawable.bsbluesquarecircle);
                        }
                        counter++;
                        v.setTag(circle);

                        if (v.getTag().equals(one.getTag())
                                && v.getTag().equals(nine.getTag())) {
                            if (style == 1) {
                                one.setBackgroundResource(R.drawable.redcircle);
                                five.setBackgroundResource(R.drawable.redcircle);
                                nine.setBackgroundResource(R.drawable.redcircle);
                            } else if (style == 2) {
                                one.setBackgroundResource(R.drawable.bscirclewon);
                                five.setBackgroundResource(R.drawable.bscirclewon);
                                nine.setBackgroundResource(R.drawable.bscirclewon);
                            }

                            disableAllButtons();

                            p1Wins.startAnimation(p1winner);
                            delayDialog();
                        } else if (v.getTag().equals(two.getTag())
                                && v.getTag().equals(eight.getTag())) {
                            if (style == 1) {
                                two.setBackgroundResource(R.drawable.redcircle);
                                five.setBackgroundResource(R.drawable.redcircle);
                                eight.setBackgroundResource(R.drawable.redcircle);
                            } else if (style == 2) {
                                two.setBackgroundResource(R.drawable.bscirclewon);
                                five.setBackgroundResource(R.drawable.bscirclewon);
                                eight.setBackgroundResource(R.drawable.bscirclewon);
                            }

                            disableAllButtons();

                            p1Wins.startAnimation(p1winner);
                            delayDialog();
                        } else if (v.getTag().equals(three.getTag())
                                && v.getTag().equals(seven.getTag())) {
                            if (style == 1) {
                                three.setBackgroundResource(R.drawable.redcircle);
                                five.setBackgroundResource(R.drawable.redcircle);
                                seven.setBackgroundResource(R.drawable.redcircle);
                            } else if (style == 2) {
                                three.setBackgroundResource(R.drawable.bscirclewon);
                                five.setBackgroundResource(R.drawable.bscirclewon);
                                seven.setBackgroundResource(R.drawable.bscirclewon);
                            }
                            disableAllButtons();

                            p1Wins.startAnimation(p1winner);
                            delayDialog();
                        } else if (v.getTag().equals(four.getTag())
                                && v.getTag().equals(six.getTag())) {
                            if (style == 1) {
                                four.setBackgroundResource(R.drawable.redcircle);
                                five.setBackgroundResource(R.drawable.redcircle);
                                six.setBackgroundResource(R.drawable.redcircle);
                            } else if (style == 2) {
                                four.setBackgroundResource(R.drawable.bscirclewon);
                                five.setBackgroundResource(R.drawable.bscirclewon);
                                six.setBackgroundResource(R.drawable.bscirclewon);
                            }
                            disableAllButtons();

                            p1Wins.startAnimation(p1winner);
                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk) {
                                p2.startAnimation(p2turn);
                            } else {
                                disableAllButtons();

                                draw.startAnimation(itsdraw);
                                delayDialog();
                            }

                        }
                    } else {
                        if (style == 1) {
                            v.setBackgroundResource(R.drawable.redsquarecross);
                        } else if (style == 2) {
                            v.setBackgroundResource(R.drawable.bsbluesquarecross);
                        }
                        counter--;
                        v.setTag(cross);
                        if (v.getTag().equals(one.getTag())
                                && v.getTag().equals(nine.getTag())) {
                            if (style == 1) {
                                one.setBackgroundResource(R.drawable.redcross);
                                five.setBackgroundResource(R.drawable.redcross);
                                nine.setBackgroundResource(R.drawable.redcross);
                            } else if (style == 2) {
                                one.setBackgroundResource(R.drawable.bscrosswon);
                                five.setBackgroundResource(R.drawable.bscrosswon);
                                nine.setBackgroundResource(R.drawable.bscrosswon);
                            }
                            disableAllButtons();

                            p2Wins.startAnimation(p2winner);
                            delayDialog();
                        } else if (v.getTag().equals(two.getTag())
                                && v.getTag().equals(eight.getTag())) {
                            if (style == 1) {
                                two.setBackgroundResource(R.drawable.redcross);
                                five.setBackgroundResource(R.drawable.redcross);
                                eight.setBackgroundResource(R.drawable.redcross);
                            } else if (style == 2) {
                                two.setBackgroundResource(R.drawable.bscrosswon);
                                five.setBackgroundResource(R.drawable.bscrosswon);
                                eight.setBackgroundResource(R.drawable.bscrosswon);
                            }
                            disableAllButtons();

                            p2Wins.startAnimation(p2winner);
                            delayDialog();
                        } else if (v.getTag().equals(three.getTag())
                                && v.getTag().equals(seven.getTag())) {
                            if (style == 1) {
                                three.setBackgroundResource(R.drawable.redcross);
                                five.setBackgroundResource(R.drawable.redcross);
                                seven.setBackgroundResource(R.drawable.redcross);
                            } else if (style == 2) {
                                three.setBackgroundResource(R.drawable.bscrosswon);
                                five.setBackgroundResource(R.drawable.bscrosswon);
                                seven.setBackgroundResource(R.drawable.bscrosswon);
                            }

                            disableAllButtons();

                            p2Wins.startAnimation(p2winner);
                            delayDialog();
                        } else if (v.getTag().equals(four.getTag())
                                && v.getTag().equals(six.getTag())) {
                            if (style == 1) {
                                four.setBackgroundResource(R.drawable.redcross);
                                five.setBackgroundResource(R.drawable.redcross);
                                six.setBackgroundResource(R.drawable.redcross);
                            } else if (style == 2) {
                                four.setBackgroundResource(R.drawable.bscrosswon);
                                five.setBackgroundResource(R.drawable.bscrosswon);
                                six.setBackgroundResource(R.drawable.bscrosswon);
                            }

                            disableAllButtons();

                            p2Wins.startAnimation(p2winner);
                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk) {
                                p1.startAnimation(p1turn);
                            } else {
                                disableAllButtons();

                                draw.startAnimation(itsdraw);
                                delayDialog();
                            }

                        }
                    }

                    five.setEnabled(false);

                }
                break;

            case R.id.empty_button6:
                if (six.isEnabled()) {
                    sixChk = true;
                    if (counter == 0) {
                        if (style == 1) {
                            v.setBackgroundResource(R.drawable.blacksquarecircle);
                        } else if (style == 2) {
                            v.setBackgroundResource(R.drawable.bswhitesquarecircle);
                        }
                        counter++;
                        v.setTag(circle);
                        if (v.getTag().equals(three.getTag())
                                && v.getTag().equals(nine.getTag())) {
                            if (style == 1) {
                                three.setBackgroundResource(R.drawable.redcircle);
                                six.setBackgroundResource(R.drawable.redcircle);
                                nine.setBackgroundResource(R.drawable.redcircle);
                            } else if (style == 2) {
                                three.setBackgroundResource(R.drawable.bscirclewon);
                                six.setBackgroundResource(R.drawable.bscirclewon);
                                nine.setBackgroundResource(R.drawable.bscirclewon);
                            }

                            disableAllButtons();

                            p1Wins.startAnimation(p1winner);
                            delayDialog();
                        } else if (v.getTag().equals(five.getTag())
                                && v.getTag().equals(four.getTag())) {
                            if (style == 1) {
                                four.setBackgroundResource(R.drawable.redcircle);
                                five.setBackgroundResource(R.drawable.redcircle);
                                six.setBackgroundResource(R.drawable.redcircle);
                            } else if (style == 2) {
                                four.setBackgroundResource(R.drawable.bscirclewon);
                                five.setBackgroundResource(R.drawable.bscirclewon);
                                six.setBackgroundResource(R.drawable.bscirclewon);
                            }

                            disableAllButtons();

                            p1Wins.startAnimation(p1winner);
                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk) {
                                p2.startAnimation(p2turn);
                            } else {
                                disableAllButtons();

                                draw.startAnimation(itsdraw);
                                delayDialog();
                            }

                        }
                    } else {
                        if (style == 1) {
                            v.setBackgroundResource(R.drawable.blacksquarecross);
                        } else if (style == 2) {
                            v.setBackgroundResource(R.drawable.bswhitesquarecross);
                        }
                        counter--;
                        v.setTag(cross);
                        if (v.getTag().equals(three.getTag())
                                && v.getTag().equals(nine.getTag())) {
                            if (style == 1) {
                                three.setBackgroundResource(R.drawable.redcross);
                                six.setBackgroundResource(R.drawable.redcross);
                                nine.setBackgroundResource(R.drawable.redcross);
                            } else if (style == 2) {
                                three.setBackgroundResource(R.drawable.bscrosswon);
                                six.setBackgroundResource(R.drawable.bscrosswon);
                                nine.setBackgroundResource(R.drawable.bscrosswon);
                            }

                            disableAllButtons();

                            p2Wins.startAnimation(p2winner);
                            delayDialog();
                        } else if (v.getTag().equals(five.getTag())
                                && v.getTag().equals(four.getTag())) {
                            if (style == 1) {
                                four.setBackgroundResource(R.drawable.redcross);
                                five.setBackgroundResource(R.drawable.redcross);
                                six.setBackgroundResource(R.drawable.redcross);
                            } else if (style == 2) {
                                four.setBackgroundResource(R.drawable.bscrosswon);
                                five.setBackgroundResource(R.drawable.bscrosswon);
                                six.setBackgroundResource(R.drawable.bscrosswon);
                            }

                            disableAllButtons();

                            p2Wins.startAnimation(p2winner);
                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk) {
                                p1.startAnimation(p1turn);
                            } else {
                                disableAllButtons();

                                draw.startAnimation(itsdraw);
                                delayDialog();
                            }

                        }
                    }

                    six.setEnabled(false);
                }
                break;

            case R.id.empty_button7:
                if (seven.isEnabled()) {
                    sevenChk = true;
                    if (counter == 0) {
                        if (style == 1) {
                            v.setBackgroundResource(R.drawable.redsquarecircle);
                        } else if (style == 2) {
                            v.setBackgroundResource(R.drawable.bsbluesquarecircle);
                        }
                        counter++;
                        v.setTag(circle);
                        if (v.getTag().equals(four.getTag())
                                && v.getTag().equals(one.getTag())) {
                            if (style == 1) {
                                one.setBackgroundResource(R.drawable.redcircle);
                                four.setBackgroundResource(R.drawable.redcircle);
                                seven.setBackgroundResource(R.drawable.redcircle);
                            } else if (style == 2) {
                                one.setBackgroundResource(R.drawable.bscirclewon);
                                four.setBackgroundResource(R.drawable.bscirclewon);
                                seven.setBackgroundResource(R.drawable.bscirclewon);
                            }

                            disableAllButtons();

                            p1Wins.startAnimation(p1winner);
                            delayDialog();
                        } else if (v.getTag().equals(five.getTag())
                                && v.getTag().equals(three.getTag())) {
                            if (style == 1) {
                                three.setBackgroundResource(R.drawable.redcircle);
                                five.setBackgroundResource(R.drawable.redcircle);
                                seven.setBackgroundResource(R.drawable.redcircle);
                            } else if (style == 2) {
                                three.setBackgroundResource(R.drawable.bscirclewon);
                                five.setBackgroundResource(R.drawable.bscirclewon);
                                seven.setBackgroundResource(R.drawable.bscirclewon);
                            }

                            disableAllButtons();

                            p1Wins.startAnimation(p1winner);
                            delayDialog();
                        } else if (v.getTag().equals(eight.getTag())
                                && v.getTag().equals(nine.getTag())) {
                            if (style == 1) {
                                seven.setBackgroundResource(R.drawable.redcircle);
                                eight.setBackgroundResource(R.drawable.redcircle);
                                nine.setBackgroundResource(R.drawable.redcircle);
                            } else if (style == 2) {
                                seven.setBackgroundResource(R.drawable.bscirclewon);
                                eight.setBackgroundResource(R.drawable.bscirclewon);
                                nine.setBackgroundResource(R.drawable.bscirclewon);
                            }

                            disableAllButtons();

                            p1Wins.startAnimation(p1winner);
                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk) {
                                p2.startAnimation(p2turn);
                            } else {

                                disableAllButtons();

                                draw.startAnimation(itsdraw);
                                delayDialog();
                            }

                        }
                    } else {
                        if (style == 1) {
                            v.setBackgroundResource(R.drawable.redsquarecross);
                        } else if (style == 2) {
                            v.setBackgroundResource(R.drawable.bsbluesquarecross);
                        }
                        counter--;
                        v.setTag(cross);
                        if (v.getTag().equals(four.getTag())
                                && v.getTag().equals(one.getTag())) {
                            if (style == 1) {
                                one.setBackgroundResource(R.drawable.redcross);
                                four.setBackgroundResource(R.drawable.redcross);
                                seven.setBackgroundResource(R.drawable.redcross);
                            } else if (style == 2) {
                                one.setBackgroundResource(R.drawable.bscrosswon);
                                four.setBackgroundResource(R.drawable.bscrosswon);
                                seven.setBackgroundResource(R.drawable.bscrosswon);
                            }


                            disableAllButtons();

                            p2Wins.startAnimation(p2winner);
                            delayDialog();
                        } else if (v.getTag().equals(five.getTag())
                                && v.getTag().equals(three.getTag())) {
                            if (style == 1) {
                                three.setBackgroundResource(R.drawable.redcross);
                                five.setBackgroundResource(R.drawable.redcross);
                                seven.setBackgroundResource(R.drawable.redcross);
                            } else if (style == 2) {
                                three.setBackgroundResource(R.drawable.bscrosswon);
                                five.setBackgroundResource(R.drawable.bscrosswon);
                                seven.setBackgroundResource(R.drawable.bscrosswon);
                            }


                            disableAllButtons();

                            p2Wins.startAnimation(p2winner);
                            delayDialog();
                        } else if (v.getTag().equals(eight.getTag())
                                && v.getTag().equals(nine.getTag())) {
                            if (style == 1) {
                                seven.setBackgroundResource(R.drawable.redcross);
                                eight.setBackgroundResource(R.drawable.redcross);
                                nine.setBackgroundResource(R.drawable.redcross);
                            } else if (style == 2) {
                                seven.setBackgroundResource(R.drawable.bscrosswon);
                                eight.setBackgroundResource(R.drawable.bscrosswon);
                                nine.setBackgroundResource(R.drawable.bscrosswon);
                            }


                            disableAllButtons();

                            p2Wins.startAnimation(p2winner);
                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk) {
                                p1.startAnimation(p1turn);
                            } else {

                                disableAllButtons();

                                draw.startAnimation(itsdraw);
                                delayDialog();
                            }

                        }
                    }

                    seven.setEnabled(false);
                }
                break;

            case R.id.empty_button8:
                if (eight.isEnabled()) {
                    eightChk = true;
                    if (counter == 0) {
                        if (style == 1) {
                            v.setBackgroundResource(R.drawable.blacksquarecircle);
                        } else if (style == 2) {
                            v.setBackgroundResource(R.drawable.bswhitesquarecircle);
                        }
                        counter++;
                        v.setTag(circle);
                        if (v.getTag().equals(five.getTag())
                                && v.getTag().equals(two.getTag())) {
                            if (style == 1) {
                                two.setBackgroundResource(R.drawable.redcircle);
                                five.setBackgroundResource(R.drawable.redcircle);
                                eight.setBackgroundResource(R.drawable.redcircle);
                            } else if (style == 2) {
                                two.setBackgroundResource(R.drawable.bscirclewon);
                                five.setBackgroundResource(R.drawable.bscirclewon);
                                eight.setBackgroundResource(R.drawable.bscirclewon);
                            }


                            disableAllButtons();

                            p1Wins.startAnimation(p1winner);
                            delayDialog();
                        } else if (v.getTag().equals(seven.getTag())
                                && v.getTag().equals(nine.getTag())) {
                            if (style == 1) {
                                seven.setBackgroundResource(R.drawable.redcircle);
                                eight.setBackgroundResource(R.drawable.redcircle);
                                nine.setBackgroundResource(R.drawable.redcircle);
                            } else if (style == 2) {
                                seven.setBackgroundResource(R.drawable.bscirclewon);
                                eight.setBackgroundResource(R.drawable.bscirclewon);
                                nine.setBackgroundResource(R.drawable.bscirclewon);
                            }


                            disableAllButtons();

                            p1Wins.startAnimation(p1winner);
                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk) {
                                p2.startAnimation(p2turn);
                            } else {

                                disableAllButtons();

                                draw.startAnimation(itsdraw);
                                delayDialog();
                            }

                        }
                    } else {
                        if (style == 1) {
                            v.setBackgroundResource(R.drawable.blacksquarecross);
                        } else if (style == 2) {
                            v.setBackgroundResource(R.drawable.bswhitesquarecross);
                        }
                        counter--;
                        v.setTag(cross);
                        if (v.getTag().equals(five.getTag())
                                && v.getTag().equals(two.getTag())) {
                            if (style == 1) {
                                two.setBackgroundResource(R.drawable.redcross);
                                five.setBackgroundResource(R.drawable.redcross);
                                eight.setBackgroundResource(R.drawable.redcross);
                            } else if (style == 2) {
                                two.setBackgroundResource(R.drawable.bscrosswon);
                                five.setBackgroundResource(R.drawable.bscrosswon);
                                eight.setBackgroundResource(R.drawable.bscrosswon);
                            }


                            disableAllButtons();

                            p2Wins.startAnimation(p2winner);
                            delayDialog();
                        } else if (v.getTag().equals(seven.getTag())
                                && v.getTag().equals(nine.getTag())) {
                            if (style == 1) {
                                seven.setBackgroundResource(R.drawable.redcross);
                                eight.setBackgroundResource(R.drawable.redcross);
                                nine.setBackgroundResource(R.drawable.redcross);
                            } else if (style == 2) {
                                seven.setBackgroundResource(R.drawable.bscrosswon);
                                eight.setBackgroundResource(R.drawable.bscrosswon);
                                nine.setBackgroundResource(R.drawable.bscrosswon);
                            }


                            disableAllButtons();

                            p2Wins.startAnimation(p2winner);
                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk) {
                                p1.startAnimation(p1turn);
                            } else {

                                disableAllButtons();

                                draw.startAnimation(itsdraw);
                                delayDialog();
                            }

                        }
                    }

                    eight.setEnabled(false);
                }
                break;

            case R.id.empty_button9:
                if (nine.isEnabled()) {
                    nineChk = true;
                    if (counter == 0) {
                        if (style == 1) {
                            v.setBackgroundResource(R.drawable.redsquarecircle);
                        } else if (style == 2) {
                            v.setBackgroundResource(R.drawable.bsbluesquarecircle);
                        }
                        counter++;
                        v.setTag(circle);
                        if (v.getTag().equals(three.getTag())
                                && v.getTag().equals(six.getTag())) {
                            if (style == 1) {
                                three.setBackgroundResource(R.drawable.redcircle);
                                six.setBackgroundResource(R.drawable.redcircle);
                                nine.setBackgroundResource(R.drawable.redcircle);
                            } else if (style == 2) {
                                three.setBackgroundResource(R.drawable.bscirclewon);
                                six.setBackgroundResource(R.drawable.bscirclewon);
                                nine.setBackgroundResource(R.drawable.bscirclewon);
                            }


                            disableAllButtons();

                            p1Wins.startAnimation(p1winner);
                            delayDialog();
                        } else if (v.getTag().equals(one.getTag())
                                && v.getTag().equals(five.getTag())) {
                            if (style == 1) {
                                one.setBackgroundResource(R.drawable.redcircle);
                                five.setBackgroundResource(R.drawable.redcircle);
                                nine.setBackgroundResource(R.drawable.redcircle);
                            } else if (style == 2) {
                                one.setBackgroundResource(R.drawable.bscirclewon);
                                five.setBackgroundResource(R.drawable.bscirclewon);
                                nine.setBackgroundResource(R.drawable.bscirclewon);
                            }


                            disableAllButtons();

                            p1Wins.startAnimation(p1winner);
                            delayDialog();
                        } else if (v.getTag().equals(seven.getTag())
                                && v.getTag().equals(eight.getTag())) {
                            if (style == 1) {
                                seven.setBackgroundResource(R.drawable.redcircle);
                                eight.setBackgroundResource(R.drawable.redcircle);
                                nine.setBackgroundResource(R.drawable.redcircle);
                            } else if (style == 2) {
                                seven.setBackgroundResource(R.drawable.bscirclewon);
                                eight.setBackgroundResource(R.drawable.bscirclewon);
                                nine.setBackgroundResource(R.drawable.bscirclewon);
                            }


                            disableAllButtons();

                            p1Wins.startAnimation(p1winner);
                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk) {
                                p2.startAnimation(p2turn);
                            } else {

                                disableAllButtons();

                                draw.startAnimation(itsdraw);
                                delayDialog();
                            }

                        }
                    } else {
                        if (style == 1) {
                            v.setBackgroundResource(R.drawable.redsquarecross);
                        } else if (style == 2) {
                            v.setBackgroundResource(R.drawable.bsbluesquarecross);
                        }
                        counter--;
                        v.setTag(cross);
                        if (v.getTag().equals(three.getTag())
                                && v.getTag().equals(six.getTag())) {
                            if (style == 1) {
                                three.setBackgroundResource(R.drawable.redcross);
                                six.setBackgroundResource(R.drawable.redcross);
                                nine.setBackgroundResource(R.drawable.redcross);
                            } else if (style == 2) {
                                three.setBackgroundResource(R.drawable.bscrosswon);
                                six.setBackgroundResource(R.drawable.bscrosswon);
                                nine.setBackgroundResource(R.drawable.bscrosswon);
                            }


                            disableAllButtons();

                            p2Wins.startAnimation(p2winner);
                            delayDialog();
                        } else if (v.getTag().equals(one.getTag())
                                && v.getTag().equals(five.getTag())) {
                            if (style == 1) {
                                one.setBackgroundResource(R.drawable.redcross);
                                five.setBackgroundResource(R.drawable.redcross);
                                nine.setBackgroundResource(R.drawable.redcross);
                            } else if (style == 2) {
                                one.setBackgroundResource(R.drawable.bscrosswon);
                                five.setBackgroundResource(R.drawable.bscrosswon);
                                nine.setBackgroundResource(R.drawable.bscrosswon);
                            }


                            disableAllButtons();

                            p2Wins.startAnimation(p2winner);
                            delayDialog();
                        } else if (v.getTag().equals(seven.getTag())
                                && v.getTag().equals(eight.getTag())) {
                            if (style == 1) {
                                seven.setBackgroundResource(R.drawable.redcross);
                                eight.setBackgroundResource(R.drawable.redcross);
                                nine.setBackgroundResource(R.drawable.redcross);
                            } else if (style == 2) {
                                seven.setBackgroundResource(R.drawable.bscrosswon);
                                eight.setBackgroundResource(R.drawable.bscrosswon);
                                nine.setBackgroundResource(R.drawable.bscrosswon);
                            }


                            disableAllButtons();

                            p2Wins.startAnimation(p2winner);
                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk) {
                                p1.startAnimation(p1turn);
                            } else {

                                disableAllButtons();

                                draw.startAnimation(itsdraw);
                                delayDialog();
                            }

                        }
                    }

                    nine.setEnabled(false);

                }
                break;
        }

    }

    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to end the game?")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                FourInToFourTwoPlayer.this.finish();
                                startActivity(end);

                            }
                        }).setNegativeButton("No", null).show();
    }

    public void disableAllButtons(){
        one.setEnabled(false);
        two.setEnabled(false);
        three.setEnabled(false);
        four.setEnabled(false);
        five.setEnabled(false);
        six.setEnabled(false);
        seven.setEnabled(false);
        eight.setEnabled(false);
        nine.setEnabled(false);
        ten.setEnabled(false);
        eleven.setEnabled(false);
        twelve.setEnabled(false);
        thirteen.setEnabled(false);
        fourteen.setEnabled(false);
        fifteen.setEnabled(false);
        sixteen.setEnabled(false);
    }
    public void animationSetup() {


        startTheGame.setDuration(3000);
        startTheGame.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationEnd(Animation a) {
                startGame.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub

            }
        });
        startGame.setAnimation(startTheGame);


        p1turn.setDuration(1000);
        p1turn.setStartOffset(500);
        p1turn.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationEnd(Animation a) {

                p1.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub

            }
        });


        p2turn.setDuration(1000);
        p2turn.setStartOffset(500);
        p2turn.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationEnd(Animation a) {

                p2.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub

            }

        });


        p1winner.setDuration(5000);
        p1winner.setStartOffset(1000);
        p1winner.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationEnd(Animation a) {
                p1Wins.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub

            }
        });


        p2winner.setDuration(5000);
        p2winner.setStartOffset(1000);
        p2winner.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationEnd(Animation a) {
                p2Wins.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub

            }
        });


        itsdraw.setStartOffset(1000);
        itsdraw.setDuration(5000);
        itsdraw.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationEnd(Animation a) {
                p2.setVisibility(View.GONE);
                p1.setVisibility(View.GONE);
                p1Wins.setVisibility(View.GONE);
                p2Wins.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub

            }
        });
    }
}
