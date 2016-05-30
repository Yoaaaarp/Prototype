package tuto.david.prototype;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class ChatListFragment extends ListFragment implements OnItemClickListener{
    private ListView list;
    private Fragment nextFragment = null;

    public ChatListFragment() {
        // constructeur vide
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat_list, container, false);
        list = (ListView) view.findViewById(android.R.id.list);
        return view;
    }

    // methode appelée lorsque l'activité a terminée son onCreate()
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),R.array.Planets, android.R.layout.simple_list_item_1);
        setListAdapter(adapter);
        list.setOnItemClickListener(this);
        //getListView().setOnClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(getActivity(), "Clicked item : " + position, Toast.LENGTH_SHORT).show();
        String title = getListAdapter().getItem(position).toString();


        Bundle args = new Bundle();
        args.putString("Title", title);

        nextFragment = new ChatFragment();
        nextFragment.setArguments(args);

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.messaging_main, nextFragment)
                .addToBackStack(null)
                .commit();
    }
}
