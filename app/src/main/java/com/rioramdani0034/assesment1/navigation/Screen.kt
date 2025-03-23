package com.rioramdani0034.assesment1.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("mainScreen")
    data object About : Screen("aboutScreen")
    data object AddBlog : Screen("addBlogScreen")

}