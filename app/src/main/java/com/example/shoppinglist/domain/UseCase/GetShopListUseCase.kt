package com.example.shoppinglist.domain.UseCase

import androidx.lifecycle.LiveData
import com.example.shoppinglist.domain.ShopItem
import com.example.shoppinglist.domain.repository.ShopListRepository

class GetShopListUseCase(private val shopListRepository: ShopListRepository) {

    fun getShopList(): LiveData<List<ShopItem>>{
        return shopListRepository.getShopList()
    }
}