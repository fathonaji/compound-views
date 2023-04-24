package dev.fathonaji.compoundviews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class MainActivity : AppCompatActivity() {
    private lateinit var state1: StateView
    private lateinit var state2: StateView
    private lateinit var state3: StateView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        state1 = findViewById(R.id.state1)
        state2 = findViewById(R.id.state2)
        state3 = findViewById(R.id.state3)

        state1.setText("Uploading profile picture")
        state1.setState(State.LOADING)

        state2.setText("Uploading document")
        state2.setState(State.DISABLED)

        state3.setText("Verifying data")
        state3.setState(State.DISABLED)

        Handler(Looper.myLooper()!!).postDelayed({
            state1.setState(State.DONE)
            state2.setState(State.LOADING)

            Handler(Looper.myLooper()!!).postDelayed({
                state2.setState(State.DONE)
                state3.setState(State.LOADING)

                Handler(Looper.myLooper()!!).postDelayed({
                    state3.setState(State.FAILED)
                },3000)
            },3000)
        },3000)
    }
}