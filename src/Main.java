
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class Main
{

    public static void main(String[] args)
    {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);


        CascadeClassifier faceDetector = new CascadeClassifier();

        faceDetector.load("C:\\opencv\\sources\\data\\lbpcascades\\lbpcascade_frontalface.xml");
        System.out.println ( "Iniciando proceso..." );
        // Se lee la imagen
        Mat image = Imgcodecs.imread("C:\\Users\\amaya\\eclipse-workspace3\\DeteccionRostros\\images\\imagenGente.jpg");

        // Detección de rostros
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(image, faceDetections);

        // Crea un rectángulo en la cara detectadas
        Rect rectCrop=null;
        for (Rect rect : faceDetections.toArray())
        {
            Imgproc.rectangle(image, new Point(rect.x, rect.y),
             new Point(rect.x + rect.width, rect.y + rect.height),
                                           new Scalar(0, 255, 0));
            rectCrop = new Rect(rect.x, rect.y, rect.width, rect.height);
        }

        // Se crear una imagen con las caras detectadas
        String filename = "Ouput.jpg";
        Imgcodecs.imwrite("C:\\Users\\amaya\\eclipse-workspace3\\DeteccionRostros\\images\\"+filename, image);
        // Se recorta la imagen solo con el rostro detectado
        Mat markedImage = new Mat(image,rectCrop);
        Imgcodecs.imwrite("C:\\Users\\amaya\\eclipse-workspace3\\DeteccionRostros\\images\\cropimage.jpg",markedImage );
    }
}
