package iotest;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

 
public class FileTest {
 
   static Long i=0L;
   static String path2="F:\\img3";//全局复制路劲 修改
   static String wenjianjia="20170816001";
   //程序入口
   public static void main(String[] args) throws IOException {
		  String path="F:\\img";//源路径
		  getFileList(path);
   }
   public static void getFileList(String path) throws IOException {
	  
	  String path3 = path2 +"\\"+ wenjianjia;//新建文件夹路径
	  File file2 = new File(path3);
	  if(file2==null || !file2.isDirectory()){//判断文件夹是否存在
		  file2.mkdir();//创建
	  }
      File srcFile=new File(path);//需要复制的文件的源路径
      
      String srcPath=srcFile.getAbsolutePath();//获得源路径
      File[] str=srcFile.listFiles();//获取所有文件
      
      for (File file : str) {
    	  if (file.isDirectory()) { // 判断是文件还是文件夹
              getFileList(file.getAbsolutePath()); // 获取文件绝对路径
          } else if (file.getName().endsWith("jpg")||file.getName().endsWith("tif")||file.getName().endsWith("tiff")||file.getName().endsWith("jpeg")||file.getName().endsWith("png")||file.getName().endsWith("gif")) { // 判断文件名是否以.avi结尾
                File aimFile=new File(path2 +"\\"+ wenjianjia);//复制后的文件的目标路径
                String aimPath=aimFile.getAbsolutePath();//获得目标路径
                
                File oldFile=new File(srcPath+"\\"+file.getName()); //需要复制的文件
                File newFile=new File(aimPath+"\\"+file.getName().replace(".", "_"+(i++)+"."));//复制后的文件
                if (i==10) {//判断文件中文件个数
                	Long wenjianjia2 = (Long.parseLong(wenjianjia)+ 1);//强转文件名
                	wenjianjia=String.valueOf(wenjianjia2);
                	//判断创建文件的位数
                	String path4 = path2 +"\\"+ wenjianjia;//新建文件夹路径
	              	  File file3 = new File(path4);
	              	  if(file3==null || !file3.isDirectory()){
	              		  file3.mkdir();
	              	  }
                	i=0L;//初始化文件个数
				}
                //创建流对象
                DataInputStream dis=new DataInputStream(new FileInputStream(oldFile));
                DataOutputStream dos=new DataOutputStream(new FileOutputStream(newFile));
               
                int temp;
                //读写数据
                while((temp=dis.read())!=-1){//读数据
                   dos.write(temp);//把读到的数据写入到Temp文件中
                }
               
                //关闭流
                dis.close();
                dos.close();
              
              System.out.println("文件已提取成功！");
              
          } else {
              continue;
          }
	}
      
   }
 
}