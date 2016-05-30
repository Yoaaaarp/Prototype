package tuto.david.prototype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

        // recupération du bouton de login, des deux EditText et du TextView
        lButton         = (Button) findViewById(R.id.login_button);
        userEditText    = (EditText) findViewById(R.id.login_username);
        pwdEditText     = (EditText) findViewById(R.id.login_pwd);
        errTextView     = (TextView) findViewById(R.id.login_err_view);

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
                } else if (username.equals("test")){ // ceci est une erreur codée en dur
                    //TODO authentification auprès du serveur
                    errTextView.setText(R.string.login_error_msg_refuse);
                } else {
                    //TODO start new activity
                    errTextView.setText("");
                    // création de l'intent
                    Intent mainActivity = new Intent(LoginActivity.this, HomeActivity.class);
                    // démarrage de l'activité
                    startActivity(mainActivity);
                }
            }
        });
    }
}
