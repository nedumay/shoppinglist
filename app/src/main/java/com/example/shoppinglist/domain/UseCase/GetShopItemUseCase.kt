package com.example.shoppinglist.domain.UseCase

import com.example.shoppinglist.domain.ShopItem
import com.example.shoppinglist.domain.repository.ShopListRepository

class GetShopItemUseCase(private val shopListRepository: ShopListRepository) {

    suspend fun getShopItem(shopItemId: Int):ShopItem{
        return shopListRepository.getShopItem(shopItemId = shopItemId)
    }
}