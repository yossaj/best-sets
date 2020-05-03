package com.example.bestset.ui.exercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.bestset.data.ExerciseDatabase
import com.example.bestset.databinding.FragmentExerciseBinding
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet


class ExerciseFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentExerciseBinding.inflate(inflater)
        val arguments = ExerciseFragmentArgs.fromBundle(arguments!!)
        val application = requireNotNull(this.activity).application
        val datasource = ExerciseDatabase.getInstance(application)

        val viewModelFactory = ExerciseViewModelFactory(datasource ,arguments.exerciseName)
        binding.viewModel = ViewModelProviders.of(this, viewModelFactory).get(ExerciseViewModel::class.java)
        setupLineChart(binding, arguments)


        return binding.root
    }

    private fun setupLineChart(
        binding: FragmentExerciseBinding,
        arguments: ExerciseFragmentArgs
    ) {
        val entries = ArrayList<Entry>()
        entries.add(Entry(1f, 2f))
        entries.add(Entry(2f, 3f))
        entries.add(Entry(3f, 4f))
        entries.add(Entry(4f, 3f))
        entries.add(Entry(5f, 3f))
        entries.add(Entry(6f, 3f))
        entries.add(Entry(7f, 1f))
        val dataSet = LineDataSet(entries, "Test")
        val lineData = LineData(dataSet)
        binding.chart.description.text = arguments.exerciseName
        binding.chart.description.textSize += 8
        binding.chart.data = lineData
//        binding.chart.description.textColor = context!!.getColor(R.color.colorAccent)
        binding.chart.invalidate()
    }

    fun recordExerciseResults(exerciseName : String, exerciseVolume: Int){



    }



}