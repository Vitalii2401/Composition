package com.example.composition.presentation

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.composition.R
import com.example.composition.domain.entities.GameResult

@BindingAdapter("gameFinishedTVRequiredAnswers")
fun requiredAnswers(textView: TextView, count: Int) {
    textView.text = textView.context.getString(
        R.string.text_required_answers,
        count.toString()
    )
}

@BindingAdapter("gameFinishedTVScoreAnswers")
fun scoreAnswers(textView: TextView, count: Int) {
    textView.text = textView.context.getString(
        R.string.text_score_answers,
        count.toString()
    )
}

@BindingAdapter("gameFinishedTVRequiredPercentage")
fun requiredPercentage(textView: TextView, count: Int) {
    textView.text = textView.context.getString(
        R.string.text_required_percentage,
        count.toString()
    )
}

@BindingAdapter("gameFinishedTVScorePercentage")
fun scorePercentage(textView: TextView, gameResult: GameResult) {
    textView.text = textView.context.getString(
        R.string.text_score_percentage,
        getPercentOfRightAnswers(gameResult).toString()
    )
}

private fun getPercentOfRightAnswers(gameResult: GameResult): Int = with(gameResult) {
    if (countOfQuestions == 0) 0 else ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()
}

@BindingAdapter("gameFinishedIVSmileResource")
fun smileResource(imageView: ImageView, winner: Boolean) {
    imageView.setImageResource(getSmileResId(winner))
}

private fun getSmileResId(winner: Boolean): Int =
    if (winner) R.drawable.ic_smile_good else R.drawable.ic_smile_sad