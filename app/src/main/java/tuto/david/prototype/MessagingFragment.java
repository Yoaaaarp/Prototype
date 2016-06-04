package tuto.david.prototype;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tuto.david.prototype.adapter.ViewPagerAdapter;


public class MessagingFragment extends Fragment {
    private View view;

    public MessagingFragment() {
        // constructeur vide
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.i("Messaging", "Before creation activity");
        // lorsque l'on va sur la messagerie, on desire créer une nouvelle activity qui gérera la messagerie
        // création de l'intent
        //Intent msgActivity = new Intent(getActivity(), MessagingActivity.class);
        // démarrage de l'activité
        //startActivity(msgActivity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_messaging, container, false);
        // ajout du fragment principal au FrameLayout
        Log.i("Messaging", "before inserting into viewpager");
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.viewpager, new ChatListFragment(), "CHAT_LIST")
                .addToBackStack(null)
                .commit();
        Log.i("Messaging", "after inserting into viewpager");
        return view;
    }
}
