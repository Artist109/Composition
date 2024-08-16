package com.example.composition.presentation

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.composition.R
import com.example.composition.databinding.FragmentGameBinding
import com.example.composition.domain.entity.GameResult
import com.example.composition.domain.entity.Level

class GameFragment : Fragment() {

    private val args by navArgs<GameFragmentArgs>()

    private val viewModelFactory by lazy {
        GameViewModelFactory(requireActivity().application,args.level)
    }
    private val viewModel by lazy {
        ViewModelProvider(this,viewModelFactory)[GameViewModel::class.java]
    }
//    private val tvVariants by lazy {
//        mutableListOf<TextView>().apply {
//            add(binding.tvOption1)
//            add(binding.tvOption2)
//            add(binding.tvOption3)
//            add(binding.tvOption4)
//            add(binding.tvOption5)
//            add(binding.tvOption6)
//        }
//    }

    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding = null")


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        observeViewModel()
//        setClickListenersToOptions()
    }

//    private fun setClickListenersToOptions() {
//        for (tvVariant in tvVariants) {
//            tvVariant.setOnClickListener {
//                viewModel.chooseAnswer(tvVariant.text.toString().toInt())
//            }
//        }
//    }

    fun observeViewModel() {
//        viewModel.question.observe(viewLifecycleOwner) {
//            binding.tvSum.text = it.sum.toString()
//            binding.tvLeftNumber.text = it.visibleNumber.toString()
//            for (i in 0 until tvVariants.size) {
//                tvVariants[i].text = it.variants[i].toString()
//            }
//        }
//        viewModel.percentOfRightAnswers.observe(viewLifecycleOwner) {
//            binding.progressBar.setProgress(it, true)
//        }
//        viewModel.enoughCountOfRightAnswers.observe(viewLifecycleOwner) {
//            val colorResId = if (it) {
//                android.R.color.holo_green_light
//            } else {
//                android.R.color.holo_red_light
//            }
//            val color = ContextCompat.getColor(requireContext(), colorResId)
//            binding.tvAnswersProgress.setTextColor(color)
//        }
//        viewModel.progressOfAnswers.observe(viewLifecycleOwner) {
//            binding.tvAnswersProgress.text = it
//        }
//        viewModel.enoughPercentOfRightAnswers.observe(viewLifecycleOwner) {
//            val colorResId = if (it) {
//                android.R.color.holo_green_light
//            } else {
//                android.R.color.holo_red_light
//            }
//            val color = ContextCompat.getColor(requireContext(), colorResId)
//            binding.progressBar.progressTintList = ColorStateList.valueOf(color)
//        }
//        viewModel.minPercent.observe(viewLifecycleOwner) {
//            binding.progressBar.secondaryProgress = it
//        }
        viewModel.gameResult.observe(viewLifecycleOwner) {
            launchGameFinishedFragment(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun launchGameFinishedFragment(gameResult: GameResult) {
//        requireActivity().supportFragmentManager.beginTransaction()
//            .replace(R.id.main_container, GameFinishedFragment.newInstance(gameResult))
//            .addToBackStack(null)
//            .commit()
        findNavController().navigate(GameFragmentDirections.actionGameFragmentToGameFinishedFragment(gameResult))
    }

//    private fun parseArgs() {
//        requireArguments().getParcelable<Level>(KEY_LEVEL)?.let {
//            level = it
//        }
//    }
}