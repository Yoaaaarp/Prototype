package tuto.david.prototype;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import tuto.david.prototype.database.dao.ChatDAO;
import tuto.david.prototype.database.dao.MemberDAO;
import tuto.david.prototype.database.entity.Chat;


public class ChatListFragment extends ListFragment implements OnItemClickListener{
    private ListView list;
    private Fragment nextFragment = null;

    public ChatListFragment() {
        // constructeur vide
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.i("ChatList", "onCreate()");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat_list, container, false);
        list = (ListView) view.findViewById(android.R.id.list);
        Log.i("ChatList", "onCreateView()");
        return view;
    }

    // methode appelée lorsque l'activité a terminée son onCreate()
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        //ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),R.array.Planets, android.R.layout.simple_list_item_1);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1);

        // recuperation des fils de discussion que suit l'utilisateur
        ChatDAO chatDAO = new ChatDAO();
        List<Chat> chatList = chatDAO.getSubscribedChats(getActivity().getIntent().getLongExtra(MemberDAO.MEMBER_KEY, -1));
        // s'il est inscrit à des fils de discussion, on les ajoute à la liste
        if (chatList != null) {
            int size = chatList.size();
            for (int i = 0; i < size; i++){
                adapter.add(chatList.get(i).getTitle());
            }
        }

        // on affiche la liste
        setListAdapter(adapter);
        list.setOnItemClickListener(this);
        //getListView().setOnClickListener(this);
        Log.i("ChatList", "onActivityCreated()");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(getActivity(), "Clicked item : " + position, Toast.LENGTH_SHORT).show();
        String title = getListAdapter().getItem(position).toString();

        /*
        Bundle args = new Bundle();
        args.putString("Title", title);

        nextFragment = new ChatFragment();
        nextFragment.setArguments(args);

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.viewpager, nextFragment)
                .addToBackStack(null)
                .commit();
        */
        Intent chatActivity = new Intent(getActivity(), MessagingActivity.class);
        chatActivity.putExtra("title", title);
        startActivity(chatActivity);
    }
}
