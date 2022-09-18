package com.nasa.gallery.mobile.presentation.ui.main


import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnitRunner
import com.nasa.gallery.mobile.R
import com.nasa.mobile.CustomTestRunner
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@HiltAndroidTest
class ExploreFramgnetTest {

    val targetContext: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule(order = 1)
    var testRule = ActivityTestRule(MainActivity::class.java, false, false)


    @Before
    fun setup() {
        hiltRule.inject()
        testRule.launchActivity(Intent(targetContext, MainActivity::class.java))
    }
    @Test
    fun testListItemsAreShown_WhenDataLoaded() {
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
    fun testNavigateAnotherScreen_ItemClicked() {
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
        shapeableImageView.perform(ViewActions.click())

        val frameLayout = onView(
            allOf(
                withParent(
                    allOf(
                        withId(R.id.nav_host_fragment_content_main),
                        withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        frameLayout.check(matches(isDisplayed()))
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
