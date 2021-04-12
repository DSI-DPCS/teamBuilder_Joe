import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


import java.util.Iterator;
import java.math.BigDecimal;

public class sys {

	public static void main(String[] args) {
		class toolBox{  		//python ���ߣ���
			public int toInt(String num) {//strתint
				try {
				    return Integer.parseInt(num);
				} catch (NumberFormatException e) {
				    e.printStackTrace();
				}return 0;	
			}
			public void execCommand(String command) {//����ִ������
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
				return rawString.substring(0,rawString.length()-1);//�ѻ��з��õ�
			}
		}toolBox joel = new toolBox();//ԭ���ĸ�������

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
			int age;  //�����䣬����Ϊ��λ
			//18/05/1999
			int DD;
			int MM;
			int YY;
			BigDecimal nameCount; //��һ����������ʾ����
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
			private void dateAnalyse() {//ת���ռ�����
				DD=joel.toInt(date.split("/")[0]);
				MM=joel.toInt(date.split("/")[1]);
				YY=joel.toInt(date.split("/")[2]);
			}
//			private void namecountAnalyse() {//�������ֵĴ�С����������ֵı���
//				//��Ӧ���� = ��ĸ���µ�ASCIIֵ x pow��26����ǰλ����
//				//ÿһλ��������ok��
//				//Ϊ�˱��ⳬ��������ʹ�ô�����BigDecimal
//				//��������������ֳ���һƪEE��Ҳ����������
//				for(char c : fullName.toCharArray()) {
//				    // ��ȻҪʹ�����ŵĵ�������~
//					
//				}
//				
//			}
 			public aManber(String inputString){
				infoString=inputString;  //����ԭ�ַ���
				inforAnalyse();          //������������������
				dateAnalyse();           //��������
				ageAnalyse();            //��������
				fullName = nameString + surnameString;//�ϲ�����
			}
			
		}
		
		//////////////////////START////////////////////////
		joel.print("Welcome to the Team Builder System\r\n"
				+ "==================================\r\n"
				+ "");
		int menberNum = joel.toInt(joel.pyInput("How many menbers?"));
		ArrayList <aManber> mamberArray= new ArrayList();
		for (int  t = 0;  t < menberNum;  t++) {			//ѭ�����ΰ�����ת��ȥ
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
		if (commandString.equals("2")) {//��������
			joel.print("findingOld===\n");
			aManber theOldmanber = mamberArray.get(0); //���ó�����һ��
			for (Iterator<aManber> MB = mamberArray.iterator(); MB.hasNext();) {
				aManber thisMamber = MB.next();
				if (thisMamber.showAge()>theOldmanber.showAge()) {
					theOldmanber = thisMamber;
				}
			}theOldmanber.showInfo();
		}
		if (commandString.equals("3")) {//����������
			joel.print("findingYoung===\n");
			aManber theYoungmanber = mamberArray.get(0); //���ó�����һ��
			for (Iterator<aManber> MB = mamberArray.iterator(); MB.hasNext();) {
				aManber thisMamber = MB.next();
				if (thisMamber.showAge() < theYoungmanber.showAge()) {
					theYoungmanber = thisMamber;
				}	
			}theYoungmanber.showInfo();
		}
		if (commandString.substring(0,1).equals("4")) {
			if (commandString.substring(commandString.length()-1,commandString.length()).equals("u")) { //����
				joel.print("Ascending order!!!");
				int swap=0;
				int arrow=0;
				while (swap != 0 || arrow!=mamberArray.size()-1) {
					if(arrow == mamberArray.size()-1) {
						//��ĩβ��ʱ��˵���Ѿ�����һ����
						//��arrow�ָ���arraylist��ͷ����ʼ��һ��
						//˳�����swap
						//���swap
						arrow=0;
						swap=0;
					}
//					joel.print(mamberArray.get(arrow).fullname());
//					joel.print(mamberArray.get(arrow+1).fullname());
//					joel.print(mamberArray.get(arrow).fullname().compareTo(mamberArray.get(arrow+1).fullname()));
					if(mamberArray.get(arrow).fullname().compareTo(mamberArray.get(arrow+1).fullname()) > 0 ) {
						joel.print("swap!!!!!!!!");
						aManber first = mamberArray.get(arrow);
						//�ѵ�һ���������
						mamberArray.set(arrow , mamberArray.get(arrow+1));
						mamberArray.set(arrow+1, first);
						swap++; //���ӽ������
					}
					arrow++;
				}
				///��ӡһ���ź����
				for (Iterator<aManber> MB = mamberArray.iterator(); MB.hasNext();) {
					MB.next().showInfo();
				}
			}
			if (commandString.substring(commandString.length()-1,commandString.length()).equals("d")) { //����
				joel.print("Ascending order!!!");
				int swap=0;
				int arrow=0;
				while (swap != 0 || arrow!=mamberArray.size()-1) {
					if(arrow == mamberArray.size()-1) {
						//��ĩβ��ʱ��˵���Ѿ�����һ����
						//��arrow�ָ���arraylist��ͷ����ʼ��һ��
						//˳�����swap
						//���swap
						arrow=0;
						swap=0;
					}
					joel.print(mamberArray.get(arrow).fullname());
					joel.print(mamberArray.get(arrow+1).fullname());
					if(mamberArray.get(arrow).fullname().compareTo(mamberArray.get(arrow+1).fullname()) < 0 ) {
						aManber first = mamberArray.get(arrow);
						//�ѵ�һ���������
						mamberArray.set(arrow , mamberArray.get(arrow+1));
						mamberArray.set(arrow+1, first);
						swap++; //���ӽ������
					}
					arrow++;
				}
				///��ӡһ���ź����
				for (Iterator<aManber> MB = mamberArray.iterator(); MB.hasNext();) {
					MB.next().showInfo();
				}
			}
			
		}
		if (commandString.substring(0,1).equals("5")) {
			if (commandString.substring(commandString.length()-1,commandString.length()).equals("u")) { //����
				joel.print("Ascending order!!!");
				int swap=0;
				int arrow=0;
				while (swap != 0 || arrow!=mamberArray.size()-1) {
					if(arrow == mamberArray.size()-1) {
						//��ĩβ��ʱ��˵���Ѿ�����һ����
						//��arrow�ָ���arraylist��ͷ����ʼ��һ��
						//˳�����swap
						//���swap
						arrow=0;
						swap=0;
					}
					joel.print(mamberArray.get(arrow).showAge());
					joel.print(mamberArray.get(arrow+1).showAge());
					if(mamberArray.get(arrow).showAge() > mamberArray.get(arrow+1).showAge()) {
						aManber first = mamberArray.get(arrow);
						//�ѵ�һ���������
						mamberArray.set(arrow , mamberArray.get(arrow+1));
						mamberArray.set(arrow+1, first);
						swap++; //���ӽ������
					}
					arrow++;
				}
				///��ӡһ���ź����
				for (Iterator<aManber> MB = mamberArray.iterator(); MB.hasNext();) {
					MB.next().showInfo();
				}
			}
			if (commandString.substring(commandString.length()-1,commandString.length()).equals("d")) { //����
				joel.print("Descending order!!!");
				int swap=0;
				int arrow=0;
				while (swap != 0 || arrow!=mamberArray.size()-1) {
					if(arrow == mamberArray.size()-1) {
						//��ĩβ��ʱ��˵���Ѿ�����һ����
						//��arrow�ָ���arraylist��ͷ����ʼ��һ��
						//˳�����swap
						//���swap
						arrow=0;
						swap=0;
					}
					joel.print(mamberArray.get(arrow).showAge());
					joel.print(mamberArray.get(arrow+1).showAge());
					if(mamberArray.get(arrow).showAge() < mamberArray.get(arrow+1).showAge()) {
						aManber first = mamberArray.get(arrow);
						//�ѵ�һ���������
						mamberArray.set(arrow , mamberArray.get(arrow+1));
						mamberArray.set(arrow+1, first);
						swap++; //���ӽ������
					}
					arrow++;
				}
				///��ӡһ���ź����
				for (Iterator<aManber> MB = mamberArray.iterator(); MB.hasNext();) {
					MB.next().showInfo();
				}
			}
			
		}
		/////////////////////////////////////////////////
	}

}
