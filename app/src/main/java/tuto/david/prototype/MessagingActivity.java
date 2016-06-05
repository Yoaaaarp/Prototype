package tuto.david.prototype;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import tuto.david.prototype.database.dao.ChatDAO;
import tuto.david.prototype.database.dao.MemberDAO;


public class MessagingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging);

        // on crée le prochain fragment
        Fragment nextFragment = new ChatFragment();
        // on prépare les arguments
        Bundle args = new Bundle();

        Log.i("Messaging", "Chat title -> " + getIntent().getStringExtra(ChatDAO.CHAT_TITLE));
        Log.i("Messaging", "Chat id -> " + getIntent().getLongExtra(ChatDAO.CHAT_KEY, -1));
        Log.i("Messaging", "member id -> " + getIntent().getLongExtra(MemberDAO.MEMBER_KEY, -1));

        args.putString(ChatDAO.CHAT_TITLE, getIntent().getStringExtra(ChatDAO.CHAT_TITLE));
        args.putLong(ChatDAO.CHAT_KEY, getIntent().getLongExtra(ChatDAO.CHAT_KEY, -1));
        args.putLong(MemberDAO.MEMBER_KEY, getIntent().getLongExtra(MemberDAO.MEMBER_KEY, -1));
        nextFragment.setArguments(args);

        // on assigne le fragment à la FrameLayout
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.messaging_main, nextFragment, "CHAT")
                .commit();
        Log.i("Messaging", "After creation Activity");
    }
}
