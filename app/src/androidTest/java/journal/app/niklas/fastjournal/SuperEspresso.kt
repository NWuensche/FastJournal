package journal.app.niklas.fastjournal

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.runner.RunWith

/**
 * Created by nwuensche on 10.03.17.
 */
@RunWith(AndroidJUnit4::class)
abstract class SuperEspresso {
    @Rule @JvmField var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)
    val appContext: Context = InstrumentationRegistry.getTargetContext()
}