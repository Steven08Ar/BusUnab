package co.edu.unab.santiagoarias.busunab.navigation

enum class Screens {
    SplashScreen,
    LoginScreen,
    HomeScreen, }

data class User(val emailc: String = "", val password: String = "", val userId: String = "")