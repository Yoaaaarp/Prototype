package tuto.david.prototype;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import tuto.david.prototype.database.DatabaseHandler;
import tuto.david.prototype.database.dao.ChatDAO;
import tuto.david.prototype.database.dao.MemberDAO;
import tuto.david.prototype.database.dao.SubscriptionDAO;
import tuto.david.prototype.database.entity.Chat;
import tuto.david.prototype.database.entity.Member;
import tuto.david.prototype.database.entity.Subscription;

public class LoginActivity extends AppCompatActivity {
    private Button lButton;
    private EditText userEditText;
    private EditText pwdEditText;
    private TextView errTextView;

    private String username;
    private String pwd;

    private Member member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // on attribue le contexte de l'application au database handler
        DatabaseHandler.setContext(getApplicationContext());
        // on initialise la DB
        Log.i("INFO","Avant initialisation DB");
        initDB();
        Log.i("INFO", "Après initialisation DB");


        // recupération du bouton de login, des deux EditText et du TextView
        lButton         = (Button) findViewById(R.id.login_button);
        userEditText   = (EditText) findViewById(R.id.login_username);
        pwdEditText    = (EditText) findViewById(R.id.login_pwd);
        errTextView    = (TextView) findViewById(R.id.login_err_view);

        // Ajout d'un listener sur le bouton de login
        lButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // recuperation des champs des EditText
                username = userEditText.getText().toString();
                pwd = pwdEditText.getText().toString();
                // on verifie que les champs sont renseignés
                if(username.isEmpty() || pwd.isEmpty()){
                    errTextView.setText(R.string.login_error_msg_fields);
                } // on vérifie la validité des identifiants
                else {
                    // lance l'activité principale si authentification réussie
                    if(auth(username, pwd)){
                        errTextView.setText("");
                        // création de l'intent
                        Intent homeActivity = new Intent(LoginActivity.this, HomeActivity.class);
                        homeActivity.putExtra(MemberDAO.MEMBER_NAME, username);
                        homeActivity.putExtra(MemberDAO.MEMBER_KEY, member.getId());
                        // démarrage de l'activité
                        startActivity(homeActivity);
                    } else { // autrement on indique l'echec
                        errTextView.setText("Inccorect user name or password...");
                    }

                }
            }
        });
    }

    private boolean auth(String name, String pwd){
        boolean success = false;
        MemberDAO memberDAO = new MemberDAO();
        member = memberDAO.findMemberByName(name);
        if (member != null && member.getPwd().equals(pwd)){
            success = true;
        }

        return success;
    }

    private void initDB(){
        long id = -1;
        // Creation de deux utilisateurs
        Member m1 = new Member(1, "dav", "123");
        Member m2 = new Member(2, "tony", "123");
        MemberDAO memberDAO = new MemberDAO();

        id = memberDAO.create(m1);
        if (id > -1){
            m1.setId(id);
        }
        id = memberDAO.create(m2);
        if (id > -1){
            m2.setId(id);
        }
        // Creation de trois fils de discussion
        Chat c1 = new Chat(1, "You're welcome here !");
        Chat c2 = new Chat(2, "Serious business!");
        Chat c3 = new Chat(3, "The Inn");
        ChatDAO chatDAO = new ChatDAO();
        id = chatDAO.create(c1);
        if (id > -1){
            c1.setId(id);
        }
        id = chatDAO.create(c2);
        if (id > -1){
            c2.setId(id);
        }
        id = chatDAO.create(c3);
        if (id > -1){
            c3.setId(id);
        }

        // on inscrit nos deux utilisateurs aux fils de discussion
        SubscriptionDAO subDAO = new SubscriptionDAO();
        subDAO.create(new Subscription(1, c1, m1));
        subDAO.create(new Subscription(1, c2, m1));
        subDAO.create(new Subscription(1, c2, m2));
        subDAO.create(new Subscription(1, c3, m2));

        // on ajoute quelques messages de base

    }
}
