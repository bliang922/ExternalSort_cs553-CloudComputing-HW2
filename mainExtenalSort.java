import java.util.*;
import java.io.*;

public class mainExtenalSort {
	public static String inputFilePath = null;
	public static String outputFilePath = null;
	public static int threadNum=1;
	public static int splitSize;
	public static int chunkNum = 32;

	public static void main(String[] args) throws IOException, InterruptedException
	{
		inputFilePath=args[0];
		outputFilePath=args[1];
	//	threadNum = Integer.parseInt(args[2]);
	//	inputFilePath="/Users/home/Desktop/data-2GB.in";
	//    outputFilePath="/Users/home/Desktop/temp";

		File file=new File(inputFilePath);
		long fileSize=file.length();
		if(fileSize>2000000000) {
			chunkNum=32;
		}
		splitSize=(int) (fileSize/chunkNum/99+1);
		splitFile(file);		
		System.out.println("inputfile has been splitted into "+ chunkNum +" chunks");	
		
		TwoWayMergeSort[] threadArray = new TwoWayMergeSort[chunkNum];
			int pass=0;
			while(chunkNum>1){
				int countThread=0;
	
				for(int j = 0; j< chunkNum; j=j+2)
				{
					threadArray[countThread] = new TwoWayMergeSort(pass+"-"+countThread,outputFilePath+"/pass"+pass+"run" +j,
							outputFilePath+"/pass"+pass+"run" +(j+1),outputFilePath+"/pass"+(pass+1)+"run"+countThread);
					threadArray[countThread].start();
					System.out.println("Thread " + countThread + " is working on pass"+(pass+1)+"run"+countThread );	

					countThread++;
				}					
				//Join threads
				for(int j = 0; j< countThread; j++)
				{
					threadArray[j].join();
					System.out.println("Thread " + j + " has joined");

				}
				
				for(int i=0;i<2*countThread;i++){
					File tempfile=new File(outputFilePath+"/pass"+pass+"run" +i);
					tempfile.delete();
				}
				chunkNum=countThread;
				pass++;
			}

	}
	
	//split file into chunkNum chunks
	public static void splitFile(File file) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(file));
		ArrayList<String>list = new ArrayList<String>();
		String[] list1 = new String[splitSize];

		String str = reader.readLine(); //read line from buffer
		int countChunk=0;
		int pos=0;
		while(str != null) 
		{	
			if(pos>=splitSize){
				Arrays.sort(list1);
				BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath +"/pass0run" +countChunk));
				for(String sr:list1){
					writer.write(sr);
					writer.write("\n");

				}
				
				writer.close(); 
				pos=0;
				countChunk++;
			}
			list1[pos]=str;
			str=reader.readLine();
			pos++;
		}
		
		if(pos<splitSize){
			Arrays.sort(list1);
			BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath +"/pass0run" +countChunk));
			for(String sr:list1){
				writer.write(sr);
				writer.write("\n");
			}
			writer.close(); 
			pos=0;
			countChunk++;
		}
		
		reader.close();

	}
}


