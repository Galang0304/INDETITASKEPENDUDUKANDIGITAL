@file:Suppress("DEPRECATION")

package com.example.indetitaskependudukandigital

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult

class home : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_home)

        val ktp = findViewById<ConstraintLayout>(R.id.ktp)
        val profile = findViewById<ImageView>(R.id.profile)
        val lock = findViewById<ImageView>(R.id.lock)
        val barcode = findViewById<ImageView>(R.id.barcode)
        val barcodeResultTextView = findViewById<TextView>(R.id.barcode_result2) // TextView untuk menampilkan hasil

        // Tombol untuk pindah ke aktivitas lain
        ktp.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        profile.setOnClickListener {
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }

        lock.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Tombol untuk membuka barcode scanner
        barcode.setOnClickListener {
            startBarcodeScanner()  // Memanggil fungsi startBarcodeScanner
        }

        // Menampilkan hasil barcode terakhir yang dipindai (jika ada)
        getSavedBarcodeResult(barcodeResultTextView)
    }

    // Fungsi untuk memulai pemindai barcode
    private fun startBarcodeScanner() {
        val integrator = IntentIntegrator(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
        integrator.setPrompt("Scan a barcode")
        integrator.setCameraId(0) // 0 for rear camera, 1 for front camera
        integrator.setBeepEnabled(true)
        integrator.setOrientationLocked(true) // Lock orientation to portrait
        integrator.setTorchEnabled(false) // Selalu aktifkan flash saat pemindaian
        integrator.initiateScan()
        integrator.setTimeout(15)
    }

    // Fungsi untuk menangani hasil pemindaian barcode
    @Deprecated("This method has been deprecated in favor of using the Activity Result API...")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result: IntentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result.contents == null) {
            // Scan dibatalkan
            Toast.makeText(this, "Scan cancelled", Toast.LENGTH_SHORT).show()
        } else {
            // Hasil barcode berhasil
            Toast.makeText(this, "Scanned: " + result.contents, Toast.LENGTH_SHORT).show()

            // Simpan hasil barcode dan tampilkan di TextView
            saveBarcodeResult(result.contents)
            val barcodeResultTextView = findViewById<TextView>(R.id.barcode_result2)
            barcodeResultTextView.text = result.contents // Tampilkan hasil di TextView
        }
    }

    // Fungsi untuk menyimpan hasil barcode
    private fun saveBarcodeResult(result: String) {
        val sharedPreferences = getSharedPreferences("barcode_prefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("last_scanned_barcode", result)
        editor.apply()
    }

    // Fungsi untuk mengambil hasil yang disimpan
    private fun getSavedBarcodeResult(barcodeResultTextView: TextView) {
        val sharedPreferences = getSharedPreferences("barcode_prefs", MODE_PRIVATE)
        val lastScannedBarcode = sharedPreferences.getString("last_scanned_barcode", null)
        if (lastScannedBarcode != null) {
            barcodeResultTextView.text = lastScannedBarcode // Tampilkan hasil yang disimpan
        } else {
            barcodeResultTextView.text = "Belum ada hasil barcode yang dipindai"
        }
    }
}
