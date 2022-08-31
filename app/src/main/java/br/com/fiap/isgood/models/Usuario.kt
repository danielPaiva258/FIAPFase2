package br.com.fiap.isgood.models

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

open class Usuario(
    val id: String,
    var name : String,
    var email : String,
    var cep : String
) {
    private var password : String? = null

    fun setPassword(password : String) : Usuario{
        this.password = password
        return this
    }

    private fun autenthicate(pEmail : String, pPassword : String) : Boolean{
        if (email.equals(pEmail) and password.equals(pPassword))
            return true
        return false
    }


    companion object{
        val SHARED_PREFERENCES_LIB = "login_information"
        val PREFERENCES_KEY_LOGIN_TOKEN = "firebase_token"
        val PREFERENCES_KEY_EMAIL_LOGIN = "key_email"
        val PREFERENCES_KEY_PASSWORD_LOGIN = "key_password"

        fun getSharedPreferencesEditor(context: Context): SharedPreferences.Editor? {
            return context.getSharedPreferences(SHARED_PREFERENCES_LIB, Context.MODE_PRIVATE).edit()
        }

        fun getAutenticatedUser(email : String, password: String) : Usuario{
            FirebaseApp.initializeApp(Application().applicationContext)
            var auth = Firebase.auth
            auth.signInWithEmailAndPassword(email, password)
                .addOnFailureListener{
                    throw SecurityException("Usuário não autenticado")
                }
            var cur = auth.currentUser!!
            return Usuario(id=cur.uid, name = cur.displayName!!, email=cur.email!!, cep="")
            /*
            for (usuario in Usuario.getSampleArray()){
                if (usuario.autenthicate(email, password))
                    return usuario
            }*/

        }

        fun getSampleArray() : ArrayList<Usuario>{
            var ret = ArrayList<Usuario>()
            ret.add(Usuario(
                "1",
                "Willian Andrade",
                "williandrade@gmail.com",
                "88034460"
            ).setPassword("teste123"))
            ret.add(Usuario(
                "2",
                "Mr. Nice",
                "teste@teste.com.br",
                "88034900"
            ).setPassword("12345"))
            ret.add(Usuario(
                "3",
                "Chatonildo da Silva",
                "outro@email.com",
                "88000900"
            ).setPassword(""))
            ret.add(Usuario(
                "4",
                "1234",
                "1234",
                "88034460"
            ).setPassword("1234"))
            return ret
        }

        fun getById(idUsuario: Int): Usuario {
            for (usuario in getSampleArray()){
                if (usuario.id.equals(idUsuario))
                    return usuario
            }
            throw SecurityException("Usuário não encontrado pelo ID")
        }

        fun getByEmail(email : String) : Usuario {
            for (usuario in getSampleArray()){
                if (usuario.email.equals(email))
                    return usuario
            }
            throw SecurityException("Usuário não encontrado pelo email")

        }
    }
}