package br.com.fiap.isgood.activities

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.fiap.isgood.R
import br.com.fiap.isgood.models.Usuario
import com.google.android.material.snackbar.Snackbar
import java.lang.Exception


class LoginActivity : AppCompatActivity() {
    lateinit var editTextEmail: EditText;
    lateinit var editTextPassword: EditText;
    lateinit var linearLayoutLoginPrincipal: LinearLayout;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val loginCriarContaTextView = findViewById<TextView>(R.id.textViewLoginCriarConta);
        val underlineString = SpannableString(loginCriarContaTextView.text);
        underlineString.setSpan(UnderlineSpan(), 0, underlineString.length, 0);
        loginCriarContaTextView.text = underlineString;
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        linearLayoutLoginPrincipal = findViewById(R.id.linearLayoutLoginPrincipal);

        /*
         * Apenas para facilitar testes no desenvolvimento!!!
         */
        findViewById<TextView>(R.id.tvEmail).setOnClickListener{
            editTextEmail.setText("williandrade@gmail.com")
            editTextPassword.setText("teste123")
        }

        verificaCadastro(intent.extras);

    }

    private fun verificaCadastro(extras: Bundle?) {
        val extras = intent.extras
        var fromCadastro:Boolean;

        if (extras?.get("fromCadastro") != null)
            fromCadastro = extras.get("fromCadastro") as Boolean;
        else
            fromCadastro = false;

        if (fromCadastro) {
            Snackbar.make(
                linearLayoutLoginPrincipal,
                "Conta criada com sucesso",
                Snackbar.LENGTH_LONG
            ).setAction(null) {
                val toastTrue =
                    Toast.makeText(this, "Conta criada com sucesso", Toast.LENGTH_SHORT)
                toastTrue.setGravity(Gravity.TOP, 0, 0)
                toastTrue.show()
            }.show()
        }
    }

    public open fun login(v: View): Unit {

        if (!editTextEmail.text.isEmpty() && !editTextPassword.text.isEmpty()) {
            try {
                var usuario = Usuario.getAutenticatedUser(editTextEmail.text.toString(), editTextPassword.text.toString())
                val intent = Intent(this, PesquisaActivity::class.java)
                intent.putExtra("idUsuario", usuario.id)
                startActivity(intent)
                finish()
            } catch (e : Exception){
                Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
                editTextPassword.setText("")
            }
        } else {
            Snackbar.make(
                linearLayoutLoginPrincipal,
                "Por favor preencha email e senha",
                Snackbar.LENGTH_LONG
            ).setAction(null) {
                    val toastTrue =
                        Toast.makeText(this, "Por favor preencha email e senha", Toast.LENGTH_SHORT)
                    toastTrue.setGravity(Gravity.TOP, 0, 0)
                    toastTrue.show()
            }.show()
        }
    }

    open fun criarConta(v: View) {
        val intent = Intent(this, CadastroActivity::class.java)
        startActivity(intent)
    }
}