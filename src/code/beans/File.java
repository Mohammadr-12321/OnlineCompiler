package code.beans;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Iterator;

public class File {
  private int file_id;
  private String file_name;
  private Blob file_data;
  private String file_type;

  public String getFile_type() {
    return file_type;
  }

  public void setFile_type(String file_type) {
    this.file_type = file_type;
  }

  public File(String file_name, Blob file_data) {
    this.file_name = file_name;
    this.file_data = file_data;
  }

  public File(String file_name,Blob file_data,String file_type){
      this.file_name = file_name;
      this.file_data = file_data;
      this.file_type=file_type;
  }

  public int getFile_id() {
    return file_id;
  }

  public void setFile_id(int file_id) {
    this.file_id = file_id;
  }

  public String getFile_name() {
    return file_name;
  }

  public void setFile_name(String file_name) {
    this.file_name = file_name;
  }

  public Blob getFile_data() {
    return file_data;
  }

  public void setFile_data(Blob file_data) {
    this.file_data = file_data;
  }

  public static byte[] convertInputstreamToByteArray(InputStream file_data) throws IOException {
    byte[] bytes=new byte[8192];
    int bytesRead;
    ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
    while ((bytesRead = file_data.read(bytes)) != -1)
    {
      byteArrayOutputStream.write(bytes,0,bytesRead);
    }
    return byteArrayOutputStream.toByteArray();
  }

  public static InputStream resizeImage(InputStream file_data,int desiredWidth,int desiredHeight){

      // If all you want to do is read the same information more than once,
      // and the input data is small enough to fit into memory,
      // you can copy the data from your InputStream to a ByteArrayOutputStream.
      //Then you can obtain the associated array of bytes and
      // open as many "cloned" ByteArrayInputStreams as you like.

      InputStream original_image=null;
      InputStream copy_image=null;

      try {
          byte [] data=convertInputstreamToByteArray(file_data);
          original_image=new ByteArrayInputStream(data);
          copy_image=new ByteArrayInputStream(data);

      } catch (IOException e) {
          e.printStackTrace();
      }

      String originalImageFormat=getImageFormat(copy_image);
      System.out.println("original image format : "+originalImageFormat);

      int type=0;
      InputStream inputStream=null;

      try {
          BufferedImage originalImage=ImageIO.read(original_image);
          type=originalImage.getType()==0 ? BufferedImage.TYPE_INT_ARGB :originalImage.getType();
          BufferedImage resizedImage=new BufferedImage(desiredWidth,desiredHeight,type);
          Graphics2D g=resizedImage.createGraphics();
          g.drawImage(originalImage,0,0,desiredWidth,desiredHeight,null);
          g.dispose();

          // Convert BufferedImage object to InputStream Object
          ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
          ImageIO.write(resizedImage,originalImageFormat,byteArrayOutputStream);
          inputStream=new ByteArrayInputStream(byteArrayOutputStream.toByteArray());

      } catch (IOException e) {
          e.printStackTrace();
      }
      return inputStream;
  }

  public  static String getImageFormat(InputStream inputStream){

      String imageFormat=null;
      try {
          // Create ImageInputStream Using InputStream Object
          ImageInputStream imageInputStream=ImageIO.createImageInputStream(inputStream);

          // Get the image readers for that input stream object
          Iterator<ImageReader> imageReadersList=ImageIO.getImageReaders(imageInputStream);

          if(!imageReadersList.hasNext()){
              throw new RuntimeException("ImageReaders Not found ! ");
          }

          // Get the image type
          ImageReader reader=imageReadersList.next();
          imageFormat=reader.getFormatName();

          imageInputStream.close();
      } catch (IOException e) {
          e.printStackTrace();
      }

      return imageFormat;
  }

  public static InputStream convertStringToInputStream(String string){
      InputStream stream=new ByteArrayInputStream(string.getBytes(StandardCharsets.UTF_8));
      return stream;
  }

  public String convertInputStreamToString() throws IOException {
      InputStream stream= null;
      try {
          stream = getFile_data().getBinaryStream();
      } catch (SQLException e) {
          e.printStackTrace();
      }

      ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
      byte[] buffer=new byte[4096];
      for(int i=0;(i=stream.read(buffer))>0;){
          byteArrayOutputStream.write(buffer,0,i);
      }
      byteArrayOutputStream.close();
      String string =new String(byteArrayOutputStream.toByteArray(),StandardCharsets.UTF_8);
      return string;
  }


}
