package br.com.fiap.isgood.models
import org.junit.Test
import java.lang.Exception


class UsuarioTest {

    lateinit var authUser : Usuario

    @Test
    fun `Should return true if user Willian could authenticate`(){
        try {
            authUser = Usuario.getAutenticatedUser("williandrade@gmail.com", "teste123")
        } catch (e : Exception){
            assert(false)
        }
        assert (authUser.name.equals("Willian Andrade"))
    }

    @Test
    fun `Should return true if user Chatonildo could authenticate`(){
        try {
            authUser = Usuario.getAutenticatedUser("outro@email.com", "")
        } catch (e : Exception){
            assert(false)
        }
        assert (authUser.name.equals("Chatonildo da Silva"))
    }

}