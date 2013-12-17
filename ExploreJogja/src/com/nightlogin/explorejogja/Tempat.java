package com.nightlogin.explorejogja;

public class Tempat {
	
	public static String getURL()
	{
		String url = "http://192.168.137.1/explorejogja/testingbeta.php";
		 //private static String url_all_products = "http://192.168.137.130/pariwisatajogja/testingbeta.php";
		 //private static String url_all_products = "http://10.42.200.193/pariwisatajogja/testingbeta.php"; 
		return url;
	}
	public static String ambilListKategori(int referensi)
	{
		switch (referensi) {
		case 1:
			return "RESTAURANT";			
		case 2:
			return "CAFE";
		case 3:
			return "BAKERY";
		case 4:
			return "BAR";
		case 5:
			return "HOTEL";
		case 6:
			return "VILLA";
		case 7:
			return "HISTORICAL PLACE";
		case 8:
			return "TEMPLE";
		case 9:
			return "CULTURAL PERFORMANCE";
		case 10:
			return "SPORTS AND ADVENTURE";
		case 11:
			return "NATURE";
		case 12:
			return "MUSEUM";
		case 13:
			return "FAMILY ATTRACTION";
		default:
			return "";
		}
	}
	
	
	public static String[] ambilDetailEvent(int referensi)
	{
		switch (referensi) {
		case 1:
			String[] daftar1 = {
					"3-12 Desember 2013",
					"Pameran Seni Grafis Etiket Batik Tenun 1930-1990",	
					"19.30",
					"Bentara Budaya Yogyakarta	Pameran seni grafis, mempersembahkan karya batik dari jogja, cirebon, pekalongan dan daerah sekitarnya, dilaksanakan di Bentara Budaya Yogyakarta Jl.Suroto 2, Kotabaru",
					"batiktenun"
			};
			return daftar1;
		case 2:
			String[] daftar2 = {
					"6-12 Desember 2013",	
					"Perempuan Lahung Bak Bidadari",
					"10.00",	
					"Kedai Belakang Jl. Taman Siswa	Merupakan persembahan karya seni yang berada di Kedai Belakang Jl. Taman Siswa, mempertunjukan seni puisi, menggambar dan foto, dengan penampilan band akustik, tari tradisional serta musikalisasi puisi",
					"perempuanlahungbakbidadari"

			};
			return daftar2;
		case 3:
			String[] daftar3 ={
					"07 Desember 2013",
					"ARTCTION",
					"18.00 - 22.00",	
					"Gedung Pusat Kebudayaan Koesnadi Hardjasoemantri	Pertunjukan tari dari Unit Tari Bali UGM, mempertunjukan Balinese Dance Performance, tiket box dapat diperoleh di gelanggang mahasiswa UGM",
					"artction"
			};
			return daftar3;
		case 4:
			String[] daftar4 = {
					"07 Desember 2013",	
					"Jazztraffic Festical",	
					"19.30",	
					"Grand Pacific Hall	Lorem ipsum dolor sit amet, consectetur adididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. erunt mollit anim id est laborum.",
					"jazztrafficfestival"
			};
			return daftar4;
		case 5:
			String[] daftar5 = {
					"07 Desember 2013",	
					"Young Entrepreneurs Show National Seminar",
					"8.30",
					"Auditorium Magister Manajemen UGM	Seminar nasional mengenai entrepreneur, dengan nara sumber dan rangkaian acara menarik serta memberi anda wawasan mengenai dunia entrepreneur serta rahasia kesuksesan mereka.",
					"youngentrepreneursshow"
					

			};
			return daftar5;
		case 6:
			String[] daftar6 = {
					"7-8 Desember 2013",	
					"Diplomatic Course & Table Manner",
					"07.00",	
					"Gedung Lengkung & Hotel Arjuna	Seminar yang mengangkat tema mengenai Forging the Indonesia's Future Diplomacy Actor in Facing ASEAN Community 2015",
					"diplomaticcoursetablemanner"
			};
			return daftar6;
		case 7:
			String[] daftar7 = {
					"7-8 Desember 2013",
					"National Roadshow EXPO 2013 Toys & Games",
					"10.00",
					"Jogja Expo Center	Pertunjukan mainan terbesar yang diadakan di 3 kota, yaitu malang, semarang dan yogyakarta.",
					"toysgame"
			};
			return daftar7;
		case 8:
			String[] daftar8 = {
					"14 Desember 2013",
					"Stand Up Night #2",	
					"20.00 - 23.00",	
					"Grha Sabha Pramana UGM	menampilkan komika-komika yang siap menghibur anda dengan berbagai bahan candaan. Tiket dapat diperoleh di Waralaba Circle K",
					"standupbight2"
			};
			return daftar8;
		default:
			return null;
		}
	}
}