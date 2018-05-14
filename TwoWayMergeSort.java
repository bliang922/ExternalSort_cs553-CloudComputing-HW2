import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class TwoWayMergeSort extends Thread{

	   String file1,file2,outputFile;
	   int linesInMem=100000;
	   TwoWayMergeSort(String name,String file1, String file2, String outputFile){
		   this.file1=new String(file1);
		   this.file2=new String(file2);
		   this.outputFile=new String(outputFile);

	   }
	   
		public void run(){
			BufferedReader input1Reader=null,input2Reader=null;
			BufferedWriter outputWriter=null;
			String str1=null,str2=null,strOut=null;
			try {
				input1Reader = new BufferedReader(new FileReader(new File(file1)));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				input2Reader = new BufferedReader(new FileReader(new File(file2)));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				outputWriter = new BufferedWriter(new FileWriter(new File(outputFile)));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			while(true){
				String[] list1 = new String[linesInMem];
				String[] list2 = new String[linesInMem];
				String[] list3 = new String[2*linesInMem];
				int len1=0,pos1=0;
				int len2=0,pos2=0;
				int pos3=0;

				for(int i=0;i<linesInMem;i++){
					try {
						str1=input1Reader.readLine();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(str1!=null){
						list1[i]=str1;
						len1++;
					} else break;
				}

				for(int i=0;i<linesInMem;i++){
					try {
						str2=input2Reader.readLine();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(str2!=null){
						list2[i]=str2;
						len2++;
					} else break;
				}	
								
				while(pos1<len1&&pos2<len2){
					if(list1[pos1].compareTo(list2[pos2])<0){
						list3[pos3]=list1[pos1];
						pos1++;
						pos3++;
					}else{
						list3[pos3]=list2[pos2];
						pos2++;
						pos3++;
					}
				}
				
				if(pos1<len1){
					while(pos1<len1){
						list3[pos3]=list1[pos1];
						pos3++;
						pos1++;
					}
				}else if(pos2<len2){
					while(pos2<len2){
						list3[pos3]=list2[pos2];
						pos3++;
						pos2++;
					}

				}
				for(int i=0;i<pos3;i++){
					try {
						outputWriter.write(list3[i]);
						outputWriter.write("\n");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				if(str1==null&&str2==null){
					try {
						outputWriter.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;	
				} 				

			}
		}
	   	
	}