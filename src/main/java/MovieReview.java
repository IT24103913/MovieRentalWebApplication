public class MovieReview {
    private String username;
    private String movieTitle;
    private String comment;
    private int rating;

    public MovieReview(String username, String movieTitle, String comment, int rating) {
        this.username = username;
        this.movieTitle = movieTitle;
        this.comment = comment;
        this.rating = rating;
    }

    public void displayReview() {
        System.out.println("User: " + username);
        System.out.println("Movie: " + movieTitle);
        System.out.println("Rating: " + rating + "/5");
        System.out.println("Comment: " + comment);
        System.out.println("---------------------------");
    }
}



