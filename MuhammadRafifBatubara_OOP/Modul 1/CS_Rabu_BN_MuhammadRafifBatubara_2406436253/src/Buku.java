public class Buku {
    private String judul;
    private int year;
    private BookGenre genre;
    private Author author;

    public Buku(String judul, int year, BookGenre genre, Author author){
        this.judul = judul;
        this.year = year;
        this.genre = genre;
        this.author = author;
    }
    public int getAuthorCurrentAge(){
        return year - author.getTahunLahir();
    }
    public void showDetail(){
        System.out.println("Judul: " + judul);
        System.out.println("Genre: " + genre);
        System.out.println("Tahun Terbit: " + year);
        author.showDetail();
        System.out.println("Usia Author saat buku diterbitkan: " + getAuthorCurrentAge() + " tahun");
        System.out.println("\n");
    }
}