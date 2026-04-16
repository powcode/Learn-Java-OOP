package main;

import java.util.ArrayList;
import java.util.Scanner;

import abstraction.Audio;
import abstraction.Livestream;
import abstraction.Video;
import abstraction.Youtube;


public class Main {
	static Scanner sc = new Scanner(System.in);
	static ArrayList<Youtube> files= new ArrayList<Youtube>();
	static ArrayList<Youtube> histories = new ArrayList<Youtube>(); 
	
//	Validasi judul
	static Boolean validTitle(String s) {
		if(s.length() < 3 || s.length() > 30)return  false;
		return true;
	}
	
//	Validasi deskripsi
	static Boolean validDesc(String s) {
		if(s.length() < 5 || s.length() > 50)return false;
		return true;
	}
	
//	Validasi durasi
	static Boolean validDur(int d) {
		if(d < 10 || d > 999)return false;
		return true;
	}
	
// 	==============================================
	
//	Add Media menu
	static void addMedia() {
		int choice;
		
		System.out.println("1. Add Audio File");
		System.out.println("2. Add Video File");
		System.out.println("3. Add Live Stream");
		System.out.println("4. Back");
		System.out.print("Choose: ");
		
		choice = sc.nextInt();
		sc.nextLine();
		
		switch (choice) {
		case 1: {
			insert("Audio");
			break;
		}
		case 2:{
			insert("Video");
			break;
		}
		case 3:{
			insert("LiveStream");
			break;
		}
		case 4:{
			
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + choice);
		}
	}
	
//	Insert media menu
	static void insert(String type) {
		String title, decsription;
		title = decsription = "";
		boolean valid = false;
		int duration = 0;
		
		while(!valid) {
			System.out.print("Title (3-30 characters): ");
			title = sc.nextLine();
			valid = validTitle(title);
		}
		valid = false;
	
		while(!valid) {			
			System.out.print("Description (5-50 characters): ");
			decsription = sc.nextLine();
			valid = validDesc(decsription);
		}
		valid = false;
		
		if(type.equalsIgnoreCase("Audio") || type.equalsIgnoreCase("Video")) {
			while(!valid) {				
				System.out.print("Duration (10-999):");
				duration = sc.nextInt();
				sc.nextLine();
				valid = validDur(duration);
			}
			
			if(type.equalsIgnoreCase("Audio")) {
				files.add(new Audio(title, decsription, duration));
			}else {
				files.add(new Video(title, decsription, duration));
			}
		}else {
			files.add(new Livestream(title, decsription));
		}
		
		System.out.println("Media added!");
	}
	
//  Play media
	static void playMedia() {
		int choice;
		view(false);
		System.out.print("Choose index: ");
		choice = sc.nextInt();
		sc.nextLine();
		
		Youtube temp = files.get(choice-1);
		play(temp);
	}
	
//	Find watchHistory
	static Youtube find(String title) {
		for (Youtube youtube : histories) {
			if(youtube.getTitle().equals(title)) {
				return youtube;
			}
		}
		return null;
	}
	
//	Play
	static void play(Youtube y) {
		boolean loop = true;
		Youtube curr;
		Youtube temp = find(y.getTitle());
		
		if(temp != null) {
			if(y.getType().equalsIgnoreCase("Audio")) {
				curr = (Audio) temp;
			}else if(y.getType().equalsIgnoreCase("Video")) {
				curr = (Video) temp;
			}else {
				curr = (Livestream) temp;
			}
		}else {
			if(y.getType().equalsIgnoreCase("Audio")) {
				curr = new Audio(y.getTitle(), y.getDescription(), y.getDuration());
			}else if(y.getType().equalsIgnoreCase("Video")) {
				curr = new Video(y.getTitle(), y.getDescription(), y.getDuration());
			}else {
				curr = new Livestream(y.getTitle(), y.getTitle());
			}
			curr.setCurrDur(10);
			histories.add(curr);
		}
		
		while(loop) {
			int choice;
			curr.play();
			
			choice = sc.nextInt();
			sc.nextLine();
			
			if(choice == 0) loop = false;
			else if(choice == 1) curr.skipForward();
			else curr.skipBackward();
		}
	}
	
//	Watch History
	static void watchHistory() {
		int choice;
		boolean loop = true;
		
		while(loop) {			
			view(true);
			System.out.print("Index to delete (0: back): ");
			choice = sc.nextInt();
			sc.nextLine();
			if(choice <= 0) break;
			if(choice > histories.size()) continue;
			histories.remove(choice-1);
		}
	}
	
//	Menu utama
	static void menu() {
		boolean loop = true;
		int choice;
		
		while(loop) {
			System.out.println("Youtube");
			for(int i = 0; i < 20; i++)System.out.print("-");
			
			System.out.println();
			
			System.out.println("1. Add Media");
			System.out.println("2. Play Media");
			System.out.println("3. Watch History");
			System.out.println("4. Exit");
			System.out.print("Choose : ");
			
			choice = sc.nextInt();
			sc.nextLine();
			
			switch (choice) {
			case 1: {
				addMedia();
				break;
			}
			case 2: {
				playMedia();
				break;
			}
			case 3: {
				watchHistory();
				break;
			}
			case 4: {
				loop = false;
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + choice);
			}
			
		}
		
	}
	
//	View
	static void view(boolean isPlayed) {
		int id = 1;
		if(!isPlayed) {
			for (Youtube youtube : files) {
				System.out.printf("[%d] ", id);
				youtube.showInfo();
				System.out.println();
				id++;
			}
		}else {
			for (Youtube youtube : histories) {
				System.out.printf("[%d] ", id);
				if(youtube.getType().equals("Livestream")) {
					youtube.showInfo();
				}else {
					if(youtube.getType().equals("Audio")) {
						((Audio) youtube).showInfo(youtube.getCurrDur());
					}else {
						((Video) youtube).showInfo(youtube.getCurrDur());
					}
				}
				System.out.println();
				id++;
			}
		}
	}
	
//	Main
	public static void main(String[] args) {
		Audio audio1 = new Audio("Podcast Ep.1", "Belajar Dasar Java", 3600);
		Audio audio2 = new Audio("Midnight Rain", "Taylor Swift - Midnights", 231);
		Video video1 = new Video("Tutorial OOP", "Konsep Dasar Abstraction", 1200);
		Livestream live1 = new Livestream("Daily Vlog Live", "Jalan-jalan di Bandung");
		Livestream live2 = new Livestream("Breaking News", "Kabar Terbaru Hari Ini" );
		files.add(audio1);
		files.add(audio2);
		files.add(video1);
		files.add(live1);
		files.add(live2);
		
		menu();
		
		
//		System.out.println(audio1.getCurrDur());
//		playMedia();
//		
//		view(false);
//		view(true);
//		
//		System.out.println(audio1.getCurrDur());
	}
}
