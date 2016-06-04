package tuto.david.prototype;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


public class MessagingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging);

        // on crée le prochain fragment
        Fragment nextFragment = new ChatFragment();
        // on récupère le titre
        Bundle args = new Bundle();
        args.putString("title", getIntent().getStringExtra("title"));
        nextFragment.setArguments(args);

        // on assigne le fragment à la FrameLayout
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.messaging_main, nextFragment, "CHAT")
                .commit();
        Log.i("Messaging", "After creation Activity");
    }
}
