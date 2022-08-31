package br.com.fiap.isgood.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.fiap.isgood.models.Usuario
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivityViewModel() : ViewModel() {
    val email = MutableLiveData("")
    val password = MutableLiveData("")
    val dadosProntos = MutableLiveData(false)
    val mensagem = MutableLiveData("")
    private var autenticando = false
    val autenticado = MutableLiveData(false)

    init {

        email.observeForever{
            validaDados()
        }
        password.observeForever{
            validaDados()
        }
    }

    fun tryLoginWithSharedData(app : Application) {
        // Verificar se já existe algum token cadastrado
        val sharedPref = app.getSharedPreferences(Usuario.SHARED_PREFERENCES_LIB, Context.MODE_PRIVATE)
        val email = sharedPref.getString(Usuario.PREFERENCES_KEY_LOGIN_TOKEN, "")
        val password = sharedPref.getString(Usuario.PREFERENCES_KEY_PASSWORD_LOGIN, "")
        if (email.toString().isNotEmpty() && password.toString().isNotEmpty()) {
            //Faz login pelo usuário e senha gravados
            this.email.value = email
            this.password.value = password
            doLogin()
        }
    }

    private fun validaDados(){
        dadosProntos.value =  !(email.value.toString() == "" || password.value.toString() == "")
    }

    fun doLogin() {
        if (dadosProntos.value == false) {
            mensagem.value = "Informe o e-mail para login e a senha."
            return
        }

        if (autenticando) {
            mensagem.value = "Autenticando. Aguarde..."
            return
        }

        autenticando = true

        Firebase.auth.signOut()
        Firebase.auth.useAppLanguage()

        Firebase.auth.signInWithEmailAndPassword(email.value.toString(), password.value.toString())
            .addOnSuccessListener {
                val nome = it.user?.displayName.toString()
                mensagem.value = "Seja bem vindo $nome!"
                autenticado.value = true
                autenticando = false
                validaDados()
            }
            .addOnFailureListener {
                if (it.message!!.startsWith("We have blocked all requests from this device due to unusual activity. Try again")) {
                    mensagem.value =
                        "Usuário bloqueado devido a várias tentativas sem sucesso. Tente novamente mais tarde."
                } else if (it.message!!.startsWith("The password is invalid or the user does not have a password")) {
                    mensagem.value = "Senha inválida."
                } else if (it.message!!.startsWith("There is no user record corresponding to this identifier.")) {
                    mensagem.value = "Não existe usuário registrado com esse e-mail."
                } else {
                    mensagem.value = "Falha ao autenticar o usuario.\n${it.localizedMessage}."
                }
                Log.i("LOGIN", "Mensagem: ${it.message}")
                autenticando = false
                validaDados()
            }
        validaDados()
    }

}