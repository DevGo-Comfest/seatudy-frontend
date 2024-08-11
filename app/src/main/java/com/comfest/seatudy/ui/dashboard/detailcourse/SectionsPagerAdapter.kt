package com.comfest.seatudy.ui.dashboard.detailcourse

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.comfest.seatudy.R
import com.comfest.seatudy.ui.dashboard.detailcourse.fargment.SubmissionFragment
import com.comfest.seatudy.ui.dashboard.detailcourse.fargment.SyllabusFragment

private val TAB_TITLES = arrayOf(
    R.string.tab_syllabus,
    R.string.tab_submission
)

class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> SyllabusFragment()
            1 -> SubmissionFragment()
            else -> throw IllegalStateException("Unexpected position $position")
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return 2
    }
}