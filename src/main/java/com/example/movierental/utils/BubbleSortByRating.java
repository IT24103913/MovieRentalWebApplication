import com.example.movierental.models.Movie;
import com.example.movierental.models.Review;
import com.example.movierental.utils.MovieWithRating;

import static com.example.movierental.utils.MovieSorter.calculateAverageRatings;

public static void bubbleSortByRating(List<MovieWithRating> movieRatings) {
    int n = movieRatings.size();
    boolean swapped;

    for (int i = 0; i < n - 1; i++) {
        swapped = false;
        for (int j = 0; j < n - 1 - i; j++) {
            // Compare adjacent elements
            if (movieRatings.get(j).getAverageRating() < movieRatings.get(j + 1).getAverageRating()) {
                // Swap if the current element has a lower rating
                MovieWithRating temp = movieRatings.get(j);
                movieRatings.set(j, movieRatings.get(j + 1));
                movieRatings.set(j + 1, temp);
                swapped = true;
            }
        }
        // If no swaps occurred, the list is sorted
        if (!swapped) {
            break;
        }
    }
}
public static void main(String[] args) {
    // Sample movies
    List<Movie> movies = new ArrayList<>();
   //movies.add(new Movie(1L, "The Shawshank Redemption", "Drama", 1994, 3.99));
   //movies.add(new Movie(2L, "The Godfather", "Crime", 1972, 4.99));
   //movies.add(new Movie(3L, "Inception", "Sci-Fi", 2010, 2.99));
   //movies.add(new Movie(1L, "The Shawshank Redemption", "Drama", 1994, 3.99));
   //movies.add(new Movie(2L, "The Godfather", "Crime", 1972, 4.99));
   //movies.add(new Movie(3L, "Inception", "Sci-Fi", 2010, 2.99));

    // Sample reviews
    List<Review> reviews = new ArrayList<>();
    reviews.add(new Review(1, "The Shawshank Redemption", "Amazing!", 5, LocalDate.now(), "Alice"));
    reviews.add(new Review(2, "The Shawshank Redemption", "Good", 4, LocalDate.now(), "Bob"));
    reviews.add(new Review(3, "The Godfather", "Classic!", 5, LocalDate.now(), "Charlie"));
    reviews.add(new Review(4, "The Godfather", "Okay", 3, LocalDate.now(), "Dave"));
    reviews.add(new Review(5, "Inception", "Mind-blowing", 4, LocalDate.now(), "Eve"));

    // Calculate average ratings
    List<MovieWithRating> movieRatings = calculateAverageRatings(movies, reviews);

    // Sort by average rating
    bubbleSortByRating(movieRatings);

    // Print sorted movies
    System.out.println("Movies sorted by average rating (descending):");
    for (MovieWithRating mr : movieRatings) {
        System.out.printf("Movie: %s, Average Rating: %.2f%n",
                mr.getMovie().getTitle(), mr.getAverageRating());
    }
}