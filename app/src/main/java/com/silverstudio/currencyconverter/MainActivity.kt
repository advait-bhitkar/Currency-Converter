package com.silverstudio.currencyconverter

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    private var requestQueue: RequestQueue? = null

    var curr1: Int = 0
    var curr2: Int = 0

    private lateinit var button: Button
    private lateinit var text1:EditText
    private lateinit var text2:TextView
    private lateinit var names:TextView
    private lateinit var names22:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponent()
    }

    fun initComponent(){



        names = findViewById(R.id.names)
        names22 = findViewById(R.id.name22)
        text1 = findViewById(R.id.edittext)
        text2 = findViewById(R.id.edittext1)
        val currency = resources.getStringArray(R.array.Currency)
        val currencyname= resources.getStringArray(R.array.name)

        val spinner = findViewById<Spinner>(R.id.spinner)
        if (spinner != null) {
            val adapter = ArrayAdapter(this,
                    R.layout.spinner_item_text, currency)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                    AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {

                    curr1 = position
                    names.text = currencyname[position]


                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }



        val spinner1 = findViewById<Spinner>(R.id.spinner1)
        if (spinner != null) {
            val adapter = ArrayAdapter(this,
                R.layout.spinner_item_text, currency)
            spinner1.adapter = adapter

            spinner1.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {

                    curr2 = position
                    names22.text = currencyname[position]

                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }



        button = findViewById(R.id.button)
        button.setOnClickListener {

            button.hideKeyboard()
            var str1: String = "EGP_"
            var str2: String = "EGP"
            when (curr1) {
                0 -> {
                    str1 = "EGP_"
                }
                1 -> {
                    str1 = "IDR_"
                }
                2 -> {
                    str1 = "INR_"
                }
                3 -> {
                    str1 = "JPY_"
                }
                4 -> {
                    str1 = "LKR_"
                }
                5 -> {
                    str1 = "NPR_"
                }
                6 -> {
                    str1 = "NZD_"
                }
                7 -> {
                    str1 = "PKR_"
                }
                8 -> {
                    str1 = "RUB_"
                }
                9 -> {
                    str1 = "SGD_"
                }
                10 -> {
                    str1 = "USD_"
                }
            }



            when {
                curr2==0 -> {
                    str2 = "EGP"
                }
                curr2==1 -> {
                    str2 = "IDR"
                }
                curr2==2 -> {
                    str2 = "INR"
                }
                curr2==3 -> {
                    str2 = "JPY"
                }
                curr2==4 -> {
                    str2 = "LKR"
                }
                curr2==5 -> {
                    str2 = "NPR"
                }
                curr2==6 -> {
                    str2 = "NZD"
                }
                curr2==7 -> {
                    str2= "PKR"
                }
                curr2==8 -> {
                    str2 = "RUB"
                }
                curr2==9 -> {
                    str2 = "SGD"
                }
                curr2==10 -> {
                    str2 = "USD"
                }
            }


            var query = "https://free.currconv.com/api/v7/convert?q=$str1$str2&compact=ultra&apiKey=4ddc37eb5e87f4917d42"



            if (text1.text.isEmpty()) {

                Toast.makeText(this, "Please enter currency", Toast.LENGTH_SHORT).show()

            }
            else {
                var pairValue: String = str1 + str2
                val v1: Float = text1.text.toString().toFloat()

            jsonParse(pairValue,query)

            }

        }


    }



    fun jsonParse(pairValue: String, url: String) {



        val requestQueue = Volley.newRequestQueue(this)
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                try {

//                    Toast.makeText(this, pairValue.toString(), Toast.LENGTH_SHORT).show()

                    var result: Float = 0.0F
                    val value = text1.text.toString().toFloat()
                    val value2: Float = response.getString(pairValue).toFloat()

                    result = value*value2

                    text2.text = result.toString()





                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }) { error -> error.printStackTrace() }
        requestQueue.add(jsonObjectRequest)


    }


    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

}
