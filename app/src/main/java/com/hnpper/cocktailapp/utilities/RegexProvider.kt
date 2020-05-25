package com.hnpper.cocktailapp.utilities

class RegexProvider() {
    val emailPattern = Regex("^[a-zA-Z0-9\\.]+@[a-zA-Z0-9.]+.[a-zA-Z]{2,}\$")
    val namePattern = Regex("([a-zA-Z].*\\s{0,1})+")
    val phonePattern =
        Regex("^((\\+36)*(06)*)([0-9\\\\/-]{6,12})\$")
    val cityPattern = Regex("^[a-zA-Z-].+\$")
    val zipPattern = Regex("^[0-9]{4}\$")
    val streetPattern = Regex("([a-zA-Z-0-9].+\\s?)+")
    val numberPattern = Regex("([a-zA-Z-0-9\\\\/].*\\s?)+")
}