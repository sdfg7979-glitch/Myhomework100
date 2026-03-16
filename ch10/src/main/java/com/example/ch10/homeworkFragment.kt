package com.example.ch10

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment


class homeworkFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_homework, container, false)

        val etName = view.findViewById<EditText>(R.id.etName)
        val etEmail = view.findViewById<EditText>(R.id.etEmail)
        val radioGender = view.findViewById<RadioGroup>(R.id.radioGender)
        val checkAgree = view.findViewById<CheckBox>(R.id.checkAgree)
        val btnJoin = view.findViewById<Button>(R.id.btnJoin)

        btnJoin.setOnClickListener {

            val name = etName.text.toString()
            val email = etEmail.text.toString()

            val gender = when (radioGender.checkedRadioButtonId) {
                R.id.radioMale -> "남성"
                R.id.radioFemale -> "여성"
                else -> ""
            }

            if (!checkAgree.isChecked) {
                Toast.makeText(context, "약관에 동의하세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Toast.makeText(
                context,
                "이름: $name\n이메일: $email\n성별: $gender",
                Toast.LENGTH_LONG
            ).show()
        }

        return view
    }
}