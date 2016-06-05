package tuto.david.prototype.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tuto.david.prototype.R;
import tuto.david.prototype.database.entity.Message;

public class ChatArrayAdapter extends ArrayAdapter<Message>{
    private TextView chatText;
    private List<Message> msgList = new ArrayList<>();
    private Context context;

    public ChatArrayAdapter(Context context, int textViewRessourceId){
        super(context, textViewRessourceId);
        this.context = context;
    }

    @Override
    public void add(Message msg){
        msgList.add(msg);
        super.add(msg);
    }

    public int getCount() {
        return msgList.size();
    }

    public Message getItem(int index){
        return msgList.get(index);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        Message msg = getItem(position);
        View row = convertView;
        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (msg.getLeft()){
            row = inflater.inflate(R.layout.left_msg, parent, false);
        } else {
            row = inflater.inflate(R.layout.right_msg, parent, false);
        }
        chatText = (TextView)row.findViewById(R.id.message);
        chatText.setText(msg.getText());
        return row;
    }

}
