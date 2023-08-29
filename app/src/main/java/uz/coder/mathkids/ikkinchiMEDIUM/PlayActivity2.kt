package uz.coder.mathkids.ikkinchiMEDIUM

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.coder.mathkids.databinding.ActivityPlay2Binding

class PlayActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityPlay2Binding
    var cals = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlay2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent2 = intent.getStringExtra("orta")
        if (intent2 != null) {
            cals = intent2
        }

        binding.apply {
            addtion.setOnClickListener{
                val calInt = Intent(this@PlayActivity2, MediumClass::class.java)
                calInt.putExtra("key","+")
                startActivity(calInt)
            }

            sub.setOnClickListener{
                val calInt = Intent(this@PlayActivity2, MediumClass::class.java)
                calInt.putExtra("key","-")
                startActivity(calInt)
            }

            multi.setOnClickListener{
                val calInt = Intent(this@PlayActivity2, MediumClass::class.java)
                calInt.putExtra("key","*")
                startActivity(calInt)
            }

            divie.setOnClickListener{
                val calInt = Intent(this@PlayActivity2, MediumClass::class.java)
                calInt.putExtra("key ","/")
                startActivity(calInt)
            }

        }

    }
}