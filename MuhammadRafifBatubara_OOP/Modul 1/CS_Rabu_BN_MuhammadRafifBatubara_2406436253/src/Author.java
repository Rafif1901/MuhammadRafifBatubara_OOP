public class Author {
    private String nama;
    private int tahunLahir;

    public Author(String nama, int tahunLahir){
        this.nama = nama;
        this.tahunLahir = tahunLahir;
    }
    public String getNama(){
        return nama;
    }
    public int getTahunLahir(){
        return tahunLahir;
    }
    public void showDetail(){
        System.out.println("Nama Author: " + nama);
        System.out.println("Tahun Lahir: " + tahunLahir);
    }
}
