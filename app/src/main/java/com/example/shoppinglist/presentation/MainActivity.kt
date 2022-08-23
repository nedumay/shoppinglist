package com.example.shoppinglist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.shoppinglist.R

class MainActivity : AppCompatActivity() {

    private lateinit var vm: MainViewModel

    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vm = ViewModelProvider(this)[MainViewModel::class.java]
        // подписка на объект vm
        vm.shopList.observe(this) {
            Log.d("MainActivityTest", it.toString())
            if (count == 0) {
                count++
                val item = it[0]
                vm.removeShopItem(shopItem = item)
            }
        }
    }
}