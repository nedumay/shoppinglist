package com.example.shoppinglist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppinglist.data.ShopListRepositoryImpl
import com.example.shoppinglist.domain.ShopItem
import com.example.shoppinglist.domain.UseCase.EditShopItemUseCase
import com.example.shoppinglist.domain.UseCase.GetShopListUseCase
import com.example.shoppinglist.domain.UseCase.RemoveShopItemUseCase

class MainViewModel : ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val removeShopItemUseCase = RemoveShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    fun removeShopItem(shopItem: ShopItem){
        removeShopItemUseCase.removeShopItem(shopItem = shopItem) // удаляем элемент
    }
    // enable & disable элемента списка
    fun changeEnabledState(shopItem: ShopItem){
        val newItem = shopItem.copy(enabled = !shopItem.enabled) // делаем копию объекта
        editShopItemUseCase.editShopItem(newItem) // изменяем элемент списка
    }

}