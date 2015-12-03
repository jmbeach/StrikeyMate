package strikeaturkeytechnologiesllc.strikeymate;

/**
 * Created by chuhui1 on 12/3/2015.
 */


import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import none.strikeymatetemp.R;


/*
Take input from the xml then throw a flag that gives boolean result
 */
public class PlayerActivity extends GameSessionActivity {
    private Button btnvote;
    public Player p1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_vote);

        btnvote = (Button)findViewById(R.id.btnVote);
        btnvote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                // grab ID
                EditText tbName = (EditText) findViewById(R.id.tbname);
                // grab Frame
                EditText tbFrame = (EditText) findViewById(R.id.tbframe);


                // flag a score
                flagScore(tbName.getText().toString(), tbFrame.getText().toString());
            }
        });
    }


    public void flagScore(String id, String frame) {
        int i = Integer.parseInt(frame);



    }





}
