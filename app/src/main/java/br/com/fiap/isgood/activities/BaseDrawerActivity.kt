package br.com.fiap.isgood.activities

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import br.com.fiap.isgood.R
import br.com.fiap.isgood.models.Usuario
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


open class BaseDrawerActivity : AppCompatActivity() {

    lateinit  var drawerLayout: DrawerLayout;
    lateinit  var navView: NavigationView;
    lateinit  var toolbar: Toolbar;
    lateinit  var actionBarToggle: ActionBarDrawerToggle;
    lateinit  var usuario: Usuario;
    lateinit var originalContentView:FrameLayout;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_drawer)
        originalContentView =  findViewById<FrameLayout>(R.id.originalContentView);

        usuario = Usuario.getByEmail(Firebase.auth.currentUser?.email +"")
        usuario.name = Firebase.auth.currentUser?.displayName+""

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawerLayoutMenuLateral);
        navView = findViewById(R.id.navigationView);
        actionBarToggle = ActionBarDrawerToggle(this,drawerLayout,
            R.string.menu_open,
            R.string.menu_close
        );
        drawerLayout.addDrawerListener(actionBarToggle);
        actionBarToggle.syncState();

        supportActionBar!!.setDisplayHomeAsUpEnabled(true);
        configurarNavView();
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                drawerLayout.openDrawer(GravityCompat.START)
                return true
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private fun configurarNavView () {
        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    val intentMain = Intent(this, MainActivity::class.java)
                    intentMain.putExtra("idUsuario", usuario.id);
                    startActivity(intentMain)
                    true
                }
                R.id.nav_busca -> {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    val intentPesquisa = Intent(this, PesquisaActivity::class.java)
                    intentPesquisa.putExtra("idUsuario", usuario.id);
                    startActivity(intentPesquisa)
                    true
                }

                R.id.nav_logout -> {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    val intentLogout = Intent(this, LoginActivity::class.java)
                    startActivity(intentLogout)
                    true
                }
                else -> false
            }
        }
    }

    fun setOriginalContentView(layoutResID: Int) {
        originalContentView =  findViewById<FrameLayout>(R.id.originalContentView);
        if (originalContentView != null) {
            val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val lp = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            val stubView: View = inflater.inflate(layoutResID, originalContentView, false)
            originalContentView.addView(stubView, lp)
        }
    }

    fun setOriginalContentView(view: View?) {
        originalContentView =  findViewById<FrameLayout>(R.id.originalContentView);
        if (originalContentView != null) {
            val lp = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            originalContentView.addView(view, lp)
        }
    }
}