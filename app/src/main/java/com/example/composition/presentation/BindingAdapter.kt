package com.example.composition.presentation

import android.content.res.ColorStateList
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.composition.R
import com.example.composition.domain.entities.GameResult

interface OnOptionClickListener {
    fun onOptionClick(option: Int)
}

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

@BindingAdapter("gameFragmentPBPercentOfRightAnswers")
fun bindMinProgress(progressBar: ProgressBar, value: Int) {
    progressBar.setProgress(value, true)
}

@BindingAdapter("gameFragmentPBTint")
fun bindProgressTint(progressBar: ProgressBar, state: Boolean) {
    val color = getColorByState(state, progressBar)
    progressBar.progressTintList = ColorStateList.valueOf(color)
}

@BindingAdapter("gameFragmentTVTextColor")
fun bindProgressTint(textView: TextView, state: Boolean) {
    val color = getColorByState(state, textView)
    textView.setTextColor(color)
}

private fun getColorByState(state: Boolean, view: View): Int {
    val colorResId =
        if (state) android.R.color.holo_green_light else android.R.color.holo_red_light
    return ContextCompat.getColor(view.context, colorResId)
}

@BindingAdapter("gameFragmentTVNumberAsText")
fun bindNumberAsText(textView: TextView, number: Int) {
    textView.text = number.toString()
}

@BindingAdapter("gameFragmentTVOnOptionClickListener")
fun bindOnOptionClickListener(textView: TextView, clickListener: OnOptionClickListener) {
    textView.setOnClickListener {
        clickListener.onOptionClick(textView.text.toString().toInt())
    }
}