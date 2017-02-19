package zengcs.treehacks2017;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class StartScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        Spinner S_gender = (Spinner) findViewById(R.id.S_gender);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.genders, R.layout.spinner_item);
        S_gender.setAdapter(adapter);

        Spinner S_sexualorientation = (Spinner) findViewById(R.id.S_sexualorientation);
        adapter = ArrayAdapter.createFromResource(this, R.array.sexualorientations, R.layout.spinner_item);
        S_sexualorientation.setAdapter(adapter);

    }

    public void startChatClicked(View view)
    {
        TextView ET_name = (TextView) findViewById(R.id.ET_name);
        Spinner S_gender = (Spinner) findViewById(R.id.S_gender);
        Spinner S_sexualorientation = (Spinner) findViewById(R.id.S_sexualorientation);

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("name", ET_name.getText().toString() );
        intent.putExtra("gender", S_gender.getSelectedItem().toString() );
        intent.putExtra("sexualOrientation", S_sexualorientation.getSelectedItem().toString() );
        startActivity(intent);
    }
}
