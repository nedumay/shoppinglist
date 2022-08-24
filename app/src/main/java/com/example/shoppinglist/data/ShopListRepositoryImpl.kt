package com.example.shoppinglist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shoppinglist.domain.ShopItem
import com.example.shoppinglist.domain.repository.ShopListRepository
import java.lang.RuntimeException
import kotlin.random.Random

object ShopListRepositoryImpl:ShopListRepository {

    private val shopListLD = MutableLiveData<List<ShopItem>>()
    private val shopList = sortedSetOf<ShopItem>({ o1,o2 -> o1.id.compareTo(o2.id) })

    private var autoIncId = 0

    init {
        for(i in 0 until 1000){
            val item = ShopItem(name = "Name $i", count = i, enabled = Random.nextBoolean())
            addShopItem(item)
        }
    }

    override fun addShopItem(shopItem: ShopItem) {
        // проверка существования id для элемента списка
        if(shopItem.id == ShopItem.UNDEFINED_ID){
            shopItem.id = autoIncId++ // автоустановка id для каждого созданного элемента списка
        }
        shopList.add(shopItem)
        updateList()
    }

    override fun removeShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateList()
    }

    override fun editShopItem(shopItem: ShopItem) {
       val oldElement = getShopItem(shopItemId = shopItem.id) // поиск и получение старого элемента через id
        shopList.remove(oldElement) // удаляем старый элемент
        addShopItem(shopItem = shopItem) // добавляем новый элемент
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        return shopList.find { it.id == shopItemId } ?: throw RuntimeException("Element with $shopItemId not found")
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListLD // возвращаем копию shopList, чтобы не использовать в других местах случайно
    }
    // автоматическое обновление списка
    private fun updateList(){
        shopListLD.value = shopList.toList()
    }
}