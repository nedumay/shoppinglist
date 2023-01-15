package com.example.shoppinglist.domain.repository

import androidx.lifecycle.LiveData
import com.example.shoppinglist.domain.ShopItem

interface ShopListRepository {

    // Добавление элемента в список
    suspend fun addShopItem(shopItem: ShopItem)
    // Удаление элемента из списка
    suspend fun removeShopItem(shopItem: ShopItem)
    // Редактирование элемента в списке
    suspend fun editShopItem(shopItem: ShopItem)
    // Получить элемент по его id
    suspend fun getShopItem(shopItemId: Int):ShopItem
    // Добавление спика
    fun getShopList(): LiveData<List<ShopItem>>

}