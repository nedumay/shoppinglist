package com.example.shoppinglist.domain.UseCase

import com.example.shoppinglist.domain.ShopItem
import com.example.shoppinglist.domain.repository.ShopListRepository

class EditShopItemUseCase(private val shopListRepository: ShopListRepository) {

    fun editShopItem(shopItem: ShopItem){
        shopListRepository.editShopItem(shopItem = shopItem)
    }
}