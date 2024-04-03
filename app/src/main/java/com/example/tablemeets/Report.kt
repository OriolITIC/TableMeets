package com.example.tablemeets

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class Report : AppCompatActivity() {

    private lateinit var grafica: BarChart
    private lateinit var yearSpinner: Spinner
    private lateinit var selectedYear: String

    private val usersByYear = generateFixedData()

    private val PERMISSION_REQUEST_CODE = 101

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

        val btnCreatePdf = findViewById<Button>(R.id.btnCreatePdf)
        btnCreatePdf.setOnClickListener {

            checkPermissions()
        }
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
    private fun getSDCardDirectory(): File? {
        val storageDirectory = File("/storage")
        if (storageDirectory.exists()) {
            val files = storageDirectory.listFiles()
            if (files != null) {
                for (file in files) {
                    if (file.canWrite() && file.absolutePath.contains("sdcard")) {
                        return file
                    }
                }
            }
        }
        return null
    }
    private fun generateFixedData(): Map<String, List<Int>> {
        val data = mutableMapOf<String, List<Int>>()

        val counts2020 = listOf(60, 78, 43, 90, 94, 77, 53, 100, 88, 56, 76, 67)
        val counts2021 = listOf(92, 75, 77, 55, 61, 45, 66, 56, 88, 35, 56, 33)
        val counts2022 = listOf(55, 80, 50, 66, 73, 76, 47, 110, 88, 76, 65, 71)
        val counts2023 = listOf(75, 85, 63, 62, 53, 71, 56, 59, 79, 83, 65, 43)
        val counts2024 = listOf(53, 77, 23, 45, 55, 75, 56, 50, 45, 58, 65, 49)

        data["2020"] = counts2020
        data["2021"] = counts2021
        data["2022"] = counts2022
        data["2023"] = counts2023
        data["2024"] = counts2024

        return data
    }

    private fun generatePdf() {

        val sdCardDirectory = File("/mnt/sdcard/PDFs")
        sdCardDirectory.mkdirs()

        val pdfFileName = "Report.pdf"
        val pdfFile = File(sdCardDirectory, pdfFileName)

        val pdfDocument = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(816, 1054, 1).create()
        val page = pdfDocument.startPage(pageInfo)
        val canvas = page.canvas
        val paint = Paint()

        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.main_logo)
        val scaledBitmap = Bitmap.createScaledBitmap(bitmap, 240, 100, false)
        val logoX = (canvas.width - scaledBitmap.width) / 2f
        canvas.drawBitmap(scaledBitmap, logoX, 60f, paint)

        val titleText = "Reporte de usuarios por año"
        val descriptionText = StringBuilder()

        usersByYear.forEach { (year, counts) ->
            descriptionText.append("                      Año: $year\n\n")
            counts.forEachIndexed { index, count ->
                val monthName = getMonthName(index + 1)
                descriptionText.append("                      $monthName: $count usuarios\n")
            }
            descriptionText.append("\n")
        }

        val titlePaint = Paint().apply {
            isFakeBoldText = true
            textSize = 20f
        }
        val titleX = 75f
        canvas.drawText(titleText, titleX, 220f, titlePaint)

        val descriptionPaint = Paint().apply {
            textSize = 14f
        }
        val lines = descriptionText.toString().split("\n")
        var yPosition = 270f
        for (line in lines) {
            canvas.drawText(line, 10f, yPosition, descriptionPaint)
            yPosition += descriptionPaint.textSize
        }

        pdfDocument.finishPage(page)

        try {
            val outputStream = FileOutputStream(pdfFile)
            pdfDocument.writeTo(outputStream)
            outputStream.close()
            pdfDocument.close()

            Toast.makeText(this, "Se creó el PDF correctamente", Toast.LENGTH_LONG).show()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun getMonthName(monthNumber: Int): String {
        return when (monthNumber) {
            1 -> "Enero"
            2 -> "Febrero"
            3 -> "Marzo"
            4 -> "Abril"
            5 -> "Mayo"
            6 -> "Junio"
            7 -> "Julio"
            8 -> "Agosto"
            9 -> "Septiembre"
            10 -> "Octubre"
            11 -> "Noviembre"
            12 -> "Diciembre"
            else -> ""
        }
    }

    private fun checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED
        ) {
            // Permission is not granted, request the permission
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                PERMISSION_REQUEST_CODE
            )
        } else {
            // Permission has already been granted
            generatePdf()
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // Permission granted
                    generatePdf()
                } else {
                    // Permission denied
                    // You can handle this according to your app's requirements
                    // For example, display a message or request permission again
                    Toast.makeText(this, "Permiso denegado para guardar el PDF", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }

}
