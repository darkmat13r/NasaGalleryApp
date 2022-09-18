package com.nasa.gallery.mobile.presentation.ui.main


import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.nasa.gallery.mobile.R
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
class DetailFragmentTest {

    val targetContext: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule(order = 1)
    var testRule = ActivityTestRule(MainActivity::class.java, false, false)

    @Before fun setup() {
        hiltRule.inject()
        testRule.launchActivity(Intent(targetContext, MainActivity::class.java))
    }
    @Test
    fun testTitleExists_WhenLoaded() {
        navigateToDetailsScreen()

        val textView = onView(
            allOf(
                withId(R.id.title), withText("Starburst Galaxy M94 from Hubble"),
                withParent(withParent(withId(R.id.list))),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Starburst Galaxy M94 from Hubble")))
    }

    @Test
    fun testDescriptionExists_WhenLoaded() {
        navigateToDetailsScreen()


        val textView2 = onView(
            allOf(
                withId(R.id.textView3), withText(
                    "Why does this galaxy have a ring of bright blue stars?  Beautiful island universe Messier 94 lies a mere 15 million light-years distant in the northern constellation of the Hunting Dogs (Canes Venatici). A popular target for Earth-based astronomers, the face-on spiral galaxy is about 30,000 light-years across, with spiral arms sweeping through the outskirts of its broad disk. But this Hubble Space Telescope field of view spans about 7,000 light-years across M94's central region. The featured close-up highlights the galaxy's compact, bright nucleus, prominent inner dust lanes, and the remarkable bluish ring of young massive stars. The ring stars are all likely less than 10 million years old, indicating that M94 is a starburst galaxy that is experiencing an epoch of rapid star formation from inspiraling gas. The circular ripple of blue stars is likely a wave propagating outward, having been triggered by the gravity and rotation of a oval matter distributions. Because M94 is relatively nearby, astronomers can better explore details of its starburst ring.    Astrophysicists: Browse 2,000+ codes in the Astrophysics Source Code Library"
                ),
                withParent(withParent(withId(R.id.list))),
                isDisplayed()
            )
        )
        textView2.check(matches(isDisplayed()))
    }


    @Test
    fun testImageExists_WhenLoaded() {
        navigateToDetailsScreen()

        val imageView = onView(
            allOf(
                withId(R.id.image),
                withParent(withParent(withId(R.id.list))),
                isDisplayed()
            )
        )
        imageView.check(matches(isDisplayed()))
    }


    @Test
    fun testNavigateToBack_WhenBackButtonIsClicked(){
        navigateToDetailsScreen()

        val appCompatImageView = onView(
            allOf(
                withId(R.id.back),
                childAtPosition(
                    childAtPosition(
                        withClassName(Matchers.`is`("android.widget.FrameLayout")),
                        1
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatImageView.perform(click())

        val scrollView = onView(
            allOf(
                withParent(withParent(withId(R.id.nav_host_fragment_content_main))),
                isDisplayed()
            )
        )
        scrollView.check(matches(isDisplayed()))
    }

    private fun navigateToDetailsScreen() {
        val shapeableImageView = onView(
            allOf(
                withId(R.id.image),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.list),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        shapeableImageView.perform(click())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
