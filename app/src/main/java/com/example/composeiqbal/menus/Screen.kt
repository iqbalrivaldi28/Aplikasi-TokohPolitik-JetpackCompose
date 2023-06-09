package com.example.composeiqbal.menus

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object DetailTokohPolitik: Screen("home/{TokohId}") {
        fun createRoute(TokohId: Int) = "home/$TokohId"
    }
    object Profile : Screen("profile")
}