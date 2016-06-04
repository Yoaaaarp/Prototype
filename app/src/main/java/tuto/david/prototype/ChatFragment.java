package tuto.david.prototype;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ChatFragment extends Fragment {
    private String title = null;
    private View view;
    private TextView tView;

    public ChatFragment() {
        // constructeur vide
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chat, container, false);

        title = getArguments().getString("title");
        Log.i("Chat", "Title = " + title);
        tView = (TextView) view.findViewById(R.id.chat_title);
        tView.setText(title);

        return view;
    }

}
