package com.comfest.seatudy.ui.dashboard.detail

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.comfest.seatudy.R
import com.comfest.seatudy.ui.dashboard.detail.fragment.CourseProgramFragment
import com.comfest.seatudy.ui.dashboard.detail.fragment.ForumsFragment
import com.comfest.seatudy.ui.dashboard.detail.fragment.InstructorsFragment
import com.comfest.seatudy.ui.dashboard.detail.fragment.ReviewsFragment

private val TAB_TITLES = arrayOf(
    R.string.tab_course,
    R.string.tab_instructor,
    R.string.tab_reviews,
    R.string.tab_forum
)

class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position){
            0 -> CourseProgramFragment()
            1 -> InstructorsFragment()
            2 -> ReviewsFragment()
            3 -> ForumsFragment()
            else -> throw IllegalStateException("Unexpected position $position")
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return 4
    }
}