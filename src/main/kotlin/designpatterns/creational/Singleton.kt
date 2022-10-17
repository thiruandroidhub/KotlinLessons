package designpatterns.creational


fun main() {
    val myFavouriteMovies: List<String> = emptyList() // empty list is a singleton
    val yourFavouriteMovies: List<String> = emptyList() // // empty list is a singleton

    print("are they the same? = ${myFavouriteMovies === yourFavouriteMovies}")
}