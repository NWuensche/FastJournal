package journal.app.niklas.fastjournal

import android.app.Activity
import android.content.Context
import com.google.android.material.snackbar.Snackbar
import android.view.inputmethod.InputMethodManager
import kotlinx.android.synthetic.main.fragment_tabs.*
import java.io.FileOutputStream


/**
 * Created by nwuensche on 19.02.17.
 */
object SaveFiles {

    private var activity: Activity? = null

    fun onSave(mainActivity: Activity) {
        activity = mainActivity

        saveFile(mainActivity, FileName.getRealDate(), getAllContent())

        // Message
        Snackbar.make(mainActivity.findViewById(R.id.layout_today), "Saved!", Snackbar.LENGTH_SHORT)
                .setAction("Action",null)
                .show()

        //Hide Keyboard
        val imm = mainActivity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(mainActivity.layout_today.windowToken, 0)
    }
    
    fun saveFile(context: Context, date: String, content: String) {
        try {
            val outputStream: FileOutputStream = context.openFileOutput(FileName.convertDateToFileName(date), Context.MODE_PRIVATE)
            outputStream.write(content.toByteArray())
            outputStream.close()
        }
        catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getAllContent(): String {
        val grateful: String = getGrateful()
        val great: String = getGreat()
        val daily: String = getDaily()
        val happend: String = getHappend()
        val better: String = getBetter()

        val input = "$grateful$great$daily$happend$better"

        return input
    }

    private fun getGrateful(): String {
        val grateful1 = activity!!.editText_grateful1.text.toString().plus('\n')
        val grateful2 = activity!!.editText_grateful2.text.toString().plus('\n')
        val grateful3 = activity!!.editText_grateful3.text.toString().plus('\n')

        val grateful = "$grateful1$grateful2$grateful3\n"

        return grateful
    }

    private fun getGreat(): String {
        val great1 = activity!!.editText_great1.text.toString().plus('\n')
        val great2 = activity!!.editText_great2.text.toString().plus('\n')
        val great3 = activity!!.editText_great3.text.toString().plus('\n')

        val great = "$great1$great2$great3\n"

        return great
    }

    private fun getDaily(): String {
        val daily1 = activity!!.editText_daily1.text.toString().plus('\n')
        val daily2 = activity!!.editText_daily2.text.toString().plus('\n')

        val daily = "$daily1$daily2\n"

        return daily
    }

    private fun getHappend(): String {
        val happend1 = activity!!.editText_happend1.text.toString().plus('\n')
        val happend2 = activity!!.editText_happend2.text.toString().plus('\n')
        val happend3 = activity!!.editText_happend3.text.toString().plus('\n')

        val happend = "$happend1$happend2$happend3\n"

        return happend
    }

    private fun getBetter(): String {
        val better1 = activity!!.editText_better1.text.toString().plus('\n')
        val better2 = activity!!.editText_better2.text.toString().plus('\n')

        val better = "$better1$better2"

        return better
    }

}