package journal.app.niklas.a5minutejournal

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpTabs()

    }

    private fun setUpTabs() {
        tabhost.setup()
        tabhost.addTab(tabhost.newTabSpec("tab1").setIndicator("ALL ENTRIES").setContent(R.id.entries))
        tabhost.addTab(tabhost.newTabSpec("tab2").setIndicator("TODAY").setContent(R.id.today))
        tabhost.setCurrentTabByTag("tab2")
    }

}
