package com.example.shoppinglist.domain.repository

import androidx.lifecycle.LiveData
import com.example.shoppinglist.domain.ShopItem

interface ShopListRepository {

    // Добавление элемента в список
    fun addShopItem(shopItem: ShopItem)
    // Удаление элемента из списка
    fun removeShopItem(shopItem: ShopItem)
    // Редактирование элемента в списке
    fun editShopItem(shopItem: ShopItem)
    // Получить элемент по его id
    fun getShopItem(shopItemId: Int):ShopItem
    // Добавление спика
    fun getShopList(): LiveData<List<ShopItem>>

}