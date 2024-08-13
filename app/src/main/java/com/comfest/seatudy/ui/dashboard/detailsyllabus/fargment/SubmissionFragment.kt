package com.comfest.seatudy.ui.dashboard.detailsyllabus.fargment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.comfest.seatudy.R

class SubmissionFragment(val courseID: String) : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_submission, container, false)
    }

    // berupa card yang isi nya subsmission to Detail Submission

}