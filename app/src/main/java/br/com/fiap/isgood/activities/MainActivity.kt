package br.com.fiap.isgood.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import br.com.fiap.isgood.R
import com.google.firebase.FirebaseApp


class MainActivity : BaseDrawerActivity() {
//
//    lateinit  var drawerLayout: DrawerLayout;
//    lateinit  var navView: NavigationView;
//    lateinit  var toolbar: Toolbar;
//    lateinit  var actionBarToggle: ActionBarDrawerToggle;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.setOriginalContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawerLayoutMenuLateral);
        navView = findViewById(R.id.navigationView);
        actionBarToggle = ActionBarDrawerToggle(this,drawerLayout,R.string.menu_open,R.string.menu_close);
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
                    val intent = Intent(this, PesquisaActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.nav_busca -> {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    true
                }

                R.id.nav_logout -> {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    true
                }
                else -> false
            }
        }
    }
}