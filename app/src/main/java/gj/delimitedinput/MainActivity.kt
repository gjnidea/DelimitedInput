package gj.delimitedinput

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import gj.delimitedinput.view.CurrencyInputEditText
import gj.delimitedinput.view.DelimitedInputEditText

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val delimited = findViewById<DelimitedInputEditText>(R.id.delimited_text)
        val currency = findViewById<CurrencyInputEditText>(R.id.currency_text)

        currency.setText("")

        findViewById<Button>(R.id.click_me).setOnClickListener {
            Log.e("asdf", delimited.getInput())

            Log.e("asdf", currency.getStringInput())
            Log.e("asdf", currency.getBigDecimal().toPlainString())
        }
    }
}
