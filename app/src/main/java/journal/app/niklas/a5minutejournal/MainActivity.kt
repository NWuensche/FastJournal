package journal.app.niklas.a5minutejournal

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.ab_today_title.*
import java.text.SimpleDateFormat
import java.util.*

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

        val today: Date = Calendar.getInstance().time
        ab_today_title.text = SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH).format(today.time)
    }

}
