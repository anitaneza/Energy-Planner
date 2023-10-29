
import java.util.Scanner;

public class User {
    private String nama;
    private String password;
    private int id;
    
    private int dayaMeteran;
    private float tarifDayaPerKwh;
    
    private BarangElektronik[] barang = new BarangElektronik[20];
    private float[] biayaPerBarang = new float[10];
    private int jumlahBarang;
    private float[] persentasePemakaian = new float[10];
    
    private float totalKwh;
    private float totalBiaya;

    
    public User(String nama, String password, int id, int dayaMeteran) {
        this.nama = nama;
        this.password = password;
        this.id = id;
        this.dayaMeteran = dayaMeteran;
        
            if (dayaMeteran == 450 || dayaMeteran == 900) {
                this.tarifDayaPerKwh = (float) 1352.00;
            } else if (dayaMeteran == 1300 || dayaMeteran == 2200) {
                this.tarifDayaPerKwh =  (float) 1444.70;
            } else if (dayaMeteran == 3500 || dayaMeteran == 6600) {
                this.tarifDayaPerKwh = (float) 1699.53;
            }
          
        }

    public boolean login(String namaUser, String passwordUser) {
        if (this.nama.equals(namaUser) && this.password.equals(passwordUser)) {
            System.out.println("Login berhasil!");
            return true;
        } else {
            System.out.println("Login Gagal ! Username atau Password salah.");
            return false;
        }
    }
    
    public void tambahBarang(BarangElektronik barang){
        this.barang[jumlahBarang] = barang;
        this.biayaPerBarang[jumlahBarang] = this.barang[jumlahBarang].getKwHPerBarang()* this.tarifDayaPerKwh;
        jumlahBarang++;
    }
    
    public void updateBarang(int idBarang,String name,int dayaBarang,float durasi){
        if (idBarang>=0 && idBarang <=jumlahBarang){
            this.barang[idBarang].setNama(name);
            this.barang[idBarang].setDaya(dayaBarang);
            this.barang[idBarang].setDurasi(durasi);
            this.barang[idBarang].setKwHPerBarang((dayaBarang*durasi/1000)*30);
            this.biayaPerBarang[idBarang]=this.barang[idBarang].getKwHPerBarang()*this.tarifDayaPerKwh;
            setTotalBiaya();
            setTotalKwh();
        }else {
            System.out.println("Barang Tidak Ada");
        }
    }

    public void deleteBarang(int idBarang){
        if (idBarang>=0&&idBarang<=jumlahBarang){
            for (int i=idBarang;i<jumlahBarang;i++){
                barang[i]=barang[i+1];
                biayaPerBarang[i]=biayaPerBarang[i+1];
            }
            jumlahBarang--;
            setTotalBiaya();
            setTotalKwh();
        }else {
            System.out.println("Id Barang tidak ada");
        }
    }
    
    public void kalkulatorEnergy(){
        String namaBarang;
        int dayaBarang,hariPerhitungan;
        float lamaPemakaian,totalKwh,totalTarifBarang;
        Scanner sc = new Scanner(System.in);
        System.out.println("Nama Barang:");
        namaBarang = sc.next();
        System.out.println("Daya Barang:");
        dayaBarang=sc.nextInt();
        System.out.println("Lama Pemakaian Barang:");
        lamaPemakaian= sc.nextFloat();
        System.out.println("Berapa Hari Perhitungan:");
        hariPerhitungan = sc.nextInt();

        totalKwh=((dayaBarang*lamaPemakaian)/1000)*hariPerhitungan;
        totalTarifBarang=totalKwh*this.tarifDayaPerKwh;

        System.out.println("Nama Barang           : "+namaBarang);
        System.out.println("Daya Barang           : "+dayaBarang);
        System.out.println("Lama Pemakaian Barang : "+lamaPemakaian);
        System.out.println("Nama Barang           : "+hariPerhitungan);
        System.out.println("Total kWh             : "+totalKwh);
        System.out.println("Tarif Barang          : "+totalTarifBarang);
    }
    
    //setter
    public void setTotalBiaya(){
        for (int i = 1; i < jumlahBarang; i++) {
            totalBiaya = totalBiaya + biayaPerBarang[i];
        }
    }
    
    public void setTotalKwh(){
        for (int i = 0; i < jumlahBarang; i++) {
            totalKwh = totalKwh + barang[i].getKwHPerBarang();
        }
        
        for (int i = 0; i < jumlahBarang; i++) {
            this.persentasePemakaian[i] = (this.barang[i].getKwHPerBarang() / totalKwh) * 100;
        }
    }
    
    //getter

    public int getDayaMeteran() {
        return dayaMeteran;
    }

    public float getTarifDayaPerKwh() {
        return tarifDayaPerKwh;
    }

    public float[] getBiayaPerBarang() {
        return biayaPerBarang;
    }

    public float getTotalKwh() {
        return totalKwh;
    }

    public float getTotalBiaya() {
        return totalBiaya;
    }

    public int getJumlahBarang() {
        return jumlahBarang;
    }

    public float[] getPersentasePemakaian() {
        return persentasePemakaian;
    }
    
    
    public void tampilanDataUser(){
        System.out.println("\n================================================================");
        System.out.println("========================= Informasi User ========================");
        System.out.println("ID\t\t\t: " + id);
        System.out.println("Nama\t\t\t: " + nama);
        System.out.println("Password\t\t: " + password);
        System.out.println("Daya dalam KiloWatt\t: " + getDayaMeteran());
        System.out.println("Tarif per KwH\t\t: " + getTarifDayaPerKwh());
        System.out.println("\n");
    }
    
    public void tampilanBarang(){
        System.out.println("\n\n================================================================");
        System.out.println("=================== Tampilan Data Barang Elektronik ==================");
        System.out.println("Jumlah Barang\t: " + getJumlahBarang());
        for (int i = 0; i < jumlahBarang; i++) {
            System.out.println("\nBarang Elektronik ke-" + (i + 1) );
            System.out.println("Nama Barang\t\t: " + this.barang[i].getNama());
            System.out.println("Daya Barang\t\t: " + this.barang[i].getDaya());
            System.out.println("Durasi Pemakaian\t: " + this.barang[i].getDurasi());
        }
    }
    
    public void analisisKeseluruhan() {
        System.out.println("\n\n================================================================");
        System.out.println("=================== Analisis Barang Elektronik Keseluruhan ==================");
        System.out.println("Jumlah Barang\t: " + getJumlahBarang());
        for (int i = 0; i < jumlahBarang; i++) {
            //System.out.println(barang[i]);
            System.out.println("\nBarang Elektronik ke-" + (i + 1) );
            System.out.println("Nama Barang\t\t: " + this.barang[i].getNama());
            System.out.println("Daya Barang\t\t: " + this.barang[i].getDaya());
            System.out.println("Durasi Pemakaian\t: " + this.barang[i].getDurasi());
            System.out.println("kwh Barang\t\t:" + this.barang[i].getKwHPerBarang());
            System.out.println("Biaya per Barang\t: " + biayaPerBarang[i]);
            System.out.println("Persentase Pemakaian\t: " + persentasePemakaian[i] + "% dari total KwH keseluruhan");
            //System.out.println(this.barang[i].getNamaBarang() + "\t\t: " + this.tarifBarang[i]);
        }
        System.out.println("-----------------------------------------------------------------");
        System.out.println("\nTotal KwH keseluruhan\t\t: " + getTotalKwh() + " KwH");
        System.out.println("Total Biaya keseluruhan\t\t: Rp " + getTotalBiaya());
        validationEfficiencyEnergy();
        System.out.println("\n-----------------------------------------------------------------");
    }
    
    public void validationEfficiencyEnergy(){
        float ikeBangunan=this.getTotalKwh()/36;
        
        if (ikeBangunan<=7.92) {
            System.out.println("Intensitas Konsumsi Energi\t: "+ikeBangunan+" kWh/m^2 per bulan");
            System.out.println("\tPemberitahuan\t\t: Energi sangat Efisiensi");
            System.out.println("\tPesan\t\t\t: Bagus sekali !! kamu sangat pandai dalam mengelola pemakaian barang elektronik :D ");
        } else if (ikeBangunan<=12.02) {
            System.out.println("Intensitas Konsumsi Energi\t: "+ikeBangunan+"kWh/m^2/month");
            System.out.println("\tPemberitahuan\t\t: Energi Efisiensi");
            System.out.println("\tPesan\t\t\t: Kamu sudah bagus dalam mengelola pemakaian barang elektronik :) Pertahankan !!");
        } else {
            System.out.println("Intensitas Konsumsi Energi\t: "+ikeBangunan+"kWh/m^2/month");
            System.out.println("\tPemberitahuan\t\t: Energi Boros");
            System.out.println("\tPesan\t\t\t: Waduh kamu boros sekali :( Coba untuk lebih mengelola pemakaian barang elektronik ya !");
        }
    }
}
