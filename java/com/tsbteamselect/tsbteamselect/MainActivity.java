package com.tsbteamselect.tsbteamselect;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class MainActivity extends ActionBarActivity {

    LinearLayout MainLinearLayout,TeamSelectLinearLayout;
    TextView MainTextView, P1P2Header, P1MatchupTextView, P2MatchupTextView, MatchupVSTextView;
    TextView Team1TextView, Team2TextView, Team3TextView, MatchupAdvantageTextView;
    ImageView Team1HelmetImageView, Team2HelmetImageView, Team3HelmetImageView;
    ImageView P1MatchupHelmetImageView, P2MatchupHelmetImageView;
    Button OKButton, ResetButton;
    int RandTeam1, RandTeam2, RandTeam3, Player1Team, Player2Team = -1;
    boolean Player1Turn = false;

    String Teams[] = {  "BUF.", "IND.", "MIA.", "N.E.", "JETS",
                        "CIN.", "CLE.", "HOU.", "PIT.",
                        "DEN.", "K.C.", "RAI.", "S.D.", "SEA.",
                        "WAS.", "GIA.", "PHI.", "PHX.", "DAL.",
                        "CHI.", "DET.", "G.B.", "MIN.", "T.B.",
                        "S.F.", "RAMS", "N.O.", "ATL."};

    // Helmet artwork created by modifying this image file:
    // https://oogiegames.files.wordpress.com/2016/01/picture-13.png?w=397&h=375
    int TeamHelmet[] = {
            R.drawable.buf,
            R.drawable.ind,
            R.drawable.mia,
            R.drawable.ne,
            R.drawable.jets,
            R.drawable.cin,
            R.drawable.cle,
            R.drawable.hou,
            R.drawable.pit,
            R.drawable.den,
            R.drawable.kc,
            R.drawable.rai,
            R.drawable.sd,
            R.drawable.sea,
            R.drawable.was,
            R.drawable.gia,
            R.drawable.phi,
            R.drawable.phx,
            R.drawable.dal,
            R.drawable.chi,
            R.drawable.det,
            R.drawable.gb,
            R.drawable.min,
            R.drawable.tb,
            R.drawable.sf,
            R.drawable.rams,
            R.drawable.no,
            R.drawable.atl};

    int TeamRanking[] = {
            6,
            28,
            13,
            26,
            20,
            8,
            23,
            3,
            18,
            15,
            5,
            4,
            10,
            27,
            11,
            1,
            7,
            19,
            14,
            17,
            9,
            25,
            12,
            21,
            2,
            22,
            24,
            16};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainLinearLayout = (LinearLayout) findViewById(R.id.MainLinearLayout);
        MainLinearLayout.setBackgroundColor(Color.rgb(0,123,255));

        TeamSelectLinearLayout = (LinearLayout) findViewById(R.id.TeamSelectLinearLayout);

        // Set initial state of all views and buttons
        MainTextView = (TextView) findViewById(R.id.MainTextView);

        P1P2Header = (TextView) findViewById(R.id.P1P2Header);
        P1P2Header.setText("Player 2\npick a team!");

        OKButton = (Button) findViewById(R.id.OKButton);
        OKButton.setVisibility(View.INVISIBLE);

        Team1TextView = (TextView) findViewById(R.id.Team1TextView);
        Team2TextView = (TextView) findViewById(R.id.Team2TextView);
        Team3TextView = (TextView) findViewById(R.id.Team3TextView);
        P1MatchupTextView = (TextView) findViewById(R.id.P1MatchupText);
        P2MatchupTextView = (TextView) findViewById(R.id.P2MatchupText);
        MatchupVSTextView = (TextView) findViewById(R.id.MatchupVSText);
        MatchupAdvantageTextView = (TextView) findViewById(R.id.MatchupAdvantage);

        Team1HelmetImageView = (ImageView) findViewById(R.id.Team1HelmetImageView);
        Team2HelmetImageView = (ImageView) findViewById(R.id.Team2HelmetImageView);
        Team3HelmetImageView = (ImageView) findViewById(R.id.Team3HelmetImageView);
        P1MatchupHelmetImageView = (ImageView) findViewById(R.id.P1MatchupHelmet);
        P2MatchupHelmetImageView = (ImageView) findViewById(R.id.P2MatchupHelmet);

        ResetButton = (Button) findViewById(R.id.ResetButton);

        // Set Text colors
        MainTextView.setTextColor(Color.rgb(255,189,0));
        P1P2Header.setTextColor(Color.rgb(255,189,0));
        Team1TextView.setTextColor(Color.WHITE);
        Team2TextView.setTextColor(Color.WHITE);
        Team3TextView.setTextColor(Color.WHITE);
        OKButton.setTextColor(Color.WHITE);
        ResetButton.setTextColor(Color.WHITE);
        P1MatchupTextView.setTextColor(Color.WHITE);
        P2MatchupTextView.setTextColor(Color.WHITE);
        MatchupVSTextView.setTextColor(Color.WHITE);
        MatchupAdvantageTextView.setTextColor(Color.WHITE);

        // Use TSB Font
        Typeface TSBFont = Typeface.createFromAsset(getAssets(),"fonts/GF-TecmoSet1.TTF");
        MainTextView.setTypeface(TSBFont);
        OKButton.setTypeface(TSBFont);
        ResetButton.setTypeface(TSBFont);
        P1P2Header.setTypeface(TSBFont);
        Team1TextView.setTypeface(TSBFont);
        Team2TextView.setTypeface(TSBFont);
        Team3TextView.setTypeface(TSBFont);
        P1MatchupTextView.setTypeface(TSBFont);
        P2MatchupTextView.setTypeface(TSBFont);
        MatchupVSTextView.setTypeface(TSBFont);
        MatchupAdvantageTextView.setTypeface(TSBFont);

        GetThreeRandomTeams();

        Team1TextView.setText(Teams[RandTeam1]);
        Team2TextView.setText(Teams[RandTeam2]);
        Team3TextView.setText(Teams[RandTeam3]);

        Team1HelmetImageView.setImageResource(TeamHelmet[RandTeam1]);
        Team2HelmetImageView.setImageResource(TeamHelmet[RandTeam2]);
        Team3HelmetImageView.setImageResource(TeamHelmet[RandTeam3]);

        ResetButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = getIntent();
                finish();
                startActivity(intent);

            }
        });

        // When Player 2 selects a team, turn the team text to pink
        Team1TextView = (TextView) findViewById(R.id.Team1TextView);
        Team1TextView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Team1TextView.setTextColor(Color.rgb(255,90,156));
                Team2TextView.setTextColor(Color.WHITE);
                Team3TextView.setTextColor(Color.WHITE);
                OKButton.setVisibility(View.VISIBLE);
            }
        });

        Team2TextView = (TextView) findViewById(R.id.Team2TextView);
        Team2TextView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Team1TextView.setTextColor(Color.WHITE);
                Team2TextView.setTextColor(Color.rgb(255,90,156));
                Team3TextView.setTextColor(Color.WHITE);
                OKButton.setVisibility(View.VISIBLE);
            }
        });

        Team3TextView = (TextView) findViewById(R.id.Team3TextView);
        Team3TextView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Team1TextView.setTextColor(Color.WHITE);
                Team2TextView.setTextColor(Color.WHITE);
                Team3TextView.setTextColor(Color.rgb(255,90,156));
                OKButton.setVisibility(View.VISIBLE);
            }
        });

        // When OK button is pressed, save Player 2's team.  Make it Player 1's turn to select a team.
        OKButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
            if(!Player1Turn) {
                final ColorStateList Team1ColorStateList = Team1TextView.getTextColors();
                final ColorStateList Team2ColorStateList = Team2TextView.getTextColors();
                final ColorStateList Team3ColorStateList = Team3TextView.getTextColors();

                int Team1Color = Team1ColorStateList.getDefaultColor();
                int Team2Color = Team2ColorStateList.getDefaultColor();
                int Team3Color = Team3ColorStateList.getDefaultColor();

                if(Team1Color == Color.rgb(255,90,156)) {
                    P2MatchupHelmetImageView.setImageResource(TeamHelmet[RandTeam1]);
                    Player2Team = RandTeam1;
                    RateP2Pick(Player2Team,RandTeam2, RandTeam3);

                    P1P2Header.setText("Player 1\npick a team!");
                    GetThreeRandomTeams();
                    Player1Turn = true;
                    Team1TextView.setTextColor(Color.WHITE);
                }

                if(Team2Color == Color.rgb(255,90,156)) {
                    P2MatchupHelmetImageView.setImageResource(TeamHelmet[RandTeam2]);
                    Player2Team = RandTeam2;
                    RateP2Pick(Player2Team,RandTeam1, RandTeam3);
                    P1P2Header.setText("Player 1\npick a team!");
                    GetThreeRandomTeams();
                    Player1Turn = true;
                    Team2TextView.setTextColor(Color.WHITE);
                }

                if(Team3Color == Color.rgb(255,90,156)) {
                    P2MatchupHelmetImageView.setImageResource(TeamHelmet[RandTeam3]);
                    Player2Team = RandTeam3;
                    RateP2Pick(Player2Team,RandTeam1, RandTeam2);
                    P1P2Header.setText("Player 1\npick a team!");
                    GetThreeRandomTeams();
                    Player1Turn = true;
                    Team3TextView.setTextColor(Color.WHITE);
                }

                if(Teams[Player2Team].equals("DAL.")) {
                    Toast toast= Toast.makeText(getApplicationContext(),
                                                "Dallas SUCKS!!!", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                }

                // Player 1's turn now
                Team1TextView.setText(Teams[RandTeam1]);
                Team2TextView.setText(Teams[RandTeam2]);
                Team3TextView.setText(Teams[RandTeam3]);

                Team1HelmetImageView.setImageResource(TeamHelmet[RandTeam1]);
                Team2HelmetImageView.setImageResource(TeamHelmet[RandTeam2]);
                Team3HelmetImageView.setImageResource(TeamHelmet[RandTeam3]);

                OKButton.setVisibility(View.INVISIBLE);
            } else {
                // Find the team P1 selected!
                final ColorStateList Team1ColorStateList = Team1TextView.getTextColors();
                final ColorStateList Team2ColorStateList = Team2TextView.getTextColors();
                final ColorStateList Team3ColorStateList = Team3TextView.getTextColors();

                int Team1Color = Team1ColorStateList.getDefaultColor();
                int Team2Color = Team2ColorStateList.getDefaultColor();
                int Team3Color = Team3ColorStateList.getDefaultColor();

                if(Team1Color == Color.rgb(255,90,156)) {
                    P1MatchupHelmetImageView.setImageResource(TeamHelmet[RandTeam1]);
                    Player1Team = RandTeam1;
                    RateP1Pick(Player1Team,RandTeam2, RandTeam3);
                    EndPlayer1Turn();
                }

                if(Team2Color == Color.rgb(255,90,156)) {
                    P1MatchupHelmetImageView.setImageResource(TeamHelmet[RandTeam2]);
                    Player1Team = RandTeam2;
                    RateP1Pick(Player1Team,RandTeam1, RandTeam3);
                    EndPlayer1Turn();
                }

                if(Team3Color == Color.rgb(255,90,156)) {
                    P1MatchupHelmetImageView.setImageResource(TeamHelmet[RandTeam3]);
                    Player1Team = RandTeam3;
                    RateP1Pick(Player1Team,RandTeam1, RandTeam2);
                    EndPlayer1Turn();
                }

                if(Teams[Player1Team].equals("DAL.")) {
                    Toast toast= Toast.makeText(getApplicationContext(),
                                                "Dallas SUCKS!!!", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                }
            }
        }

    });

        // When Player 1 selects a team, turn the team text to red and make OK button visible
        Team1TextView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Team1TextView.setTextColor(Color.rgb(255,90,156));
                Team2TextView.setTextColor(Color.WHITE);
                Team3TextView.setTextColor(Color.WHITE);
                OKButton.setVisibility(View.VISIBLE);
            }
        });

        Team2TextView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Team1TextView.setTextColor(Color.WHITE);
                Team2TextView.setTextColor(Color.rgb(255,90,156));
                Team3TextView.setTextColor(Color.WHITE);
                OKButton.setVisibility(View.VISIBLE);
            }
        });

        Team3TextView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Team1TextView.setTextColor(Color.WHITE);
                Team2TextView.setTextColor(Color.WHITE);
                Team3TextView.setTextColor(Color.rgb(255,90,156));
                OKButton.setVisibility(View.VISIBLE);
            }
        });
    }


    public void EndPlayer1Turn() {

        OKButton.setVisibility(View.GONE);
        TeamSelectLinearLayout.setVisibility(LinearLayout.GONE);
        P1P2Header.setText("Today's Matchup is set!");

        // Determine Matchup Advantage
        int P1TeamRankingClass = -1;
        int P2TeamRankingClass = -1;

        if (TeamRanking[Player1Team] < 5) {
            P1TeamRankingClass = 1;
        } else {
            if(TeamRanking[Player1Team] < 10) {
                P1TeamRankingClass = 2;
            } else {
                if(TeamRanking[Player1Team] < 16) {
                    P1TeamRankingClass = 3;
                } else {
                    if(TeamRanking[Player1Team] < 22) {
                        P1TeamRankingClass = 4;
                    } else {
                        P1TeamRankingClass = 5;
                    }
                }
            }
        }

        if (TeamRanking[Player2Team] < 5) {
            P2TeamRankingClass = 1;
        } else {
            if(TeamRanking[Player2Team] < 10) {
                P2TeamRankingClass = 2;
            }
            else {
                if(TeamRanking[Player2Team] < 16) {
                    P2TeamRankingClass = 3;
                } else {
                    if(TeamRanking[Player2Team] < 22) {
                        P2TeamRankingClass = 4;
                    }
                    else {
                        P2TeamRankingClass = 5;
                    }
                }
            }
        }

        if(TeamRanking[Player1Team] < TeamRanking[Player2Team]) {
            if(P1TeamRankingClass == P2TeamRankingClass) {
                MatchupAdvantageTextView.setText("<-");
            } else {
                if(P2TeamRankingClass - P1TeamRankingClass == 1) {
                    MatchupAdvantageTextView.setText("<--");
                } else {
                    if(P2TeamRankingClass - P1TeamRankingClass == 2) {
                        MatchupAdvantageTextView.setText("<---");
                    } else {
                        if(P2TeamRankingClass - P1TeamRankingClass == 3) {
                            MatchupAdvantageTextView.setText("<----");
                        } else {
                            MatchupAdvantageTextView.setText("<-----");
                        }
                    }
                }
            }
        } else {
            if(P1TeamRankingClass == P2TeamRankingClass) {
                MatchupAdvantageTextView.setText("->");
            } else {
                if(P1TeamRankingClass - P2TeamRankingClass == 1) {
                    MatchupAdvantageTextView.setText("-->");
                } else {
                    if(P1TeamRankingClass - P2TeamRankingClass == 2) {
                        MatchupAdvantageTextView.setText("--->");
                    } else {
                        if(P1TeamRankingClass - P2TeamRankingClass == 3) {
                            MatchupAdvantageTextView.setText("---->");
                        } else {
                            MatchupAdvantageTextView.setText("----->");
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void GetThreeRandomTeams() {

        int temp = randTeam();

        while (temp == Player2Team) {
            temp = randTeam();
        }

        RandTeam1 = temp;
        temp = randTeam();

        while (temp == Player2Team || temp == RandTeam1) {
            temp = randTeam();
        }

        RandTeam2 = temp;
        temp = randTeam();

        while ((temp == Player2Team || temp == RandTeam1) || temp == RandTeam2) {
            temp = randTeam();
        }

        RandTeam3 = temp;
    }

    public static int randTeam() {

        Random rand = new Random();

        return rand.nextInt(28);
    }

    public void RateP2Pick(int P2Team, int OtherChoice1, int OtherChoice2) {

        if(TeamRanking[P2Team] < TeamRanking[OtherChoice1]) {
            if(TeamRanking[P2Team] < TeamRanking[OtherChoice2]) {
                P2MatchupTextView.setTextColor(Color.GREEN);
            } else {
                P2MatchupTextView.setTextColor(Color.YELLOW);
            }
        } else {
            if(TeamRanking[P2Team] < TeamRanking[OtherChoice2]) {
                P2MatchupTextView.setTextColor(Color.YELLOW);
            } else {
                P2MatchupTextView.setTextColor(Color.RED);
            }
        }
    }

    public void RateP1Pick(int P1Team, int OtherChoice1, int OtherChoice2) {

        if(TeamRanking[P1Team] < TeamRanking[OtherChoice1]) {
            if(TeamRanking[P1Team] < TeamRanking[OtherChoice2]) {
                P1MatchupTextView.setTextColor(Color.GREEN);
            } else {
                P1MatchupTextView.setTextColor(Color.YELLOW);
            }
        } else {
            if(TeamRanking[P1Team] < TeamRanking[OtherChoice2])
            {
                P1MatchupTextView.setTextColor(Color.YELLOW);
            } else {
                P1MatchupTextView.setTextColor(Color.RED);
            }
        }
    }
}
