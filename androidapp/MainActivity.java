package zengcs.treehacks2017;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
{
    static final int GAME_LOSE_TIMER = 40000;

    boolean sigothMessaged;
    int count;
    Timer sigothAnger;

    String name;
    String selfGender;
    String sigothGender;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize variables
        sigothAnger = new Timer();
        sigothMessaged = false;
        count = 0;

        // Get intent variables
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        selfGender = intent.getStringExtra("gender");
        if( intent.getStringExtra("sexualOrientation").equals("Gay") )
        {
            sigothGender = selfGender;
        }
        else if( intent.getStringExtra("sexualOrientation").equals("Straight") )
        {
            if( selfGender.equals("Female") )
            {
                sigothGender = "Male";
            }
            else if( selfGender.equals("Male") )
            {
                sigothGender = "Female";
            }
            else
            {
                sigothGender = "Other";
            }
        }
        else
        {
            sigothGender = "Other";
        }

        // Change name
        TextView TV_name = (TextView) findViewById(R.id.TV_name);
        switch( sigothGender )
        {
            case "Male":
                TV_name.setText("Paul Johnson");
                break;
            case "Female":
                TV_name.setText("Sarah O'Connor");
                break;
            default:
                TV_name.setText("Sam Barnes");
        }

        // Start story
        sigothResponse();
        setupGameEnd();
    }

    /* Sending Texts */

    // Button to send message clicked
    public void messageSubmitClicked(View view)
    {
        EditText message = (EditText) findViewById(R.id.message);
        if( message.getText().toString().length() < 1 ) return;

        // Add message and remove text from input box
        sendMessage();
        message.setText("");

        // Update game if necessary
        if( sigothMessaged )
        {
            count++;
            sigothResponse();
            setupGameEnd();
        }

        sigothMessaged = false;

        scrollDown();
    }

    public void setupGameEnd()
    {
        sigothAnger.cancel();
        sigothAnger.purge();
        sigothAnger = new Timer();
        sendAngryText("hello?", 18000);
        sendAngryText("r u there", 25000);
        sendAngryText("heyyyyy :( ", 30000);
        sendAngryText("Why aren't you responding?", 31000);
        setGameEndTimer();
    }

    // Send text back to sigoth
    public void sendMessage()
    {
        // Create new messagebox to add
        View selfmessage = getLayoutInflater().inflate(R.layout.selfmessage, null);

        // Add edittext box's text to message
        TextView tv = (TextView) selfmessage.findViewById(R.id.TV_selfmessage);
        EditText message = (EditText) findViewById(R.id.message);
        tv.setText(message.getText());

        // Add message to the chat
        LinearLayout chat = (LinearLayout) findViewById(R.id.chat);
        chat.addView(selfmessage);
    }

    /* Receiving texts */

    // Schedules the proper next response from your sigoth
    public void sigothResponse()
    {
        switch (count%16) {
            case 0:
                if( selfGender.equals("Male") ) sendTimedText("Hey boyfriend ;)", 2000);
                if( selfGender.equals("Female") ) sendTimedText("Hey girlfriend ;)", 2000);
                if( selfGender.equals("Other") ) sendTimedText("Hey sweetie ;)", 2000);
                break;
            case 1:
                sendTimedText("oh you responded", 2000);
                sendTimedText("whats up", 3500);
                break;
            case 2:
                sendTimedText("im gonna b rly lonely 2nite", 3000);
                sendTimedText("Come over?", 6000);
                break;
            case 3:
                sendTimedText("actually u kno what", 2000);
                sendTimedText("idc what u say", 4000);
                sendTimedText("ill just drive over to ur house, ok?", 7000);
                break;
            case 4:
                sendTimedText("wait i just txted ur mom", 1000);
                sendTimedText("she said ur not home", 4000);
                sendTimedText("wher r u?", 5500);
                break;
            case 5:
                sendTimedText("its k", 1000);
                sendTimedText("ill just come find u", 4000);
                sendTimedText("wanna get dinner when i find u", 5000);
                break;
            case 6:
                sendTimedText("actually my mom says i cant :(", 4000);
                sendTimedText("i h8 my mom", 5500);
                sendTimedText("she doesnt understand me", 6500);
                sendTimedText("i bet u dont even understand me :(", 8000);
                break;
            case 7:
                sendTimedText("ugh", 1000);
                sendTimedText("idc what u say", 3000);
                sendTimedText("theres no way u can understand me", 5500);
                sendTimedText("oh well. did u hear emily broke up with her bf", 8000);
                break;
            case 8:
                sendTimedText("omg emily is such a bad influence", 2000);
                sendTimedText("dont u h8 her too", 4000);
                break;
            case 9:
                sendTimedText("ya no but she always bothers me,", 3000);
                sendTimedText("but yeah.", 3500);
                sendTimedText("o yea did u get ur car fixed?", 6000);
                break;
            case 10:
                sendTimedText("ha ok", 500);
                sendTimedText("take me for a drive sometime", 2500);
                sendTimedText("preferably at night ;)", 4500);
                sendTimedText("how does friday night sound bb", 6500);
                break;
            case 11:
                sendTimedText("o darn w8 i hav hw", 2000);
                sendTimedText("w8 idk how to do this lol", 5000);
                sendTimedText("can u summarize a tale of two cities for me", 7000);
                break;
            case 12:
                sendTimedText("ohhhhh ok ty ty", 3000);
                sendTimedText("ok time 4 math", 4000);
                sendTimedText("whats 3 plus 2? pls help", 6000);
                break;
            case 13:
                sendTimedText("omg thx so much", 1500);
                sendTimedText("how r u so smart omg", 3500);
                break;
            case 14:
                sendTimedText("i wish i were as smart as u", 2000);
                sendTimedText("what would i ever do without u", 4000);
                break;
            case 15:
                sendTimedText("sigh lol we shoudl just get married", 3500);
                sendTimedText("lol im going to sleep talk to u l8r", 5500);
            default:

                break;
        }
    }

    // Sigoth sends a text to you after |time| time
    public void sendTimedText(final String text, int time)
    {
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            public void run()
            {
                Message msg = new Message();
                Bundle b = new Bundle();
                b.putString("message", text);
                msg.setData(b);
                messageHandler.sendMessage(msg);
            }
        }, time);
    }

    // Schedules an angry text from your sigoth
    public void sendAngryText(final String text, int time)
    {
        sigothAnger.schedule(new TimerTask() {
            public void run()
            {
                Message msg = new Message();
                Bundle b = new Bundle();
                b.putString("message", text);
                b.putString("angry", "angry");
                msg.setData(b);
                messageHandler.sendMessage(msg);
            }
        }, time);
    }

    // Message handler for timer
    private Handler messageHandler = new Handler()
    {
        public void handleMessage(Message msg)
        {
            Bundle b = msg.getData();

            if( b.getString("message") != null )
            {
                messageReceived(b.getString("message"));
                Vibrator v = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
                v.vibrate(250);
                if( b.getString("angry") == null ) {
                    setupGameEnd();
                }
            }
            else if( b.getString("error") != null )
            {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                alertDialogBuilder.setTitle("GAME OVER");
                alertDialogBuilder.setMessage("You've lost").setCancelable(false);
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        }
    };

    // Add received message to screen
    public void messageReceived(String text)
    {
        View sigothmessage = getLayoutInflater().inflate(R.layout.sigothmessage, null);
        LinearLayout chat = (LinearLayout) findViewById(R.id.chat);

        TextView tv = (TextView) sigothmessage.findViewById(R.id.TV_girlfriend);
        tv.setText(text);

        ImageView iv = (ImageView) sigothmessage.findViewById(R.id.IV_sigoth);
        if( sigothMessaged )
        {
            iv.setVisibility(View.INVISIBLE);
        }
        else
        {
            if( sigothGender.equals("Male") ) iv.setImageResource(R.drawable.boyfriendicon);
            if( sigothGender.equals("Female") ) iv.setImageResource(R.drawable.girlfriendicon);
            if( sigothGender.equals("Other") ) iv.setImageResource(R.drawable.othericon);
        }

        chat.addView(sigothmessage);
        sigothMessaged = true;

        scrollDown();
    }

    /* app function */

    public void scrollDown()
    {
        // Scroll to the bottom of the chat box
        final ScrollView sv = (ScrollView) findViewById(R.id.sv);
        sv.post(new Runnable() {
            @Override
            public void run() {
                sv.fullScroll(View.FOCUS_DOWN);
            }
        });
    }

    // Sigoth sends a text to you after |time| time
    public void setGameEndTimer()
    {
        sigothAnger.schedule(new TimerTask() {
            public void run()
            {
                Message msg = new Message();
                Bundle b = new Bundle();
                b.putString("error", "error");
                msg.setData(b);
                messageHandler.sendMessage(msg);
            }
        }, GAME_LOSE_TIMER);
    }

}
