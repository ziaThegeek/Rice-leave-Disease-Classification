package com.example.diseaseclassification;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.example.diseaseclassification.ml.FirstModel;
import com.example.diseaseclassification.ml.Model3;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.Tensor;
import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;

public class MainActivity extends AppCompatActivity {
    private static final int CAMERA_REQUEST = 1888,REQUEST_CODE=10,REQUST_PERMISSION_CODE=1;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    ImageView input_image;
    Button capture,upload_image;
    TextView results,desccription;
    String[] output_classes={"brown spot","healthy","hispa","leaf blast"};
    String[] description={
            "Brown spot is caused by the fungusSeptoria glycines and may also be called Septoria leaf spot. It was first reported in the U.S. in 1923 infecting soybeans in North Carolina and is now widely distributed through the north central states, the mid Atlantic states, and the southeastern U.S. Brown spot rarely affects soybean yield in Nebraska. In some years it may hasten maturity by causing premature defoliation.\n" +
                    "\n" +
                    "Yield loss estimates due to brown spot range from 8% to 15% nationally and occur when 25% to 50% of the canopy prematurely defoliates. Severe brown spot infection usually results in smaller seed size. The disease is most severe when soybean is grown continuously in the same field, particularly in fields where tillage is reduced, since this is a residue-borne disease. Disease Symptoms\n" +
                    "Infection occurs as early as the V2 growth stage on lower leaves. Under favorable weather conditions (warm, wet weather), the disease may continue to spread to the upper canopy. Late in the growing season, infected leaves may turn rusty brown or yellow and drop prematurely.\n" +
                    "\n" +
                    "Symptoms start as dark brown, irregular spots on both upper and lower leaf surfaces. Lesions typically will have a yellow or chlorotic halo when held up to a back light. Adjacent lesions frequently merge to form irregularly shaped blotches. Leaves become yellow to rusty brown. Symptoms of Brown Spot can also develop on stems and pods of plants approaching maturity. Stem and pod lesions have indefinite margins, are dark in appearance and range in size from flecks to larger areas."
            ,
            "Healthy"
            ,
            "Symptom of Damage :\n" +
            "\n" +
            "The mining of the grubs will be clearly seen on the leaves.\n" +
            "Scraping of the upper surface of the leaf blade leaving only the lower epidermis as white streaks parallel to the midrib.\n" +
            "Tunneling of larvae through leaf tissue causes irregular translucent white patches that are parallel to the leaf veins.\n" +
            "Damaged leaves wither off.\n" +
            "Rice field appears burnt when severely infested.\n" +
            "\n" +
            "Nature of Damage :\n" +
            "The grub mines into the leaf blade and feed on the green tissue between the veins.\n" +
            "Adults also feed in the green tissue; they scrape green matter of the tender leaves.\n" +
            "Generally the plants are affected in the young stage Identification of pest :\t\n" +
            "\n" +
            "\n" +
            "              Scientific Name - Dicladispa armigera\n" +
            "\n" +
            "Egg :\n" +
            "Eggs are laid inside minute slits on the tender leaves generally toward the tip.\n" +
            "\n" +
            "Grub :\n" +
            "The grub is whitish yellow and flattened. It feed inside the leaf tissue by mining. It pupates inside.\n" +
            "\n" +
            "Adult :\n" +
            "The adult beetle is somewhat square shaped about 1/6 to 1/8” in length and width Dark blue or blackish in colour with spines all over the body. Management Strategies:\t\n" +
            "\n" +
            "\n" +
            "Avoid over fertilizing the field.\n" +
            "Close plant spacing results in greater leaf densities that can tolerate higher hispa numbers.\n" +
            "Leaf tip containing blotch mines should be destroyed.\n" +
            "Manual collection and killing of beetles – hand nets.\n" +
            "To prevent egg laying of the pests, the shoot tips can be cut.\n" +
            "Clipping and burying shoots in the mud can reduce grub populations by 75 - 92%.\n" +
            "Reduviid bug eats upon the adults.\n" +
            "Spraying of methyl parathion 0.05% or Quinalphos 0.05%"

    ,
"Symptoms and Signs\n" +
        "The symptoms of rice blast include lesions that can be found on all parts of the plant, including leaves, leaf collars, necks, panicles, pedicels, and seeds. A recent report shows that even roots can become infected. However, the most common and diagnostic symptom, diamond shaped lesions, of rice blast occur on the leaves, whereas lesions on the sheaths are relatively rare.\n" +
        "\n" +
        "Rice leaves. The symptoms on leaves may vary according to the environmental conditions, the age of the plant, and the levels of resistance of the host cultivars (Figure 4). On susceptible cultivars, lesions may initially appear gray-green and water-soaked with a darker green border and they expand rapidly to several centimeters in length. On susceptible cultivars, older lesions often become light tan in color with necrotic borders. On resistant cultivars, lesions often remain small in size (1-2 mm) and brown to dark brown in color.\n" +
        "\n" +
        "Rice collars. The collar of a rice plant refers to the junction of the leaf and the stem sheath. Symptoms of infection of the collars consist of a general area of necrosis at the union of the two tissues (Figure 5). Collar infections can kill the entire leaf and may extend a few millimeters into and around the sheath. The fungus may produce spores on these lesions.\n" +
        "\n" +
        "Rice necks and panicles. The neck of the rice plant refers to that portion of the stem that rises above the leaves and supports the seed head or panicle. Necks are often infected at the node by the rice blast fungus and infection leads to a condition called rotten neck or neck blast (Figure 6). Infection of the necks can be very destructive, causing failure of the seeds to fill (a condition called blanking) or causing the entire panicle to fall over as if rotted. The rice blast fungus can also infect the panicles as the seeds form (Figure 7). Lesions can be found on the panicle branches, spikes, and spikelets. The lesions are often gray brown discolorations of the branches of the panicle, and, over time, the branches may break at the lesion.\n" +
        "\n" +
        "Rice seeds. The fungus has often been isolated from the pedicels of the seeds. Seeds are not produced when pedicels become infected, a condition called blanking. Symptoms of rice blast on seeds themselves consist of brown spots, blotches (Figure 8), and occasionally the classic diamond-shaped lesion often seen on leaves. The process and the time during which infection of seeds by spores of the pathogen occurs has not been fully described but recent information shows that the fungus can infect seeds by infecting the florets as they mature into seeds, and it is believed that this is the main way seed infection develops."
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input_image = findViewById(R.id.input_image);
        capture = findViewById(R.id.upload_imgae);
        upload_image = findViewById(R.id.view_results);
        results = findViewById(R.id.results);
        desccription=findViewById(R.id.description);


        capture.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                } else {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }
            }
        });
        upload_image.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {

                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUST_PERMISSION_CODE);
                } else {
                    Intent cameraIntent = new Intent(Intent.ACTION_GET_CONTENT);
                    cameraIntent.setType("image/*");
                    startActivityForResult(cameraIntent, REQUEST_CODE);
                }
            }
        });
    }

//    public static void makeGray(Bitmap img)
//    {
//        for (int x = 0; x < img.getWidth(); ++x)
//            for (int y = 0; y < img.getHeight(); ++y)
//            {
//                int color = img.getPixel(x, y);
//                int r = Color.red(color);
//                int g = Color.green(color);
//                int b = Color.blue(color);
//
////                int r = (rgb >> 16) & 0xFF;
////                int g = (rgb >> 8) & 0xFF;
////                int b = (rgb & 0xFF);
//
//                // Normalize and gamma correct:
//                float rr = (float) Math.pow(r / 255.0, 2.2);
//                float gg = (float) Math.pow(g / 255.0, 2.2);
//                float bb = (float) Math.pow(b / 255.0, 2.2);
//
//                // Calculate luminance:
//                float lum = (float) (0.2126 * rr + 0.7152 * gg + 0.0722 * bb);
//
//                // Gamma compand and rescale to byte range:
//                int grayLevel = (int) (255.0 * Math.pow(lum, 1.0 / 2.2));
//                int gray = (grayLevel << 16) + (grayLevel << 8) + grayLevel;
//                img.setPixel(x, y, gray);
//            }
//    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK||requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            input_image.setImageBitmap(photo);
            classify(photo);
        }
        if(requestCode==REQUEST_CODE&&resultCode==RESULT_OK)
        {
            Glide.with(this).load(data.getData()).into(input_image);
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
            } catch (IOException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(this, bitmap.toString(), Toast.LENGTH_SHORT).show();
            classify(bitmap);
        }


    }
    @Override
    public void onRequestPermissionsResult (int requestCode, @NonNull String[] permissions,
                                            @NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUST_PERMISSION_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                open_file_activity();
            } else {
                Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }
    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE))
        {
            open_file_activity();
        }
        else {
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},REQUST_PERMISSION_CODE);
        }
    }

    public void open_file_activity()
    {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)
        {
            Intent browse_file=new Intent(Intent.ACTION_OPEN_DOCUMENT);
            browse_file.setType("image/*");
//    browse_file.setType("*/*");
            startActivityForResult(browse_file,REQUEST_CODE);
        }
        else
            requestPermission();

    }
    public void classify(Bitmap bitmap)
    {
        
        Bitmap img = Bitmap.createScaledBitmap(bitmap, 256, 256, true);
        try 
        {

            Model3 model = Model3.newInstance(getApplicationContext());
            TensorImage tensorImage = new TensorImage(DataType.FLOAT32);
            tensorImage.load(img);
            ByteBuffer byteBuffer = tensorImage.getBuffer();

            // Creates inputs for reference.
            TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 256, 256, 3}, DataType.FLOAT32);
            inputFeature0.loadBuffer(byteBuffer);

            // Runs model inference and gets result.
            Model3.Outputs outputs = model.process(inputFeature0);
            TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();
//            results.setText(outputFeature0.getFloatArray().toString());
            float output=outputFeature0.getFloatArray()[0];
            String temp="";
            int index=0;
            for (int i=0;i<outputFeature0.getFloatArray().length;i++)
            {
                temp+=outputFeature0.getFloatArray()[i]+",";
                if (outputFeature0.getFloatArray()[i]>output)
                {
                    output=outputFeature0.getFloatArray()[i];
                    index=i;
                }
            }
//            results.setText(output_classes[index]);
//            desccription.setText(description[index]);
            Intent intent=new Intent(MainActivity.this, com.example.diseaseclassification.results.class);
            intent.putExtra("index",index);
startActivity(intent);
            // Releases model resources if no longer used.
            model.close();
        } catch (IOException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            // TODO Handle the exception
        }
//        try {
//            FirstModel model = FirstModel.newInstance(getApplicationContext());
//            TensorImage tensorImage = new TensorImage(DataType.FLOAT32);
//            tensorImage.load(img);
//            ByteBuffer byteBuffer = tensorImage.getBuffer();
//
//            // Creates inputs for reference.
//            TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 200, 200, 3}, DataType.FLOAT32);
//            inputFeature0.loadBuffer(byteBuffer);
//            // Runs model inference and gets result.
//            FirstModel.Outputs outputs = model.process(inputFeature0);
//            TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();
//
//            float output=outputFeature0.getFloatArray()[0];
//            String temp="";
//            int index=0;
//            for (int i=0;i<outputFeature0.getFloatArray().length;i++)
//            {
//                temp+=outputFeature0.getFloatArray()[i]+",";
//                if (outputFeature0.getFloatArray()[i]>output)
//                {
//                    output=outputFeature0.getFloatArray()[i];
//                    index=i;
//                }
//            }

//            results.setText(temp);
//
//            // Releases model resources if no longer used.
//            model.close();
//        } catch (IOException e) {
//            // TODO Handle the exception
//        }

    }

}