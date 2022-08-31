package br.com.fiap.isgood.activities

import android.content.Context
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
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import br.com.fiap.isgood.R
import br.com.fiap.isgood.databinding.ActivityLoginBinding
import br.com.fiap.isgood.models.Usuario
import br.com.fiap.isgood.viewmodel.LoginActivityViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.lang.Exception


class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    lateinit var loginViewModel: LoginActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Ativa o ActivityViewModel
        loginViewModel = ViewModelProvider.NewInstanceFactory().create(LoginActivityViewModel::class.java)

        //Ativando os listeners
        binding.editTextEmail.doOnTextChanged { _, _, _, _ ->
            loginViewModel.email.value = binding.editTextEmail.text.toString()
        }

        binding.editTextPassword.doOnTextChanged{_,_,_,_ ->
            loginViewModel.password.value = binding.editTextPassword.text.toString()
        }

        //Para ajudar nos testes
        binding.tvEmail.setOnClickListener{
            binding.editTextEmail.setText("williandrade@gmail.com")
            binding.editTextPassword.setText("teste123")
        }

        binding.textViewLoginCriarConta.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }

        binding.btLogin.setOnClickListener{
            loginViewModel.doLogin()
        }

        //Ativando os watchers
        loginViewModel.dadosProntos.observe(this){
            binding.btLogin.isEnabled = it
        }

        loginViewModel.autenticado.observe(this){
            if (it) {
                val intent = Intent(this, PesquisaActivity::class.java)
                //intent.putExtra("idUsuario", "1")
                startActivity(intent)
                finish()
            }
        }

        loginViewModel.mensagem.observe(this){
            showMessage(it)
        }

        loginViewModel.tryLoginWithSharedData(this.application)


        val loginCriarContaTextView = binding.textViewLoginCriarConta;
        val underlineString = SpannableString(loginCriarContaTextView.text);
        underlineString.setSpan(UnderlineSpan(), 0, underlineString.length, 0);
        loginCriarContaTextView.text = underlineString;

        /*
         * Apenas para facilitar testes no desenvolvimento!!!
         */
        findViewById<TextView>(R.id.tvEmail).setOnClickListener{
            binding.editTextEmail.setText("williandrade@gmail.com")
            binding.editTextPassword.setText("teste123")
        }


        //verificaCadastro(intent.extras);

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
                binding.linearLayoutLoginPrincipal,
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

    fun login(v: View): Unit {
        val txtEmail = binding.editTextEmail.text.toString()
        val txtPassword = binding.editTextPassword.text.toString()

        if (txtEmail.isEmpty() && txtPassword.isEmpty()) {
            Snackbar.make(
                binding.linearLayoutLoginPrincipal,
                "Preencha email e senha.",
                Snackbar.LENGTH_LONG
            ).show()
        } else {
            try {
                var usuario = Usuario.getAutenticatedUser(txtEmail, txtPassword)
                val intent = Intent(this, PesquisaActivity::class.java)
                intent.putExtra("idUsuario", usuario.id)
                startActivity(intent)
                finish()
            } catch (e : Exception){
                Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
                binding.editTextPassword.setText("")
            }
        }
    }



    fun showMessage (texto:String) {
        val toastTrue =
            Toast.makeText(this, texto, Toast.LENGTH_SHORT)
        toastTrue.setGravity(Gravity.TOP, 0, 0)
        toastTrue.show()

    }

}