import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.opencv_core.CvIsInstanceFunc;
import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacv.cvkernels;
import org.junit.Test;

import static org.bytedeco.javacpp.lept.*;
import static org.bytedeco.javacpp.tesseract.*;
import static org.junit.Assert.assertTrue;
import static org.bytedeco.javacpp.opencv_imgcodecs.*;
public class untouchedTess {
    
	
    @Test
    public void givenTessBaseApi_whenImageOcrd_thenTextDisplayed() throws Exception {
        BytePointer outText;

        TessBaseAPI api = new TessBaseAPI();
        // Initialize tesseract-ocr with English, without specifying tessdata path
        if (api.Init(".", "ENG") != 0) {
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