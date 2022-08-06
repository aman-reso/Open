package `in`.book.repo.open

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import android.view.Menu
import android.view.MenuItem
import `in`.book.repo.open.databinding.ActivityMainBinding
import `in`.book.repo.open.listeners.home.WidgetsInteraction
import `in`.book.repo.open.models.home.Book
import `in`.book.repo.open.ui.topic_list.TopicListActivity
import android.content.Intent
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeLandingMainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private var binding: ActivityMainBinding? = null
    private var bottomNavigationView: BottomNavigationView? = null
    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.homeToolbar)
        navController = findNavController(R.id.nav_host_fragment_content_main)
        initializeViewComponent()

    }

    private fun initializeViewComponent() {
        bottomNavigationView = binding?.bottomNavigationView
        if (navController != null) {
            bottomNavigationView?.setupWithNavController(navController!!)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

}