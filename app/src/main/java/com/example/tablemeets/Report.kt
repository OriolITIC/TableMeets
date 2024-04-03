package com.example.tablemeets

import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import kotlin.random.Random

class Report : AppCompatActivity() {

    private lateinit var grafica: BarChart
    private lateinit var yearSpinner: Spinner
    private lateinit var selectedYear: String


    private val usersByYear = generateRandomData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)

        grafica = findViewById(R.id.barChart)
        yearSpinner = findViewById(R.id.yearSpinner)

        val years = usersByYear.keys.toList()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, years)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        yearSpinner.adapter = adapter

        yearSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                selectedYear = parent?.getItemAtPosition(position).toString()
                updateChart(selectedYear)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        selectedYear = years.first()
        updateChart(selectedYear)
    }

    private fun updateChart(year: String) {
        val counts = usersByYear[year] ?: return

        val entries = ArrayList<BarEntry>()
        counts.forEachIndexed { index, count ->
            entries.add(BarEntry(index.toFloat(), count.toFloat()))
        }

        val dataSet = BarDataSet(entries, "Número de usuarios registrados por mes en el año $year")
        dataSet.color = ContextCompat.getColor(this, R.color.blue)
        val data = BarData(dataSet)

        // Configuramos el gráfico
        grafica.data = data
        grafica.setFitBars(true)
        grafica.invalidate()
    }

    private fun generateRandomData(): Map<String, List<Int>> {
        val random = Random.Default
        val data = mutableMapOf<String, List<Int>>()
        val years = listOf("2020", "2021", "2022", "2023", "2024")
        for (year in years) {
            val counts = mutableListOf<Int>()
            for (i in 0 until 12) {
                counts.add(random.nextInt(50, 100))
            }
            data[year] = counts
        }
        return data
    }
}


