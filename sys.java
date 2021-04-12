import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


import java.util.Iterator;
import java.math.BigDecimal;

public class sys {

	public static void main(String[] args) {
		class toolBox{  		//python 赛高！！
			public int toInt(String num) {//str转int
				try {
				    return Integer.parseInt(num);
				} catch (NumberFormatException e) {
				    e.printStackTrace();
				}return 0;	
			}
			public void execCommand(String command) {//用以执行命令
				Runtime rt = Runtime.getRuntime();
				Process p = null;
				try {p = rt.exec(command);
				} catch (IOException e1) {}
			}
			public toolBox() {System.out.println("T0olbox loaded!");}
			public void print(String L) {System.out.println(L);}
			public void print(String L, String tail) {System.out.print(L+tail);}
			public void print(int L) {System.out.println(L);}
			public void print(int L, String tail) {System.out.print(L+tail);}
			public String pyInput(String question) {
				System.out.print(question);
				Scanner sc=new Scanner(System.in).useDelimiter("\n");
				String rawString=sc.next();
				return rawString.substring(0,rawString.length()-1);//把换行符裁掉
			}
		}toolBox joel = new toolBox();//原地拍个工具箱

//		BigDecimal a=new BigDecimal(new String("123453243455535634535252345234677576252241234123523453664563634"));
//		a=a.multiply(new BigDecimal(26).pow(2333));
//		joel.print( a+"" );
		class aManber{
			String nameString;
			String surnameString;
			String fullName;
			String sexString;
			String date;
			String infoString;
			int age;  //总年龄，以天为单位
			//18/05/1999
			int DD;
			int MM;
			int YY;
			BigDecimal nameCount; //用一组数字来表示名字
			private String fullname() {
				return fullName;
			}
			private void showInfo() {
				joel.print(infoString);
			}
			private int showAge() {
				return age;
			}
			private void ageAnalyse() {
				age=YY*365+MM*30+DD;
			}
			private void inforAnalyse() {
				nameString=infoString.split(":")[0];
				surnameString=infoString.split(":")[1];
				sexString=infoString.split(":")[2];
				date=infoString.split(":")[3];
				}
			private void dateAnalyse() {//转化收集日期
				DD=joel.toInt(date.split("/")[0]);
				MM=joel.toInt(date.split("/")[1]);
				YY=joel.toInt(date.split("/")[2]);
			}
//			private void namecountAnalyse() {//计算名字的大小。。。好奇怪的表述
//				//对应数字 = 字母本事的ASCII值 x pow（26，当前位数）
//				//每一位加起来就ok！
//				//为了避免超出，这里使用大数库BigDecimal
//				//这样就算你的名字长达一篇EE，也能正常排序
//				for(char c : fullName.toCharArray()) {
//				    // 当然要使用优雅的迭代器啦~
//					
//				}
//				
//			}
 			public aManber(String inputString){
				infoString=inputString;  //保存原字符串
				inforAnalyse();          //解析并更新其他数据
				dateAnalyse();           //解析日期
				ageAnalyse();            //解析年龄
				fullName = nameString + surnameString;//合并姓名
			}
			
		}
		
		//////////////////////START////////////////////////
		joel.print("Welcome to the Team Builder System\r\n"
				+ "==================================\r\n"
				+ "");
		int menberNum = joel.toInt(joel.pyInput("How many menbers?"));
		ArrayList <aManber> mamberArray= new ArrayList();
		for (int  t = 0;  t < menberNum;  t++) {			//循环几次把数据转进去
			try {
			aManber M=new aManber(joel.pyInput("input info, Name:Surname:Sex:DD/MM/YYYY"));
			mamberArray.add(M);
			}catch (Exception e) {
				joel.print("Errer input, type again!");
				t--;
			}			
		}
//		George:Georgiou:m:18/05/1999
//		Alice:Georgiou:f:03/03/1999
//		Stephen:Foster:m:03/01/1999

		joel.print("Team construct finished!\n");
		joel.print("Team Builder Menu:\r\n"
				+ "==================================\r\n"
				+ "1.	List the team members\r\n"
				+ "2.	Find the oldest member\r\n"
				+ "3.	Find the youngest member\r\n"
				+ "4.	Sort by name\r\n"
				+ "5.	Sort by birthday\r\n"
				);

//		aManber m=new aManber("JOE:Foster:m:03/01/1999");
//		mamberArray.set(0, m);
//		mamberArray.get(menberNum);
		String commandString = joel.pyInput("");
		if (commandString.equals("1")) {
			for (Iterator<aManber> MB = mamberArray.iterator(); MB.hasNext();) {
				MB.next().showInfo();
			}
		}
		if (commandString.equals("2")) {//查找最老
			joel.print("findingOld===\n");
			aManber theOldmanber = mamberArray.get(0); //先拿出来第一个
			for (Iterator<aManber> MB = mamberArray.iterator(); MB.hasNext();) {
				aManber thisMamber = MB.next();
				if (thisMamber.showAge()>theOldmanber.showAge()) {
					theOldmanber = thisMamber;
				}
			}theOldmanber.showInfo();
		}
		if (commandString.equals("3")) {//查找最年轻
			joel.print("findingYoung===\n");
			aManber theYoungmanber = mamberArray.get(0); //先拿出来第一个
			for (Iterator<aManber> MB = mamberArray.iterator(); MB.hasNext();) {
				aManber thisMamber = MB.next();
				if (thisMamber.showAge() < theYoungmanber.showAge()) {
					theYoungmanber = thisMamber;
				}	
			}theYoungmanber.showInfo();
		}
		if (commandString.substring(0,1).equals("4")) {
			if (commandString.substring(commandString.length()-1,commandString.length()).equals("u")) { //升序
				joel.print("Ascending order!!!");
				int swap=0;
				int arrow=0;
				while (swap != 0 || arrow!=mamberArray.size()-1) {
					if(arrow == mamberArray.size()-1) {
						//到末尾的时候说明已经跑完一轮了
						//把arrow恢复到arraylist开头，开始下一轮
						//顺便清空swap
						//如果swap
						arrow=0;
						swap=0;
					}
//					joel.print(mamberArray.get(arrow).fullname());
//					joel.print(mamberArray.get(arrow+1).fullname());
//					joel.print(mamberArray.get(arrow).fullname().compareTo(mamberArray.get(arrow+1).fullname()));
					if(mamberArray.get(arrow).fullname().compareTo(mamberArray.get(arrow+1).fullname()) > 0 ) {
						joel.print("swap!!!!!!!!");
						aManber first = mamberArray.get(arrow);
						//把第一个保存出来
						mamberArray.set(arrow , mamberArray.get(arrow+1));
						mamberArray.set(arrow+1, first);
						swap++; //增加交换标记
					}
					arrow++;
				}
				///打印一次排好序的
				for (Iterator<aManber> MB = mamberArray.iterator(); MB.hasNext();) {
					MB.next().showInfo();
				}
			}
			if (commandString.substring(commandString.length()-1,commandString.length()).equals("d")) { //降序
				joel.print("Ascending order!!!");
				int swap=0;
				int arrow=0;
				while (swap != 0 || arrow!=mamberArray.size()-1) {
					if(arrow == mamberArray.size()-1) {
						//到末尾的时候说明已经跑完一轮了
						//把arrow恢复到arraylist开头，开始下一轮
						//顺便清空swap
						//如果swap
						arrow=0;
						swap=0;
					}
					joel.print(mamberArray.get(arrow).fullname());
					joel.print(mamberArray.get(arrow+1).fullname());
					if(mamberArray.get(arrow).fullname().compareTo(mamberArray.get(arrow+1).fullname()) < 0 ) {
						aManber first = mamberArray.get(arrow);
						//把第一个保存出来
						mamberArray.set(arrow , mamberArray.get(arrow+1));
						mamberArray.set(arrow+1, first);
						swap++; //增加交换标记
					}
					arrow++;
				}
				///打印一次排好序的
				for (Iterator<aManber> MB = mamberArray.iterator(); MB.hasNext();) {
					MB.next().showInfo();
				}
			}
			
		}
		if (commandString.substring(0,1).equals("5")) {
			if (commandString.substring(commandString.length()-1,commandString.length()).equals("u")) { //升序
				joel.print("Ascending order!!!");
				int swap=0;
				int arrow=0;
				while (swap != 0 || arrow!=mamberArray.size()-1) {
					if(arrow == mamberArray.size()-1) {
						//到末尾的时候说明已经跑完一轮了
						//把arrow恢复到arraylist开头，开始下一轮
						//顺便清空swap
						//如果swap
						arrow=0;
						swap=0;
					}
					joel.print(mamberArray.get(arrow).showAge());
					joel.print(mamberArray.get(arrow+1).showAge());
					if(mamberArray.get(arrow).showAge() > mamberArray.get(arrow+1).showAge()) {
						aManber first = mamberArray.get(arrow);
						//把第一个保存出来
						mamberArray.set(arrow , mamberArray.get(arrow+1));
						mamberArray.set(arrow+1, first);
						swap++; //增加交换标记
					}
					arrow++;
				}
				///打印一次排好序的
				for (Iterator<aManber> MB = mamberArray.iterator(); MB.hasNext();) {
					MB.next().showInfo();
				}
			}
			if (commandString.substring(commandString.length()-1,commandString.length()).equals("d")) { //降序
				joel.print("Descending order!!!");
				int swap=0;
				int arrow=0;
				while (swap != 0 || arrow!=mamberArray.size()-1) {
					if(arrow == mamberArray.size()-1) {
						//到末尾的时候说明已经跑完一轮了
						//把arrow恢复到arraylist开头，开始下一轮
						//顺便清空swap
						//如果swap
						arrow=0;
						swap=0;
					}
					joel.print(mamberArray.get(arrow).showAge());
					joel.print(mamberArray.get(arrow+1).showAge());
					if(mamberArray.get(arrow).showAge() < mamberArray.get(arrow+1).showAge()) {
						aManber first = mamberArray.get(arrow);
						//把第一个保存出来
						mamberArray.set(arrow , mamberArray.get(arrow+1));
						mamberArray.set(arrow+1, first);
						swap++; //增加交换标记
					}
					arrow++;
				}
				///打印一次排好序的
				for (Iterator<aManber> MB = mamberArray.iterator(); MB.hasNext();) {
					MB.next().showInfo();
				}
			}
			
		}
		/////////////////////////////////////////////////
	}

}
