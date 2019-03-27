package pl.daftacademy.androidlevelup.view.home

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.children
import kotlinx.android.synthetic.main.activity_home.*
import pl.daftacademy.androidlevelup.R
import pl.daftacademy.androidlevelup.view.movies.MoviesFragment

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        nav_view.setNavigationItemSelectedListener { changePage(item = it) }
        setSupportActionBar(toolbar)
        val actionbar: ActionBar? = supportActionBar
        actionbar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu)
        }

        //To chyba nie jest najlepiej zrobione
        //domyślny wybór na początku
        filterMovies("All")


    }

    override fun onOptionsItemSelected(item: MenuItem) =
            when (item.itemId) {
                android.R.id.home -> {
                    drawer_layout.openDrawer(GravityCompat.START)
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }

    private fun changePage(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.all_movies -> filterMovies("All")
            R.id.action_movies -> filterMovies("Action")
            R.id.comedy_movies -> filterMovies("Comedy")
            R.id.crime_movies -> filterMovies("Crime")
            R.id.horror_movies -> filterMovies("Horror")
            R.id.romance_movies -> filterMovies("Romance")
            else -> return false
        }
        nav_view.menu.children.find { it.isChecked }?.isChecked = false
        item.isChecked = true
        drawer_layout.closeDrawers()
        return true
    }

    private fun filterMovies(category: String, addToBackStack: Boolean = false) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.content_frame, MoviesFragment.create(category))
                .apply { if(addToBackStack) addToBackStack(null) }
                .commit()
    }
}