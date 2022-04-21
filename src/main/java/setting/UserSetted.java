package setting;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


import setting.DefaultSetting;
import setting.UserSetting;

public class UserSetted {
	
	private static String path = UserSetted.class.getResource("").getPath() + "userSetted.txt";	
	private static Map<String, Integer> setting = new  ConcurrentHashMap<String, Integer>();//파일-설정값
	private static Map<String, String> realSetting = new  ConcurrentHashMap<String, String>();//파일-설정값

	private static ArrayList<String> array = new ArrayList<String>();//파일 내용

	
	private File file = new File(path);
	
	public static void read() {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(path));
			
			String line;
			int nth = 0;
			
			while((line = br.readLine()) != null) {
				if(nth == 0) {realSetting.put("LEFT", line);}
				if(nth == 1) {realSetting.put("RIGHT", line);}
				if(nth == 2) {realSetting.put("DROP", line);}
				if(nth == 3) {realSetting.put("DOWN", line);}
				if(nth == 4) {realSetting.put("ROTATE", line);}
				if(nth == 5) {realSetting.put("colorBlindMode", line);}
				if(nth == 6) {realSetting.put("difficultyLevel", line);}
				if(nth == 7) {realSetting.put("size", line);}
				System.out.println(line);
				nth++;
			}
		} catch(FileNotFoundException fnfe) {
			System.out.println("파일을 찾을 수 없습니다.");
		} catch (IOException ice) {
			System.out.println("파일 입출력 오류");
		} finally {
			System.out.println("프로그램 종료");
			try {
				br.close();
			}catch (IOException ice) {
				ice.printStackTrace();
			}
		}
		
	}
	
	public static void writeDefault() {
		BufferedWriter br = null;
		DefaultSetting defaultSetting = new DefaultSetting();
		
		try {
			br = new BufferedWriter (new FileWriter (path));
			
			String line;
			
			br.write(Integer.toString(defaultSetting.getDefaultKey("LEFT")));
			br.newLine();
		    br.write(Integer.toString(defaultSetting.getDefaultKey("RIGHT")));
		    br.newLine();
		    br.write(Integer.toString(defaultSetting.getDefaultKey("DROP")));
		    br.newLine();
		    br.write(Integer.toString(defaultSetting.getDefaultKey("DOWN")));
		    br.newLine();
		    br.write(Integer.toString(defaultSetting.getDefaultKey("ROTATE")));
		    br.newLine();
		    br.write(String.valueOf(defaultSetting.getDefaultColorBlindMode()));
		    br.newLine();
		    br.write(defaultSetting.getDefaultLevel());
		    br.newLine();
		    br.write(defaultSetting.getDefaultSize());
			
			
		} catch(FileNotFoundException fnfe) {
			System.out.println("파일을 찾을 수 없습니다.");
		} catch (IOException ice) {
			System.out.println("파일 입출력 오류");
		} finally {
			System.out.println("프로그램 종료");
			try {
				br.close();
			}catch (IOException ice) {
				ice.printStackTrace();
			}
		}
		read();

	}
		
	public static void writeUserSetting() {
		BufferedWriter br = null;
		UserSetting userSetting = new UserSetting();
		
		try {
			br = new BufferedWriter (new FileWriter (path));
			
			String line;
			
			br.write(Integer.toString(userSetting.getUserKey("LEFT")));
			br.newLine();
		    br.write(Integer.toString(userSetting.getUserKey("RIGHT")));
		    br.newLine();
		    br.write(Integer.toString(userSetting.getUserKey("DROP")));
		    br.newLine();
		    br.write(Integer.toString(userSetting.getUserKey("DOWN")));
		    br.newLine();
		    br.write(Integer.toString(userSetting.getUserKey("ROTATE")));
		    br.newLine();
		    br.write(String.valueOf(userSetting.getColorBlindMode()));
		    br.newLine();
		    br.write(userSetting.getDifficultyLevel());
		    br.newLine();
		    br.write(userSetting.getSize());
			
			
		} catch(FileNotFoundException fnfe) {
			System.out.println("파일을 찾을 수 없습니다.");
		} catch (IOException ice) {
			System.out.println("파일 입출력 오류");
		} finally {
			System.out.println("프로그램 종료");
			try {
				br.close();
			}catch (IOException ice) {
				ice.printStackTrace();
			}
		}
		read();
	}
	
	public static String getSetted(String word) {
		return realSetting.get(word);
	}
	
	
	public UserSetted() {
		read();
	}
	
	


}
