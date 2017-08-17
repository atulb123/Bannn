package tests;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

import org.apache.commons.io.FileUtils;

@SuppressWarnings("unused")
public class Constant {

    public static final String Path_TestData = "\\Banner_Auto_Old\\Banner_Automation\\Output\\";

    public static final String File_TestData = "Output.xls";
    
    //public static ScreenRecorder screenRecorder;
    
    public static void Copy() {
        Path src = Paths.get("\\Banner_Auto_Old\\Banner_Automation\\Input.xls");
          Path dest =  Paths.get("\\Banner_Auto_Old\\Banner_Automation\\Output\\Output.xls");
          try {
              Files.copy(src,dest);
          } catch (Exception e) {
              e.printStackTrace();
          }
 }
    
    public static void CopyAllFiles() throws Exception {
    	
    	 String source = "\\Banner_Auto_Old\\Banner_Automation\\Output";
	     File srcDir = new File(source);
	     String source1 = "\\Banner_Auto_Old\\Banner_Automation\\Test_Screenshot";
	     File srcDir1 = new File(source1);
	     String source2 ="\\Banner_Auto_Old\\Banner_Automation\\Input.xls";
	     File srcDir2 = new File(source2);
	     String link=ExcelUtils.getCellData(4, 2);
	    String part[]=link.split("/");
        String subpart=part[7];
        String subpart1[]=subpart.split("_");
         String subpart2=subpart1[0];
	        String destination = "D:/Archive/"+subpart2;
	        String pathName=destination+"/";
	        System.out.println(pathName);
	        File destDir = new File(destination);
	        try{
	       FileUtils.cleanDirectory(destDir);
	        }
	        catch(Exception e)
	        {
	        	System.out.println("Archive folder is not present");
	        }
	        try {
	            //
	            // Move the source directory to the destination directory.
	            // The destination directory must not exists prior to the
	            // move process.
	            //
	        	FileUtils.copyDirectory(srcDir, destDir);
	        
	        	
	        	
	            FileUtils.copyDirectory(srcDir1, destDir);
	            FileUtils.copyFileToDirectory(srcDir2, destDir);
	            System.out.println("Files copied");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

 }
    
    
    
    /*public static void startRecording() throws Exception
    {
                         
         GraphicsConfiguration gc = GraphicsEnvironment
            .getLocalGraphicsEnvironment()
            .getDefaultScreenDevice()
            .getDefaultConfiguration();

        Constant.screenRecorder = new ScreenRecorder(gc,
            new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
            new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                 CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                 DepthKey, 24, FrameRateKey, Rational.valueOf(15),
                 QualityKey, 1.0f,
                 KeyFrameIntervalKey, 15 * 60),
            new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black",
                 FrameRateKey, Rational.valueOf(30)),
            null);
       Constant.screenRecorder.start();
     
    }

    public static void stopRecording() throws Exception
    {
      Constant.screenRecorder.stop();
    } */
    }