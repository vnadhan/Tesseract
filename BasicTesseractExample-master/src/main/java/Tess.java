import static org.bytedeco.javacpp.lept.pixDestroy;
import static org.bytedeco.javacpp.lept.pixRead;
import static org.junit.Assert.assertTrue;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.lept.PIX;
import org.bytedeco.javacpp.tesseract.TessBaseAPI;


public class Tess {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 BytePointer outText;

	        TessBaseAPI api = new TessBaseAPI();
	        // Initialize tesseract-ocr with English, without specifying tessdata path
	        
	        if (api.Init("C:/Users/Vignesh/Desktop/hackathon", "ENG") != 0) {
	            System.err.println("Could not initialize tesseract.");
	            System.exit(1);
	        }

	        // Open input image with leptonica library
	        PIX image = pixRead("vishnu.png");
	        api.SetImage(image);
	        // need to process the image
	        api.SetSourceResolution(442);
	        
	        // Get OCR result
	        outText = api.GetUTF8Text();
	        
	        String string = outText.getString();
	        assertTrue(!string.isEmpty());
	        System.out.println("GetUTF8Text output:\n" + string);
	        System.out.println("------------------------------------------------------------------------");
	        //System.out.println("GetUNLVText output:\n" + api.GetUNLVText());
	        // Destroy used object and release memory
	        api.End();
	        outText.deallocate();
	        pixDestroy(image);
	}

}
