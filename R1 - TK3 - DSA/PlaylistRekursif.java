/*
 * Kelompok 5 - COSC6025 Data Structures and Algorithm Analysis
 * Anggota Kelompok:
 * 1. Naura Zulwinda Putri (2902783602)
 * 2. Indah Tri Praesti (2902795395)
 * 3. Ahmad Andi Nugroho (2902792355)
 * 4. Lynatu Khoirinnisa (2902785980)
 */

import java.util.Locale;

public class PlaylistRekursif {

    private Lagu[] playlist;

    public PlaylistRekursif(Lagu[] playlist) {
        this.playlist = playlist;
    }

    /*
     * Tujuan fungsi   : Menghitung total durasi seluruh lagu secara rekursif.
     * Base case       : n <= 0, mengembalikan 0.0
     * Recursive case  : list[n-1].getDurasi() + totalDurasi(list, n - 1)
     * Kompleksitas    : O(n)
     */
    public static double totalDurasi(Lagu[] list, int n) {
        if (list == null || n <= 0) {
            return 0.0;
        }
        return list[n - 1].getDurasi() + totalDurasi(list, n - 1);
    }

    /*
     * Tujuan fungsi   : Menampilkan judul lagu secara terbalik dari akhir ke awal.
     * Base case       : index < 0, fungsi berhenti (return)
     * Recursive case  : Cetak list[index].getJudul(), lalu panggil tampilkanMundur(list, index - 1)
     * Kompleksitas    : O(n)
     */
    public static void tampilkanMundur(Lagu[] list, int index) {
        if (list == null || index < 0 || index >= list.length) {
            return;
        }
        System.out.println((index + 1) + ". " + list[index].getJudul());
        tampilkanMundur(list, index - 1);
    }

    /*
     * Tujuan fungsi   : Mencari durasi lagu terpanjang secara rekursif.
     * Base case       : index == 0, mengembalikan list[0].getDurasi()
     * Recursive case  : Math.max(list[index].getDurasi(), cariDurasiTerpanjang(list, index - 1))
     * Kompleksitas    : O(n)
     */
    public static double cariDurasiTerpanjang(Lagu[] list, int index) {
        if (list == null || list.length == 0 || index < 0) {
            return 0.0;
        }
        if (index == 0) {
            return list[0].getDurasi();
        }
        return Math.max(list[index].getDurasi(), cariDurasiTerpanjang(list, index - 1));
    }

    /*
     * Helper fungsi   : Mencari objek Lagu dengan durasi terpanjang secara rekursif.
     * Base case       : index == 0, mengembalikan list[0]
     * Recursive case  : Bandingkan list[index] dengan hasil rekursi index - 1
     * Kompleksitas    : O(n)
     */
    public static Lagu cariLaguTerpanjang(Lagu[] list, int index) {
        if (list == null || list.length == 0 || index < 0) {
            return null;
        }
        if (index == 0) {
            return list[0];
        }
        Lagu maxSisa = cariLaguTerpanjang(list, index - 1);
        return (list[index].getDurasi() > maxSisa.getDurasi()) ? list[index] : maxSisa;
    }

    public static void main(String[] args) {
        // Data Playlist Musik (10 lagu)
        Lagu[] playlist = new Lagu[] {
            new Lagu("Perfect", "Ed Sheeran", 4.23),
            new Lagu("Shivers", "Ed Sheeran", 3.50),
            new Lagu("Fix You", "Coldplay", 4.23),
            new Lagu("Speak Now", "Taylor Swift", 4.06),
            new Lagu("Enchanted", "Taylor Swift", 5.57),
            new Lagu("Night Changes", "One Direction", 4.01),
            new Lagu("Story of My Life", "One Direction", 4.05),
            new Lagu("Perfect", "One Direction", 3.50),
            new Lagu("One Thing", "One Direction", 3.17),
            new Lagu("Sorry", "Justin Bieber", 3.20)
        };

        int n = playlist.length;

        System.out.println("=== ANALISIS REKURSIF PLAYLIST ===");
        System.out.println();

        // 1. Total Durasi
        long startTotal = System.nanoTime();
        double total = totalDurasi(playlist, n);
        long endTotal = System.nanoTime();
        long timeTotalMs = Math.max(1, (endTotal - startTotal) / 1_000_000);

        System.out.println("Jumlah lagu : " + n);
        System.out.printf(Locale.US, "Total durasi : %.2f menit\n", total);

        // 2. Lagu Terpanjang
        long startMax = System.nanoTime();
        Lagu terpanjang = cariLaguTerpanjang(playlist, n - 1);
        long endMax = System.nanoTime();
        long timeMaxMs = Math.max(1, (endMax - startMax) / 1_000_000);

        if (terpanjang != null) {
            System.out.printf(Locale.US, "Lagu terpanjang : \"%s\" - %s (%.2f menit)\n",
                    terpanjang.getJudul(), terpanjang.getArtis(), terpanjang.getDurasi());
        }

        System.out.println();
        System.out.println("Daftar lagu (ditampilkan terbalik):");

        // 3. Tampilkan Mundur
        long startMundur = System.nanoTime();
        tampilkanMundur(playlist, n - 1);
        long endMundur = System.nanoTime();
        long timeMundurMs = Math.max(1, (endMundur - startMundur) / 1_000_000);

        System.out.println();

        // Pengukuran waktu eksekusi (Execution Time)
        System.out.println("Execution Time (totalDurasi): " + timeTotalMs + " ms");
        System.out.println("Execution Time (tampilkanMundur): " + timeMundurMs + " ms");
        System.out.println("Execution Time (cariDurasiTerpanjang): " + timeMaxMs + " ms");
    }
}
