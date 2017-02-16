package journal.app.niklas.a5minutejournal

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
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
        val today: Date = Calendar.getInstance().time
        supportActionBar?.title = SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH).format(today.time)
    }

}
