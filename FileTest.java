package iotest;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

 
public class FileTest {
 
   static Long i=0L;
   static String path2="F:\\img3";//ȫ�ָ���·�� �޸�
   static String wenjianjia="20170816001";
   //�������
   public static void main(String[] args) throws IOException {
		  String path="F:\\img";//Դ·��
		  getFileList(path);
   }
   public static void getFileList(String path) throws IOException {
	  
	  String path3 = path2 +"\\"+ wenjianjia;//�½��ļ���·��
	  File file2 = new File(path3);
	  if(file2==null || !file2.isDirectory()){//�ж��ļ����Ƿ����
		  file2.mkdir();//����
	  }
      File srcFile=new File(path);//��Ҫ���Ƶ��ļ���Դ·��
      
      String srcPath=srcFile.getAbsolutePath();//���Դ·��
      File[] str=srcFile.listFiles();//��ȡ�����ļ�
      
      for (File file : str) {
    	  if (file.isDirectory()) { // �ж����ļ������ļ���
              getFileList(file.getAbsolutePath()); // ��ȡ�ļ�����·��
          } else if (file.getName().endsWith("jpg")||file.getName().endsWith("tif")||file.getName().endsWith("tiff")||file.getName().endsWith("jpeg")||file.getName().endsWith("png")||file.getName().endsWith("gif")) { // �ж��ļ����Ƿ���.avi��β
                File aimFile=new File(path2 +"\\"+ wenjianjia);//���ƺ���ļ���Ŀ��·��
                String aimPath=aimFile.getAbsolutePath();//���Ŀ��·��
                
                File oldFile=new File(srcPath+"\\"+file.getName()); //��Ҫ���Ƶ��ļ�
                File newFile=new File(aimPath+"\\"+file.getName().replace(".", "_"+(i++)+"."));//���ƺ���ļ�
                if (i==10) {//�ж��ļ����ļ�����
                	Long wenjianjia2 = (Long.parseLong(wenjianjia)+ 1);//ǿת�ļ���
                	wenjianjia=String.valueOf(wenjianjia2);
                	//�жϴ����ļ���λ��
                	String path4 = path2 +"\\"+ wenjianjia;//�½��ļ���·��
	              	  File file3 = new File(path4);
	              	  if(file3==null || !file3.isDirectory()){
	              		  file3.mkdir();
	              	  }
                	i=0L;//��ʼ���ļ�����
				}
                //����������
                DataInputStream dis=new DataInputStream(new FileInputStream(oldFile));
                DataOutputStream dos=new DataOutputStream(new FileOutputStream(newFile));
               
                int temp;
                //��д����
                while((temp=dis.read())!=-1){//������
                   dos.write(temp);//�Ѷ���������д�뵽Temp�ļ���
                }
               
                //�ر���
                dis.close();
                dos.close();
              
              System.out.println("�ļ�����ȡ�ɹ���");
              
          } else {
              continue;
          }
	}
      
   }
 
}