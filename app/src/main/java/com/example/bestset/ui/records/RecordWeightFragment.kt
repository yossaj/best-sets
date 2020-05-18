package com.example.bestset.ui.records

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import com.example.bestset.data.WeightDatabase
import com.example.bestset.databinding.FragmentRecordWeightBinding

class RecordWeightFragment : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRecordWeightBinding.inflate(inflater)
        val application = requireNotNull(requireActivity()).application
        val datasource = WeightDatabase.getInstance(application)

        val viewModel by lazy{
            val factory = RecordWeightViewModelFactory(datasource)
            ViewModelProviders.of(this, factory).get<RecordWeightViewModel>()
        }
        val adapter = WeightAdapter()


        binding.recordWeightButton.setOnClickListener({
            InputDialogs(viewModel)
                .show(requireFragmentManager(),"record weight")
        })

        viewModel.allWeightData.observe(viewLifecycleOwner, Observer {
                adapter.submitList(it)
        })
        binding.weightRecycler.adapter = adapter

        return binding.root
    }


}