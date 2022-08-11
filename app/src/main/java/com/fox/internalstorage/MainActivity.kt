package com.fox.internalstorage

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fox.internalstorage.databinding.ActivityMainBinding
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException


class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private var filename = "demoFile.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnWrite.setOnClickListener {
            writeData()
        }

        binding.btnRead.setOnClickListener {
            readData()
        }
    }

    fun printMessage(m: String?) {
       Toast.makeText(this, m, Toast.LENGTH_SHORT).show()
    }

    private fun writeData() {
        try {
            val fos: FileOutputStream = openFileOutput(filename, Context.MODE_PRIVATE)
            val data: String = binding.etName.text.toString()
            fos.write(data.toByteArray())
            fos.flush()
            fos.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        binding.etName.setText("")
        printMessage("writing  to file  $filename  completed...")
    }

    private fun readData() {
        try {
            val fin: FileInputStream = openFileInput(filename)
            var a: Int
            val temp = StringBuilder()
            while (fin.read().also { a = it } != -1) {
                temp.append(a.toChar())
            }

            // setting text from the file.
            binding.tv2Content.setText(temp.toString())
            fin.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        printMessage("reading to file $filename completed..")
    }
}