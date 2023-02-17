package com.example.checklistbygleb.presentation

import android.app.Application
import com.example.checklistbygleb.di.DaggerApplicationComponent

class CheckApp : Application() {

   val component by lazy {
        DaggerApplicationComponent
            .factory()
            .create(this)
   }
}