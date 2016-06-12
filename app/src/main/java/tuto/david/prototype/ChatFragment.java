package tuto.david.prototype;


import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import tuto.david.prototype.adapter.ChatArrayAdapter;
import tuto.david.prototype.database.dao.ChatDAO;
import tuto.david.prototype.database.dao.MemberDAO;
import tuto.david.prototype.database.dao.MessageDAO;
import tuto.david.prototype.database.entity.Message;

public class ChatFragment extends Fragment {
    private View view;
    private Button sendButton;
    private EditText editText;
    private ListView listView;
    private ChatArrayAdapter chatArrayAdapter;

    private long userId;
    private long chatId;
    private String title;

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

        // récupération des id du membre et du fil de discussion
        Bundle args = getArguments();
        userId = args.getLong(MemberDAO.MEMBER_EXTRA_ID);
        chatId = args.getLong(ChatDAO.CHAT_EXTRA_ID);
        title = args.getString(ChatDAO.CHAT_EXTRA_TITLE);
        //userId = getActivity().getIntent().getLongExtra(MemberDAO.MEMBER_KEY, -1);
       // chatId = getActivity().getIntent().getLongExtra(ChatDAO.CHAT_KEY, -1);

        Log.i("Chat", "Chat title -> " + title);
        Log.i("Chat", "Chat id -> " + chatId);
        Log.i("Chat", "Member id -> " + userId);

        // recupération des éléments de la vue
        sendButton = (Button) view.findViewById(R.id.send_msg_button);
        editText = (EditText) view.findViewById(R.id.msg_text);
        listView = (ListView) view.findViewById(R.id.msg_list_view);

        // on crée le array adapter
        chatArrayAdapter = new ChatArrayAdapter(getContext(),R.layout.right_msg); // pk droite
        // on le peuple avec les messages se trouvant dans la DB
        populateChatArrayAdapter();
        // puis on lie l'adapter a la ListView
        listView.setAdapter(chatArrayAdapter);
        listView.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);

        //to scroll the list view to bottom on data change
        chatArrayAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                listView.setSelection(chatArrayAdapter.getCount() - 1);
            }
        });

        // on ajoute un listener au bouton pour envoyer les messages
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                sendChatMessage();
            }
        });
        // on ajoute un listener au clavier numérique pour envoyer le message écrit
        editText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    return sendChatMessage();
                }
                return false;
            }
        });
        return view;
    }

    private boolean sendChatMessage(){
        boolean success = false;
        if(!editText.getText().toString().isEmpty()) {
            Message newMsg = new Message();
            newMsg.setAuthor(userId);
            newMsg.setChat(chatId);
            newMsg.setText(editText.getText().toString());
            newMsg.setLeft(true);

            MessageDAO msgDAO = new MessageDAO();
            if (msgDAO.create(newMsg) > -1) {
                Log.i("Chat", "Message créé chatId -> " + newMsg.getChat() + " memberId -> " + newMsg.getAuthor() + " texte -> " + newMsg.getText());
                chatArrayAdapter.add(newMsg);
                success = true;
            }
            editText.setText("");
        }
        return success;
    }

    private void populateChatArrayAdapter(){
        Log.i("Chat", "userId is " + userId);
        Log.i("Chat", "chatId is " + chatId);

        if (userId > -1 && chatId > -1){
            // on récupère tous les messages associés au fil de discussion
            MessageDAO msgDAO = new MessageDAO();
            List<Message> msgList = msgDAO.getMessagesForChat(chatId);
            // on vérifie si l'on a bien récupéré des messages
            if (msgList != null && !msgList.isEmpty()) {
                int size = msgList.size();
                // on ajoute tous les messages récupéré dans la ListView
                for (int i = 0; i < size; i++) {
                    // pour chaque message, on vérifie si l'utilisateur est l'auteur ou non
                    Message msg = msgList.get(i);
                    Log.i("Chat","Message trouvé : auteur -> " + msg.getAuthor() + " chatId -> " + msg.getChat() + " texte -> " + msg.getText());
                    if (msg.getAuthor() == userId){
                        Log.i("Chat", "User and author match !");
                        msg.setLeft(true);
                    }
                    chatArrayAdapter.add(msg);
                }
            }
        }
    }
}
