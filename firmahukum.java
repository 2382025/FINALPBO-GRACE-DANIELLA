
    import java.util.Scanner;

    public class firmahukum{
        public static Klien[] daftarKlien = new Klien[3]; // Awal kapasitas array 3, bisa diperbesar
        public static Scanner scanner = new Scanner(System.in);

        public static void main(String[] args) {
            showMainMenu();
        }

        static class Klien {
            String nama;
            String jenisKasus;
            String tanggalRegistrasi;
            String status;
            String pengacara;
            String jumlahTagihan;

            Klien(String nama, String jenisKasus, String tanggalRegistrasi, String status, String pengacara, String jumlahTagihan) {
                this.nama = nama;
                this.jenisKasus = jenisKasus;
                this.tanggalRegistrasi = tanggalRegistrasi;
                this.status = status;
                this.pengacara = pengacara;
                this.jumlahTagihan = jumlahTagihan;
            }
        }

        // Menampilkan daftar klien
        public static void showDaftarKlien() {
            System.out.println("DAFTAR KLIEN:");
            for (int i = 0; i < daftarKlien.length; i++) {
                Klien klien = daftarKlien[i];
                if (klien != null) {
                    System.out.println((i + 1) + ". | " + klien.nama + " | Kasus: " + klien.jenisKasus + " | Status: " + klien.status + " | Tagihan: Rp. " + klien.jumlahTagihan + "| Pengacara: " + klien.pengacara + "|");
                }
            }
        }

        // Menambah klien baru
        public static void addKlien(String nama, String jenisKasus, String tanggalRegistrasi, String status, String pengacara, String jumlahTagihan) {
            resizeArrayIfFull();
            for (int i = 0; i < daftarKlien.length; i++) {
                if (daftarKlien[i] == null) {
                    daftarKlien[i] = new Klien(nama, jenisKasus, tanggalRegistrasi, status, pengacara, jumlahTagihan);
                    break;
                }
            }
        }

        // Mengecek apakah array penuh
        public static boolean isArrayFull() {
            for (Klien klien : daftarKlien) {
                if (klien == null) {
                    return false;
                }
            }
            return true;
        }

        // Menambah kapasitas array
        public static void resizeArrayToTwoTimesBigger() {
            Klien[] temp = daftarKlien;
            daftarKlien = new Klien[daftarKlien.length * 2];
            System.arraycopy(temp, 0, daftarKlien, 0, temp.length);
        }

        // Mengecek dan memperbesar array jika penuh
        public static void resizeArrayIfFull() {
            if (isArrayFull()) {
                resizeArrayToTwoTimesBigger();
            }
        }

        // Menghapus klien
        public static boolean removeKlien(Integer number) {
            if (isSelectedKlienNotValid(number)) {
                return false;
            }
            for (int i = number - 1; i < daftarKlien.length - 1; i++) {
                daftarKlien[i] = daftarKlien[i + 1];
            }
            daftarKlien[daftarKlien.length - 1] = null;
            return true;
        }

        // Mengedit informasi klien
        public static boolean editKlien(Integer number, String namaBaru, String jenisKasusBaru, String tanggalBaru, String pengacaraBaru, String tagihanBaru) {
            if (isSelectedKlienNotValid(number)) {
                return false;
            }
            Klien klien = daftarKlien[number - 1];
            klien.nama = namaBaru;
            klien.jenisKasus = jenisKasusBaru;
            klien.tanggalRegistrasi = tanggalBaru;
            klien.pengacara = pengacaraBaru;
            klien.jumlahTagihan = tagihanBaru;
            return true;
        }

        // Mencari klien berdasarkan nama
        public static void searchKlien(String nama) {
            boolean found = false;
            System.out.println("HASIL PENCARIAN:");
            for (Klien klien : daftarKlien) {
                if (klien != null && klien.nama.equalsIgnoreCase(nama)) {
                    System.out.println("| Nama: " + klien.nama + " | Kasus: " + klien.jenisKasus + " | Status: " + klien.status + "|");
                    found = true;
                }
            }
            if (!found) {
                System.out.println("Klien dengan nama " + nama + " tidak ditemukan.");
            }
        }

        // Menampilkan statistik kasus
        public static void showStatistikKasus() {
            int aktif = 0;
            int selesai = 0;
            for (Klien klien : daftarKlien) {
                if (klien != null) {
                    if (klien.status.equalsIgnoreCase("Aktif")) {
                        aktif++;
                    } else if (klien.status.equalsIgnoreCase("Selesai")) {
                        selesai++;
                    }
                }
            }
            System.out.println("STATISTIK KASUS:");
            System.out.println("Kasus Aktif: " + aktif);
            System.out.println("Kasus Selesai: " + selesai);
        }

        // Menampilkan daftar tagihan
        public static void showDaftarTagihan() {
            System.out.println("DAFTAR TAGIHAN:");
            for (Klien klien : daftarKlien) {
                if (klien != null) {
                    System.out.println("Klien: " + klien.nama + ", Tagihan: Rp. " + klien.jumlahTagihan);
                }
            }
        }

        // Mengubah status kasus
        public static boolean ubahStatusKasus(Integer number, String statusBaru) {
            if (isSelectedKlienNotValid(number)) {
                return false;
            }
            Klien klien = daftarKlien[number - 1];
            klien.status = statusBaru;
            return true;
        }

        // Mengecek apakah klien yang dipilih valid
        public static boolean isSelectedKlienNotValid(Integer number) {
            if (number <= 0 || number > daftarKlien.length || daftarKlien[number - 1] == null) {
                return true;
            }
            return false;
        }

        // Input sederhana untuk memudahkan pengambilan data dari pengguna
        public static String input(String info) {
            System.out.print(info + " : ");
            return scanner.nextLine();
        }

        // Menampilkan menu utama
        public static void showMainMenu() {
            boolean isRunning = true;
            while (isRunning) {
                System.out.println("MENU:");
                System.out.println("1. Tampilkan Daftar Klien");
                System.out.println("2. Tambah Klien Baru");
                System.out.println("3. Edit Informasi Klien");
                System.out.println("4. Hapus Klien");
                System.out.println("5. Cari Klien");
                System.out.println("6. Tampilkan Statistik Kasus");
                System.out.println("7. Tampilkan Daftar Tagihan");
                System.out.println("8. Ubah Status Kasus");
                System.out.println("9. Keluar");
                String selectedMenu = input("Pilih");

                switch (selectedMenu) {
                    case "1":
                        showDaftarKlien();
                        break;
                    case "2":
                        showMenuAddKlien();
                        break;
                    case "3":
                        showMenuEditKlien();
                        break;
                    case "4":
                        showMenuRemoveKlien();
                        break;
                    case "5":
                        showMenuSearchKlien();
                        break;
                    case "6":
                        showStatistikKasus();
                        break;
                    case "7":
                        showDaftarTagihan();
                        break;
                    case "8":
                        showMenuUbahStatusKasus();
                        break;
                    case "9":
                        isRunning = false;
                        break;
                }
            }
        }

        // Menampilkan menu untuk menambah klien
        public static void showMenuAddKlien() {
            System.out.println("MENAMBAH KLIEN:");
            String nama = input("Nama Klien (x jika batal)");
            if (!nama.equals("x")) {
                String jenisKasus = input("Jenis Kasus");
                String tanggalRegistrasi = input("Tanggal Registrasi (dd-MM-yyyy)");
                String status = input("Status Kasus");
                String pengacara = input("Pengacara yang Ditugaskan");
                String jumlahTagihan = input("Jumlah Tagihan");
                addKlien(nama, jenisKasus, tanggalRegistrasi, status, pengacara, jumlahTagihan);
            }
        }

        // Menampilkan menu untuk menghapus klien
        public static void showMenuRemoveKlien() {
            System.out.println("MENGHAPUS KLIEN:");
            showDaftarKlien(); // Menampilkan daftar klien sebelum menghapus
            String number = input("Nomor klien yang dihapus (x jika batal)");
            if (!number.equals("x")) {
                boolean success = removeKlien(Integer.parseInt(number));
                if (success) {
                    System.out.println("Berhasil menghapus klien");
                } else {
                    System.out.println("Gagal menghapus klien: " + number);
                }
            }
        }

        // Menampilkan menu untuk mengedit klien
        public static void showMenuEditKlien() {
            System.out.println("EDIT KLIEN:");
            showDaftarKlien(); // Menampilkan daftar klien sebelum mengedit
            String selectedKlien = input("Nomor klien yang akan diedit (x jika batal)");
            System.out.println("UPDATE INFORMASI KLIEN");
            if (!selectedKlien.equals("x")) {
                String namaBaru = input("Nama");
                String jenisKasusBaru = input("Jenis Kasus");
                String tanggalBaru = input("Tanggal Registrasi");
                String pengacaraBaru = input("Pengacara");
                String tagihanBaru = input("Jumlah Tagihan");
                boolean isEditKlienSuccess = editKlien(Integer.parseInt(selectedKlien), namaBaru, jenisKasusBaru, tanggalBaru, pengacaraBaru, tagihanBaru);
                if (isEditKlienSuccess) {
                    System.out.println("Berhasil Mengedit Klien");
                } else {
                    System.out.println("Gagal Mengedit Klien");
                }
            }
        }

        // Menampilkan menu untuk mencari klien
        public static void showMenuSearchKlien() {
            System.out.println("MENCARI KLIEN:");
            String nama = input("Masukkan nama klien");
            searchKlien(nama);
        }

        // Menampilkan menu untuk mengubah status kasus
        public static void showMenuUbahStatusKasus() {
            System.out.println("MENGUBAH STATUS KASUS:");
            showDaftarKlien(); // Menampilkan daftar klien sebelum mengubah status
            String selectedKlien = input("Nomor klien yang akan diubah statusnya (x jika batal)");
            if (!selectedKlien.equals("x")) {
                String statusBaru = input("Status baru");
                boolean isSuccess = ubahStatusKasus(Integer.parseInt(selectedKlien), statusBaru);
                if (isSuccess) {
                    System.out.println("Berhasil Mengubah Status Kasus");
                } else {
                    System.out.println("Gagal Mengubah Status Kasus");
                }
            }
        }
    }

