package com.comfest.seatudy.ui.dashboard.detailsyllabus

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.comfest.seatudy.R
import com.comfest.seatudy.ui.dashboard.detailcourse.fragment.CourseProgramFragment
import com.comfest.seatudy.ui.dashboard.detailsyllabus.fargment.SubmissionFragment

val TAB_TITLES = arrayOf(
    R.string.tab_syllabus,
    R.string.tab_submission
)

class SectionsPagerAdapter(
    fragmentActivity: FragmentActivity,
    private val courseID: String
) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CourseProgramFragment(courseID)
            1 -> SubmissionFragment(courseID)
            else -> throw IllegalStateException("Unexpected position $position")
        }
    }

    override fun getItemCount(): Int {
        return 2
    }
}