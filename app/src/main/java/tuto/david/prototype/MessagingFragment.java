package tuto.david.prototype;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class MessagingFragment extends Fragment {
    private View view;

    public MessagingFragment() {
        // constructeur vide
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_messaging, container, false);
        // ajout du fragment principal au FrameLayout
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.messaging_main, new ChatListFragment(), "CHAT_LIST")
                .commit();
        return view;
    }
}
