package com.example.project3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController

class StartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var questionCount = 0
        var difficulty = ""
        var operation = ""

        // Inflate the layout
        val view = inflater.inflate(R.layout.fragment_start, container, false)

        val questionsCountText: TextView = view.findViewById(R.id.countTextView)

        //minus 1 question button
        val incrementMinusButton: Button = view.findViewById(R.id.minusButton)
        incrementMinusButton.setOnClickListener {
            if (questionCount > 0) { //Question box must be greater than 0
                questionCount--
                questionsCountText.text = questionCount.toString()
            }
        }

        //plus 1 question button
        val incrementPlusButton: Button = view.findViewById(R.id.plusButton)
        incrementPlusButton.setOnClickListener {
            questionCount++
            questionsCountText.text = questionCount.toString()
        }

        val difficultyRadioGroup: RadioGroup = view.findViewById(R.id.difficultyRadioGroup)
        difficultyRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.easyRadioButton -> {
                    difficulty = "easy"
                }
                R.id.mediumRadioButton -> {
                    difficulty = "medium"
                }
                R.id.hardRadioButton -> {
                    difficulty = "hard"
                }
            }
        }

        val operationRadioGroup: RadioGroup = view.findViewById(R.id.operationRadioGroup)
        operationRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.additionRadioButton -> {
                    operation = "addition"
                }
                R.id.multiplicationRadioButton -> {
                    operation = "multiplication"
                }
                R.id.divisionRadioButton -> {
                    operation = "division"
                }
                R.id.subtractionRadioButton -> {
                    operation = "subtraction"
                }
            }
        }

        //start button directs to the question screen
        val startButton: Button = view.findViewById(R.id.startButton)
        startButton.setOnClickListener {
            if (difficulty.isNotEmpty() && operation.isNotEmpty() && questionCount != 0) {
                val bundle = Bundle().apply {
                    putString("difficulty", difficulty)
                    putString("operation", operation)
                    putInt("questionCount", questionCount)
                }

                val navController = findNavController()
                navController.navigate(R.id.action_startFragment_to_quizFragment, bundle)
            }
        }

        return view
    }
}
