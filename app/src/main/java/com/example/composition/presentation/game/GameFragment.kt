package com.example.composition.presentation.game

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.composition.databinding.FragmentGameBinding
import com.example.composition.domain.entities.GameResult

class GameFragment : Fragment() {

    private lateinit var binding: FragmentGameBinding

    private val args by navArgs<GameFragmentArgs>()

    private val gameViewModel: GameViewModel by lazy {
        ViewModelProvider(
            this,
            GameViewModelFactory(requireActivity().application, args.level)
        )[GameViewModel::class.java]
    }

    private val tvOptions by lazy {
        mutableListOf<TextView>().apply {
            add(binding.tvOption1)
            add(binding.tvOption2)
            add(binding.tvOption3)
            add(binding.tvOption4)
            add(binding.tvOption5)
            add(binding.tvOption6)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        setClickListenersToOptions()
    }

    private fun observeViewModel() {

        gameViewModel.question.observe(viewLifecycleOwner) {
            with(binding) {
                tvSum.text = it.sum.toString()
                tvLeftNumber.text = it.visibleNumber.toString()

                for (i in 0 until tvOptions.size) {
                    tvOptions[i].text = it.options[i].toString()
                }
            }
        }

        gameViewModel.formattedTime.observe(viewLifecycleOwner) {
            binding.tvTimer.text = it
        }

        gameViewModel.progressAnswers.observe(viewLifecycleOwner) {
            binding.tvAnswersProgress.text = it
        }

        gameViewModel.percentOfRightAnswers.observe(viewLifecycleOwner) {
            binding.progressBar.setProgress(it, true)
        }

        gameViewModel.minPercent.observe(viewLifecycleOwner) {
            binding.progressBar.secondaryProgress = it
        }

        gameViewModel.enoughCountOfRightAnswers.observe(viewLifecycleOwner) {
            binding.tvAnswersProgress.setTextColor(getColorByState(it))
        }

        gameViewModel.enoughPercentOfRightAnswers.observe(viewLifecycleOwner) {
            val color = getColorByState(it)
            binding.progressBar.progressTintList = ColorStateList.valueOf(color)
        }

        gameViewModel.gameResult.observe(viewLifecycleOwner) {
            launchGameFinishedFragment(it)
        }
    }

    private fun setClickListenersToOptions() {
        for (tvOption in tvOptions) {
            tvOption.setOnClickListener {
                gameViewModel.chooseAnswer(tvOption.text.toString().toInt())
            }
        }
    }

    private fun getColorByState(state: Boolean): Int {
        val colorResId =
            if (state) android.R.color.holo_green_light else android.R.color.holo_red_light
        return ContextCompat.getColor(requireContext(), colorResId)
    }

    private fun launchGameFinishedFragment(result: GameResult) {
        findNavController().navigate(
            GameFragmentDirections.actionGameFragmentToGameFinishedFragment(
                result
            )
        )
    }
}