package journal.app.niklas.a5minutejournal

import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.ab_today_title.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpTabs()
        setUpActionBar()
    }

    private fun setUpTabs() {
        tabhost.setup()
        tabhost.addTab(tabhost.newTabSpec("tab1").setIndicator("ALL ENTRIES").setContent(R.id.entries))
        tabhost.addTab(tabhost.newTabSpec("tab2").setIndicator("TODAY").setContent(R.id.today))
        tabhost.setCurrentTabByTag("tab2")
    }


    private fun setUpActionBar() {
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.ab_today_title)

        ab_today_title.text = Today.getToday()
    }

    fun getAllDateFileNames(): List<String> {
        return filesDir
                .listFiles()
                .map { it.name }
                .filter { Character.isDigit(it[0]) } // 9 Feb -> 09 February
    }

    fun onTodaySaveButton(v: View) {
        Save.onSaveToday(v)
    }

}
