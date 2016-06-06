package tuto.david.prototype;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import tuto.david.prototype.database.dao.ChatDAO;
import tuto.david.prototype.database.dao.MemberDAO;
import tuto.david.prototype.database.dao.SubscriptionDAO;


public class MessagingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging);

        Bundle extra = getIntent().getExtras();


        Log.i("Messaging", "Chat title -> " + getIntent().getStringExtra(ChatDAO.CHAT_EXTRA_TITLE));
        Log.i("Messaging", "Chat id -> " + getIntent().getLongExtra(ChatDAO.CHAT_EXTRA_ID, -1));
        Log.i("Messaging", "member id -> " + getIntent().getLongExtra(MemberDAO.MEMBER_EXTRA_ID, -1));

        // on crée le prochain fragment
        Fragment nextFragment = new ChatFragment();

        // on transfert les arguments de l'activité au fragment
        nextFragment.setArguments(getIntent().getExtras());

        /*
        Bundle args = new Bundle();
        args.putString(ChatDAO.CHAT_TITLE, getIntent().getStringExtra(ChatDAO.CHAT_TITLE));
        args.putLong(ChatDAO.CHAT_KEY, getIntent().getLongExtra(ChatDAO.CHAT_KEY, -1));
        args.putLong(MemberDAO.MEMBER_KEY, getIntent().getLongExtra(MemberDAO.MEMBER_KEY, -1));
        nextFragment.setArguments(args);
        */

        // on assigne le fragment à la FrameLayout
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.messaging_main, nextFragment, "CHAT")
                .commit();
    }
}
