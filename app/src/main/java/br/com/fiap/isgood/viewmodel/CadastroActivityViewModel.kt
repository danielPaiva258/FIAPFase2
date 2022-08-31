package br.com.fiap.isgood.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.fiap.isgood.models.Usuario
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class CadastroActivityViewModel () : ViewModel() {
    val nome = MutableLiveData("")
    val email = MutableLiveData("")
    val senha = MutableLiveData("")
    val confirmaSenha = MutableLiveData("")
    val cep = MutableLiveData("")
    val mensagem = MutableLiveData("")
    val tudoPreenchido = MutableLiveData(false)
    val senhasConferem = MutableLiveData(true)
    val usuarioCriado = MutableLiveData(false)
    var criandoUsuario = false

    init {
        nome.observeForever{
            podeCadastrar()
        }

        email.observeForever {
            podeCadastrar()
        }

        senha.observeForever{
            podeCadastrar()
        }
        confirmaSenha.observeForever{
            podeCadastrar()
        }
        cep.observeForever{
            podeCadastrar()
        }

    }

    private fun podeCadastrar() : Boolean{
        //Todos os campos com valor
        tudoPreenchido.value = !(
            email.value.toString() == ""
            || nome.value.toString() == ""
            || email.value.toString() == ""
            || nome.value.toString() == ""
            || senha.value.toString() == ""
            || confirmaSenha.value.toString() == ""
            || cep.value.toString() == ""
            || criandoUsuario)

        // Se tiver todos os dados registrados
        senhasConferem.value = senha.value.toString() == confirmaSenha.value.toString()

        return (tudoPreenchido.value == true && senhasConferem.value == true)
    }

    fun doCadastro(){

        mensagem.value = "Criando seu registro..."

        if (tudoPreenchido.value == false){
            mensagem.value = "Preencha todos os dados."
            return
        }
        if (senhasConferem.value == false) {
            mensagem.value = "Senhas não conferem."
            return
        }

        if (criandoUsuario){
            mensagem.value = "Ainda criando o seu registro, aguarde mais um pouco."
            return
        }

        // Condições favoráveis para fazer o cadastro
        // Cria usuário no Firebase
        criandoUsuario = true
        Firebase.auth.createUserWithEmailAndPassword(email.value.toString(), senha.value.toString())
            .addOnSuccessListener {
                val cr = UserProfileChangeRequest.Builder().setDisplayName(nome.value.toString()).build()
                it.user?.updateProfile(cr)

                //Salvao os dados do usuário no celular, pra facilitar um próximo acesso.
                Usuario.getSharedPreferencesEditor(context = Application().applicationContext)!!
                    .putString( Usuario.PREFERENCES_KEY_EMAIL_LOGIN, email.value.toString())
                    .putString( Usuario.PREFERENCES_KEY_PASSWORD_LOGIN, senha.value.toString())
                    .apply()

                mensagem.value = "Cadastro realizado com sucesso."
                usuarioCriado.value = true
                criandoUsuario = false
                podeCadastrar()
            }
            .addOnFailureListener{
                if (it.message.equals("The email address is already in use by another account."))
                    mensagem.value = "Esse e-mail já é registrado para outro usuário."
                else
                    mensagem.value = "Falha na criação de usuário. Verifique o log para maiores detalhes."
                Log.e("CREATING_USER", "Message: ${it.message}")
                criandoUsuario = false
                podeCadastrar()
            }.addOnCanceledListener {
                mensagem.value = "Criação de usuário cancelada."
                criandoUsuario = false
                podeCadastrar()
            }

        podeCadastrar()

    }

}