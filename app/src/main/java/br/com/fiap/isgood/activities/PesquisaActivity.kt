package br.com.fiap.isgood.activities

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import br.com.fiap.isgood.R
import br.com.fiap.isgood.fragments.tab.LancheFragment
import br.com.fiap.isgood.fragments.tab.RestauranteFragment
import br.com.fiap.isgood.models.Usuario
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.lang.Exception


class PesquisaActivity : BaseDrawerActivity() {

    lateinit  var recyclerView:RecyclerView;
    lateinit  var tabLayout:TabLayout;
    lateinit  var viewPager2: ViewPager2;
    lateinit  var searchView: SearchView;
    val tabs = arrayListOf<String>("Restaurantes","Lanches");



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setOriginalContentView(R.layout.activity_pesquisa);
        searchView = findViewById(R.id.search_bar);
        try {
            usuario = Usuario.getById(intent.getIntExtra("idUsuario", 0))
        } catch (e : Exception){
            Toast.makeText(applicationContext, e.message + "\nAlgumas operações podem não funcionar corretamente.", Toast.LENGTH_SHORT).show()
        }
        configureTabs();
        Toast.makeText(applicationContext, "Bem vindo ${usuario.name}!", Toast.LENGTH_SHORT).show()
    }

    private fun configureTabs() {
        tabLayout = findViewById(R.id.tabLayoutPesquisa);
        viewPager2 = findViewById(R.id.fragViewPagerPesquisa);
        viewPager2.adapter = FragmentViewPageAdapter(this,tabs.size);

        TabLayoutMediator (tabLayout,viewPager2,TabLayoutMediator.TabConfigurationStrategy { tab, position ->
            tab.text = tabs[position];
        }).attach();
    }

    class FragmentViewPageAdapter (activity: BaseDrawerActivity, private val tabSize: Int) : FragmentStateAdapter (activity) {

        override fun getItemCount(): Int {
            return tabSize;
        }

        override fun createFragment(position: Int): Fragment {

            when(position) {
                0 -> return RestauranteFragment();
                1 -> return LancheFragment();
            }

            return Fragment();
        }
    }
}