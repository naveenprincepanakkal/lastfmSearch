package poc.naveen.com.search

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import junit.framework.TestCase.assertNotNull
import org.junit.Rule
import org.junit.Test
import poc.naveen.com.search.CustomAssertions.Companion.hasItemCount
import poc.naveen.com.search.CustomMatchers.Companion.withItemCount
import poc.naveen.com.search.ui.view.MainActivity


class MainActivityTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun countPrograms() {
        val recyclerView = activityRule.activity.findViewById(R.id.rvSearchList) as RecyclerView
        val count = recyclerView.adapter?.itemCount
        onView(withId(R.id.searchView))
            .check(matches(count?.let { withItemCount(it) }))
    }

    @Test
    fun countProgramsWithViewAssertion() {
        val recyclerView = activityRule.activity.findViewById(R.id.rvSearchList) as RecyclerView
        val count = recyclerView.adapter?.itemCount
        onView(withId(R.id.rvSearchList))
            .check(count?.let { hasItemCount(it) })
    }

    @Test
    fun checkRecyclerViewDisplayed() {
        activityRule.launchActivity(Intent())
        onView(withId(R.id.rvSearchList)).check(matches(isDisplayed()))
        val recyclerView = activityRule.activity.findViewById(R.id.rvSearchList) as RecyclerView
        assertNotNull(recyclerView)
    }

}