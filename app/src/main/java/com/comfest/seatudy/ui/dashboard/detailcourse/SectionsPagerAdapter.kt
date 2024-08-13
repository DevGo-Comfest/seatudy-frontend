package com.comfest.seatudy.ui.dashboard.detailcourse

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.comfest.seatudy.R
import com.comfest.seatudy.ui.dashboard.detailcourse.fragment.CourseProgramFragment
import com.comfest.seatudy.ui.dashboard.detailcourse.fragment.ForumsFragment
import com.comfest.seatudy.ui.dashboard.detailcourse.fragment.InstructorsFragment
import com.comfest.seatudy.ui.dashboard.detailcourse.fragment.ReviewsFragment

val TAB_TITLES = arrayOf(
    R.string.tab_course,
    R.string.tab_instructor,
    R.string.tab_reviews,
    R.string.tab_forum
)

class SectionsPagerAdapterDetail(
    fragmentActivity: FragmentActivity,
    private val courseID: String
) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CourseProgramFragment(courseID)
            1 -> InstructorsFragment(courseID)
            2 -> ReviewsFragment(courseID)
            3 -> ForumsFragment(courseID)
            else -> throw IllegalStateException("Unexpected position $position")
        }
    }
}