public class BarangElektronik {
    private String nama;
    private int daya;
    private float durasi;
    private float KwHPerBarang;

    public BarangElektronik(String nama, int daya, float durasi) {
        this.nama = nama;
        this.daya = daya;
        this.durasi = durasi;
        this.KwHPerBarang = menghitungKwhBarang(); 
    }
    
    public float menghitungKwhBarang(){
        KwHPerBarang = ((daya * durasi) / 1000)*30;
        return KwHPerBarang;
    }
    
    public String getNama() {
        return nama;
    }

    public int getDaya() {
        return daya;
    }

    public float getDurasi() {
        return durasi;
    }
    
    public float getKwHPerBarang() {
        return KwHPerBarang;
    }
    
    
//    public String toString(){
//        return "\nNama Barang\t\t: " + this.nama + "\n" +
//                "Daya Barang\t\t: " + this.daya + " Watt\n" +
//                "Lama Pemakaian\t\t: " + this.durasi  + " Jam\n" +
//                "kwh Barang\t\t: " + this.KwHPerBarang + " KwH";
//    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setDaya(int daya) {
        this.daya = daya;
    }

    public void setDurasi(float durasi) {
        this.durasi = durasi;
    }

    public void setKwHPerBarang(float KwHPerBarang) {
        this.KwHPerBarang = KwHPerBarang;
    }
   
}
