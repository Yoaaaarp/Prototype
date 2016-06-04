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
import tuto.david.prototype.database.dao.MemberDAO;
import tuto.david.prototype.database.entity.Member;

public class LoginActivity extends AppCompatActivity {
    private Button lButton;
    private EditText userEditText;
    private EditText pwdEditText;
    private TextView errTextView;

    private String username;
    private String pwd;

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
                        // démarrage de l'activité
                        startActivity(homeActivity);
                    } else { // autrement on indique l'echec
                        errTextView.setText("Inccorect user name or password...");
                    }

                }
            }
        });
    }

    private void initDB(){
        // Creation de deux utilisateurs
        Member m1 = new Member(1, "dav", "123");
        Member m2 = new Member(2, "tony", "123");
        MemberDAO memberDAO = new MemberDAO();
        memberDAO.create(m1);
        memberDAO.create(m2);
    }

    private boolean auth(String name, String pwd){
        boolean success = false;
        MemberDAO memberDAO = new MemberDAO();
        Member member = memberDAO.findMemberByName(name);
        if (member != null && member.getPwd().equals(pwd)){
            success = true;
        }

        return success;
    }
}
