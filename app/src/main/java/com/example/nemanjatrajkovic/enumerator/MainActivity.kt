package com.example.nemanjatrajkovic.enumerator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val defaultSample = 1_000_000_0
    private var sample = Sample()

    private val stringConstantsClass = StringConstantsClass(sample)
    private val enumeratorTest = EnumeratorTest(sample)
    private val intConstantsTest = IntConstantsTest(sample)
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sample.value = defaultSample
        startConstantTest.setOnClickListener(this::clickStart)
        startEnumTest.setOnClickListener(this::clickStart)
        startIntTest.setOnClickListener(this::clickStart)

    }

    private fun clickStart(view: View) {
        sample.value = try {
            Integer.parseInt(sampleInput.text.toString())
        } catch (nfe: NumberFormatException) {
            defaultSample
        }

        when (view.id) {
            R.id.startConstantTest -> constantTest()
            R.id.startEnumTest -> enumTest()
            R.id.startIntTest -> intTest()

        }

    }

    private fun constantTest() {
        constantsResult.text = "working..."
        stringConstantsClass.runSample(handler, constantsResult)
    }

    private fun enumTest() {
        enumResult.text = "working..."
        enumeratorTest.runSample(handler, enumResult)
    }

    private fun intTest() {
        intResult.text = "working..."
        intConstantsTest.runSample(handler, intResult)
    }
}
