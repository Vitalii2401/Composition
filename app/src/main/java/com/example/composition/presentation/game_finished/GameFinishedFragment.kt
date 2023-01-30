package com.example.composition.presentation.game_finished

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import com.example.composition.R
import com.example.composition.databinding.FragmentGameFinishedBinding
import com.example.composition.domain.entities.GameResult
import com.example.composition.presentation.game.GameFragment

class GameFinishedFragment : Fragment() {

    private lateinit var binding: FragmentGameFinishedBinding
    private lateinit var gameResult: GameResult

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClickListeners()
        bindViews()
    }

    private fun setupClickListeners() {
        binding.buttonRetry.setOnClickListener {
            retryGame()
        }
    }

    private fun bindViews() {
        with(binding) {

            ivSmile.setImageResource(getSmileResId())

            tvRequiredAnswers.text = getString(
                R.string.text_required_answers,
                gameResult.gameSettings.minCountOfRightAnswers.toString()
            )

            tvScoreAnswers.text = getString(
                R.string.text_score_answers,
                gameResult.countOfRightAnswers.toString()
            )

            tvRequiredPercentage.text = getString(
                R.string.text_required_percentage,
                gameResult.gameSettings.minPercentOfRightAnswers.toString()
            )

            tvScorePercentage.text = getString(
                R.string.text_score_percentage,
                getPercentOfRightAnswers().toString()
            )
        }
    }

    private fun getPercentOfRightAnswers(): Int = with(gameResult) {
        if (countOfQuestions == 0) 0 else ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()
    }

    private fun getSmileResId(): Int =
        if (gameResult.winner) R.drawable.ic_smile_good else R.drawable.ic_smile_sad

    private fun retryGame() {
        findNavController().popBackStack()
    }

    private fun parseArgs() {
        requireArguments().getParcelable<GameResult>(KEY_GAME_RESULT)?.let {
            gameResult = it
        }
    }

    companion object {

        const val KEY_GAME_RESULT = "result"

        fun newInstance(result: GameResult): GameFinishedFragment {
            return GameFinishedFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_GAME_RESULT, result)
                }
            }
        }
    }
}