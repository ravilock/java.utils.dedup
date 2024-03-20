package dedup;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import queue.Queue;

public class Dedup {
	// TODO: Utilizar de overloading para implementar método recursivo ou método com
	// parâmetro de profundidade
	Map<String, ArrayList<File>> hashToFile;
	Queue<File> directoryQueue;

	public Dedup(String directoryPath) {
		hashToFile = new HashMap<String, ArrayList<File>>();
		directoryQueue = new Queue<File>();
		File directory = new File(directoryPath);
		directoryQueue.enqueue(directory);
	}

	public void run(boolean recursive) {
		while (!directoryQueue.isEmpty()) {
			File directory = directoryQueue.dequeue();
			File[] contents = directory.listFiles();
			System.out.println(contents);
			if (contents == null) {
				continue;
			}
			MessageDigest digest;
			try {
				digest = MessageDigest.getInstance("SHA-256");
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
				return;
			}
			for (File f : contents) {
				if (f.isDirectory()) {
					if (recursive)
						directoryQueue.enqueue(f);
					continue;
				}
				try {
					Scanner fileReader = new Scanner(f);
					String hash = this.hash(fileReader, digest);
					fileReader.close();
					if (hashToFile.containsKey(hash)) {
						hashToFile.get(hash).add(f);
					} else {
						ArrayList<File> fileList = new ArrayList<File>();
						fileList.add(f);
						hashToFile.put(hash, fileList);
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					continue;
				}
			}
			for (String key : hashToFile.keySet()) {
				System.out.printf("%s: %s\n", key, hashToFile.get(key).toString());
			}
		}
	}

	// TODO: Implement depth-limited search
	public void run(int depth) {
		while (!directoryQueue.isEmpty()) {
			File directory = directoryQueue.dequeue();
			File[] contents = directory.listFiles();
			System.out.println(contents);
			if (contents == null) {
				continue;
			}
			MessageDigest digest;
			try {
				digest = MessageDigest.getInstance("SHA-256");
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
				return;
			}
			for (File f : contents) {
				if (f.isDirectory()) {
					continue;
				}
				try {
					Scanner fileReader = new Scanner(f);
					String hash = this.hash(fileReader, digest);
					fileReader.close();
					if (hashToFile.containsKey(hash)) {
						hashToFile.get(hash).add(f);
					} else {
						ArrayList<File> fileList = new ArrayList<File>();
						fileList.add(f);
						hashToFile.put(hash, fileList);
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					continue;
				}
			}
			for (String key : hashToFile.keySet()) {
				System.out.printf("%s: %s\n", key, hashToFile.get(key).toString());
			}
		}
	}

	private String hash(Scanner scanner, MessageDigest digest) {
		digest.reset();
		while (scanner.hasNext()) {
			String data = scanner.next();
			digest.update(data.getBytes());
		}
		byte[] encondedHash = digest.digest();
		return this.bytesToHex(encondedHash);
	}

	private String bytesToHex(byte[] hash) {
		StringBuilder hexString = new StringBuilder(2 * hash.length);
		for (int i = 0; i < hash.length; i++) {
			String hex = Integer.toHexString(0xff & hash[i]);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString();
	}
}