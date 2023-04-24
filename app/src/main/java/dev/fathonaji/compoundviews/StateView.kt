package dev.fathonaji.compoundviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat

/**
 * Created by Fathona Aji on 23/04/23
 */
class StateView @JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    LinearLayout(context, attrs, defStyleAttr) {
    private lateinit var tvTitle: TextView
    private lateinit var ivIcon: ImageView
    private lateinit var progressBar: ProgressBar

    init {
        init()
    }

    fun setText(text: String) {
        tvTitle.text = text
    }

    fun setState(state: State) {
        when (state) {
            State.DISABLED -> disabledState()
            State.LOADING -> loadingState()
            State.DONE -> doneState()
            State.FAILED -> failedState()
        }
    }

    private fun disabledState() {
        progressBar.visibility = View.GONE
        ivIcon.setImageDrawable(
            ContextCompat.getDrawable(
                context,
                R.drawable.baseline_chevron_right_24
            )
        )
        ivIcon.visibility = View.VISIBLE
        alpha = 0.5f
    }

    private fun loadingState() {
        ivIcon.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
        alpha = 1f
    }

    private fun doneState() {
        progressBar.visibility = View.GONE
        ivIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.baseline_check_24))
        ivIcon.visibility = View.VISIBLE
        alpha = 1f
    }

    private fun failedState() {
        progressBar.visibility = View.GONE
        ivIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.baseline_clear_24))
        ivIcon.visibility = View.VISIBLE
        tvTitle.setTextColor(ContextCompat.getColor(context, R.color.red))
        alpha = 1f
    }

    private fun init() {
        LayoutInflater.from(context).inflate(R.layout.state_view, this, true)

        tvTitle = findViewById(R.id.tvTitleState)
        ivIcon = findViewById(R.id.ivIcState)
        progressBar = findViewById(R.id.progressbar)
    }
}
