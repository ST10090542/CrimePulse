package com.st10090542.crimepulse_group6.ui.home

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.st10090542.crimepulse_group6.R
import com.st10090542.crimepulse_group6.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val policeEmergencyNumber: TextView = root.findViewById(R.id.police_emergency_number)
        val crimeStop: TextView = root.findViewById(R.id.crime_stop)
        val policeComplaints: TextView = root.findViewById(R.id.police_complaints)
        val ambulance: TextView = root.findViewById(R.id.ambulance)

        // Set clickable links for emergency numbers
        setClickablePhoneLink(policeEmergencyNumber, "10111")
        setClickablePhoneLink(crimeStop, "0860 010 111")
        setClickablePhoneLink(policeComplaints, "0800 333 177")
        setClickablePhoneLink(ambulance, "10177")

        return root
    }

    private fun setClickablePhoneLink(textView: TextView, phoneNumber: String) {
        val text = textView.text.toString()
        val index = text.indexOf(phoneNumber)

        if (index != -1) {
            val spannableString = SpannableString(text)
            val clickableSpan = object : ClickableSpan() {
                override fun onClick(view: View) {
                    val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                    if (dialIntent.resolveActivity(requireActivity().packageManager) != null) {
                        startActivity(dialIntent)
                    }
                }
            }

            spannableString.setSpan(
                clickableSpan,
                index,
                index + phoneNumber.length,
                SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            textView.text = spannableString
            textView.movementMethod = LinkMovementMethod.getInstance() // Set the movement method here
            textView.highlightColor = ContextCompat.getColor(requireContext(), android.R.color.transparent)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
