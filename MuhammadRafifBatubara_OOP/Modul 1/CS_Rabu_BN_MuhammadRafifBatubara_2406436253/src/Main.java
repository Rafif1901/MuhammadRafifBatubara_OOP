
public class Main {
    public static void main(String[] args){
        Author authorWibu = new Author("Eiichiro Oda", 1975);
        Author authorKalcer = new Author("Yuval Noah Harari", 1976);
        Author authorDuduk = new Author("Stephen Hawking", 1942);

        Buku bukuOnePiece = new Buku("One Piece", 1997, BookGenre.FIKSI, authorWibu);
        Buku bukuSapiens = new Buku("Sapiens", 2011, BookGenre.SEJARAH, authorKalcer);
        Buku bukuUniverse = new Buku("A Brief History Of Time", 1988, BookGenre.SAINS, authorDuduk);

        bukuOnePiece.showDetail();
        bukuSapiens.showDetail();
        bukuUniverse.showDetail();
    }
}
