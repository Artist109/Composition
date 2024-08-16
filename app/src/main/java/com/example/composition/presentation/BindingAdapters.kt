package com.example.composition.presentation

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.composition.R
import com.example.composition.domain.entity.GameResult

@BindingAdapter("requiredAnswers")
fun bindRequiredAnswers(textView: TextView, count: Int) {
    textView.text = String.format(textView.context.getString(R.string.required_score),count)
}
@BindingAdapter("scoreAnswers")
fun bindScoreAnswers(textView: TextView, count: Int) {
    textView.text = String.format(textView.context.getString(R.string.score_answers),count)
}
@BindingAdapter("requiredPercentage")
fun bindRequiredPercentage(textView: TextView, count: Int) {
    textView.text = String.format(textView.context.getString(R.string.required_percentage),count)
}

@BindingAdapter("scorePercentage")
fun bindScorePercentage(textView: TextView, gameResult: GameResult) {
    val percent = getScorePercentage(gameResult)
    textView.text = String.format(textView.context.getString(R.string.score_percentage),percent)
}

fun getScorePercentage(gameResult: GameResult): Int {
    return if (gameResult.countOfQuestionsTotal == 0) {
        0
    } else {
        (gameResult.countOfRightAnswers / gameResult.countOfQuestionsTotal.toDouble() * 100).toInt()
    }
}

@BindingAdapter("emojiResult")
fun bindEmojiResult(imageView: ImageView, winner: Boolean) {
    imageView.setImageResource(getRightImage(winner))
}

private fun getRightImage(winner: Boolean): Int {
    return if (winner) {
        R.drawable.ic_smile
    } else {
        R.drawable.ic_sad
    }
}