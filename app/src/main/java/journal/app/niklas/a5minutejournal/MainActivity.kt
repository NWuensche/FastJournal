package journal.app.niklas.a5minutejournal

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.ab_today_title.*
import java.text.SimpleDateFormat
import java.util.*
import android.content.Context.MODE_PRIVATE
import java.io.File
import java.io.FileOutputStream


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

        ab_today_title.text = getToday()
    }

    private fun getToday(): String {
        val today: Date = Calendar.getInstance().time
        return SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH).format(today.time)
    }

    fun onTodaySaveButton(v: View) {
        val filename = getToday().replace(" ", "_").plus(".txt")
        val string = "Hello world!"

        try {
            val outputStream: FileOutputStream = openFileOutput(filename, Context.MODE_PRIVATE)
            outputStream.write(string.toByteArray())
            outputStream.close()
        }
        catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getAllDateFileNames(): List<String> {
        return filesDir
                .listFiles()
                .map { it.name }
                .filter { Character.isDigit(it[0]) } // 9 Feb -> 09 February
    }

}
