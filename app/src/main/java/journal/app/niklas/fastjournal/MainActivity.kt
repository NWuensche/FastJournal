package journal.app.niklas.fastjournal

import android.content.Context
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.appcompat.widget.Toolbar
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_tabs.*
import kotlinx.android.synthetic.main.fragment_tabs.view.*


class MainActivity : AppCompatActivity() {
    private var firstTime = true

    /**
     * The [android.support.v4.view.PagerAdapter] that will provide
     * fragments for each of the sections. We use a
     * [FragmentPagerAdapter] derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * [android.support.v4.app.FragmentStatePagerAdapter].
     */
    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    /**
     * The [ViewPager] that will host the section contents.
     */
    private var mViewPager: ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container) as ViewPager
        mViewPager!!.adapter = mSectionsPagerAdapter
        mViewPager!!.currentItem++ // Today as curr Tab
        val tabLayout = findViewById(R.id.tabs) as TabLayout
        tabLayout.setupWithViewPager(mViewPager)


        tabLayout.addOnTabSelectedListener(
                object : TabLayout.ViewPagerOnTabSelectedListener(mViewPager) {

                    override fun onTabSelected(tab: TabLayout.Tab) {
                        super.onTabSelected(tab)
                        layout_today.scrollTo(0, section_grateful_label.bottom - section_grateful_label.height)

                        //Hide Keyboard
                        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.hideSoftInputFromWindow(layout_today.windowToken, 0)

                        // Show Save Icon only in Today VIew
                        when(tab.position) {
                            0 -> {
                                val save = findViewById(R.id.action_save_today) as ActionMenuItemView
                                save.visibility = View.GONE
                            }
                            1 -> {
                                val save = findViewById(R.id.action_save_today) as ActionMenuItemView
                                save.visibility = View.VISIBLE
                                editText_grateful1.setSelection(editText_grateful1.text.length)
                            }
                        }
                    }

                    override fun onTabUnselected(tab: TabLayout.Tab?) {
                        super.onTabUnselected(tab)
                    }

                    override fun onTabReselected(tab: TabLayout.Tab?) {
                        super.onTabReselected(tab)
                    }
                }
        )
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        if (id == R.id.action_save_today) {
            SaveFiles.onSave(this)
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    class PlaceholderFragment : Fragment() {

        fun loadAllEntries() {
            val files: List<String> = LoadFiles.getAllDatesDisplayName(activity!!.applicationContext)
            all_entries_view.adapter = ArrayAdapter(activity!!.applicationContext, R.layout.entry_item, files)


            all_entries_view.setOnItemClickListener { _, view, _, _ ->
                val textView = view as TextView
                val date: String = textView.text.toString()

                LoadFiles.loadSomeDayTextFromFileToView(activity!!.applicationContext, date)

                FileName.setCurrentFileDate(date)
                activity!!.container.currentItem++// Today as curr Tab

                Design.setEditTabTitle(activity!!) // Tab Title
            }
        }

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
            val rootView = inflater.inflate(R.layout.fragment_tabs, container, false)

            when(arguments!!.getInt(ARG_SECTION_NUMBER)) {
                1 -> {
                    rootView.layout_today.visibility = View.GONE
                    rootView.layout_all_entries.visibility = View.VISIBLE
                }

                2 -> {
                    rootView.layout_today.visibility = View.VISIBLE
                    rootView.layout_all_entries.visibility = View.GONE
                }
            }
            return rootView
        }

        override fun onStart() {
            super.onStart()

            loadAllEntries()
        }

        companion object {
            /**
             * The fragment argument representing the section number for this
             * fragment.
             */
            private val ARG_SECTION_NUMBER = "section_number"

            /**
             * Returns a new instance of this fragment for the given section
             * number.
             */
            fun newInstance(sectionNumber: Int): PlaceholderFragment {
                val fragment = PlaceholderFragment()
                val args = Bundle()
                args.putInt(ARG_SECTION_NUMBER, sectionNumber)
                fragment.arguments = args
                return fragment
            }
        }
    }

    /**
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1)
        }

        override fun getCount(): Int {
            // Show 2 total pages.
            return 2
        }

        override fun getPageTitle(position: Int): CharSequence? {
            when (position) {
                0 -> return "ALL ENTRIES"
                1 -> return "TODAY"
            }
            return null
        }

    }

    // Will be called after onChanged
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if(firstTime) {
            LoadFiles.loadTodaysTextFromFileToView(this)
            firstTime = false
            editText_grateful1.setSelection(editText_grateful1.text.length)
        }
    }

}
