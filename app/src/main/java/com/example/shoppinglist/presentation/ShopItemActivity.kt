package com.example.shoppinglist.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shoppinglist.R
import com.example.shoppinglist.domain.ShopItem
import kotlin.RuntimeException

class ShopItemActivity : AppCompatActivity(),ShopItemFragment.OnEditingFinishedListener {

    private var screenMode = MODE_UNKNOWN
    private var shopItemId = ShopItem.UNDEFINED_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_item)
        parseIntent()
        // чтобы onCreate создавался 1 раз
        if(savedInstanceState == null){
            launchRightMode()
        }
        //observeViewModel()
    }

    override fun onEditingFinished() {
        finish()
    }



    private fun launchRightMode(){
        val fragment = when(screenMode){
            MODE_EDIT -> ShopItemFragment.newInstanceEditItem(shopItemId = shopItemId)
            MODE_ADD -> ShopItemFragment.newInstanceAddItem()
            else -> throw  RuntimeException("Unknown screen mode $screenMode")
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.shop_item_container, fragment) // замена контейнера
            .commit()
    }

    private fun parseIntent(){
        if(!intent.hasExtra(EXTRA_SCREEN_MODE)){
            throw RuntimeException("Param screen mode is absent")
        }
        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
        if (mode != MODE_EDIT && mode != MODE_ADD){
            throw RuntimeException("Unknown screen mode $mode")
        }
        screenMode = mode
        if(screenMode == MODE_EDIT ){
            if(!intent.hasExtra(EXTRA_SHOP_ITEM_ID)){
                throw RuntimeException("Param shop item id is absent")
            }
            shopItemId = intent.getIntExtra(EXTRA_SHOP_ITEM_ID, ShopItem.UNDEFINED_ID)
        }

    }

    companion object {
        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val EXTRA_SHOP_ITEM_ID = "extra_shop_item_id"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"
        private const val MODE_UNKNOWN = ""

        fun newIntentAddItem(context: Context):Intent{
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent
        }

        fun newIntentEditItem(context: Context, shopItemId:Int):Intent{
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
            intent.putExtra(EXTRA_SHOP_ITEM_ID, shopItemId)
            return intent
        }
    }

}

/*
    private lateinit var viewModel: ShopItemViewModel

    private lateinit var tilName: TextInputLayout
    private lateinit var tilCount: TextInputLayout
    private lateinit var etName: EditText
    private lateinit var etCount: EditText
    private lateinit var buttonSave: Button*/

/*

  private fun launchEditMode(){
      viewModel.getShopItem(shopItemId)
      viewModel.shopItem.observe(this){
          etName.setText(it.name)
          etCount.setText(it.count.toString())
      }
      buttonSave.setOnClickListener {
          viewModel.editShopItem(etName.text?.toString(), etCount.text?.toString())
      }

  }

  private fun launchAddMode(){
      buttonSave.setOnClickListener {
          viewModel.addShopItem(etName.text?.toString(), etCount.text?.toString())
      }
  }*/


/*
private fun initViews(){
    tilName = findViewById(R.id.til_name)
    tilCount = findViewById(R.id.til_count)
    etName = findViewById(R.id.et_name)
    etCount = findViewById(R.id.et_count)
    buttonSave = findViewById(R.id.save_btn)
}*/

/*
    private fun observeViewModel(){
        viewModel.errorInputCount.observe(this){
            val message = if(it){
                getString(R.string.error_input_count)
            }else{
                null
            }
            tilCount.error = message
        }

        viewModel.errorInputName.observe(this){
            val message = if(it){
                getString(R.string.error_input_name)
            }else{
                null
            }
            tilName.error = message
        }
        viewModel.shouldCloseScreen.observe(this){
            finish()
        }
    }*/