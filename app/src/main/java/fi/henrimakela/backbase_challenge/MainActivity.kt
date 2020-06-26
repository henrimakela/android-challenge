package fi.henrimakela.backbase_challenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fi.henrimakela.backbase_challenge.repository.CityRepository
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
}
