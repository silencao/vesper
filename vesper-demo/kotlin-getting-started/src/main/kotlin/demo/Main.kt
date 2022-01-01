package demo

import kotlin.math.floor

fun reward(coins: Int): Int {
    return floor(coins.toDouble() / 50).toInt().coerceAtMost(60)
}

fun buy(prices: List<Int>) {
    println(prices.mapIndexed { index, price ->
        var coins = price
        while (reward(coins) * index > reward(coins - price) * (index + 1)) coins++
        coins
    })
}

fun main() {
    buy(listOf(180, 310, 530,  910, 1560)) // 100%
    buy(listOf(200, 345, 600, 1020, 1760)) // 125%
    buy(listOf(205, 355, 610, 1050, 1800)) // 130%
    buy(listOf(210, 365, 620, 1070, 1840)) // 125%
    buy(listOf(220, 380, 650, 1120      )) // 145%
    buy(listOf(260, 450, 770, 1320, 2270)) // 190%
    buy(listOf(350, 600, 770, 1320      )) // 190%

}

fun music(): String {
    return "qwety~yuy yttettye qweteww qweteww qhqee rert"
}